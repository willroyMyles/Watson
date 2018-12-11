package presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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




    public void sendMessage(){
        System.out.print(sendBox.getText());
        intermediary.sendMessage(sendBox.getText());
        sendBox.clear();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setIntermediary(Intermediary intermediary) {
        this.intermediary = intermediary;
    }
}
