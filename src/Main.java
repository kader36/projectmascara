import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/loginPage.fxml"));
        primaryStage.setTitle("تسجيل الدخول");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.setMaxHeight(469);
        primaryStage.setMaxWidth(460);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);


    }
}
