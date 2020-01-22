package com.example.Movie

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface MovieService {


    fun find(id : String): Mono<Movie>

    fun list(): Flux<Movie>


}



