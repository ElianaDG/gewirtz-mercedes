package gewirtz.mercedes;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.List;

public class MercedesController {

    @FXML
    Button getImage;
    @FXML
    RadioButton interior, exterior;
    @FXML
    TextField vehicleIdTextField;
    @FXML
    CheckBox roofOpenCheck, backgroundCheck, nightCheck, jpegCheck;
    @FXML
    ImageView vehicleImage;
    @FXML
    List<RadioButton> toggleUnits;
    private final ToggleGroup viewUnits = new ToggleGroup();

    private final MercedesService service;

    public MercedesController(MercedesService service){
        this.service = service;
    }

    public void initialize(){
        for (RadioButton rb : toggleUnits) {
            rb.setToggleGroup(viewUnits);
        }
        exterior.setSelected(true);
    }

    public void onGetImage(){
        String vehicleId = vehicleIdTextField.getText();
        Boolean roofOpen = roofOpenCheck.isSelected();
        Boolean night = nightCheck.isSelected();
        Boolean background = backgroundCheck.isSelected();
        Boolean jpeg = jpegCheck.isSelected();

        Disposable disposable = service.getImageID(vehicleId, roofOpen, night, background, jpeg)
                //request the data in the background
                .subscribeOn(Schedulers.io())
                //work w the data in the foreground
                .observeOn(Schedulers.trampoline())
                //work w the feed whenever it gets downloaded
                .subscribe(this::onMercedesImageID, this::onError);
    }

    public void onMercedesImageID(MercedesImageID imageId){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){displayImage(imageId);}
        });
    }

    public void displayImage(MercedesImageID imageId){
        String viewId = interior.isSelected() ? imageId.INT1 : imageId.EXT150;
        String imageUrl = "https://api.mercedes-benz.com/vehicle_images/v1/images/" + viewId;
        vehicleImage.setImage(new Image(imageUrl));
    }

    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

}
