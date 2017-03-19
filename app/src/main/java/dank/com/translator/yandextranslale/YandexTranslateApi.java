package dank.com.translator.yandextranslale;


import dank.com.translator.yandextranslale.model.Translation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexTranslateApi {


    @GET("translate")
    Call<Translation> translate(@Query("text") String text,
                                @Query("lang") String lang);

}
