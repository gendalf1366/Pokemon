package ru.gendalf13666.pokemon.features.shared

interface IAdapterPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
