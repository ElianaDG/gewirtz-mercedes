package gewirtz.mercedes;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class MercedesControllerTest {

    private MercedesController controller;

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
    }

    private void givenMercedesController(){
        controller.exterior = mock(RadioButton.class);
        controller.interior = mock(RadioButton.class);
        controller.vehicleImage = mock(ImageView.class);
        controller.getImage = mock(Button.class);
        controller.toggleUnits = Arrays.asList(
                controller.interior, controller.exterior
        );
        controller.roofOpenCheck = mock(CheckBox.class);
        controller.backgroundCheck = mock(CheckBox.class);
        controller.nightCheck = mock(CheckBox.class);
        controller.jpegCheck = mock(CheckBox.class);
        controller.vehicleIdOptions = mock(ComboBox.class);
    }

    @Test
    public void initialize(){
        //given
        MercedesService service = mock(MercedesService.class);
        controller = new MercedesController(service);
        givenMercedesController();
        SingleSelectionModel<String> selectionModel = mock(SingleSelectionModel.class);
        doReturn(selectionModel).when(controller.vehicleIdOptions).getSelectionModel();

        //when
        controller.initialize();

        //then
        verify(controller.exterior).setSelected(true);
        verify(controller.vehicleImage).setFitHeight(500.00);
        verify(controller.vehicleImage).setFitWidth(600.00);
        verify(controller.vehicleIdOptions).getSelectionModel();
    }

    @Test
    public void onGetImageId(){
        //given
        MercedesService service = mock(MercedesService.class);
        controller = new MercedesController(service);
        givenMercedesController();

        doReturn(Single.never()).when(service).getImageID("WDD2132231A444556", true, true, true, true);
        doReturn("WDD2132231A444556").when(controller.vehicleIdOptions).getValue();
        doReturn(true).when(controller.roofOpenCheck).isSelected();
        doReturn(true).when(controller.backgroundCheck).isSelected();
        doReturn(true).when(controller.nightCheck).isSelected();
        doReturn(true).when(controller.jpegCheck).isSelected();
        doReturn(true).when(controller.toggleUnits.get(0)).isSelected();
        // when
        controller.onGetImageId();

        // then
        verify(service).getImageID("WDD2132231A444556", true, true, true, true);
    }

    @Test
    public void displayImage_interior(){
        //given
        MercedesService service = mock(MercedesService.class);
        controller = new MercedesController(service);
        MercedesImageID imageId = mock(MercedesImageID.class);
        givenMercedesController();

        doReturn(true).when(controller.toggleUnits.get(0)).isSelected();
        imageId.INT1 = "V0REMjEzMjIzMUE0NDQ1NTY6cm9vZk9wZW49dHJ1ZTpuaWdodD10cnVlOklOVDEucG5n";

        //when
        controller.displayImage(imageId);

        //then
        verify(controller.vehicleImage).setImage(any(Image.class));
    }

    @Test
    public void displayImage_exterior(){
        //given
        MercedesService service = mock(MercedesService.class);
        controller = new MercedesController(service);
        MercedesImageID imageId = mock(MercedesImageID.class);
        givenMercedesController();

        doReturn(true).when(controller.toggleUnits.get(1)).isSelected();
        imageId.EXT150 = "V0REMjEzMjIzMUE0NDQ1NTY6cm9vZk9wZW49dHJ1ZTpuaWdodD10cnVlOkVYVDMzMC5wbmc=";

        //when
        controller.displayImage(imageId);

        //then
        verify(controller.vehicleImage).setImage(any(Image.class));
    }

}
