package run.forrest.utils

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import run.forrest.ui.MainActivity
import run.forrest.R

class Notifications(private val context: Context) {

    // region Constants

    private val CHANNEL_ID = ((System.currentTimeMillis() / 1000L).toInt() % Integer.MAX_VALUE).toString()
    private val CHANNEL_NAME = "Foreground Service"
    private val NOTIFICATION_CONTENT_TITLE = "Have a nice day"
    private val DEFAULT_NOTIFICATION_ID = 1

    // endregion

    // region Data Members

    private var notificationBuilder: NotificationCompat.Builder = buildNotification()

    // endregion

    // region Public Methods

    fun showNotification(){

        with(NotificationManagerCompat.from(context)) {

            // notificationId is a unique int for each notification that you must define
            notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    fun showForegroundNotification(service: Service){

        // Start foreground with foreground notification.
        service.startForeground(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
    }

    fun updateNotification(notificationTitle: String){

        notificationBuilder.setContentTitle(notificationTitle)

        with(NotificationManagerCompat.from(context)) {

            // notificationId is a unique int for each notification that you must define
            notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    // endregion

    // region Private Methods

    /**
     * Create a notification builder.
     */
    private fun buildNotification(): NotificationCompat.Builder {

        // Create notification channel.
        createNotificationChannel(CHANNEL_NAME)

        // Create the notification.
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)

        // Only the first time the notification appears and not for later updates.
        notificationBuilder.setOnlyAlertOnce(true)

        // Must to set icon for title will work.
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
        notificationBuilder.setContentTitle(NOTIFICATION_CONTENT_TITLE)

        // Set an intent to open activity when notification pressed.
        notificationBuilder.setContentIntent(getActivityIntent(MainActivity::class.java))

        return notificationBuilder
    }

    /**
     * Create a notification channel - Android system request.
     */
    private fun createNotificationChannel(channelName:String, importance: Int = NotificationManager.IMPORTANCE_NONE) {

        // Create notification channel.
        val notificationChannel =
            NotificationChannel(CHANNEL_ID, channelName, importance)

        // Create notification manager and register the channel with the system.
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // register the notification channel.
        notificationManager.createNotificationChannel(notificationChannel)
    }

    /**
     * Create an activity intent to open when notification pressed.
     */
    private fun getActivityIntent(activityClass: Class<*>?): PendingIntent? {

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, activityClass).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    // endregion
}