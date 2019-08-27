package com.neiljaywarner.tasc_exercise.extensions

import android.view.LayoutInflater
import android.view.ViewGroup

// Let a viewgroup inflate itself from its R.layout file
fun ViewGroup.inflate(layoutId: Int) =
    LayoutInflater.from(context).inflate(layoutId, this, false)