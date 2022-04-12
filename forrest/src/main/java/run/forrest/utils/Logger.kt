package run.forrest.utils

import android.content.Context

import android.util.Log

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * noglob adb -d logcat -s "Indicator"
 */
class Logger {
    companion object {
        private val sTimePrefix = SimpleDateFormat("hh:mm:ss.SSS", Locale.US)
        private var sIsReady = false
        private val TAG = "FORREST"

        fun initLog(context: Context) {
            val sessionLog = StringBuilder()
            val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())
            sessionLog.append(currentDateTimeString)
            sessionLog.append("\n\n\n\n\n")
            sIsReady = true
            log(sessionLog.toString())
        }

        private fun log(text: String) {
            try {
                Log.e(TAG, text)

            } catch (t: Throwable) {
                Log.e(TAG, t.toString())
            }
        }

        private fun log(text: String, t: Throwable) {
            log(text + " " + t.message)
        }

        fun d(msg: String) {
            log(msg)
        }

        fun e(msg: String) {
            log(msg)
        }

//        fun e(t: Throwable) {
//            log(t.message!!)
//        }

        fun e(msg: String, t: Throwable) {
            log("$msg: Exception: ${t.message}")
        }


//        fun sendLogs(context: Context) {
//            AndroidLogger.processPendingLogsStopAndGetLogFiles(object :
//                AndroidLogger.GetFilesCallback {
//                override fun onFiles(logFiles: Array<File>) {
//                    val emailIntent = Intent(Intent.ACTION_SEND)
//                    emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//                    // set the type to 'email'
//                    emailIntent.type = "vnd.android.cursor.dir/email"
//                    val to = arrayOf("levy.shai@gmail.com")
//                    emailIntent.putExtra(Intent.EXTRA_EMAIL, to)
//
//                    val path = FileProvider.getUriForFile(
//                        context,
//                        context.getApplicationContext().getPackageName(),
//                        logFiles[0]
//                    )
//                    emailIntent.putExtra(Intent.EXTRA_STREAM, path)
//                    // the mail subject
//                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Explain the bug please")
//                    context.startActivity(Intent.createChooser(emailIntent, "Send the logs..."))
//
//                    try {
//                        AndroidLogger.reinitAndroidLogger()
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                }
//            })
//        }

//        fun DeleteFolderContents(folder: File) {
//            for (tempFile in folder.listFiles()) {
//                if (tempFile.name.contains("genda")) {
//                    tempFile.delete()
//                }
//            }
//        }
    }
}