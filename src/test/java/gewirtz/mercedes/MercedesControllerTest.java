package gewirtz.mercedes;

import io.reactivex.rxjava3.core.Single;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class MercedesControllerTest {

    private MercedesController controller;
    private MercedesService service;
    private MercedesImageID imageId;

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
    }

    private void givenMercedesController(){
        service = mock(MercedesService.class);
        imageId = mock(MercedesImageID.class);
        controller = new MercedesController(service);
        controller.exterior = mock(RadioButton.class);
        controller.interior = mock(RadioButton.class);
        controller.vehicleImage = mock(ImageView.class);
        controller.getImage = mock(Button.class);
        controller.roofOpenCheck = mock(CheckBox.class);
        controller.backgroundCheck = mock(CheckBox.class);
        controller.nightCheck = mock(CheckBox.class);
        controller.jpegCheck = mock(CheckBox.class);
        controller.toggleUnits = Arrays.asList(
                controller.interior, controller.exterior
        );
    }

    @Test
    public void initialize(){
        //given
        givenMercedesController();

        //when
        controller.initialize();

        //then
        verify(controller.exterior).setSelected(true);
        verifyNoInteractions(controller.vehicleImage);
    }

    @Test
    public void onGetImage_exterior(){
        //given
        givenMercedesController();
        doReturn(Single.never()).when(service).getImageID("WDD2132231A444556", true, true, true, true);
        doReturn("WDD2132231A444556").when(controller.vehicleIdTextField.getText());
        //doReturn(true).when(controller.toggleUnits.get(1)).isSelected();

        // when
        controller.onGetImage();

        // then
        verify(service).getImageID("WDD2132231A444556", true, true, true, true);
    }

    @Test
    public void onGetImage_interior(){

    }

}
