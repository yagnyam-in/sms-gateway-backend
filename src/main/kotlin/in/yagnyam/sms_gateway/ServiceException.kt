package `in`.yagnyam.sms_gateway

import org.springframework.http.HttpStatus

open class ServiceException : RuntimeException {

    val statusCode: HttpStatus

    protected constructor(statusCode: HttpStatus, message: String) : super(message) {
        this.statusCode = statusCode
    }

    protected constructor(statusCode: HttpStatus, message: String, cause: Throwable) : super(message, cause) {
        this.statusCode = statusCode
    }

    companion object {

        fun badRequest(message: String): BadRequestException {
            return BadRequestException(message)
        }

        fun badRequest(message: String, cause: Throwable): BadRequestException {
            return BadRequestException(message, cause)
        }

        fun notFound(message: String): NotFoundException {
            return NotFoundException(message)
        }

        fun unauthorized(message: String): UnauthorizedException {
            return UnauthorizedException(message)
        }

        fun unauthorized(message: String, cause: Throwable): UnauthorizedException {
            return UnauthorizedException(message, cause)
        }

        fun internalServerError(message: String): InternalServerErrorException {
            return InternalServerErrorException(message)
        }

        fun internalServerError(message: String, cause: Throwable): InternalServerErrorException {
            return InternalServerErrorException(message, cause)
        }

        fun forbidden(message: String): ForbiddenException {
            return ForbiddenException(message)
        }

        fun forbidden(message: String, cause: Throwable): ForbiddenException {
            return ForbiddenException(message, cause)
        }
    }

}

class UnauthorizedException : ServiceException {

    constructor(message: String) : super(HttpStatus.UNAUTHORIZED, message)

    constructor(message: String, cause: Throwable) : super(HttpStatus.UNAUTHORIZED, message, cause)

}


class NotFoundException : ServiceException {

    constructor(message: String) : super(HttpStatus.NOT_FOUND, message)

    constructor(message: String, cause: Throwable) : super(HttpStatus.NOT_FOUND, message, cause)
}

class InternalServerErrorException : ServiceException {

    constructor(message: String) : super(HttpStatus.INTERNAL_SERVER_ERROR, message)

    constructor(message: String, cause: Throwable) : super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause)
}

class ForbiddenException : ServiceException {

    constructor(message: String) : super(HttpStatus.FORBIDDEN, message)

    constructor(message: String, cause: Throwable) : super(HttpStatus.FORBIDDEN, message, cause)

}

class BadRequestException : ServiceException {

    constructor(message: String) : super(HttpStatus.BAD_REQUEST, message)

    constructor(message: String, cause: Throwable) : super(HttpStatus.BAD_REQUEST, message, cause)

}
