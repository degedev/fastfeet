package project.personal.fastfeet.useCase.createUser

data class CreateUserInput(
    val cpf: String,
    val fullName: String,
    val password: String,
    val vehiclePlate: String,
    val latitude: Double,
    val longitude: Double,
)
