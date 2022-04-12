//package run.forrest.utils
//
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.preference.PreferenceManager
//import androidx.core.content.edit
//
//import java.util.*
//
//
//object PersistentData {
//    private const val PREF_KEY_CONFIGURATION_SET_NUMBER = "configuration_set_number"
//
//
//
//    fun getPrefs(): SharedPreferences {
//
//        return PreferenceManager.getDefaultSharedPreferences(App.get())
//    }
//
//    fun getConfigurationSetNumber(): Int {
//        return getPrefs().getInt(PREF_KEY_CONFIGURATION_SET_NUMBER, 1)
//
//    }
//
//    fun setConfigurationSetNumber(setNumber: Int) {
//        return getPrefs().edit {
//            putInt(PREF_KEY_CONFIGURATION_SET_NUMBER, setNumber)
//        }
//    }
//
//    fun clearWrongSite() {
//        getPrefs().edit {
//            remove(PREF_KEY_WRONG_BEAONCON_TIMESTAMP)
//        }
//    }
//
//
//    fun getSafetyNotificationIds(): List<String>? {
//        return getPrefs()
//            .getString(PREF_KEY_NOTIFICATION_SAFETY_ALERT_IDS, "")
//            ?.split(';')
//    }
//
//    fun setSafetyNotificationIds(ids: List<String>) {
//        return getPrefs().edit {
//            putString(PREF_KEY_NOTIFICATION_SAFETY_ALERT_IDS, ids.joinToString(";"))
//        }
//    }
//
//    fun getScanDurationSeconds(): Int {
//        return getPrefs().getInt(PREF_KEY_SCAN_DURATION, 15) //30
//    }
//
//    fun getSleepDurationBetweenScansSeconds(): Int {
//        return getPrefs().getInt(PREF_KEY_DURATION_BETWEEN_SCANS, 1) * 60
//        //return 30
//    }
//
//    @Deprecated("Use getSleepDurationBetweenScansSeconds")
//    fun getDurationBetweenScansMinutes(): Int {
//        return getPrefs().getInt(PREF_KEY_DURATION_BETWEEN_SCANS, 1)
//    }
//
//    fun saveDurationBetweenScannsMinutes(minutes: Int) {
//        getPrefs().edit {
//            putInt(PREF_KEY_DURATION_BETWEEN_SCANS, minutes)
//        }
//    }
//
//    fun saveScanDuration(seconds: Int) {
//        getPrefs().edit {
//            putInt(PREF_KEY_SCAN_DURATION, seconds)
//        }
//    }
//
//    fun getBackgroundTrackingEnabled(): Boolean {
//        return getPrefs().getBoolean(PREF_KEY_BG_TRACKING_ENABLED, true)
//    }
//
//    fun saveBackgroundTrackingEnabled(isEnable: Boolean) {
//        return getPrefs().edit {
//            putBoolean(PREF_KEY_BG_TRACKING_ENABLED, isEnable)
//        }
//    }
//
//    fun saveBeconsMeasuredPower(power: Int) {
//        return getPrefs().edit {
//            putInt(PREF_KEY_BEACON_POWER, power)
//        }
//
//    }
//
//    fun getBeconsMeasuredPower(): Int {
//        return getPrefs().getInt(PREF_KEY_BEACON_POWER, -60)
//    }
//
//    fun saveBeconsEnvironmentalFactor(factor: Int) {
//        return getPrefs().edit {
//            putInt(PREF_KEY_BEACON_ENV_FACTOR, factor)
//        }
//    }
//
//    fun getBeconsEnvironmentalFactor(): Int {
//        return getPrefs().getInt(PREF_KEY_BEACON_ENV_FACTOR, 2)
//    }
//
//    fun getSiteyeDisplayTolerancMS(): Long {
//        return 600 * 60 * 1000 //One hour
//    }
//}