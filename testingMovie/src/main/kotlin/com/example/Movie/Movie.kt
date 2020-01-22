package com.example.Movie


import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "movies")
class Movie {
    @Id
    var uuid :String? = ""
    var chi_name :String? =  ""
    var eng_name :String? =  ""
    var chi_genre :String? =  ""
    var eng_genre :String? =  ""
    var chi_director :String? =  ""
    var eng_director :String? =  ""
    var chi_cast :String? = ""
    var eng_cast :String? =  ""
    var open_date : Long? = 0
    var thumbnail :String? =  ""
    var chi_synopsis :String? =  ""
    var eng_synopsis :String? =  ""
    var category :String?=  ""
    var duration : Int? =  0



}