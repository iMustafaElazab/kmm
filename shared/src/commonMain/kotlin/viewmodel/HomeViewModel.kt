package viewmodel

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import domain.model.BannerDTO
import domain.model.CategoryDTO
import domain.model.ProductDTO
import domain.usecases.GetAllBannersUseCase
import domain.usecases.GetAllCategoriesUseCase
import domain.usecases.GetAllProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.Response

class HomeViewModel (private val getAllBannersUseCase: GetAllBannersUseCase,
                     private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
                     private val getAllProductsUseCase: GetAllProductsUseCase) : KMMViewModel() {



    private val _homeUiState = MutableStateFlow<HomeUiState?>(null)
    val homeUiState get()  = _homeUiState.asStateFlow()


    init {
        getBanners()
        getCategories()
        getProducts()
    }

    private fun getBanners(){
        viewModelScope.coroutineScope.launch {
            _homeUiState.update { HomeUiState(isBannersLoading = true) }
            try {
                val result = getAllCategoriesUseCase.invoke()
                when (result) {
                    is Response.Error -> {
                        _homeUiState.emit(
                            HomeUiState(
                                isCategoriesLoading = false,
                                categoriesError = result.message ?: "Unknown error"
                            )
                        )
                    }
                    is Response.Success -> {
                        result.data?.data?.data?.let { categories ->
                            _homeUiState.emit(HomeUiState(isCategoriesLoading = false, categories = categories))
                        } ?: run {
                            _homeUiState.emit(
                                HomeUiState(
                                    isCategoriesLoading = false,
                                    categoriesError = "Data is null or empty"
                                )
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _homeUiState.emit(HomeUiState(isCategoriesLoading = false, categoriesError = "Unexpected error"))
            }
        }
    }
    private fun getCategories() {
        viewModelScope.coroutineScope.launch {
            _homeUiState.update { HomeUiState(isCategoriesLoading = true) }
            try {
                val result = getAllBannersUseCase.invoke()
                when (result) {
                    is Response.Error -> {
                        _homeUiState.emit(
                            HomeUiState(
                                isBannersLoading = false,
                                bannersError = result.message ?: "Unknown error"
                            )
                        )
                    }
                    is Response.Success -> {
                        result.data?.data?.let { banners ->
                            _homeUiState.emit(HomeUiState(isCategoriesLoading = false, banners = banners))
                        } ?: run {
                            _homeUiState.emit(
                                HomeUiState(
                                    isBannersLoading = false,
                                    bannersError = "Data is null or empty"
                                )
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _homeUiState.emit(HomeUiState(isBannersLoading = false, bannersError = "Unexpected error"))
            }
        }
    }

    private fun getProducts(){
        viewModelScope.coroutineScope.launch {
            _homeUiState.update { HomeUiState(isProductsLoading = true) }
            try {
                val result = getAllProductsUseCase.invoke()
                when (result) {
                    is Response.Error -> {
                        _homeUiState.emit(
                            HomeUiState(
                                isProductsLoading = false,
                                productsError = result.message ?: "Unknown error"
                            )
                        )
                    }
                    is Response.Success -> {
                        result.data?.data?.data?.let { products ->
                            _homeUiState.emit(HomeUiState(isProductsLoading = false, products = products))
                        } ?: run {
                            _homeUiState.emit(
                                HomeUiState(
                                    isProductsLoading = false,
                                    productsError = "Data is null or empty"
                                )
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _homeUiState.emit(HomeUiState(isProductsLoading = false, productsError = "Unexpected error"))
            }
        }
    }
}

data class HomeUiState(
    val products: List<ProductDTO?> = emptyList(),
    val isProductsLoading: Boolean = false,
    val productsError: String = "",
    val banners: List<BannerDTO?> = emptyList(),
    var isBannersLoading: Boolean = false,
    val bannersError: String = "",
    val categories: List<CategoryDTO?> = emptyList(),
    val isCategoriesLoading: Boolean = false,
    val categoriesError: String = "",
)