package `in`.yagnyam.sms_gateway.web

import `in`.yagnyam.sms_gateway.web.ActionsController.Companion.ACTIONS_PREFIX
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(ACTIONS_PREFIX)
class ActionsController {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping(value = ["/auth"])
    fun acceptPayment(): String {
        log.info("authentication")
        return "auth"
    }


    companion object {
        const val ACTIONS_PREFIX = "/actions"
    }

}
