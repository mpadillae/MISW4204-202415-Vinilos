package com.mfpe.vinilos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mfpe.vinilos.databinding.TrackItemBinding
import com.mfpe.vinilos.data.model.Track

class TrackAdapter(private val albumCover: String, private var tracks: List<Track>) : RecyclerView.Adapter<TrackAdapter.ViewHolder>()  {

    inner class ViewHolder(val binding: TrackItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackAdapter.ViewHolder {
        val bind = TrackItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: TrackAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(tracks[position]) {
                binding.trackId.text = "%d".format(position + 1)
                binding.trackName.text = this.name
                Glide.with(binding.trackImage.context)
                    .load(albumCover)
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .centerCrop()
                    .into(binding.trackImage)
            }
        }
    }

    override fun getItemCount(): Int = tracks.size

}