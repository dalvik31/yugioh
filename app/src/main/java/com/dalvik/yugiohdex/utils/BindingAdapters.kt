package com.dalvik.yugiohdex.utils

import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.dalvik.yugiohdex.adapters.CardAdapter
import com.dalvik.yugiohdex.adapters.CardDetailAdapter
import com.dalvik.yugiohdex.models.Card
import com.dalvik.yugiohdex.viewmodel.CardsViewmodel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BindingAdapters {


    companion object{
        @JvmStatic
        @BindingAdapter("android:imageUrl")
        fun setImageUrl(imageView: ImageView, url:String){
            try {
                imageView.alpha = 0f
                Picasso.get().load(url).noFade().into(imageView, object : Callback {
                    override fun onSuccess() {
                        imageView.animate().setDuration(300).alpha(1f).start()
                    }

                    override fun onError(e: Exception) {}
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        @BindingAdapter("android:adapter")
        fun setAdapterRecycler(recyclerView: RecyclerView, cardsList: ArrayList<Card>){
           val cardAdapter = CardAdapter(cardsList)
            recyclerView.adapter = cardAdapter
        }

        @JvmStatic
        @BindingAdapter("android:adapterDetail")
        fun setAdapterDetailRecycler(recyclerView: RecyclerView, cardsList: ArrayList<Card>){
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
            recyclerView.setHasFixedSize(true)
            val cardAdapter = CardDetailAdapter(cardsList)
            recyclerView.adapter = cardAdapter
        }

        @JvmStatic
        @BindingAdapter("android:querListener")
        fun setQueryListener(searchView: SearchView,viewmodel: CardsViewmodel){
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    viewmodel.filterByName(p0!!)
                    return true
                }

            })
        }

    }

}