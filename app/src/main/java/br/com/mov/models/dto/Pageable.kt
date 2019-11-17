package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("sort", "offset", "pageNumber", "pageSize", "paged", "unpaged")
class Pageable {

    var sort: Sort? = null

    var offset: Int? = null

    var pageNumber: Int? = null

    var pageSize: Int? = null

    var paged: Boolean? = null

    var unpaged: Boolean? = null
    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = HashMap()

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}