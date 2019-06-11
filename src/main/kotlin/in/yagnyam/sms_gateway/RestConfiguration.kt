package `in`.yagnyam.sms_gateway

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.FilterChainProxy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

import javax.servlet.Filter
import java.util.Collections
import java.util.LinkedList

@Configuration
open class RestConfiguration {

    @Bean
    open fun springFilterChain(): FilterChainProxy {
        val filterChain = LinkedList<SecurityFilterChain>()
        filterChain.add(createSecurityFilterChain("/api/**", springFilterChainForAllList()))
        return FilterChainProxy(filterChain)
    }

    @Bean
    open fun springFilterChainForAllList(): List<Filter> {
        return emptyList()
    }

    private fun createSecurityFilterChain(pattern: String,
                                          filters: List<Filter>): DefaultSecurityFilterChain {
        return DefaultSecurityFilterChain(AntPathRequestMatcher(pattern), filters)
    }


}
