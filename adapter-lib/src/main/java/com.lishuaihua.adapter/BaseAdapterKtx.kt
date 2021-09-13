package com.lishuaihua.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lishuaihua.adapter.adapter.AdapterSetup

/** Create quick adapter. */
fun <IT> createAdapter(
    init: AdapterSetup<IT, BaseViewHolder>.() -> Unit
): BaseQuickAdapter<IT, BaseViewHolder>  {
    val setup = AdapterSetup<IT, BaseViewHolder>()
    setup.apply(init)
    return setup.build()
}
