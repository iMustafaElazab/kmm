package di

import co.touchlab.skie.configuration.annotations.DefaultArgumentInterop
import data.remote.HomeApiImpl
import data.repository.HomeRepositoryImpl
import domain.remote.HomeApi
import domain.repository.HomeRepository
import domain.usecases.GetAllBannersUseCase
import domain.usecases.GetAllCategoriesUseCase
import domain.usecases.GetAllProductsUseCase
import io.ktor.client.HttpClient
import org.koin.dsl.module
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import org.koin.core.context.startKoin
import org.koin.core.module.Module


private const val TIMEOUT = 60_000L

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP:status:=>${response.status.value}")
            }
        }
            install(HttpTimeout) {
                connectTimeoutMillis = TIMEOUT
                requestTimeoutMillis = TIMEOUT
                socketTimeoutMillis = TIMEOUT
            }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    }

    single<HomeApi> { HomeApiImpl(httpClient = get()) }
    single<HomeRepository> { HomeRepositoryImpl(homeApi = get()) }
    factory { GetAllProductsUseCase(homeRepository = get()) }
    factory { GetAllCategoriesUseCase(homeRepository = get()) }
    factory { GetAllBannersUseCase(homeRepository = get()) }
}

@DefaultArgumentInterop.Enabled
fun initKoin(modules: List<Module> = emptyList()) {
    startKoin {
        modules(
            dataModule,
            *modules.toTypedArray(),
        )
    }
}