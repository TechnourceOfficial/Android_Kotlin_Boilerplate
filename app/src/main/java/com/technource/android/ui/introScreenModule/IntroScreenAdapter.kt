package com.technource.android.ui.introScreenModule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.android_kotlin_boilerplate.R

class IntroScreenAdapter(private val context: Context) : PagerAdapter() {
    private lateinit var layoutInflater: LayoutInflater
    private var imageList = arrayOf(
        R.drawable.img_intro_vector,
        R.drawable.img_intro_vector,
        R.drawable.img_intro_vector
    )

    private var introTitle = arrayOf(
        R.string.title_text_1,
        R.string.title_text_2,
        R.string.title_text_3
    )
    private var introDescription = arrayOf(
        R.string.description_text_1,
        R.string.description_text_2,
        R.string.description_text_3
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.intro_screen, container, false)
        val imageView = view.findViewById<ImageView>(R.id.introImage)
        val title = view.findViewById<TextView>(R.id.introTitle)
        val description = view.findViewById<TextView>(R.id.introDescription)

        imageView.setImageResource(imageList[position])
        title.setText(introTitle[position])
        description.setText(introDescription[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getCount(): Int {
        return introTitle.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}