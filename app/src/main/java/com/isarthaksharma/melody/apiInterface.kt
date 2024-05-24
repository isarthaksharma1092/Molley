import com.isarthaksharma.melody.musicDataClass

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface apiInterface {

    @Headers("x-rapidapi-key: 081bf411c2mshf472f0920115fffp1ccea6jsn43ac2b024b82",
        "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String): Call<musicDataClass>
}
