package ly.img.android.pesdk.backend.sticker_smart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.Keep
import com.photoeditorsdk.android.app.custom.CustomSmartStickerConfig
import com.photoeditorsdk.android.app.custom.MyCustomDataProvider

import ly.img.android.pesdk.backend.model.ImageSize
import ly.img.android.pesdk.backend.model.chunk.MultiRect
import ly.img.android.pesdk.backend.model.chunk.recycleAfter
import ly.img.android.pesdk.backend.model.state.manager.stateHandlerResolve
import ly.img.android.pesdk.backend.smart.SmartSticker
import ly.img.android.pesdk.kotlin_extension.ColorValue

import kotlin.math.roundToInt

@Suppress("MemberVisibilityCanBePrivate")
open class CustomSmartSticker(context: Context, textColor: ColorValue, boxColor: ColorValue = Color.TRANSPARENT) : SmartSticker(context) {


    companion object {
        @Suppress("unused") @Keep const val PROVIDER_NAME = MyCustomDataProvider.PROVIDER_NAME

        const val fontSize = 320f
        const val CORNER_RADIUS = 210f
    }

    private val smartStickerConfig by stateHandlerResolve<CustomSmartStickerConfig>()

    val drawableFont = getDrawableFont(Font.OpenSans)

    val padding = if (boxColor == Color.TRANSPARENT) 0f else 150f

    open fun generateText(): String {
        val provider = smartStickerConfig.customProvider ?: return "Error"
        return provider.getText()
    }

    val boxPaint = if (boxColor != Color.TRANSPARENT) Paint().also {
        it.isAntiAlias = true
        it.color = boxColor
    } else null

    val textPaint = drawableFont.createTextPaint {
        textSize = fontSize
        color = textColor
    }

    val text : String by lazy { generateText() }

    val textBounds by lazy {
        drawableFont.boundsOf(text, fontSize = fontSize)
    }

    /**
     * Return the base sticker size. The sticker on the canvas will be scaled, relative to the image size.
     * This defines you drawing area. It is e.g. used to create the correct bounding box.
     * Keep sure to drawing only in this limits!
     */
    override fun calculateSize() : ImageSize {
        val contentWidth = textBounds.width
        val contentHeight = textBounds.height
        return ImageSize((contentWidth + padding * 2).roundToInt(), (contentHeight + padding * 2).roundToInt())
    }

    override fun draw(canvas: Canvas) {
        if (boxPaint != null) {
            MultiRect.obtain(0, 0, size.width, size.height).recycleAfter {
                canvas.drawRoundRect(it, CORNER_RADIUS, CORNER_RADIUS, boxPaint)
            }
        }

        canvas.drawText(text, size.width / 2.0f - textBounds.centerX(), size.height - textBounds.left - padding, textPaint)
    }
}


class CustomSmartSticker0(context: Context) : CustomSmartSticker(context,
  textColor = ColorValue(0xFF000000),
  boxColor = ColorValue(0x7FFFFFFF)
)

class CustomSmartSticker1(context: Context) : CustomSmartSticker(context,
  textColor = ColorValue(0xFF000000),

)

class CustomSmartSticker2(context: Context) : CustomSmartSticker(context,
  textColor = ColorValue(0xFFFFFFFF)
)
