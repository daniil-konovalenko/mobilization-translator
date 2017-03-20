package dank.com.translator;



import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import dank.com.translator.yandextranslale.YandexTranslateApi;
import dank.com.translator.yandextranslale.model.Translation;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.*;

public class YandexTranslateApiClientTest {
    MockWebServer mockWebServer = new MockWebServer();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(mockWebServer.url("").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    YandexTranslateApi service = retrofit.create(YandexTranslateApi.class);

    @Test
    public void testTranslate() throws IOException {


        //Set a response for retrofit to handle. You can copy a sample
        //response from your server to simulate a correct result or an error.
        //MockResponse can also be customized with different parameters
        //to match your test needs
        mockWebServer.enqueue(new MockResponse().setBody("{\n" +
                "    \"code\": 200,\n" +
                "    \"lang\": \"en-ru\",\n" +
                "    \"text\": [\n" +
                "        \"Здравствуй, Мир!\"\n" +
                "    ]\n" +
                "}"));


        Call<Translation> call = service.translate("text", "ru");


        Response<Translation> response = call.execute();
        assertTrue(response != null);
        assertTrue(response.body().getCode() == 200);
        assertEquals(response.body().getText().get(0), "Здравствуй, Мир!");
        assertEquals(response.body().getLang(), "en-ru");
        
        //Finish web server
        mockWebServer.shutdown();
    }

}
