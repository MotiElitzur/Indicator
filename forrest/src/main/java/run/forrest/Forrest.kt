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
import run.forrest.utils.OnForrestServiceStart

/**
 * The forrest service helper.
 */
class Forrest(val context: Context, onServiceConnected: (ForrestService) -> Unit): DefaultLifecycleObserver {

    // ForrestService class Objet
    var forrestService: ForrestService? = null

    // A flag to keep a check on service bind and unbind event.
    var isForrestServiceBound = false

//    private val forrestService: ForrestService = ForrestService()

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Logger.d("Forrest onStart()")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (isForrestServiceBound) {
            context.unbindService(boundServiceConnection)
            isForrestServiceBound = false
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Logger.d("Forrest onDestroy()")
    }

    fun run() {

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        Logger.d("Forrest run()")
        val forrestServiceIntent: Intent = Intent(context, ForrestService::class.java)
        context.startForegroundService(forrestServiceIntent)
        context.bindService(forrestServiceIntent, boundServiceConnection, Application.BIND_AUTO_CREATE)

//        forrestService.run(context)
    }

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge: ForrestService.ForrestServiceBinder = service as ForrestService.ForrestServiceBinder
            forrestService = binderBridge.service

            onServiceConnected(forrestService!!)
            isForrestServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isForrestServiceBound = false
            forrestService = null
        }
    }
}