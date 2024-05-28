@file:OptIn(ExperimentalMaterial3Api::class)

package com.pr7.jc_dialogs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.android.libraries.play.games.inputmapping.Input
import com.pr7.jc_dialogs.ui.theme.Pink40
import com.pr7.jc_dialogs.ui.theme.Purple40
import com.pr7.jc_dialogs.ui.theme.Purple80
import com.pr7.jc_dialogs.ui.theme.PurpleGrey80
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


/*
*
*  val date = remember {
        Calendar.getInstance().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, 7)
            set(Calendar.DAY_OF_MONTH, 23)
        }.timeInMillis
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date,
        yearRange = 1990..2023
    )
    var showDatePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 30,
    )
    var showTimePicker by remember { mutableStateOf(false) }
*
* */

@Composable
fun SimpleDatePickerDialogScreen(modifier: Modifier = Modifier) {


    val calendar = Calendar.getInstance()
    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)+1)
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = calendar.timeInMillis,
        yearRange = 2020..2025
    )

    datePickerState.displayMode = DisplayMode.Input

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""




    var showDatePicker by remember { mutableStateOf(false) }

    //date picker
    Button(
        onClick = {
            showDatePicker = true //changing the visibility state

        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "Date Picker ${selectedDate}")
    }


    // date picker component
    if (showDatePicker) {
        DatePickerDialog(
            modifier = Modifier.scale(0.9f),
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            },
            colors = DatePickerDefaults.colors(
                containerColor = PurpleGrey80,
            ),

            )
        {
            DatePicker(

                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    todayContentColor = Pink40,
                    todayDateBorderColor = Purple40,
                    selectedDayContentColor = Purple80,
                    dayContentColor = Purple40,
                    selectedDayContainerColor = Purple40,

                    headlineContentColor = Color.Black
                )
            )
        }
    }


    /*
    *
    *
     date picker component
    if (showDatePicker) {
        Dialog(onDismissRequest = { false  }) {
            Box(
                modifier = Modifier
                    .padding(0.dp) // Set the margin here
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
                    .wrapContentHeight()
            ) {
               Here All Designs
            }
        }

    }
    * */

}



@SuppressLint("SimpleDateFormat")
fun convertMillisToDate(millis: Long): String {
    //https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format
    //val formatter = SimpleDateFormat("MMMM dd yyyy  HH:mm")
    val formatter = SimpleDateFormat("dd-MMM")
    return formatter.format(Date(millis))
}







