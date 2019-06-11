package `in`.yagnyam.sms_gateway

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext

object AppServerStartup {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun setup(ctx: ApplicationContext) {
        logger.info("Setting up Server")
    }

}
