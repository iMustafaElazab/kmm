package viewmodel

import domain.model.BannerDTO
import domain.model.CategoryDTO
import domain.model.ProductDTO
import domain.usecases.GetAllBannersUseCase
import com.rickclephas.kmm.viewmodel.stateIn
import domain.usecases.GetAllCategoriesUseCase
import domain.usecases.GetAllProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import utils.Response

class HomeViewModel(
    private val getAllBannersUseCase: GetAllBannersUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {


    private val homeMutable =
        MutableStateFlow<HomeUiState>(HomeUiState.Uninitialized)

    val homeState = homeMutable.asStateFlow().cStateFlow()


    init {
        getBanners()
    }

    private fun getBanners() {
        homeMutable.value = HomeUiState.Loading
        viewModelScope.launch {
            try {
                when (val result = getAllBannersUseCase.execute(Unit)) {
                    is Response.Error -> {
                        homeMutable.value = HomeUiState.Error(result.message)
                    }

                    is Response.Success -> {
                        homeMutable.value = HomeUiState.Success(result.data.data!!)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                homeMutable.value = HomeUiState.Error(e.message.orEmpty())
            }
        }
    }


//    private fun getCategories() {
//        viewModelScope.coroutineScope.launch {
//            _homeUiState.update {
//                it?.copy(isCategoriesLoading = true, categoriesError = "") // Clear previous error
//            }
//            try {
//                when (val result = getAllCategoriesUseCase.invoke()) {
//                    is Response.Error -> {
//                        _homeUiState.update {
//                            it?.copy(
//                                isCategoriesLoading = false,
//                                categoriesError = result.message ?: "Unknown error"
//                            )
//                        }
//                    }
//
//                    is Response.Success -> {
//                        result.data?.data?.data?.let { categories ->
//                            _homeUiState.update {
//                                it?.copy(isCategoriesLoading = false, categories = categories)
//                            }
//                        } ?: run {
//                            _homeUiState.update {
//                                it?.copy(
//                                    isCategoriesLoading = false,
//                                    categoriesError = "Data is null or empty"
//                                )
//                            }
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                _homeUiState.update {
//                    it?.copy(isCategoriesLoading = false, categoriesError = "Unexpected error")
//                }
//            }
//        }
//    }
//
//    private fun getProducts() {
//        viewModelScope.coroutineScope.launch {
//            _homeUiState.update { state ->
//                println("State: $state")
//                state?.copy(
//                    isProductsLoading = true,
//                    productsError = "" // Clear previous error if any
//                )
//            }
//            try {
//                val result = getAllProductsUseCase.invoke()
//                when (result) {
//                    is Response.Error -> {
//                        _homeUiState.update { state ->
//                            println("State: $state")
//                            state?.copy(
//                                isProductsLoading = false,
//                                productsError = result.message ?: "Unknown error"
//                            )
//                        }
//                    }
//
//                    is Response.Success -> {
//                        result.data?.data?.data.let { products ->
//                            _homeUiState.update { state ->
//                                println("State: $state")
//                                state?.copy(
//                                    isProductsLoading = false,
//                                    products = products!!
//                                )
//                            }
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                // Consider specific exception handling here
//                _homeUiState.update { state ->
//                    println("State: $state")
//                    state?.copy(
//                        isProductsLoading = false,
//                        productsError = "Unexpected error",
//                    )
//                }
//            }
//        }
//    }
}

//data class HomeUiState(
//    val products: List<ProductDTO?> = emptyList(),
//    val isProductsLoading: Boolean = false,
//    val productsError: String = "",
//    val banners: List<BannerDTO?> = emptyList(),
//    var isBannersLoading: Boolean = false,
//    val bannersError: String = "",
//    val categories: List<CategoryDTO?> = emptyList(),
//    val isCategoriesLoading: Boolean = false,
//    val categoriesError: String = "",
//)

sealed interface HomeUiState {
    data class Success(
        val data:
        List<BannerDTO?>
    ) : HomeUiState

    data class Error(val exceptionMessage: String) : HomeUiState
    data object Loading : HomeUiState
    data object Uninitialized : HomeUiState
}