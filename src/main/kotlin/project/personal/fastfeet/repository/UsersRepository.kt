package project.personal.fastfeet.repository

import org.springframework.data.jpa.repository.JpaRepository
import project.personal.fastfeet.entity.UserEntity

interface UsersRepository : JpaRepository<UserEntity, Long> {
    fun existsByCpfOrVehiclePlate(
        cpf: String,
        vehiclePlate: String,
    ): Boolean
}
