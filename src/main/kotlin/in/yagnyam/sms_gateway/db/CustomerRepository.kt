package `in`.yagnyam.sms_gateway.db

import `in`.yagnyam.sms_gateway.services.FirebaseService
import com.google.cloud.firestore.DocumentSnapshot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


data class CustomerEntity(
        val id: String,
        val fcmToken: String? = null
)

@Component
class CustomerRepository @Autowired constructor(private val firebaseService: FirebaseService) {

    fun getCustomer(id: String): CustomerEntity? {
        val snapshot = firebaseService.firestore
                .collection("users")
                .document(id).get().get()
        return if (snapshot.exists()) {
            documentToCustomerEntity(snapshot.id, snapshot)
        } else {
            null
        }
    }

    companion object {

        private fun documentToCustomerEntity(id: String, documentSnapshot: DocumentSnapshot): CustomerEntity {
            return CustomerEntity(id = id, fcmToken = documentSnapshot.getString("fcmToken"))
        }

    }

}
