package com.lishuaihua.adapter.demo.view

import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lishuaihua.adapter.createAdapter
import com.lishuaihua.adapter.demo.R
import com.lishuaihua.adapter.demo.databinding.FragmentListBinding

import com.lishuaihua.adapter.demo.eye.HomeBean
import com.lishuaihua.adapter.demo.eye.Item
import com.lishuaihua.adapter.demo.vm.EyeViewModel
import com.lishuaihua.adapter.demo.widget.loadCover
import com.lishuaihua.adapter.demo.widget.loadRoundImage
import com.lishuaihua.adapter.viewholder.onItemChildClick
import com.lishuaihua.adapter.viewholder.onItemChildLongClick
import com.lishuaihua.adapter.viewholder.onItemClick
import com.lishuaihua.adapter.viewholder.onItemLongClick
import me.shouheng.uix.widget.rv.listener.DataLoadScrollListener
import me.shouheng.utils.ktx.dp2px
import me.shouheng.vmlib.base.ViewBindingFragment

class EyeFragment : ViewBindingFragment<EyeViewModel, FragmentListBinding>() {

    private var scrollListener: DataLoadScrollListener? = null

    private var adapter: BaseQuickAdapter<Item, BaseViewHolder>? = null

    override fun doCreateView(savedInstanceState: Bundle?) {
        createAdapter()
        binding.rv.adapter = adapter
        scrollListener = object : DataLoadScrollListener(binding.rv.layoutManager as LinearLayoutManager) {
            override fun loadMore() {
                vm.nextPage()
            }
        }
        binding.rv.addOnScrollListener(scrollListener!!)
        observes()
        vm.firstPage()
    }

    /** Create an adapter based on the kotlin DSL. */
    private fun createAdapter() {
        adapter = createAdapter {
            withType(Item::class.java, R.layout.item_eyepetizer_home) {
                // Bind data with viewholder.
                onBind { helper, item ->
                    helper.setText(R.id.tv_title, item.data.title)
                    helper.setText(R.id.tv_sub_title, item.data.author?.name + " | " + item.data.category)
                    helper.loadCover(requireContext(), R.id.iv_cover, item.data.cover?.homepage, R.drawable.recommend_summary_card_bg_unlike)
                    helper.loadRoundImage(requireContext(), R.id.iv_author, item.data.author?.icon, R.mipmap.eyepetizer, 20f.dp2px())
                }
                // Item level click and long click events.
                onItemClick { _, _, position ->
                    adapter?.getItem(position)?.let {

                    }
                }
                onItemLongClick { _, _, position ->
                    adapter?.getItem(position)?.let {

                    }
                    true
                }
                // Item child level click and long click events.
                onItemChildClick(R.id.iv_author) { _, _, position ->
                    adapter?.getItem(position)?.let {

                    }
                }
                onItemChildLongClick(R.id.iv_author) { _, _, position ->
                    adapter?.getItem(position)?.let {

                    }
                    true
                }
                onItemChildClick(R.id.tv_title) { _, _, position ->
                    adapter?.getItem(position)?.let {

                    }
                }
                onItemChildClick(R.id.tv_sub_title) { _, _, position ->
                    adapter?.getItem(position)?.let {
                    }
                }
                onItemChildLongClick(R.id.tv_sub_title) { _, _, position ->
                    adapter?.getItem(position)?.let {
                    }
                    true
                }
            }
        }
    }

    private fun observes() {
        observe(HomeBean::class.java, success = {
            val list = mutableListOf<Item>()
            it.data.issueList.forEach { issue ->
                issue.itemList.forEach { item ->
                    if (item.data.cover != null
                        && item.data.author != null
                    ) list.add(item)
                }
            }
            adapter?.addData(list as List<Item>)
            scrollListener?.loading = false
        }, fail = {
            scrollListener?.loading = false

        })
    }
}
