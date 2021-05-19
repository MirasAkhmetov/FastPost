package com.project.fastpost.main.presentation.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.fastpost.R
import com.project.fastpost.entity.Traveler
import com.project.fastpost.global.di.getFormattedDate
import com.project.fastpost.global.di.getFormattedDateAndTime
import com.project.fastpost.global.extension.setCircleImageUrl
import kotlinx.android.synthetic.main.item_customer_main.view.*

class RecomendCustomerAdapter( val OnItemSelectedListener: (Traveler) -> Unit): RecyclerView.Adapter<RecomendCustomerAdapter.MyViewHolder>() {

    private var dataList: List<Traveler> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_customer_main,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int =  dataList.size

    fun submitData(dataList: List<Traveler>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(traveler: Traveler){
            itemView.apply {

                imgAvatarPostman.setCircleImageUrl(traveler.avatar)

                txtNamePostman.text = traveler.name +" " + traveler.surname


                ratingBarPostman.rating = 5F
                txtRating.text ="5,0"

                txtKilogram?.text = traveler.weight.toString()+"кг"
                txtFromPostman?.text = traveler.from
                txtToPostman?.text = traveler.to
                txtDateHome?.text = traveler.date.getFormattedDateAndTime()

                txtPriceHome?.text = traveler.price

                setOnClickListener { OnItemSelectedListener.invoke(traveler) }


            }
        }

    }

}