package domain.model
import kotlinx.serialization.Serializable


@Serializable
data class ProductsDetailDTO(
    val `data`: ProductDTO? = null,
    val message: String? = null,
    val status: Boolean? = null
)


@Serializable
data class ProductsDTO(
    val `data`: ProductPaginationDTO? = null,
    val message: String? = null,
    val status: Boolean? = null
)

@Serializable
data class ProductPaginationDTO(

    val currentPage: Int?=null,

    val `data`: List<ProductDTO>?=null,

    val firstPageUrl: String?=null,

    val from: Int?=null,

    val lastPage: Int?=null,

    val lastPageUrl: String?=null,

    val nextPageUrl: String?=null,

    val path: String?=null,

    val perPage: Int?=null,

    val prevPageUrl: Int?=null,

    val to: Int?=null,

    val total: Int?=null


)

@Serializable
data class ProductDTO(

    val description: String?=null,

    val discount: Int?=null,

    val id: Int?=null,

    val image: String?=null,

    val images: List<String>?=null,

    val inCart: Boolean?=null,

    val inFavorites: Boolean?=null,

    val name: String?=null,

    val oldPrice: Double?=null,

    val price: Double?=null

)