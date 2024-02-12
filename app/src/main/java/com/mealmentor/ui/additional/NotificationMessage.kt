package com.mealmentor.ui.additional

import android.app.Notification
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.mealmentor.logic.database.sign_in.FirebaseViewModel

@Composable
fun NotificationMessage(viewModel: FirebaseViewModel) {
    val notificationState = viewModel.popupNotification.value
    val notificationMessage = notificationState?.getContentOrNull()
    if (notificationMessage != null) {
        Toast.makeText(
            LocalContext.current,
            notificationMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}