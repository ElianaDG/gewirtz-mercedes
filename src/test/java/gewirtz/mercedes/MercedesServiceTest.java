package gewirtz.mercedes;

import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import static org.junit.Assert.*;


public class MercedesServiceTest {

    MercedesServiceFactory factory = new MercedesServiceFactory();

    @Test
    public void getImageId(){
        //given
        MercedesService service = factory.newInstance();

        //when
        Single<MercedesImageID> single = service.getImageID("WDD2132231A444556",
                false, false, true, true);
        MercedesImageID imageId = single.blockingGet();

        //then
        assertNotNull(imageId);
        assertNotNull(imageId.EXT150);
        assertNotNull(imageId.EXT330);
        assertNotNull(imageId.INT1);

    }
}
