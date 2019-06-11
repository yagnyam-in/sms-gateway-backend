package `in`.yagnyam.sms_gateway.db

import `in`.yagnyam.sms_gateway.services.FirebaseService
import com.google.cloud.firestore.DocumentSnapshot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


data class AppEntity(
        val id: String,
        val accessToken: String? = null,
        val fcmToken: String? = null
)

@Component
class AppRepository @Autowired constructor(private val firebaseService: FirebaseService) {

    fun getApp(owner: CustomerEntity, id: String): AppEntity? {
        val snapshot = firebaseService.firestore
                .collection("users")
                .document(owner.id)
                .collection("apps")
                .document(id).get().get()
        return if (snapshot.exists()) {
            return documentToAppEntity(snapshot).copy(fcmToken = owner.fcmToken)
        } else {
            null
        }
    }

    companion object {

        private fun documentToAppEntity(documentSnapshot: DocumentSnapshot): AppEntity {
            return AppEntity(
                    id = documentSnapshot.id,
                    accessToken = documentSnapshot.getString("accessToken")
            )
        }

    }

}
