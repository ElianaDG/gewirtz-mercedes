package gewirtz.mercedes;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MercedesServiceFactory {

    public MercedesService newInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercedes-benz.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        MercedesService service = retrofit.create(MercedesService.class);

        return service;
    }
}
