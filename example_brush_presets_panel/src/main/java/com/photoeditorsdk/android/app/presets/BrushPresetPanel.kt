package com.photoeditorsdk.android.app.presets

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import androidx.annotation.MainThread
import android.view.View
import com.photoeditorsdk.android.app.R
import ly.img.android.pesdk.annotations.OnEvent
import ly.img.android.pesdk.backend.model.state.BrushSettings
import ly.img.android.pesdk.backend.model.state.EditorShowState
import ly.img.android.pesdk.backend.model.state.HistoryState
import ly.img.android.pesdk.backend.model.state.LayerListSettings
import ly.img.android.pesdk.backend.model.state.manager.StateHandler
import ly.img.android.pesdk.ui.adapter.DataSourceListAdapter
import ly.img.android.pesdk.ui.model.data.PanelData
import ly.img.android.pesdk.ui.model.data.TitleData
import ly.img.android.pesdk.ui.model.state.UiConfigBrush
import ly.img.android.pesdk.ui.model.state.UiState
import ly.img.android.pesdk.ui.model.state.UiStateMenu
import ly.img.android.pesdk.ui.panels.AbstractToolPanel
import ly.img.android.pesdk.ui.panels.BrushToolPanel
import ly.img.android.pesdk.ui.panels.StickerOptionToolPanel
import ly.img.android.pesdk.ui.panels.item.OptionItem
import ly.img.android.pesdk.ui.panels.item.ToggleOption
import ly.img.android.pesdk.ui.widgets.BrushToolPreviewView
import ly.img.android.pesdk.ui.widgets.HorizontalListView
import ly.img.android.pesdk.ui.widgets.SeekSlider
import ly.img.android.pesdk.utils.SetHardwareAnimatedViews
import ly.img.android.pesdk.utils.TimeOut
import kotlin.math.min

open class BrushPresetPanel(stateHandler: StateHandler) : AbstractToolPanel(stateHandler), SeekSlider.OnSeekBarChangeListener {

    companion object {
        const val OPTION_REDO = 0
        const val OPTION_UNDO = 1
        const val OPTION_DELETE = 2
        const val OPTION_BRING_TO_FRONT = 3

        const val TOOL_ID = BrushToolPanel.TOOL_ID
        private const val CUSTOM_BRUSH_TOOL_ID = "brush_preset_tool_panel"
        init {
            // We need to switch and register the panel and the title

            // 1. This tool becomes the default brush tool
            UiState.getPanelData(TOOL_ID)?.panelClass = BrushPresetPanel::class.java

            // 2. The old tool is register with an new ID
            UiState.addPanel(PanelData(CUSTOM_BRUSH_TOOL_ID, BrushToolPanel::class.java))
            UiState.addTitle(TitleData(CUSTOM_BRUSH_TOOL_ID, R.string.pesdk_brush_title_name))
        }
    }

    internal enum class TIMER {
        BRUSH_PREVIEW_POPUP
    }

    private var listAdapter: DataSourceListAdapter = DataSourceListAdapter()
    private var quickListAdapter: DataSourceListAdapter = DataSourceListAdapter()

    private var seekSlider: SeekSlider? = null
    private var brushPopup: View? = null
    private var optionsListView: HorizontalListView? = null
    private var quickOptionListView: HorizontalListView? = null
    private var brushSettingsPreviewView: BrushToolPreviewView? = null

    private val editorShowState = stateHandler[EditorShowState::class]
    private val presetSettings = stateHandler[BrushPresetSettings::class]
    private val brushSettings = stateHandler[BrushSettings::class]
    private val mainMenuState = stateHandler[UiStateMenu::class]
    private val uiBrushConfig = stateHandler[UiConfigBrush::class]
    private val layerSettings = stateHandler[LayerListSettings::class]

    private val brushPopupTimeOut = TimeOut(null)

    override fun getLayoutResource() = R.layout.imgly_panel_tool_brush

    override fun getHistorySettings(): Array<Class<Any>>? = arrayOf(BrushSettings::class.java as Class<Any>)

    override fun onAttached(context: Context?, panelView: View) {
        super.onAttached(context, panelView)

        brushSettings.setInEditMode(true)

        seekSlider = panelView.findViewById(R.id.seekBar)
        brushPopup = panelView.findViewById(R.id.brushPreviewPopup)
        optionsListView = panelView.findViewById(R.id.optionList)
        quickOptionListView = panelView.findViewById(R.id.quickOptionList)
        brushSettingsPreviewView = panelView.findViewById(R.id.brushToolPreview)

        listAdapter = DataSourceListAdapter()
        listAdapter.setData(presetSettings.presetList)
        listAdapter.setOnItemClickListener {
            if (it is BrushPresetCustomItem) {
                mainMenuState.openSubTool(CUSTOM_BRUSH_TOOL_ID)
            } else if (it is BrushPresetItem) {
                brushSettings.brushSize = it.size
                brushSettings.brushColor = it.color
                brushSettings.brushHardness = it.hardness
                updateBrushPreview()
            }
        }

        brushPopup?.visibility = View.GONE

        val quickOptionListView = quickOptionListView
        if (quickOptionListView != null) {
            quickListAdapter = DataSourceListAdapter()
            quickListAdapter.setData(presetSettings.quickOptionList)
            quickListAdapter.setOnItemClickListener { entity ->
                if (entity is OptionItem) {
                    when (entity.id) {
                        OPTION_DELETE -> brushSettings.painting.clear()
                        OPTION_REDO -> redoLocalState()
                        OPTION_UNDO -> undoLocalState()
                        OPTION_BRING_TO_FRONT -> {
                            stateHandler[LayerListSettings::class].also {
                                it.bringLayerToFront(it.selected)
                            }
                        }
                    }
                }
            }
            quickOptionListView.setAdapter(quickListAdapter)

        }
        val optionsListView = optionsListView
        optionsListView?.setAdapter(listAdapter)

        val seekSlider = seekSlider
        if (seekSlider != null) {
            val imageRect = stateHandler[EditorShowState::class].imageRect
            seekSlider.setSteps(120)
            seekSlider.min = uiBrushConfig.minimumSize.coerceAtLeast(1.0f / min(imageRect.width(), imageRect.height()))
            seekSlider.max = 0.125f
            seekSlider.value = brushSettings.brushSize

            seekSlider.setOnSeekBarChangeListener(this)
        }

        brushPopupTimeOut.addCallback {
            val brushPopup = brushPopup
            if (brushPopup != null && brushPopup.visibility == View.VISIBLE) {
                AnimatorSet().also { set ->
                    set.playTogether(
                      ObjectAnimator.ofFloat(brushPopup as View, "alpha", brushPopup.alpha, 0f)
                    )
                    //set.addListener(SetHardwareAnimatedViews(brushPopup))
                    set.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            brushPopup.visibility = View.GONE
                        }
                    })
                    set.duration = ANIMATION_DURATION.toLong()
                    set.start()
                }
            }
        }
    }

    protected open fun updateBrushPreview() {
        val previewView = brushSettingsPreviewView ?: return
        val brushPopup = brushPopup ?: return
        val imageRect = editorShowState.imageRect
        val smallerImageSide = min(imageRect.width(), imageRect.height()) * editorShowState.scale
        val contextRelationScale = previewView.relativeContext.toRelativeSize(smallerImageSide.toDouble())
        val previewBrushSize = (brushSettings.brushSize * contextRelationScale).toFloat()

        previewView.setColor(brushSettings.brushColor)
        previewView.setSize(previewBrushSize.toDouble())
        previewView.setHardness(brushSettings.brushHardness)
        previewView.update()
        if (brushPopup.visibility == View.GONE) {
            brushPopup.visibility = View.VISIBLE
            brushPopup.alpha = 0f
            AnimatorSet().also { set ->
                set.playTogether(
                  ObjectAnimator.ofFloat(brushPopup, "alpha", 0f, 1f)
                )
                //set.addListener(SetHardwareAnimatedViews(previewView))
                set.duration = ANIMATION_DURATION.toLong()
                set.start()
            }
        }
        brushPopupTimeOut.setTimeOut(1000)
    }

    @OnEvent(HistoryState.Event.UNDO, HistoryState.Event.REDO, HistoryState.Event.HISTORY_CREATED)
    @MainThread
    protected open fun onHistoryChanged(historyState: HistoryState) {
        if (quickOptionListView != null) {
            for (option in presetSettings.quickOptionList) {
                if (option is ToggleOption) {
                    if (option.id == StickerOptionToolPanel.OPTION_REDO || option.id == StickerOptionToolPanel.OPTION_UNDO) {
                        option.setEnabled(
                          option.id == StickerOptionToolPanel.OPTION_REDO && historyState.hasRedoState(1) || option.id == StickerOptionToolPanel.OPTION_UNDO && historyState.hasUndoState(1)
                        )
                    }
                    quickListAdapter.invalidateItem(option)
                }
            }
        }
    }

    @MainThread @OnEvent(value = [UiStateMenu.Event.TOOL_STACK_CHANGED], triggerDelay = 30)
    open fun changeQuickOptionVisibility(menuState: UiStateMenu) {
        val visibility = if (menuState.currentTool === this) View.VISIBLE else View.INVISIBLE
        quickOptionListView?.visibility = visibility
        seekSlider?.visibility = visibility
    }

    @MainThread @OnEvent(LayerListSettings.Event.LAYER_LIST, LayerListSettings.Event.SELECTED_LAYER)
    protected open fun onLayerOrderChange() {
        if (quickOptionListView != null) {
            for (option in presetSettings.quickOptionList) {
                if (option is ToggleOption) {
                    if (option.id == StickerOptionToolPanel.OPTION_TO_FRONT) {
                        option.setEnabled(!layerSettings.isLayerAtTop(brushSettings))
                    }
                    quickListAdapter.invalidateItem(option)
                }
            }
        }
    }

    override fun onDetached() {
        brushSettings.setInEditMode(false)
    }

    override fun createShowAnimator(panelView: View): Animator {
        val optionsListView = optionsListView ?: throw RuntimeException("remove optionsListView from the show and exit animation")
        val quickOptionListView = quickOptionListView ?: throw RuntimeException("remove quickOptionListView from the animation")
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
          ObjectAnimator.ofFloat(optionsListView, "alpha", 0f, 1f),
          ObjectAnimator.ofFloat(optionsListView, "translationY", optionsListView.height.toFloat(), 0f),
          ObjectAnimator.ofFloat(quickOptionListView, "alpha", 0f, 1f),
          ObjectAnimator.ofFloat(quickOptionListView, "translationY", quickOptionListView.height.toFloat(), 0f)
        )

        animatorSet.addListener(SetHardwareAnimatedViews(optionsListView, quickOptionListView))
        animatorSet.duration = ANIMATION_DURATION.toLong()
        return animatorSet
    }

    override fun createExitAnimator(p0: View): Animator {
        val optionsListView = optionsListView ?: throw RuntimeException("remove optionsListView from the show and exit animation")
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
          ObjectAnimator.ofFloat(toolView, "alpha", 1f, 0f),
          ObjectAnimator.ofFloat(toolView, "translationY", 0f, optionsListView.height.toFloat())
        )

        animatorSet.addListener(SetHardwareAnimatedViews(toolView))
        animatorSet.duration = ANIMATION_DURATION.toLong()
        return animatorSet
    }

    @OnEvent(BrushSettings.Event.SIZE)
    fun onChangeBrushSizeEvent() {
        seekSlider?.value = brushSettings.brushSize
    }

    override fun onOnSeekBarValueChange(view: SeekSlider?, value: Float) {
        brushSettings.brushSize = value
        updateBrushPreview()
    }

    override fun onOnSeekBarThumbLeaved(p0: SeekSlider?, p1: Float) {}
}
