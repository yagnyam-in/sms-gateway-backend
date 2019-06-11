package `in`.yagnyam.sms_gateway.api


import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.core.MediaType

/**
 *
 * TODO: Implement health check logic
 */
@RestController
@RequestMapping("/health-check")
class HealthCheckApi @Autowired
constructor() {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping(produces = [MediaType.TEXT_PLAIN])
    fun healthCheck(): String {
        return "GOOD"
    }

}
