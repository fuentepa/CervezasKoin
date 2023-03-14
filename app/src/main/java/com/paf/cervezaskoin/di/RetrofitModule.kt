package com.paf.cervezaskoin.di

import android.app.Application
import com.paf.cervezaskoin.BuildConfig
import com.paf.cervezaskoin.data.database.BeerDataBase
import com.paf.cervezaskoin.data.database.RoomDataSource
import com.paf.cervezaskoin.data.repository.BeersRepository
import com.paf.cervezaskoin.data.server.TheBeerDb
import com.paf.cervezaskoin.data.server.TheBeerDbDataSource
import com.paf.cervezaskoin.data.source.LocalDataSource
import com.paf.cervezaskoin.data.source.RemoteDataSource
import com.paf.cervezaskoin.domain.FindByIdUseCase
import com.paf.cervezaskoin.domain.GetBeersUseCase
import com.paf.cervezaskoin.ui.detail.DetailBeerFragmentViewModel
import com.paf.cervezaskoin.ui.main.BeersFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(this@initDI)
        modules(listOf(
            appModule,
            retrofitRepositoryModule,
            viewModelModule,
            useCaseModule))
    }
}

val appModule = module {
    single(named("baseUrl")) { "https://api.punkapi.com/v2/" }
    single { TheBeerDb(get(named("baseUrl"))) }
    single { BeerDataBase.build(get()) }

    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { TheBeerDbDataSource(get()) }
}

val retrofitRepositoryModule = module {
    factory { BeersRepository( get(), get()) }
}

val viewModelModule = module {
    viewModel { BeersFragmentViewModel( getBeersUseCase = get()) }
    viewModel { (id: Int) -> DetailBeerFragmentViewModel( beerId = id, findByIdUseCase = get()) }
}

val useCaseModule = module {
    factory {
        GetBeersUseCase(
            beersRepository = get()
        )
    }

    factory { FindByIdUseCase(get()) }
    // factory { ToggleAvailableUseCase(get()) }
}

