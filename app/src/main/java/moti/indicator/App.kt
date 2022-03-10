package moti.indicator

import android.app.Application
import android.content.Context
import run.forrest.Forrest
import run.forrest.service.run
import run.forrest.service.ForrestService


import run.forrest.utils.Logger

class App: Application() {

    companion object {
        private var instance: App? = null

        fun get(): App {
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
        super.onCreate()
        instance = this

        Logger.d("App create")

        Forrest(applicationContext){ forrestService ->

            forrestService.setNotificationTitle("bla bla")
        }.run()


//        ForrestService().run(applicationContext)


    }
}