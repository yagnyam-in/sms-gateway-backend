package `in`.yagnyam.sms_gateway

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ServiceExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(BadRequestException::class, NotFoundException::class, UnauthorizedException::class, InternalServerErrorException::class)
    fun handleServiceException(ex: ServiceException): ResponseEntity<String> {
        logger.info("Exception processing request - " + ex.message, ex)
        return ResponseEntity(ex.statusCode.reasonPhrase, ex.statusCode)
    }


    @ExceptionHandler(kotlin.UninitializedPropertyAccessException::class)
    fun handleDeserializationExceptions(ex: Exception): ResponseEntity<String> {
        logger.info("Exception processing request - " + ex.message, ex)
        return ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST)
    }

}
