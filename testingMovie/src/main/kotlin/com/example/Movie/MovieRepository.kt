package com.example.Movie

import org.springframework.data.mongodb.repository.ReactiveMongoRepository


interface MovieRepository : ReactiveMongoRepository<Movie, String>{
}