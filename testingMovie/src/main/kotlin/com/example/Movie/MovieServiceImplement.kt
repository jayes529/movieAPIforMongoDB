package com.example.Movie


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MovieServiceImpl : MovieService{

    @Autowired lateinit var movieRepository: MovieRepository

    override fun find(uuid: String): Mono<Movie> {
        return movieRepository.findById(uuid)
    }

    override fun list(): Flux<Movie> {
        return movieRepository.findAll()
    }

}