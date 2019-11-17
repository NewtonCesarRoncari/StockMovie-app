package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("sort", "offset", "pageNumber", "pageSize", "paged", "unpaged")
class Pageable {
    @get:JsonProperty("sort")
    @set:JsonProperty("sort")
    @JsonProperty("sort")
    var sort: Sort? = null
    @get:JsonProperty("offset")
    @set:JsonProperty("offset")
    @JsonProperty("offset")
    var offset: Int? = null
    @get:JsonProperty("pageNumber")
    @set:JsonProperty("pageNumber")
    @JsonProperty("pageNumber")
    var pageNumber: Int? = null
    @get:JsonProperty("pageSize")
    @set:JsonProperty("pageSize")
    @JsonProperty("pageSize")
    var pageSize: Int? = null
    @get:JsonProperty("paged")
    @set:JsonProperty("paged")
    @JsonProperty("paged")
    var paged: Boolean? = null
    @get:JsonProperty("unpaged")
    @set:JsonProperty("unpaged")
    @JsonProperty("unpaged")
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