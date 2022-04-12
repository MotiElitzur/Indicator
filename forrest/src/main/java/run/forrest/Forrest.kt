package run.forrest

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import run.forrest.service.ForrestService
import run.forrest.service.run
import run.forrest.utils.Logger
import run.forrest.utils.Notifications
import run.forrest.utils.OnForrestServiceStart

/**
 * The forrest service helper. //TODO Check if DefaultLifecycleObserver necessary or forrest can implement application.
 */
abstract class Forrest: Application(), DefaultLifecycleObserver {

    companion object {
        private var instance: Forrest? = null

        fun get(): Forrest {
            return this.instance!!
        }

        fun getApplicationContext(): Context {

            return get().applicationContext!!
        }
    }

    // ForrestService class Objet
    private var forrestService: ForrestService? = null

    // A flag to keep a check on service bind and unbind event.
    var isForrestServiceBound = false

//    private val forrestService: ForrestService = ForrestService()

    override fun onCreate() {
        super<Application>.onCreate()

        // Register to DefaultLifecycleObserver to listen app life cycle changes.
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)


        Logger.d("Forrest create")

        run()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

       Logger.d("App is in foreground")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)

        Logger.d("App is in background")


//TODO Unbind service if we want to close it.

//        if (isForrestServiceBound) {
//            applicationContext.unbindService(boundServiceConnection)
//            isForrestServiceBound = false
//        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Logger.d("Forrest onDestroy()")
    }

    fun run() {
        Logger.d("Forrest run()")
        val forrestServiceIntent = Intent(applicationContext, ForrestService::class.java)
        applicationContext.startForegroundService(forrestServiceIntent)
        applicationContext.bindService(forrestServiceIntent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {

            Logger.d("service listener - service connected")

            // Get forrest service.
            val binderBridge: ForrestService.ForrestServiceBinder = service as ForrestService.ForrestServiceBinder
            forrestService = binderBridge.service

            onForrestConnected(forrestService!!)


//            forrestService?.serviceNotification?.updateNotification("mi")

//            onServiceConnected(forrestService?.serviceNotification!!)
            isForrestServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {

            Logger.d("service listener - service disconnected")

            isForrestServiceBound = false
            forrestService = null
        }
    }

    abstract fun onForrestConnected(forrestService: ForrestService)
}