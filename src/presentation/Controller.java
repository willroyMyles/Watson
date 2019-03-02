package presentation;

import engine.Salaries;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    private Stage stage;


    @FXML
    private void closeApp(ActionEvent event){
        stage.close();
        intermediary.closeSession();
    }


    public void sendMessage(){
        System.out.print(sendBox.getText());
        addMessageToView(sendBox.getText(),0);
        addMessageToView(intermediary.sendMessage(sendBox.getText()),1);
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

    public void 


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Salaries.getSalary("Software-Developer");
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
