package com.example.acronymserviceinterviewtask

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.text.Typography.dagger


/**
 * Application class for Dagger-Hilt compatability
 */
@HiltAndroidApp
class MainApplication : Application() {
}