package presentation;

//import com.ibm.watson.developer_cloud.assistant.v2.model.InputData;

import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.assistant.v2.model.*;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import engine.Responses;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Intermediary {

    final private static String api =               "gM_MfsexHd8fvtR6KkgZ7GnoKKqbjsC2boHAnbl6cEnO";
    final private static String gateway =           "https://gateway-wdc.watsonplatform.net/assistant/api";
    final private static String version =           "2018-12-00";
//    final private static String assistantID =       "aa0de638-de5a-4f98-bb26-79a275270269";
    final private static String assistantID =       "e7e28cbb-43ba-4f9a-b7b1-246c3ef7efa2";
    final private static String workspaceId =       "d311a758-cc6c-4ded-832d-5a1e5969aa82";
    private String sessionID = "";
    private Assistant assistant;

    public boolean textAvailable = false;
    public boolean optionAvailable = false;

    private Controller con;



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
        System.out.println(response);
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


        List<DialogRuntimeResponseGeneric> list = response.getOutput().getGeneric();
        List<RuntimeEntity> list1               = response.getOutput().getEntities();

        appropriateResponse(list);
        appropriateResponse(list1, 0);



        System.out.println(response);
    }

    public void appropriateResponse(List<RuntimeEntity> list, int val){
        for (int i = 0; i < list.size(); i++) {
            String entity = list.get(i).getEntity();
            String value = list.get(0).getValue();
            con.getEntityManager().insert(entity,value);
        }
    }

    public void appropriateResponse(List<DialogRuntimeResponseGeneric> list){


        //examine generics
        for (int i = 0; i < list.size(); i++) {
            switch(list.get(i).getResponseType()){
                case "text":
                    con.addMessageToView(list.get(i).getText(),2);
                    textAvailable = true;
                    break;
                case "option" :
                    Responses response = new Responses();

                    response.options.title = list.get(i).getTitle();
                    response.options.label = list.get(i).getOptions().get(0).getLabel();
                    response.options.inputText = list.get(i).getOptions().get(0).getValue().getInput().text();
                    con.addOptionsToView(response.options);
                    optionAvailable = true;
                    break;

                default:

                    break;
            }
        }

    }


    public void setCon(Controller con) {
        this.con = con;
    }
}
