package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.*
import java.math.BigDecimal
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
        "id",
        "title",
        "originalTitle",
        "overview",
        "pictureUrl",
        "studio",
        "country",
        "releaseDate",
        "status",
        "tagLine",
        "voteAverage",
        "voteCount",
        "budget",
        "originalLanguage",
        "revenue",
        "popularity",
        "runtime",
        "stockPrice",
        "quantityAvailable",
        "amount",
        "cast",
        "genres",
        "production"
)
class MovieFullRequest {

    var id: Long? = null

    var title: String = ""

    var originalTitle: String = ""

    var overview: String = ""

    var pictureUrl: String = ""

    var studio: String = ""

    var country: String = ""

    var releaseDate: String = ""

    var status: String = ""

    var tagLine: Any? = null

    var voteAverage: Double = 0.0

    var voteCount: Int = 0

    var budget: BigDecimal? = null

    var originalLanguage: String = ""

    var revenue: BigDecimal? = null

    var popularity: Int = 0

    var runtime: Int = 0

    var stockPrice: BigDecimal? = null

    var quantityAvailable: Long? = null

    var amount: BigDecimal = BigDecimal.ZERO

    var cast: List<Cast>? = null

    var genres: List<Genre>? = null

    var production: List<Production>? = null
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