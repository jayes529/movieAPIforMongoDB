package com.example.Movie


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping
class MovieController {

    @Autowired lateinit var movieService: MovieService



    //show all movie details
    @GetMapping("/movies")
    fun listMovie(): Flux<Movie> {
        return movieService.list()
    }

  //find movie by uuid
    @GetMapping("/movieDetails")
    fun movie(@RequestParam uuid : String): Mono<Movie> {
        return movieService.find(uuid)
    }

}
