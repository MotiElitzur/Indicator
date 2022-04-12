package moti.indicator


import run.forrest.Forrest
import run.forrest.service.ForrestService
import run.forrest.utils.Logger

class App : Forrest() {

    /**
     * This method will be called after the Application onCreate method returns.
     */
    override fun onCreate() {
        super.onCreate()

        Logger.d("App create")


//        forrestService.updateNotification("sdf")

//        Forrest(applicationContext){ forrestService ->
//
//            forrestService.updateNotification("sdf")
//
////            forrestService.setNotificationTitle("bla bla")
//        }.run()


//        ForrestService().run(applicationContext)


    }


    override fun onForrestConnected(forrestService: ForrestService){

        Logger.d("App - onForrestConnected")

        forrestService.serviceNotification?.updateNotification("326")
    }
}