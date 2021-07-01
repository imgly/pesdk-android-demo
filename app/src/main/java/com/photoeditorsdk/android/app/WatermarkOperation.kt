package com.photoeditorsdk.android.app


import android.opengl.GLES20
import android.support.annotation.Keep
import ly.img.android.opengl.canvas.GlRect
import ly.img.android.opengl.programs.GlProgramShapeDraw
import ly.img.android.opengl.textures.GlFrameBufferTexture
import ly.img.android.opengl.textures.GlImageTexture
import ly.img.android.opengl.textures.GlTexture
import ly.img.android.pesdk.backend.decoder.ImageSource
import ly.img.android.pesdk.backend.model.chunk.MultiRect
import ly.img.android.pesdk.backend.model.chunk.recyclePool
import ly.img.android.pesdk.backend.model.chunk.setRecycler
import ly.img.android.pesdk.backend.model.state.TransformSettings
import ly.img.android.pesdk.backend.model.state.manager.stateHandlerResolve
import ly.img.android.pesdk.backend.operator.rox.RoxGlOperation
import ly.img.android.pesdk.backend.operator.rox.models.Requested

@Keep class WatermarkOperation() : RoxGlOperation() {
    private val transformSettings: TransformSettings by stateHandlerResolve()
    private val textureDrawProgram by SetupInit {
        GlProgramShapeDraw()
    }

    private val watermarkTexture by SetupInit {
        GlImageTexture().also {
            it.setBehave(GLES20.GL_LINEAR, GLES20.GL_CLAMP_TO_EDGE)
        }
    }
    private val frameBufferTexture by SetupInit {
        GlFrameBufferTexture().also {
            it.setBehave(GLES20.GL_LINEAR, GLES20.GL_CLAMP_TO_EDGE)
        }
    }

    private val frameGlRect by SetupInit(::GlRect)
    override val estimatedMemoryConsumptionFactor: Float = 1.0f


    override fun glSetup(): Boolean {
        // Load the Watermark into the Texture
        val watermarkBitmap = ImageSource.create(R.drawable.imgly_icon_camera).getBitmap(512, 512, true)
        if (watermarkBitmap != null) {
            watermarkTexture.setBitmap(watermarkBitmap)
        }
        return true
    }
    override fun doOperation(requested: Requested): GlTexture = recyclePool { pool ->
        val sourceTexture = sourceTextureAsRequested(requested)

        val cropedArea = transformSettings.obtainFitRect(requested.transformation).setRecycler(pool)
        val chunkRegion = requested.region

        // Calculate the scale for 25% of the width
        val scale = cropedArea.width / watermarkTexture.width / 4

        // Calculated the position for bottom right
        val destinationArea = MultiRect.obtainIn(pool).also {
            it.top = cropedArea.bottom - watermarkTexture.height * scale
            it.left = cropedArea.right - watermarkTexture.width * scale
            it.right = cropedArea.right
            it.bottom = cropedArea.bottom
        }

        // Test if the Watermark is inside the current chuck
        if (MultiRect.intersects(chunkRegion, destinationArea)) {
            // Set watermark destination relative to the chunk
            frameGlRect.setShape(destinationArea, contextRect = chunkRegion)

            // Copy the source texture to it
            frameBufferTexture.copyFrom(sourceTexture)

            // Write the watermark onto the result texture
            frameBufferTexture.record(doClear = false) {
                // Draw the watermark
                frameGlRect.drawWith(textureDrawProgram) {
                    it.setUniformImage(watermarkTexture)
                }
            }
            frameBufferTexture
        } else {
            sourceTexture
        }
    }

}