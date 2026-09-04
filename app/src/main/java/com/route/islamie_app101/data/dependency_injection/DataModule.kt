package com.route.islamie_app101.data.dependency_injection

import com.route.islamie_app101.data.repositorys.ahadeth_repository.ImplAhadethRepository
import com.route.islamie_app101.data.repositorys.quran_repository.ImplQuranRepository
import com.route.islamie_app101.data.repositorys.quran_repository.recent_suras.ImplRecentSurasRepository
import com.route.islamie_app101.data.repositorys.radio_repository.radio_tab.ImplRadioRepository
import com.route.islamie_app101.data.repositorys.radio_repository.reciters_tab.ImpRecitersRepository
import com.route.islamie_app101.domain.repository.hadeth_repository.AhadethRepository
import com.route.islamie_app101.domain.repository.quran_repository.QuranRepository
import com.route.islamie_app101.domain.repository.quran_repository.recent_suras.RecentSuraReader
import com.route.islamie_app101.domain.repository.quran_repository.recent_suras.RecentSuraWriter
import com.route.islamie_app101.domain.repository.quran_repository.recent_suras.RecentSurasRepository
import com.route.islamie_app101.domain.repository.radio_repository.radio.RadioRepository
import com.route.islamie_app101.domain.repository.radio_repository.reciters.RecitersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindAhadethRepository(impl: ImplAhadethRepository): AhadethRepository

    @Binds
    abstract fun bindQuranRepository(impl: ImplQuranRepository): QuranRepository

    @Binds
    abstract fun bindRadioRepository(impl: ImplRadioRepository): RadioRepository

    @Binds
    abstract fun bindRecitersRepository(impl: ImpRecitersRepository): RecitersRepository

    @Binds
    abstract fun bindRecentSurasRepository(impl: ImplRecentSurasRepository): RecentSurasRepository

    @Binds
    abstract fun bindRecentSurasReader(impl: ImplRecentSurasRepository): RecentSuraReader

    @Binds
    abstract fun bindRecentSuraWriter(impl: ImplRecentSurasRepository): RecentSuraWriter
}