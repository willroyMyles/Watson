package presentation;

//import com.ibm.watson.developer_cloud.assistant.v2.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.assistant.v2.model.*;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Intermediary {

    final private static String api =               "gM_MfsexHd8fvtR6KkgZ7GnoKKqbjsC2boHAnbl6cEnO";
    final private static String gateway =           "https://gateway-wdc.watsonplatform.net/assistant/api";
    final private static String version =           "2018-12-00";
    final private static String assistantID =       "aa0de638-de5a-4f98-bb26-79a275270269";
    private String sessionID = "";
    private Assistant assistant;

    public Intermediary(){

    }

    public void authorize(){
        IamOptions iamOptions = new IamOptions.Builder().apiKey(api).build();
        assistant = new Assistant(version, iamOptions);
        assistant.setEndPoint(gateway);
    }

    public void createSession(){

        CreateSessionOptions options = new CreateSessionOptions.Builder(assistantID).build();
        SessionResponse response = assistant.createSession(options).execute();
        sessionID = response.getSessionId();
        System.out.println(response);
    }

    public void closeSession(){
        DeleteSessionOptions options = new DeleteSessionOptions.Builder(assistantID, sessionID).build();
        assistant.deleteSession(options).execute();
    }

    public void testCode(){
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text("hi")
                .build();

        MessageOptions options = new MessageOptions.Builder(assistantID, sessionID)
                .input(input)
                .build();

        MessageResponse response = assistant.message(options).execute();

        String msg = response.getOutput().getGeneric().get(0).getText();
        System.out.println(msg);
    }

    public void sendMessage(String text){
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text(text)
                .build();

        MessageOptions options = new MessageOptions.Builder(assistantID, sessionID)
                .input(input)
                .build();

        MessageResponse response = assistant.message(options).execute();

        String msg = response.getOutput().getGeneric().get(0).getText();
        System.out.println(msg);
    }






}
