package br.com.mov.views.slidepager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

import br.com.mov.R
import br.com.mov.models.Slide

class SlidePagerAdapter(private val context: Context, private val slides: List<Slide>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val slideLayout = inflater.inflate(R.layout.slide_item, null)

        val slideImage = slideLayout.findViewById<ImageView>(R.id.slideImage)
        slideImage.setImageResource(slides[position].image)

        container.addView(slideLayout)
        return slideLayout
    }

    override fun getCount() = slides.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
