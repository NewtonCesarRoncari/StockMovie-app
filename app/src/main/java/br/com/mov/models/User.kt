package br.com.mov.models

import br.com.mov.models.dto.UserRequest

class User(val id: Long? = null,
           val name: String = "",
           val email: String = "",
           val password: String = "") {

    constructor(user: UserRequest) : this(
            id = user.id,
            name = user.name,
            email = user.email
    )
}

