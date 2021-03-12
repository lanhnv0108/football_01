package com.sun.uefascore.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.sun.uefascore.R
import com.sun.uefascore.screen.favorite.FavoriteFragment
import com.sun.uefascore.screen.fixtures.FixturesFragment
import com.sun.uefascore.screen.standing.StandingFragment
import com.sun.uefascore.screen.topscorers.TopScorersFragment
import com.sun.uefascore.utils.MenuItem
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment() {

    private val fragments = mutableListOf<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
        fragmentManager?.let {
            viewPagerContainer.adapter = ViewPagerContainerAdapter(it, fragments)
            viewPagerContainer.offscreenPageLimit = 3
            initBottomItem()
        }
    }

    private fun initFragment() {
        fragments.apply {
            add(MenuItem.FIXTURES.ordinal, FixturesFragment.newInstance())
            add(MenuItem.STANDING.ordinal, StandingFragment.newInstance())
            add(MenuItem.SCORERS.ordinal, TopScorersFragment.newInstance())
            add(MenuItem.FAVORITE.ordinal, FavoriteFragment.newInstance())
        }
    }

    private fun initBottomItem() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem -> {
                    viewPagerContainer.currentItem = MenuItem.FIXTURES.ordinal
                    true
                }
                R.id.standingItem -> {
                    viewPagerContainer.currentItem = MenuItem.STANDING.ordinal
                    true
                }
                R.id.topScorersItem -> {
                    viewPagerContainer.currentItem = MenuItem.SCORERS.ordinal
                    true
                }
                R.id.favoriteItem -> {
                    viewPagerContainer.currentItem = MenuItem.FAVORITE.ordinal
                    true
                }
                else -> false
            }
        }
        viewPagerContainer.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavigation.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}
