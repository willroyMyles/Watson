package engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.Controller;
import presentation.Intermediary;

import static javafx.application.Application.launch;

public class Driver extends Application {

    Intermediary intermediary;

    public static void main(String[] args) {


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/main.fxml"));
        AnchorPane root = loader.load();

        intermediary = new Intermediary();

        Controller controller = loader.<Controller>getController();
        controller.setIntermediary(intermediary);
        intermediary.authorize();
        intermediary.createSession();;
        intermediary.testCode();
       // intermediary.closeSession();


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
