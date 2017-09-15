package baby.internal.cosmo.fr.babyscore

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class HumiliationService : FirebaseMessagingService() {
    companion object {
        val TAG: String = HumiliationService::class.java.canonicalName
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "Remote message " + remoteMessage)
    }
}
