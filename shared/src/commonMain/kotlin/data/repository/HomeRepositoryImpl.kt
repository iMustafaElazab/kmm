package data.repository

import domain.model.BannersDTO
import domain.model.CategoriesDTO
import domain.model.ProductsDTO
import domain.remote.HomeApi
import domain.repository.HomeRepository
import utils.Response


class HomeRepositoryImpl(private val homeApi: HomeApi) : HomeRepository {

    override suspend fun getAllProducts(): Response<ProductsDTO> {

        return when(val result = homeApi.getAllProducts()){
            is Response.Success -> {
                Response.Success(data = result.data)
            }
            is Response.Error -> {
                Response.Error(message = result.message)
            }
        }
    }

    override suspend fun getAllCategory(): Response<CategoriesDTO> {
        return when(val result = homeApi.getAllCategory()){
            is Response.Success -> {
                Response.Success(data = result.data)
            }
            is Response.Error -> {
                Response.Error(message = result.message)
            }
        }
    }

    override suspend fun getAllBanners(): Response<BannersDTO> {
        return when(val result = homeApi.getAllBanners()){
            is Response.Success -> {
                Response.Success(data = result.data)
            }
            is Response.Error -> {
                Response.Error(message = result.message)
            }
        }
    }
}