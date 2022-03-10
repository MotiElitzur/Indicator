package run.forrest

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class ForrestApp : Application(){

    companion object {
        private var instance: ForrestApp? = null

        fun get(): ForrestApp {
            return this.instance!!
        }

        fun getApplicationContext(): Context {

            return get().applicationContext!!
        }
    }

    /**
     * This method will be called after the Application onCreate method returns.
     */
    override fun onCreate() {
        super<Application>.onCreate()
        instance = this

//        startForrestService()
    }


//    fun startForrestService() {
//        startForegroundService(Intent(this, Forrest().javaClass))
//        Log.e("Forrest","All Over Again")
//    }
}