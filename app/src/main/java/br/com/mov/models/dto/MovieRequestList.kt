package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("content", "pageable", "last", "totalElements", "totalPages", "number", "size", "sort", "numberOfElements", "first", "empty")
class MovieRequestList {
        @get:JsonProperty("content")
        @set:JsonProperty("content")
        @JsonProperty("content")
        var content: List<Content>? = null
        @get:JsonProperty("pageable")
        @set:JsonProperty("pageable")
        @JsonProperty("pageable")
        var pageable: Pageable? = null
        @get:JsonProperty("last")
        @set:JsonProperty("last")
        @JsonProperty("last")
        var last: Boolean? = null
        @get:JsonProperty("totalElements")
        @set:JsonProperty("totalElements")
        @JsonProperty("totalElements")
        var totalElements: Int? = null
        @get:JsonProperty("totalPages")
        @set:JsonProperty("totalPages")
        @JsonProperty("totalPages")
        var totalPages: Int? = null
        @get:JsonProperty("number")
        @set:JsonProperty("number")
        @JsonProperty("number")
        var number: Int? = null
        @get:JsonProperty("size")
        @set:JsonProperty("size")
        @JsonProperty("size")
        var size: Int? = null
        @get:JsonProperty("sort")
        @set:JsonProperty("sort")
        @JsonProperty("sort")
        var sort: Sort_? = null
        @get:JsonProperty("numberOfElements")
        @set:JsonProperty("numberOfElements")
        @JsonProperty("numberOfElements")
        var numberOfElements: Int? = null
        @get:JsonProperty("first")
        @set:JsonProperty("first")
        @JsonProperty("first")
        var first: Boolean? = null
        @get:JsonProperty("empty")
        @set:JsonProperty("empty")
        @JsonProperty("empty")
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