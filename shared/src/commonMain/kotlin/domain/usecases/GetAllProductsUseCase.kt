package domain.usecases

import domain.repository.HomeRepository


class GetAllProductsUseCase (private val homeRepository: HomeRepository) {
    suspend operator fun invoke() = homeRepository.getAllProducts();
}