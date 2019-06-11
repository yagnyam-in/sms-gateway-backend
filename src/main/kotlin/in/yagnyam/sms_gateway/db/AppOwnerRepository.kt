package `in`.yagnyam.sms_gateway.db

import `in`.yagnyam.sms_gateway.services.FirebaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class AppOwnerRepository @Autowired constructor(private val firebaseService: FirebaseService) {

    fun getOwner(id: String): String? {
        val snapshot = firebaseService.firestore
                .collection("apps")
                .document(id).get().get()
        if (!snapshot.exists()) {
            return null
        }
        return snapshot.getString("userId")
    }

}
