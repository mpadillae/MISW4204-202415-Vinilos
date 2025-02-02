package com.mfpe.vinilos.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mfpe.vinilos.data.model.Musician
import com.mfpe.vinilos.databinding.ArtistItemBinding
import com.mfpe.vinilos.ui.artists.MusicianDetailActivity

class MusicianAdapter(private var musicians: List<Musician>): RecyclerView.Adapter<MusicianAdapter.ViewHolder>(),Filterable {

    inner class ViewHolder(val binding: ArtistItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianAdapter.ViewHolder {
        val bind = ArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bind)
    }

    override fun getItemCount(): Int = musicians.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // No filtering, return the original list as is
                return FilterResults().apply {
                    values = musicians
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                // Do nothing, since no filtering is applied
            }
        }
    }

    override fun onBindViewHolder(holder: MusicianAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(musicians[position]){
                binding.artistName.text = this.name
                com.bumptech.glide.Glide.with(binding.artistImage.context)
                    .load(this.image)
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .centerCrop()
                    .into(binding.artistImage)
                binding.artistCard.setOnClickListener{
                    val intent = Intent(binding.root.context,MusicianDetailActivity::class.java)
                    intent.putExtra("musician",this)
                    binding.root.context.startActivity(intent)
                }
            }}
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMusicians(newMusicians: List<Musician>) {
        musicians = newMusicians
        notifyDataSetChanged()
    }

}