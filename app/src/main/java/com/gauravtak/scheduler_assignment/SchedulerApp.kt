package com.gauravtak.scheduler_assignment

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

public class SchedulerApp: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        mContext = this
        MultiDex.install(this)
    }
    companion object {
        public  var  mContext:Context? = null;
    }
}