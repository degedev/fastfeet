package project.personal.fastfeet.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import project.personal.fastfeet.entity.enumeration.UserRole
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    val cpf: String,
    @Column(name = "full_name", nullable = false, length = 100)
    val fullName: String,
    @Column(name = "password", nullable = false)
    val password: String,
    @Column(name = "latitude", nullable = false)
    val latitude: Double,
    @Column(name = "longitude", nullable = false)
    val longitude: Double,
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false, length = 15)
    val role: UserRole = UserRole.DELIVERY_PERSON,
    @Column(name = "vehicle_plate", unique = true, length = 7)
    val vehiclePlate: String? = null,
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,
) {
    @Override
    override fun toString(): String = this::class.simpleName + "(id = $id)"
}
