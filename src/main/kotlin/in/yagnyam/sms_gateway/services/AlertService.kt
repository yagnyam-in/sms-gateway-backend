package `in`.yagnyam.sms_gateway.services

import `in`.yagnyam.sms_gateway.db.AppEntity
import `in`.yagnyam.sms_gateway.db.RequestEntity
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AlertService @Autowired
constructor(private val firebaseService: FirebaseService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun notifyThroughFcm(app: AppEntity, request: RequestEntity) {
        logger.debug("notify(app: $app, request: $request)")
        val fcmToken = app.fcmToken ?: return
        val message = Message.builder()
                .setToken(fcmToken)
                .putAllData(request.toJson())
                .build()
        notify(message)
    }

    private fun notify(message: Message) {
        try {
            firebaseService.initFirebaseApp()
            val response = FirebaseMessaging.getInstance().send(message)
            logger.info("Sent {} with response {}", message, response)
        } catch (e: FirebaseMessagingException) {
            logger.error("Error sending Notification", e)
        }

    }

}
