package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
        "content",
        "pageable",
        "last",
        "totalElements",
        "totalPages",
        "number",
        "size",
        "sort",
        "numberOfElements",
        "first",
        "empty"
)
class MovieRequestList {
        @JsonProperty("content")
        var movieRequest: List<MovieRequest>? = null

        var pageable: Pageable? = null

        var last: Boolean? = null

        var totalElements: Int? = null

        var totalPages: Int? = null

        var number: Int? = null

        var size: Int? = null

        var sort: Sort? = null

        var numberOfElements: Int? = null

        var first: Boolean? = null

        var empty: Boolean? = null
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