package com.globomed.learn

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globomed.learn.databinding.ListItemBinding

class EmployeeListAdapter(
    private val context: Context,
) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {

    lateinit var employeeList: ArrayList<Employee>
//    val TAG: String = EmployeeListAdapter::class.java.name


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {

        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun getItemCount(): Int = employeeList.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee: Employee = employeeList[position]
        holder.setData(employee.name, employee.designation, position)
        holder.setListener()
    }

    fun setEmployees(employees: ArrayList<Employee>) {
        employeeList = employees
        notifyDataSetChanged()
    }

    inner class EmployeeViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var pos = 0

        fun setData(name: String, designation: String, pos: Int) {

            binding.tvEmpName.text = name
            binding.tvEmpDesignation.text = designation
            this.pos = pos
        }

        fun setListener() {
            itemView.setOnClickListener {

                val intent = Intent(context, UpdateEmployeeActivity::class.java)
                intent.putExtra(GloboMedDbContract.EmployeeEntry.COLUMN_ID, employeeList[pos].id)
                (context as Activity).startActivityForResult(intent, 2)
            }
        }

    }
}
