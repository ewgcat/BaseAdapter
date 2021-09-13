package com.lishuaihua.adapterlib

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lishuaihua.adapterlib.adapter.AdapterSetup

/** Create quick adapter. */
fun <IT> createAdapter(
    init: AdapterSetup<IT, BaseViewHolder>.() -> Unit
): BaseQuickAdapter<IT, BaseViewHolder>  {
    val setup = AdapterSetup<IT, BaseViewHolder>()
    setup.apply(init)
    return setup.build()
}
