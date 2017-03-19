package dank.com.translator;

import android.app.Application;

import java.io.IOException;

import dank.com.translator.yandextranslale.YandexTranslateApi;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application{
    private static final App ourInstance = new App();
    private static YandexTranslateApi api;
    private Retrofit retrofit;
    private final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
    private String API_KEY;

    public static App getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        API_KEY = getString(R.string.api_key);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("key", API_KEY)
                        .build();

                Request.Builder requestBuilder = original.newBuilder().url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });


        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        api = retrofit.create(YandexTranslateApi.class);
    }

    public static YandexTranslateApi getApi() {
        return api;
    }
}
