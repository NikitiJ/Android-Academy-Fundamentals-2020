package dev.nikitij.android.myemptyapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.nikitij.android.myemptyapplication.R
import dev.nikitij.android.myemptyapplication.models.ActorModel

class ActorsAdapter : RecyclerView.Adapter<ActorsViewHolder>() {

    private var actorsList = mutableListOf<ActorModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_actor_card_view, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actorsList[position])
    }

    override fun getItemCount(): Int {
        return actorsList.size
    }

    fun submitList(newList: List<ActorModel>) {
        actorsList = newList.toMutableList()
        notifyDataSetChanged()
    }
}

class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatarImage: ImageView = itemView.findViewById(R.id.imageViewAvatar)
    private val name: TextView = itemView.findViewById(R.id.fullName)

    fun bind(model: ActorModel) {
        model.drawableBgResource?.let { avatarImage.setImageResource(it) }
        name.text = model.fullName
    }
}