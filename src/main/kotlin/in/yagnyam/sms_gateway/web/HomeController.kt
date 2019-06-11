package `in`.yagnyam.sms_gateway.web

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/")
    fun home(model: Model): String {
        log.debug("home")
        return "redirect:http://www.sg.yagnyam.in"
    }

}
