package com.paf.cervezaskoin.domain

import com.paf.cervezaskoin.data.repository.BeersRepository

class FindByIdUseCase(private val beersRepository: BeersRepository) {

    suspend operator fun invoke(beerId: Int) = beersRepository.findById(beerId)
}