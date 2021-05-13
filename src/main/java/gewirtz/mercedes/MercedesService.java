package gewirtz.mercedes;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MercedesService {

    //this returns the imageID, based on vehicleID
    @GET("/vehicle_images/v1/vehicles/{vehicleId}")
    Single<MercedesImageID> getImageID(
            @Path("vehicleId")String vehicleId,
            @Query("roofOpen")Boolean roofOpen,
            @Query("night")Boolean night,
            @Query("background")Boolean background,
            @Query("jpeg")Boolean jpeg
    );

}
