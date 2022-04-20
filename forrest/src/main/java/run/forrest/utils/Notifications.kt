package run.forrest.utils

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import run.forrest.R

class Notifications(private val context: Context, private val channelName: String) {

    // region Constants

//    private val CHANNEL_ID = "Foreground Service"
//    private val CHANNEL_NAME = "Foreground Service"
    private val NOTIFICATION_CONTENT_TITLE = "Have a nice day"
    private val notificationId = (System.currentTimeMillis() % Integer.MAX_VALUE).toInt()

    // endregion

    // region Data Members

    private var notificationBuilder: NotificationCompat.Builder = buildNotification(NOTIFICATION_CONTENT_TITLE)

    // endregion

    // region Public Methods

    fun showNotification(activityToOpen: Class<*>? = null){

        Logger.d("showNotification notificationId - $notificationId")

        if (activityToOpen != null) openActivity(activityToOpen)

        with(NotificationManagerCompat.from(context)) {

            // Notification id is a unique int for each notification that you must define.
            notify(notificationId, notificationBuilder.build())
        }
    }

    fun showForegroundNotification(service: Service){

        Logger.d("showForegroundNotification notificationId - $notificationId")

        // Start foreground with foreground notification.
        service.startForeground(notificationId, notificationBuilder.build())
    }

    fun updateNotification(notificationTitle: String, activityToOpen: Class<*>? = null){

        Logger.d("Update notification title $notificationTitle")

        notificationBuilder.setContentTitle(notificationTitle)
        if (activityToOpen != null) openActivity(activityToOpen)

        with(NotificationManagerCompat.from(context)) {

            // notificationId is a unique int for each notification that you must define.
            notify(notificationId, notificationBuilder.build())
        }
    }

    /**
     * send activity like this - MainActivity::class.java
     */
    fun openActivity(activityToOpen: Class<*>){

        // Set an intent to open activity when notification pressed.
        notificationBuilder.setContentIntent(getActivityIntent(activityToOpen))
    }

    // endregion

    // region Private Methods

    /**
     * Create a notification builder.
     */
    private fun buildNotification(notificationTitle: String): NotificationCompat.Builder {

        // Create notification channel.
        createNotificationChannel(channelName)

        // Create the notification.
        val notificationBuilder = NotificationCompat.Builder(context, channelName)

        // Only the first time the notification appears and not for later updates.
        notificationBuilder.setOnlyAlertOnce(true)

        // Must to set icon for title will work.
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
        notificationBuilder.setContentTitle(notificationTitle)

        // Set an intent to open activity when notification pressed.
        notificationBuilder.setContentIntent(getActivityIntent())

        return notificationBuilder
    }

    /**
     * Create a notification channel - Android system request.
     */
    private fun createNotificationChannel(channelName:String, importance: Int = NotificationManager.IMPORTANCE_MIN) {

        // Create notification channel.
        val notificationChannel =
            NotificationChannel(channelName, channelName, importance)

        // Create notification manager and register the channel with the system.
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // register the notification channel.
        notificationManager.createNotificationChannel(notificationChannel)
    }

    /**
     * Create an activity intent to open when notification pressed.
     */
    private fun getActivityIntent(activityClass: Class<*>? = null): PendingIntent? {

        // Create an explicit intent for an Activity in your app
        val activityIntent = if (activityClass != null)
            Intent(context, activityClass).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        else Intent()

        return PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    /**
     * Create an broadcast intent to open when notification pressed.
     */
    private fun getBroadcastIntent(broadcastClass: Class<*>?, intentName: String, intentExtra: String): PendingIntent? {

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, broadcastClass).apply {
            putExtra(intentName, intentExtra)
        }

        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    // endregion
}