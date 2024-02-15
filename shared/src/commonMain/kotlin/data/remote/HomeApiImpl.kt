package data.remote


import domain.model.BannersDTO
import domain.model.CategoriesDTO
import domain.model.ProductsDTO
import domain.remote.HomeApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CancellationException
import utils.Response

class HomeApiImpl (private val httpClient: HttpClient)  : HomeApi {

    private val baseUrl = "https://student.valuxapps.com/api"

    override suspend fun getAllProducts(): Response<ProductsDTO> {
        return try {
            Response.Success(data = httpClient.get {
                url("${baseUrl}/products")
            }.body<ProductsDTO>())
        } catch (e: IOException) {
            Response.Error("Network issue.")
        } catch (e: ClientRequestException) {
            Response.Error("Invalid request.")
        } catch (e: ServerResponseException) {
            Response.Error("Server unavailable.")
        } catch (e: HttpRequestTimeoutException) {
            Response.Error("Request timed out.")
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Response.Error("Unexpected issue occurred.")
        }
    }

    override suspend fun getAllCategory(): Response<CategoriesDTO> {
        return try {
            Response.Success(data = httpClient.get {
                url("${baseUrl}/categories")
            }.body<CategoriesDTO>())
        } catch (e: IOException) {
            Response.Error("Network issue.")
        } catch (e: ClientRequestException) {
            Response.Error("Invalid request.")
        } catch (e: ServerResponseException) {
            Response.Error("Server unavailable.")
        } catch (e: HttpRequestTimeoutException) {
            Response.Error("Request timed out.")
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Response.Error("Unexpected issue occurred.")
        }
    }

    override suspend fun getAllBanners(): Response<BannersDTO> {
        return try {
            Response.Success(data = httpClient.get {
                url("${baseUrl}/banners")
            }.body<BannersDTO>())
        } catch (e: IOException) {
            Response.Error("Network issue.")
        } catch (e: ClientRequestException) {
            Response.Error("Invalid request.")
        } catch (e: ServerResponseException) {
            Response.Error("Server unavailable.")
        } catch (e: HttpRequestTimeoutException) {
            Response.Error("Request timed out.")
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Response.Error("Unexpected issue occurred.")
        }
    }
}