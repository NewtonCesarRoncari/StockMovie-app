package br.com.mov.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import br.com.mov.models.dto.UserRequest
import java.math.BigDecimal

@Entity(tableName = "user")
class User(
        @PrimaryKey(autoGenerate = false)
        var id: Long? = null,
        var name: String = "",
        var email: String = "",
        @Ignore
        val password: String = "",
        @Ignore
        val balance: BigDecimal = BigDecimal.ZERO) {

    constructor(user: UserRequest) : this(
            id = user.id,
            name = user.name,
            email = user.email,
            balance = user.balance
    )
}

