package br.com.mov.views.slidepager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import br.com.mov.R;
import br.com.mov.models.Slide;

public class SlidePagerAdapter extends PagerAdapter {

    private Context context;
    private List<Slide> slides;

    public SlidePagerAdapter(Context context, List<Slide> slides) {
        this.context = context;
        this.slides = slides;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item, null);

        ImageView slideImage = slideLayout.findViewById(R.id.slideImage);
        slideImage.setImageResource(slides.get(position).getImage());

        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return slides.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
