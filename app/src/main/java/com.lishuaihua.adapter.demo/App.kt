package com.lishuaihua.adapterlib.demo

import android.app.Application
import me.shouheng.vmlib.VMLib
import com.lishuaihua.adapter.demo.net.Server


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Server.initServer("http://baobab.kaiyanapp.com/api/")
        VMLib.onCreate(this)
    }
}
