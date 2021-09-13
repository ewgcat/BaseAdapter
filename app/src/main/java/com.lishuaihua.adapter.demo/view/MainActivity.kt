package com.lishuaihua.adapter.demo.view

import android.os.Bundle
import com.lishuaihua.adapter.demo.R
import com.lishuaihua.adapter.demo.databinding.ActivityMainBinding
import me.shouheng.utils.ktx.onDebouncedClick
import me.shouheng.vmlib.base.ViewBindingActivity
import me.shouheng.vmlib.comn.ContainerActivity
import me.shouheng.vmlib.comn.EmptyViewModel


class MainActivity : ViewBindingActivity<EmptyViewModel, ActivityMainBinding>() {

    override fun doCreateView(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar)
        supportFragmentManager.beginTransaction().replace(R.id.container, EyeFragment()).commit()
        binding.fab.onDebouncedClick {
            ContainerActivity.open(MultiListFragment::class.java).launch(context)
        }
        setMenu(R.menu.menu_main) {
            when (it.itemId) {
                R.id.action_settings -> true
                else -> { /*noop*/ }
            }
        }
    }
}