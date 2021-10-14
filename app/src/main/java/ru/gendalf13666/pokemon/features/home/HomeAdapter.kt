package ru.gendalf13666.pokemon.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gendalf13666.pokemon.R
import ru.gendalf13666.pokemon.databinding.ItemPokemonCardBinding

class HomeAdapter(private val presenter: IHomeAdapter) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPokemonCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemPokemonCardBinding) :
        RecyclerView.ViewHolder(vb.root), IPokemonItem {

        init {
            vb.root.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

        override fun setTitle(txt: String): Unit = with(vb) { tvName.text = txt }

        override fun setImage(url: String): Unit = with(vb) {
            Glide.with(ivPokemon)
                .load(url)
                .error(R.drawable.default_image)
                .into(ivPokemon)
        }

        override var pos: Int = -1
    }
}
