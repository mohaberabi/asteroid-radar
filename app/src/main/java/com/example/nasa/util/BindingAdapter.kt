package com.example.nasa.util

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa.R
import com.example.nasa.model.Asteroid
import com.example.nasa.model.ImageOfDay
import com.example.nasa.ui.AsteroidAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("goneIfNoImage")

fun bindImgOFDayText(view: View, imageOfDay: ImageOfDay) {
    if (imageOfDay.url.isEmpty()) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("isFiltering")
fun bindFilteringButton(view: View, isFiltering: Boolean) {

    if (isFiltering) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE

    }
}

@BindingAdapter("status")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }


}


@BindingAdapter("bindIfImage")

fun bindImageOfDayIfImage(imageView: ImageView, img: ImageOfDay) {
    if (img.url.isNotEmpty()) {
        Picasso.get().load(img.url)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .error(R.drawable.placeholder_picture_of_day)
            .into(imageView)
    }

}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("listData")
fun bindRecView(recycler: RecyclerView, data: List<Asteroid>?) {
    val adpater = recycler.adapter as? AsteroidAdapter
    adpater?.submitList(data)
}


@BindingAdapter("isHarm")

fun bindIsHarm(imgView: ImageView, asteroid: Asteroid) {
    if (asteroid.isPotentiallyHazardous) {
        imgView.contentDescription = "PotentiallyHazardous Asteroid"
    } else {
        imgView.contentDescription = "Non PotentiallyHazardous Asteroid"

    }
}

@BindingAdapter("imageUrl")
fun bindImageUrl(
    imgView: ImageView,
    url: String?
) {

    Picasso.get().load(url).into(imgView);

}
