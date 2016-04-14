package glprojekt.api;

import glprojekt.Main;
import glprojekt.api.database.*;

/**
 * Created by tomas on 4/13/2016.
 */
public class WindowDataHandler {

    private HandlerDB handlerDB;
    private Select select;

    public WindowDataHandler() {
        handlerDB = new HandlerDB(Main.URL,Main.DATABASE,Main.USER,Main.PASS);
    }

    public void initiateSQLCommand(String query, QueryType type){
        
        if(type == QueryType.SELECT){
            select = new Select(handlerDB);
            select.selectWithQuery(query);
        }
        
        else{
            if(handlerDB.connect()){
                handlerDB.executeManipulate(query);
                handlerDB.disconnect();
            }
        }
        
    }
    public void initiateSQLCommand(Query query){

        if(query.getType() == QueryType.SELECT){
            select = new Select(handlerDB);
            select.selectWithQuery(query.toString());
        }

        else{
            if(handlerDB.connect()){
                handlerDB.executeManipulate(query.toString());
                handlerDB.disconnect();
            }
        }
    }

    /**
     * Potrebne checkovat ci je null ked s nim pracujete
     * @return
     */
    public Select getSelect() {
        return select;
    }
}
