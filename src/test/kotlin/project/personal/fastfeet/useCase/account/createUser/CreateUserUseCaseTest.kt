package project.personal.fastfeet.useCase.account.createUser

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import project.personal.fastfeet.entity.UserEntity
import project.personal.fastfeet.exception.UserAlreadyExistsException
import project.personal.fastfeet.repository.UsersRepository

internal class CreateUserUseCaseTest {
    private val usersRepository = mockk<UsersRepository>()
    private val subject = CreateUserUseCase(usersRepository)

    @Test
    fun `Should create user with given params`() {
        val input = buildCreateUserInput()
        val userEntity = UserEntity(
            cpf = input.cpf,
            fullName = input.fullName,
            password = input.password,
            latitude = input.latitude,
            longitude = input.longitude,
            vehiclePlate = input.vehiclePlate,
        )
        every { usersRepository.existsByCpfOrVehiclePlate(input.cpf, input.vehiclePlate) } returns false
        every { usersRepository.save(any<UserEntity>()) } returns userEntity
        val subjectOutput = subject.execute(input)
        subjectOutput.cpf shouldBe input.cpf
        subjectOutput.roleName shouldBe "DELIVERY_PERSON"
    }

    @Test
    fun `Should throw UserAlreadyExistsException with expected message if user already exists`() {
        val input = buildCreateUserInput()
        every { usersRepository.existsByCpfOrVehiclePlate(input.cpf, input.vehiclePlate) } returns true
        val exception = shouldThrowExactly<UserAlreadyExistsException> { subject.execute(input) }
        exception.message shouldBe
            "User with cpf '${input.cpf}' or vehicle plate '${input.vehiclePlate}' already exists"
    }

    private fun buildCreateUserInput() =
        CreateUserInput(
            cpf = "12345678910",
            fullName = "John Doe",
            password = "123456",
            vehiclePlate = "ABC1234",
            latitude = -22.951804,
            longitude = -43.210760,
        )
}
