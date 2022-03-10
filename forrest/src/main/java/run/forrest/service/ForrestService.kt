package run.forrest.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import org.greenrobot.eventbus.EventBus
import run.forrest.utils.Logger
import run.forrest.utils.Notifications
import run.forrest.utils.OnForrestServiceStart
import run.forrest.utils.Utils
import java.util.*


/**
 * The on going service.
 */
class ForrestService : Service() {

    //Instance of inner class created to provide access  to public methods in this class
    private val forrestBinder: ForrestServiceBinder = ForrestServiceBinder()

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class ForrestServiceBinder : Binder() {
        val service: ForrestService
            get() = this@ForrestService
    }

    // region constants

    // Update Notification every 1 second (Max that can be).
    private val UPDATE_NOTIFICATION_MILLISECONDS: Long = 1000

    // endregion

    // region Data Members

    private var foregroundNotification: Notifications? = null

    private val notificationHandler = Handler(Looper.getMainLooper())

    private val serviceStartTime: Long = System.currentTimeMillis()

    var channelName: String = "Foreground Service"

    // endregion

    // region Life Cycle

    /**
     * The service is being created.
     */
    override fun onCreate() {
        super.onCreate()
        Logger.d("forrest onCreate")
    }

    /**
     * The service is starting, due to a call to startService().
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        foregroundNotification = Notifications(this, channelName)
        foregroundNotification?.showForegroundNotification(this)

        return START_STICKY
    }

    /**
     * A client is binding to the service with bindService().
     */
    override fun onBind(intent: Intent?): IBinder {

        return forrestBinder
    }

    /**
     * This method is Called when activity have disconnected from a particular interface published by the service.
     * Note: Default implementation of the  method just  return false
     *
     * All clients have unbound with unbindService().
     */
    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    /**
     * Called when an activity is connected to the service, after it had previously been notified
     * that all had disconnected in its onUnbind method.
     * This will only be called by system if the implementation of onUnbind method was overridden to return true.
     *
     * A client is binding to the service with bindService(),
     * after onUnbind() has already been called.
     */
    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    /**
     * The service is no longer used and is being destroyed.
     */
    override fun onDestroy() {
        super.onDestroy()

        // Stop Updating notification.
        stopUpdateNotification()

        // Restart Service.
        restartService()
    }

    private fun restartService() {

        // Send restart broadcast.
        Intent().also { intent ->
            intent.action = "restartservice"
            sendBroadcast(intent)
        }
    }

    // endregion

    // region Public Methods

    fun stopUpdateNotification() {

        // Clear the notification handler.
        notificationHandler.removeCallbacksAndMessages(null)
    }

    fun setNotificationTitle(notificationTitle: String): ForrestService {

        Logger.d("is foregroundNotification null - ${foregroundNotification == null}")
        channelName = notificationTitle

        // Update Notification.
        foregroundNotification?.updateNotification(notificationTitle)

        return this
    }

    // endregion

    // region Private Methods

    private fun startUpdateNotification() {

        // Create notification runnable.
        val notificationRunnable: Runnable = object : Runnable {
            override fun run() {

                // Get time that pass in string.
                val timeThatPass =
                    Utils.convertMillisecondsToTime(System.currentTimeMillis() - serviceStartTime)

                // Update Notification.
                foregroundNotification?.updateNotification(timeThatPass)

                Log.e("Forrest", "Moti Still Running")

                // Set the handler to repeat this runnable task every x millis.
                notificationHandler.postDelayed(this, UPDATE_NOTIFICATION_MILLISECONDS)
            }
        }

        // Trigger first time.
        notificationHandler.post(notificationRunnable)
    }
}

fun ForrestService.run(context: Context): ForrestService {

    Logger.d("run forrest run")

//    ContextCompat.startForegroundService(context,Intent(context, ForrestService::class.java))

    context.startForegroundService(Intent(context, ForrestService::class.java))

    return this
}