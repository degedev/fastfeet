package project.personal.fastfeet.exception

import project.personal.fastfeet.common.ExceptionMessage

class UserAlreadyExistsException(
    cpf: String,
    vehiclePlate: String,
) : RuntimeException(ExceptionMessage.USER_ALREADY_EXISTS.format(cpf, vehiclePlate))
