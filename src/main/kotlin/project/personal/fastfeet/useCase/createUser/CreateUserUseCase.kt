package project.personal.fastfeet.useCase.createUser

import project.personal.fastfeet.entity.UserEntity
import project.personal.fastfeet.exception.UserAlreadyExistsException
import project.personal.fastfeet.repository.UsersRepository

class CreateUserUseCase(
    private val usersRepository: UsersRepository,
) {
    fun execute(input: CreateUserInput): CreateUserOutput =
        assertUserDoesNotExists(input.cpf, input.vehiclePlate)
            .let { usersRepository.save(input.toUserEntity()) }
            .toCreateUserOutput()

    private fun assertUserDoesNotExists(
        cpf: String,
        vehiclePlate: String,
    ) {
        if (usersRepository.existsByCpfOrVehiclePlate(cpf, vehiclePlate)) {
            throw UserAlreadyExistsException(cpf, vehiclePlate)
        }
    }

    private fun CreateUserInput.toUserEntity() =
        UserEntity(
            cpf = cpf,
            fullName = fullName,
            password = password,
            latitude = latitude,
            longitude = longitude,
            vehiclePlate = vehiclePlate,
        )

    private fun UserEntity.toCreateUserOutput() =
        CreateUserOutput(
            cpf = cpf,
            roleName = role.name,
        )
}
