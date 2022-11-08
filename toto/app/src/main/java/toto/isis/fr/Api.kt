package toto.isis.fr

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {

    val apiKey = "fb30babcb54f0944246de6115292eced"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val service = retrofit.create(TmdbApi::class.java)
}