import android.media.RouteListingPreference
import com.myproject.project_279.Item
import com.myproject.project_279.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @GET("/search") // Adjust this path to match your backend's search endpoint
    fun searchItems(@Query("query") query: String): Call<SearchResponse>

    @POST("/api/get-items")
    fun getItemsByNames(@Body itemNames: List<String>): Call<List<Item>>
}

