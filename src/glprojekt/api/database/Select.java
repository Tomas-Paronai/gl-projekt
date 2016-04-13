package glprojekt.api.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by tomas on 4/13/2016.
 */
public class Select{

    private String[] columns;
    private String[][] data;
    private HandlerDB handlerDB;

    public Select(HandlerDB handlerDB){
        this.handlerDB = handlerDB;
    }

    /**
     * Naplni pole columns nazvami stlpcov a 2d-pole data so selectom v poradi ako idu stlpce. Pouzitelne premenne do JTable
     * @param query
     */
    public void selectWithQuery(Query query){
        HashMap<String,ArrayList<String>> result = null;

        if(handlerDB.connect()){
            result = handlerDB.executeForResult(query.toString());
            handlerDB.disconnect();
        }

        if(result != null){
            columns = new String[result.size()];
            int i = 0;
            Iterator iterator = result.keySet().iterator();
            while(iterator.hasNext()){
                columns[i++] = (String) iterator.next();
            }

            int rowCount = result.get(columns[0]).size();
            int colCount = columns.length;
            data = new String[rowCount][colCount];

            for(int r = 0; r < rowCount; r++){
                for(int c = 0; c < colCount; c++){
                    data[r][c] = result.get(columns[c]).get(r);
                }
            }
        }
    }

    public String[] getColumns() {
        return columns;
    }

    public String[][] getData() {
        return data;
    }
}
