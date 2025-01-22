package abdulrahman.ali19.di

import abdulrahman.ali19.AppViewModel
import abdulrahman.ali19.screens.aboutme.data.repo.AboutMeRepoImpl
import abdulrahman.ali19.screens.aboutme.domain.repo.AboutMeRepo
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetContactsUseCase
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetEducationUseCase
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetPersonalInformationUseCase
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.AboutViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AppViewModel)
}

val aboutMeModule = module {
    single<AboutMeRepo> { AboutMeRepoImpl() }
    single { GetPersonalInformationUseCase(repo = get()) }
    single { GetContactsUseCase(repo = get()) }
    single { GetEducationUseCase(repo = get()) }
    viewModelOf(::AboutViewModel)
}