package engine;

import java.util.HashMap;
import java.util.Map;

public class EntityManager {

    private Map<String, Integer>  entities;

    public EntityManager(){
        entities = new HashMap<>();
    }

    public void insert(String entity, String value){
        int val =0 ;
        switch(value){
            case "extremely":
                val = 5;
                break;
            case "very":
                val = 4;
                break;
            case "moderately":
                val = 3;
                break;
            case "slightly":
                val = 2;
                break;
            case "not very":
                val = 1;
                break;
            default:
                break;
        }
        if(entities.containsKey(entity)) entities.replace(entity,val);
        else entities.put(entity, val);

        System.out.println("entity gotten" + entity);

    }

}
