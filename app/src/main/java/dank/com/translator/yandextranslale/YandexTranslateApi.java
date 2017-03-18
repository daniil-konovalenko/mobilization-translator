package dank.com.translator.yandextranslale;


import dank.com.translator.yandextranslale.model.Translation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexTranslateApi {

    String API_KEY = "trnsl.1.1.20170314T182428Z.1c0f066b3fcc8f15.12d854b4a41add059d7b0b34504058bf7112ef1a";

    @GET("/translate")
    Call<Translation> translate(@Query("key") String apiKey,
                                @Query("text") String text,
                                @Query("lang") String lang);

}
