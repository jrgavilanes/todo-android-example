package es.codekai.mi_todo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.codekai.mi_todo.R
import es.codekai.mi_todo.data.models.ToDoData
import es.codekai.mi_todo.databinding.RowLayoutBinding

//class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
//
//    private lateinit var binding: RowLayoutBinding
//
//    var dataList = emptyList<ToDoData>()
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
//        return MyViewHolder(binding.root)
//
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = dataList[position]
////        holder.bind(currentItem)
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//}