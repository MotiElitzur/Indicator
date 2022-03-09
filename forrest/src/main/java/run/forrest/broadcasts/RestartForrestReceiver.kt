package run.forrest.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import run.forrest.service.Forrest

class RestartForrestReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("Broadcast Listened", "Service tried to stop")

        Toast.makeText(context, "Service restarted", Toast.LENGTH_SHORT).show()
        context.startForegroundService(Intent(context, Forrest::class.java))
    }
}