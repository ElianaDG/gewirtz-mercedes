package gewirtz.mercedes;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MercedesService {
    //i have a limit of 5 calls to this
    //apikey = 99d80a3e-6389-43cd-b4a2-57cbacc4f743

    //this was used in the website trial call
    //apikey=d705585c-a672-11ea-bb37-0242ac130002

    //this returns the imageID, based on vehicleID
    @GET("/tryout/vehicle_images/v1/vehicles/{vehicleId}?apikey=d705585c-a672-11ea-bb37-0242ac130002")
    Single<MercedesImageID> getImageID(
            @Path("vehicleId")String vehicleId,
            @Query("roofOpen")Boolean roofOpen,
            @Query("night")Boolean night,
            @Query("background")Boolean background,
            @Query("jpeg")Boolean jpeg
    );

}
