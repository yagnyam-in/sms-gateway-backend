package `in`.yagnyam.sms_gateway.services

import `in`.yagnyam.sms_gateway.ServiceException
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletContext


@Component
open class FirebaseService @Autowired
constructor(private val servletContext: ServletContext) {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val firebaseAppLock = Any()

    private var firebaseApp: FirebaseApp? = null

    private val threadLocalFirestore: ThreadLocal<Firestore> = ThreadLocal.withInitial {
        FirestoreOptions.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(fetchCredentials()))
                .setTimestampsInSnapshotsEnabled(true)
                .build().service
    }

    val firestore: Firestore
        get() = threadLocalFirestore.get()

    private fun fetchCredentials(): GoogleCredentials {
        val credentials = servletContext.getResourceAsStream("/yagnyam-sms-gateway-fcm-admin.json")
        return GoogleCredentials.fromStream(credentials)
    }

    private fun firebaseOptions(): FirebaseOptions {
        return try {
            FirebaseOptions.Builder()
                    .setCredentials(fetchCredentials())
                    .setDatabaseUrl("https://yagnyam-sms-gateway.firebaseio.com/")
                    .build()
        } catch (e: IOException) {
            logger.error("Failed to initialize firebase", e)
            throw ServiceException.internalServerError("Failed to initialize firebase", e)
        }
    }

    fun initFirebaseApp() {
        if (firebaseApp != null) {
            return
        }
        synchronized(firebaseAppLock) {
            if (firebaseApp == null) {
                logger.info("Initializing Firebase App")
                firebaseApp = FirebaseApp.initializeApp(firebaseOptions())
            }
        }
    }

}
