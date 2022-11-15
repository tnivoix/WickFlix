package toto.isis.fr.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {

    val apiKey = "fb30babcb54f0944246de6115292eced"
    val apiImg = "http://image.tmdb.org/t/p/"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val service = retrofit.create(TmdbApi::class.java)
}

enum class Size{
    original,h632,w45,w92,w154,w185,w300,w342,w500,w780,w1280
}
/*
posterShort => w500
posterFull => w780
backdrop => w1280
*/
