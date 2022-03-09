package run.forrest

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import run.forrest.service.Forrest

class ServiceHelper(val context: Context) {

    private val forrestService: Forrest = Forrest()

    fun run() {

        ContextCompat.startForegroundService(context, Intent(context, Forrest::class.java))
    }

    fun uu(notificationTitle: String): ServiceHelper {

        forrestService.setNotificationTitle(notificationTitle)

        return this
    }
}