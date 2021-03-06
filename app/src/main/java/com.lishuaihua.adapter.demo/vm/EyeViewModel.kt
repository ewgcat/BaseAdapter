package com.lishuaihua.adapter.demo.vm

import android.app.Application
import com.lishuaihua.adapter.demo.eye.EyepetizerRepo
import com.lishuaihua.adapter.demo.eye.HomeBean
import me.shouheng.vmlib.base.BaseViewModel


class EyeViewModel(application: Application) : BaseViewModel(application) {

    private var firstPageRequested: Boolean = false

    private var nextPageUrl: String? = null

    fun firstPage() {
        if (firstPageRequested) return
        firstPageRequested = true
        EyepetizerRepo.instance.firstPage(null, {
            nextPageUrl = it.nextPageUrl
            setSuccess(HomeBean::class.java, it)
        }, { code, msg ->
            setFailed(HomeBean::class.java, code, msg)
        })
    }

    fun nextPage() {
        EyepetizerRepo.instance.morePage(nextPageUrl, {
            nextPageUrl = it.nextPageUrl
            setSuccess(HomeBean::class.java, it)
        }, { code, msg ->
            setFailed(HomeBean::class.java, code, msg)
        })
    }
}
