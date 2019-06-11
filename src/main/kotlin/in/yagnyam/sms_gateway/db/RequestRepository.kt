package `in`.yagnyam.sms_gateway.db

import `in`.yagnyam.sms_gateway.services.FirebaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

data class RequestEntity(
        val appId: String, val phone: String, val message: String, var uid: String? = null, var requestId: String? = null
) {
    fun toJson(): Map<String, String?> {
        return mapOf(
                "uid" to uid,
                "requestId" to requestId,
                "appId" to appId,
                "phone" to phone,
                "message" to message
        ).filter { (_, value) -> value != null }
    }
}

@Component
class RequestRepository @Autowired constructor(private val firebaseService: FirebaseService) {

    fun saveRequest(customer: CustomerEntity, request: RequestEntity) {
        val ref = firebaseService.firestore
                .collection("users")
                .document(customer.id)
                .collection("pending-requests")
                .document()
        ref.set(request.copy(uid = ref.id).toJson())
    }

}
