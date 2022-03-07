package run.forrest.testing

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import run.forrest.App

class ForrestWorker (appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {


    override fun doWork(): Result {

        App.get().startForrestService()

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}