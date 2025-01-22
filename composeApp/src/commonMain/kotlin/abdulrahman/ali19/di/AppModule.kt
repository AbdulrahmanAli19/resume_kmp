package abdulrahman.ali19.di

import abdulrahman.ali19.AppViewModel
import abdulrahman.ali19.screens.aboutme.data.repo.AboutMeRepoImpl
import abdulrahman.ali19.screens.aboutme.domain.repo.AboutMeRepo
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetContactsUsecase
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetPersonalInformationUsecase
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.AboutViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AppViewModel)
}

val aboutMeModule = module {
    single<AboutMeRepo> { AboutMeRepoImpl() }
    single { GetPersonalInformationUsecase(repo = get()) }
    single { GetContactsUsecase(repo = get()) }
    viewModel { AboutViewModel(getPersonalInformationUsecase = get(), getContactsUsecase = get()) }
}