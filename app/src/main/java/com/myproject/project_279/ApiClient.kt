import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8000"

    private val gson = GsonBuilder()
        .setLenient()  // Set Gson to be lenient
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson)) // Use the custom Gson
        .build()

    val retrofitService: ApiService = retrofit.create(ApiService::class.java)
}
