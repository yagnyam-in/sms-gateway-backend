package `in`.yagnyam.sms_gateway.web

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.web.context.support.SpringBeanAutowiringSupport

import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet

@Configurable
class AbstractSpringEnabledServlet : HttpServlet() {

    @Throws(ServletException::class)
    override fun init(config: ServletConfig) {
        super.init(config)
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this)
    }
}
