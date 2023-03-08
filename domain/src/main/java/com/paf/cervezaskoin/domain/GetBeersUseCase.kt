package com.paf.cervezaskoin.domain

import com.paf.cervezaskoin.data.repository.BeersRepository

class GetBeersUseCase(private val beersRepository: BeersRepository) {
    suspend operator fun invoke() = beersRepository.getBeers()
}