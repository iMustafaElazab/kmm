package domain.remote

import domain.model.BannersDTO
import domain.model.CategoriesDTO
import domain.model.ProductsDTO
import utils.Response

interface HomeApi {
    suspend fun getAllProducts() : Response<ProductsDTO>
    suspend fun getAllCategory() : Response<CategoriesDTO>
    suspend fun getAllBanners() : Response<BannersDTO>
}