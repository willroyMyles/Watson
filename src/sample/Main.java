package sample;

//import com.ibm.watson.developer_cloud.assistant.v2.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.assistant.v2.model.*;
import com.ibm.watson.developer_cloud.service.exception.NotFoundException;
import com.ibm.watson.developer_cloud.service.exception.RequestTooLargeException;
import com.ibm.watson.developer_cloud.service.exception.ServiceResponseException;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    final private static String api =               "gM_MfsexHd8fvtR6KkgZ7GnoKKqbjsC2boHAnbl6cEnO";
    final private static String gateway =           "https://gateway-wdc.watsonplatform.net/assistant/api";
    final private static String version =           "2018-12-00";
    final private static String workspaceID =       "aa0de638-de5a-4f98-bb26-79a275270269";
    final private static String assistantID =       "aa0de638-de5a-4f98-bb26-79a275270269";


    private String sessionID = "";

    private Assistant assistant;


    @Override
    public void start(Stage primaryStage) throws Exception{

        authorize();
        createSession();
        testCode();
        closeSession();



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void authorize(){
        IamOptions iamOptions = new IamOptions.Builder().apiKey(api).build();
        assistant = new Assistant(version, iamOptions);
        assistant.setEndPoint(gateway);
    }

    private void createSession(){

        CreateSessionOptions options = new CreateSessionOptions.Builder(assistantID).build();
        SessionResponse response = assistant.createSession(options).execute();
        sessionID = response.getSessionId();
        System.out.println(response);
    }

    private void closeSession(){
        DeleteSessionOptions options = new DeleteSessionOptions.Builder(assistantID, sessionID).build();
        assistant.deleteSession(options).execute();
    }

    private void testCode(){
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text("Hello")
                .build();

        MessageOptions options = new MessageOptions.Builder(assistantID, sessionID)
                .input(input)
                .build();

        MessageResponse response = assistant.message(options).execute();

        String msg = response.getOutput().getGeneric().get(0).getText();
        System.out.println(msg);
    }




    public static void main(String[] args) {


        launch(args);
    }
}
