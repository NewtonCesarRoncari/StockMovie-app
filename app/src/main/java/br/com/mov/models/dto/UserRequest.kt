package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class UserRequest(val id: Long? = null, val name: String = "", val email: String = "")