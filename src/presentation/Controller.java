package presentation;

import engine.EntityManager;
import engine.Responses;
import engine.Salaries;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Button sendButton;
    @FXML
    public TextField sendBox;
    @FXML
    public ScrollPane scrollView;
    @FXML
    public GridPane gridPane;


    private Intermediary intermediary;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private EntityManager entityManager;
    private Stage stage;


    @FXML
    private void closeApp(ActionEvent event){
        stage.close();
        intermediary.closeSession();
    }


    public void sendMessage(){
        System.out.print(sendBox.getText());
        addMessageToView(sendBox.getText(),0);
        intermediary.sendMessage(sendBox.getText());
        sendBox.clear();
    }

    public void addMessageToView(String msg, int sender){
        Label lab = new Label(msg);
        lab.setWrapText(true);
        if(sender == 0){
            // student sending message
            Pane pane = new Pane();
            pane.setMinSize(gridPane.getWidth(),19);
            pane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            pane.getChildren().add(lab);
            pane.setPrefSize(gridPane.getWidth(),19);
            gridPane.addRow(gridPane.getRowCount()+1, pane);

        }else{
            gridPane.addRow(gridPane.getRowCount()+1, lab);
        }

    }

    public void addOptionsToView(Responses.Options opt){
        Button btn = new Button();
        btn.setText(opt.label);

        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                intermediary.sendMessage(opt.inputText);
                System.out.println(opt.inputText);
            }
        });

        Label lab = new Label(opt.title);
        lab.setWrapText(true);

        HBox pane = new HBox();
        pane.setMinSize(gridPane.getWidth(),19);
        pane.getChildren().add(lab);
        pane.getChildren().add(btn);
        pane.setPrefSize(gridPane.getWidth(),19);
        gridPane.addRow(gridPane.getRowCount()+1, pane);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Salaries.getSalary("Software-Developer");
            gridPane.setVgap(5.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIntermediary(Intermediary intermediary) {
        this.intermediary = intermediary;
        intermediary.setCon(this);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
