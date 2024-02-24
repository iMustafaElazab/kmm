package domain.usecases

import domain.model.BannersDTO
import domain.repository.HomeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.Response


class GetAllBannersUseCase : BaseUseCase<Unit, Response<BannersDTO>>(), KoinComponent {
    private val homeRepository: HomeRepository by inject()
    override suspend fun execute(request: Unit): Response<BannersDTO> =
        homeRepository.getAllBanners()

}