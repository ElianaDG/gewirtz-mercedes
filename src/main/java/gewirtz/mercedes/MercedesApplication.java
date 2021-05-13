package gewirtz.mercedes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MercedesApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        MercedesService service = new MercedesServiceFactory().newInstance();
        MercedesController controller = new MercedesController(service);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mercedes_application.fxml"));
        loader.setController(controller);

        Parent parent = loader.load();

        Scene scene = new Scene(parent, 500, 800);

        stage.setTitle("Mercedes Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}