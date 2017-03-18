package dank.com.translator;

import android.app.Application;

import dank.com.translator.yandextranslale.YandexTranslateApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application{
    private static final App ourInstance = new App();
    private static YandexTranslateApi api;
    private Retrofit retrofit;

    public static App getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        retrofit = new Retrofit.Builder().baseUrl("https://translate.yandex.net/api/v1.5/tr.json/").
                addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(YandexTranslateApi.class);
    }

    public static YandexTranslateApi getApi() {
        return api;
    }



}
