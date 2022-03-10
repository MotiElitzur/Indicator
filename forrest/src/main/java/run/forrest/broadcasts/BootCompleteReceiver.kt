package run.forrest.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import run.forrest.service.ForrestService

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        // TODO check if if necessary.
        if(intent.action.equals(Intent.ACTION_BOOT_COMPLETED)){

            // Start forrest service.
            context.startForegroundService(Intent(context, ForrestService::class.java))
        }
    }
}