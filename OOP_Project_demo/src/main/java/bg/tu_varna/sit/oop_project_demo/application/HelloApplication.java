package bg.tu_varna.sit.oop_project_demo.application;

import bg.tu_varna.sit.oop_project_demo.common.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.HELLO_VIEW;

public class HelloApplication extends Application {

    private static final Logger log = Logger.getLogger(HelloApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Constants.View.HELLO_VIEW));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/

        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));

        URL path = getClass().getResource(HELLO_VIEW);

        if(path != null){
            Parent root = FXMLLoader.load(path);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            stage.setTitle(Constants.Values.Title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setWidth(615);
            stage.setHeight(440);
            stage.show();
        } else {
            log.error("Sorry, the main FXML could not be loaded");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}