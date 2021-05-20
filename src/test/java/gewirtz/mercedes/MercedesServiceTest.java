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
        MercedesImageID image = single.blockingGet();

        //then
        assertNotNull(image);
        assertNotNull(image.INT1);
        assertNotNull(image.EXT150);

    }
}
