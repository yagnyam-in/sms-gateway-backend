package `in`.yagnyam.sms_gateway.api


import `in`.yagnyam.sms_gateway.ServiceException
import `in`.yagnyam.sms_gateway.db.*
import `in`.yagnyam.sms_gateway.services.AlertService
import `in`.yagnyam.sms_gateway.services.FirebaseService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.core.MediaType

class Request private constructor() {

    var id: String? = null
        private set
    lateinit var appId: String
        private set
    lateinit var accessToken: String
        private set
    lateinit var phone: String
        private set
    lateinit var message: String
        private set
}

@RestController
@RequestMapping("/api")
class Api @Autowired
constructor(
        private val firebaseService: FirebaseService,
        private val appOwnerRepository: AppOwnerRepository,
        private val customerRepository: CustomerRepository,
        private val appRepository: AppRepository,
        private val requestRepository: RequestRepository,
        private val alertService: AlertService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun sendSMS(@RequestBody rawRequest: Request) {
        firebaseService.initFirebaseApp()
        val ownerId = appOwnerRepository.getOwner(rawRequest.appId)
                ?: throw ServiceException.unauthorized("${rawRequest.appId} is not known or invalid access token")
        val customer = customerRepository.getCustomer(ownerId)
                ?: throw ServiceException.unauthorized("${rawRequest.appId} is not known or invalid access token")
        val app = appRepository.getApp(customer, rawRequest.appId)
                ?: throw ServiceException.unauthorized("${rawRequest.appId} is not known or invalid access token")
        if (app.accessToken != rawRequest.accessToken) {
            throw ServiceException.unauthorized("${rawRequest.appId} is not known or invalid access token")
        }
        val requestEntity = RequestEntity(requestId = rawRequest.id, appId = app.id, phone = rawRequest.phone, message = rawRequest.message)
        requestRepository.saveRequest(customer, requestEntity)
        alertService.notifyThroughFcm(app, requestEntity)
    }


}
