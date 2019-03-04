package engine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import presentation.Controller;
import presentation.Intermediary;

import static javafx.application.Application.launch;

public class Driver extends Application {

    Intermediary intermediary;
    EntityManager entityManager;
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/main.fxml"));
        AnchorPane root = loader.load();

        intermediary = new Intermediary();
        entityManager = new EntityManager();


        Controller controller = loader.<Controller>getController();
        controller.setIntermediary(intermediary);
        controller.setEntityManager(entityManager);
        controller.setStage(primaryStage);
        intermediary.authorize();
        intermediary.createSession();;
        //intermediary.testCode();
       // intermediary.closeSession();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });


        primaryStage.initStyle(StageStyle.UNDECORATED );
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
        });

    }
}
