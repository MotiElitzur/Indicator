package run.forrest.testing

class Testing {


    /**
     * DataBinding RecyclerView
     */
//    /**
//     * This class contains the possible sites recycler view.
//     */
//    class PossibleSitesAdapter(private val possibleSites: MutableList<PossibleSite>) :
//        RecyclerView.Adapter<PossibleSitesAdapter.ViewHolder>() {
//
//        // region RecyclerView Adapter
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//            // Create item binding.
//            val possibleSiteItemBinding =
//                ItemPossibleSiteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//            return ViewHolder(possibleSiteItemBinding)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//            // Bind current item.
//            holder.bind(possibleSites[position])
//        }
//
//        override fun getItemCount(): Int {
//            return possibleSites.size
//        }
//
//        // endregion
//
//        // region Possible Sites View Holder
//
//        /**
//         * Receive a site name and bind it to possible site view binding.
//         */
//        inner class ViewHolder(private val possibleSiteBinding: ItemPossibleSiteBinding) :
//            RecyclerView.ViewHolder(possibleSiteBinding.root) {
//
//            /**
//             * Receive an item and bind it.
//             */
//            fun bind(possibleSite: PossibleSite){
//
//                // Set possible site.
//                possibleSiteBinding.possibleSite = possibleSite
//
//                // Set a listener for item selected.
//                possibleSiteBinding.possibleSiteRadioBtn.setOnClickListener {
//
//                    // Refresh the last item and the current one.
//
//                    notifyItemChanged(bindingAdapterPosition)
//
//                }
//            }
//        }
//
//        // endregion
//    }






    /**
     * Alarm manager code.
     */
//    private var alarmMgr: AlarmManager? = null
//    private lateinit var alarmIntent: PendingIntent
    //    fun getWakeupIntent(context: Context, wakeupReason: String): Intent {
//       val KEY_INTENT_START_REASON = "start_reason"
//        val intent = Intent(context, ForrestService::class.java)
//        intent.putExtra(KEY_INTENT_START_REASON, wakeupReason)
//        return intent
//    }
//
//    // https://developer.android.com/training/monitoring-device-state/doze-standby#restrictions
//    private fun configureAlarms() {
//
//        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmIntent = getWakeupIntent(this, "Watchdog Pending Intent").let { intent ->
//            PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
//        }
//        pushlishNextWakeupIntent()
//    }
//
////    private fun pushlishWatchDogIntentForNextDay() {
////        pushlishNextWakeupIntent(true)
////    }
//
//    // true = continur running. false = stop running
//    private fun pushlishNextWakeupIntent() {
//        if (alarmMgr == null) {
//            configureAlarms()
//        } else {
//            val nextTime =
//                (System.currentTimeMillis() + (9 * 1000))
//
//
//            if (System.currentTimeMillis() > nextTime) {
//            } else {
//                alarmMgr!!.setExactAndAllowWhileIdle(
//                    AlarmManager.RTC_WAKEUP,
//                    nextTime,
//                    alarmIntent
//                )
//            }
//        }
//    }
//
//    private fun cancelNextWakupIntent() {
//        if (alarmMgr == null) {
//            configureAlarms()

}