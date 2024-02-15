package domain.usecases

import domain.repository.HomeRepository


class GetAllCategoriesUseCase (private val homeRepository: HomeRepository){
    suspend operator fun invoke() = homeRepository.getAllCategory()
}