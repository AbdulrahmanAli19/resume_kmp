package abdulrahman.ali19.di

import abdulrahman.ali19.AppViewModel
import abdulrahman.ali19.screens.aboutme.data.repo.AboutMeRepoImpl
import abdulrahman.ali19.screens.aboutme.domain.repo.AboutMeRepo
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetContactsUseCase
import abdulrahman.ali19.screens.education.domain.usecase.GetEducationUseCase
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetPersonalInformationUseCase
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.AboutViewModel
import abdulrahman.ali19.screens.education.data.repo.EducationRepoImpl
import abdulrahman.ali19.screens.education.domain.repo.EducationRepo
import abdulrahman.ali19.screens.education.ui.viewmodel.EducationViewmodel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AppViewModel)
}

val aboutMeModule = module {
    single<AboutMeRepo> { AboutMeRepoImpl() }
    single { GetPersonalInformationUseCase(repo = get()) }
    single { GetContactsUseCase(repo = get()) }
    viewModelOf(::AboutViewModel)
}

val educationModule = module {
    single<EducationRepo> { EducationRepoImpl() }
    single { GetEducationUseCase(repo = get()) }
    viewModelOf(::EducationViewmodel)
}