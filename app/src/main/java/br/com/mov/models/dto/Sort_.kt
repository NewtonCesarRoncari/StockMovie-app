package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("sorted", "unsorted", "empty")
class Sort_ {
    @get:JsonProperty("sorted")
    @set:JsonProperty("sorted")
    @JsonProperty("sorted")
    var sorted: Boolean? = null
    @get:JsonProperty("unsorted")
    @set:JsonProperty("unsorted")
    @JsonProperty("unsorted")
    var unsorted: Boolean? = null
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