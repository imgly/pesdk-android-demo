/*
 * This file is part of the PhotoEditor Software Development Kit.
 *
 * Copyright (C) 2019 img.ly GmbH <contact@img.ly>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, without
 * modification, are permitted provided that the following license agreement
 * is approved and a legal/financial contract was signed by the user.
 *
 * The license agreement can be found under the following link:
 *
 * https://www.photoeditorsdk.com/LICENSE.txt
 */
package com.photoeditorsdk.android.app.custom

import android.os.Handler
import android.os.Looper
import ly.img.android.pesdk.backend.model.state.ProviderState
import ly.img.android.pesdk.backend.model.state.manager.StateHandler

/**
 * Created by svennahler on 11.05.17.
 */
class MyCustomDataProvider {
    companion object {
        const val PROVIDER_NAME = "MyCustomProviderID"
    }

    // Updating test, it updates the content every 2 seconds.
    // It is only a demo, you should update wenn you content is ready instead.
    private val mainLoopHandler = Handler(Looper.getMainLooper())
    private val updateLoop: () -> Unit = {
        currentState++
        update()
        delayedUpdate()
    }
    private fun delayedUpdate() = mainLoopHandler.postDelayed(updateLoop, 2000)
    init {
        delayedUpdate()
    }


    var demoText = arrayOf(
        "This is...",
        "...a Smart Sticker.",
        "With an updating...",
        "...Provider!"
    )

    var currentState = 0
        set(value) {
            field = if (value >= demoText.size) {
                0
            } else {
                value
            }
        }

    var stateHandler: StateHandler? = null

    fun getText() : String {
        return demoText[currentState]
    }

    fun update() {
        stateHandler?.get(ProviderState::class)?.updateProvider(PROVIDER_NAME)
        stateHandler?.get(CustomSmartStickerConfig::class)?.propagateStateUpdateToLayer()
    }
}