package `in`.yagnyam.sms_gateway

import com.google.appengine.api.utils.SystemProperty
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication
@ServletComponentScan
open class SmsGatewayApplication {


    @Bean
    open fun commandLineRunner(ctx: ApplicationContext): CommandLineRunner {
        return CommandLineRunner {
            val environment = SystemProperty.environment.value()
            // environment is not set during testing
            if (environment != null) {
                if (environment == SystemProperty.Environment.Value.Development && "true".equals(System.getenv("DEV_APP_SERVER"))) {
                    DevAppServerStartup.setup(ctx)
                }
                AppServerStartup.setup(ctx)
            }
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(SmsGatewayApplication::class.java, *args)
        }
    }
}
