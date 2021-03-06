package com.example.fbproj1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList


class Details : AppCompatActivity() {

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter;
    private lateinit var viewPager: ViewPager;
    private lateinit var photo: PhotoCardItem;
    private lateinit var photosCollection: ArrayList<PhotoCardItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        photo = intent.getSerializableExtra("photo") as PhotoCardItem
        photosCollection = intent.getSerializableExtra("photoCollection") as ArrayList<PhotoCardItem>

        val toolbar = findViewById(R.id.mytoolbar) as Toolbar
        setSupportActionBar(toolbar)

        sectionsPagerAdapter = SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.viewPager) as ViewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        val tabLayout = findViewById<View>(R.id.tabLayout) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = FragFullPhoto(photo)
                1 -> fragment = FragDataAndSimilar(photo, photosCollection);
            }
            return fragment
        }

        override fun getCount(): Int {
            return 2;
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return getString(R.string.frag_full_photo_desc);
                1 -> return getString(R.string.frag_photo_details_desc);
            }
            return null
        }
    }

}
