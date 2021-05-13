package gewirtz.mercedes;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MercedesService {

    //this returns the imageID, based on vehicleID
    @GET("/vehicle_images/v1/vehicles")
    Single<MercedesImageID> getImageID(
            @Path("vehicleId")String vehicleId, //use example on the website
            @Query("roofOpen")Boolean roofOpen,
            @Query("night")Boolean night,
            @Query("background")Boolean background,
            @Query("jpeg")Boolean jpeg
    );

    //this returns the image, based on the imageID
    @GET("/vehicle_images/v1/images")
    Single<MercedesImage> getVehicleImage(
            @Path("imageId")String imageId
    );
}
