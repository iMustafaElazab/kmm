package domain.usecases

import domain.repository.HomeRepository


class GetAllBannersUseCase (private val homeRepository: HomeRepository) {
    suspend operator fun invoke() = homeRepository.getAllBanners();
}