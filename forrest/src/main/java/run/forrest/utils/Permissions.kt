package run.forrest.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissions {

    // region Permissions

    // endregion


//    // Only the second tim we ask for the permission we will askfor the background location permission
//     fun requestPermissions(onPermissionResults: () -> Unit) {
//        val permissionsToAsk = mutableListOf(
//            Manifest.permission.BLUETOOTH,
//            Manifest.permission.BLUETOOTH_ADMIN,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        )
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            permissionsToAsk.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
//        }
//
//        // Check android12+
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            permissionsToAsk.add(Manifest.permission.BLUETOOTH_SCAN)
//            permissionsToAsk.add(Manifest.permission.BLUETOOTH_CONNECT)
//            permissionsToAsk.add(Manifest.permission.BLUETOOTH_ADVERTISE)
//        }
//
//        val permissionNeeded: MutableList<String> = mutableListOf()
//        var showPermissionRational = false
//
//        for (permissionName in permissionsToAsk) {
//
//            if (ContextCompat.checkSelfPermission(requireContext(), permissionName)
//                != PackageManager.PERMISSION_GRANTED) {
//
//                permissionNeeded.add(permissionName)
//
//                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissionName)) {
//                    showPermissionRational = true
//                } else {
//                    // Logger.d("SHAI!!!! NOT PERMISSIION RATIONAL")
//                }
//            }
//        }
//
//        //Caution: If your app targets Android 11 (API level 30) or higher, the system enforces this best practice. If you request a foreground location permission and the background location permission at the same time, the system ignores the request and doesn't grant your app either permission.
//        if (permissionNeeded.contains(Manifest.permission.ACCESS_BACKGROUND_LOCATION) &&
//            permissionNeeded.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//            //Cannot grant android.permission.ACCESS_BACKGROUND_LOCATION as the matching foreground permission is not already granted.
//            permissionNeeded.remove(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
//        }
//
//        if (permissionNeeded.isNotEmpty()) {
//            if (showPermissionRational) {
//                showRationaleDialog(
//                    getString(R.string.android_permissions_rationale_title),
//                    getString(R.string.android_permissions_rationale_description),
//                    permissionNeeded.toTypedArray(),
//                    MY_PERMISSIONS_REQUEST_CODE
//                )
//            } else {
//                // No explanation needed, we can request the permission.
//                requestPermissions(
//                    permissionNeeded.toTypedArray(),
//                    MY_PERMISSIONS_REQUEST_CODE
//                )
//            }
//        } else {
//            onPermissionResults()
//        }
//    }
}