package com.globomed.learn

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.globomed.learn.databinding.ActivityAddBinding
import java.text.SimpleDateFormat
import java.util.*

class UpdateEmployeeActivity: AppCompatActivity() {

	lateinit var binding: ActivityAddBinding
	lateinit var databaseHelper : DatabaseHelper
	private val myCalendar = Calendar.getInstance()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		databaseHelper = DatabaseHelper(this)

		// on clicking ok on the calender dialog
		val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
			myCalendar.set(Calendar.YEAR, year)
			myCalendar.set(Calendar.MONTH, monthOfYear)
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

			binding.etDOB.setText(getFormattedDate(myCalendar.timeInMillis))
		}

		binding.etDOB.setOnClickListener {
			setUpCalender(date)
		}

		binding.bSave.setOnClickListener {
			saveEmployee()
		}

		binding.bCancel.setOnClickListener {
			finish()
		}
	}

	private fun saveEmployee() {

		var isValid = true

		binding.etDesignation.error = if (binding.etDesignation?.text.toString().isEmpty()) {
			isValid = false
			"Required Field"
		} else null

		binding.etEmpName.error = if (binding.etEmpName?.text.toString().isEmpty()) {
			isValid = false
			"Required Field"
		} else null

		if (isValid) {

		}
	}

	private fun setUpCalender(date: DatePickerDialog.OnDateSetListener) {
		DatePickerDialog(
			this, date, myCalendar
			.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
			myCalendar.get(Calendar.DAY_OF_MONTH)
		).show()
	}

	private fun getFormattedDate(dobInMilis: Long?): String {

		return dobInMilis?.let {
			val sdf = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())
			sdf.format(dobInMilis)
		} ?: "Not Found"
	}
}