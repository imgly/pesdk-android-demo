package com.photoeditorsdk.android.app.custom

import android.graphics.Bitmap
import android.graphics.Color
import androidx.annotation.MainThread
import ly.img.android.pesdk.backend.layer.StickerGlLayer
import ly.img.android.pesdk.backend.model.chunk.recycleAfter
import ly.img.android.pesdk.backend.model.state.layer.ImageStickerLayerSettings
import ly.img.android.pesdk.backend.model.state.manager.StateHandler
import ly.img.android.pesdk.kotlin_extension.mapPointsWith
import ly.img.android.pesdk.utils.*
import kotlin.math.roundToInt

// Sticker that DON'T respond on alpha areas.
class CustomGlStickerLayer(stateHandler: StateHandler, settings: ImageStickerLayerSettings) : StickerGlLayer(stateHandler, settings) {

    companion object {
        // Alpha of min 70% is recognized as click area.
        const val ALPHA_THRESHOLD = 0.7f

        // Size of the cache.
        const val CLICK_CACHE_SIZE_IN_PIXEL = 64
    }

    /**
     * Low quality cache version for alpha check.
     * 1. This is faster
     * 2. It's more memory efficient
     * 3. It adds a threshold because the pixels are bigger
     */
    private var clickAreaCache: Bitmap = BitmapFactoryUtils.NOTHING_BITMAP
    private var isClickCacheLoading = false

    @MainThread
    override fun doRespondOnClick(event: TransformedMotionEvent) : Boolean {
        val isClickInBitmap = isInOpaqueBitmap(event)
        if (isClickInBitmap && settings.isInEditMode && settings.hasVariants()) {
            settings.nextVariant()
            refresh()
        }
        return isClickInBitmap
    }

    // We hijack internalLoadBitmapCache to know when it's time to load the cache
    override fun internalLoadBitmapCache(pixelSize: Long, sync: Boolean): Boolean {
        if (!isClickCacheLoading && clickAreaCache == BitmapFactoryUtils.NOTHING_BITMAP) {
            isClickCacheLoading = true
            // Load cache in background thread.
            ReplaceRunnable("LoadClickCache" + System.identityHashCode(this)) {
                clickAreaCache = settings.stickerConfig.stickerSource.getBitmap(CLICK_CACHE_SIZE_IN_PIXEL, CLICK_CACHE_SIZE_IN_PIXEL, true)
                    ?: throw RuntimeException("Sticker source not reachable") // TODO: If you have remote resources handle this different.
            }()
        }
        return super.internalLoadBitmapCache(pixelSize, sync)
    }

    override fun isRelativeToCrop() = false

    private fun isInOpaqueBitmap(event: TransformedMotionEvent): Boolean {
        val pointRelativeToOrigin = obtainSpriteMatrix() recycleAfter {
            it.useInverted { inverted ->
                event.getPosition(0).mapPointsWith(inverted)
            }
        }

        // Extends the in click area + 10dp
        return obtainSpriteDestinationRect(imageToScreenUITransformation)
                .recycleAfter { clickArea ->
                    // Check if we are in the extended area.
                    val clickX = pointRelativeToOrigin[0]
                    val clickY = pointRelativeToOrigin[1]
                    if (clickArea.apply { addMargin(uiDensity * 10) }.contains(clickX, clickY)) {

                        // Map to relative image coordinates
                        val relativeX = MathUtils.mapRange(clickX, clickArea.left, clickArea.right, 0f, 1f).clamp(0f, clickAreaCache.width - 1f)
                        val relativeY = MathUtils.mapRange(clickY, clickArea.top, clickArea.bottom, 0f, 1f).clamp(0f, clickAreaCache.height - 1f)

                        // Map to Pixel coordinates of the cache.
                        val absoluteXInCache = (relativeX * clickAreaCache.width).roundToInt().clamp(0, clickAreaCache.width - 1)
                        val absoluteYInCache = (relativeY * clickAreaCache.height).roundToInt().clamp(0, clickAreaCache.height - 1)

                        // Check if the color value is almost opaque
                        val color = clickAreaCache.getPixel(
                            absoluteXInCache,
                            absoluteYInCache
                        )

                        (Color.alpha(color) / 255f >= ALPHA_THRESHOLD)
                    } else false
                }

    }


}