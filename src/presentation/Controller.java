package presentation;

import engine.Salaries;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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


    private Intermediary intermediary;
    private Stage stage;


    @FXML
    private void closeApp(ActionEvent event){
        stage.close();
        intermediary.closeSession();
    }


    public void sendMessage(){
        System.out.print(sendBox.getText());
        intermediary.sendMessage(sendBox.getText());
        sendBox.clear();
    }




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
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
