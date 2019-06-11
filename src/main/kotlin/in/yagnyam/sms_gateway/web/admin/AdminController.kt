package `in`.yagnyam.sms_gateway.web.admin


import com.google.appengine.api.users.UserServiceFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminController {

    @GetMapping("/admin")
    fun admin(model: Model): String {
        addAdminAttributes(model)
        return "admin/admin"
    }

    companion object {

        fun addAdminAttributes(model: Model) {
            model.addAttribute("logoutUrl", UserServiceFactory.getUserService().createLogoutURL("/"))
        }
    }
}
