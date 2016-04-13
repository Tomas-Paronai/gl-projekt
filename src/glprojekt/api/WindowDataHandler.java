package glprojekt.api;

/**
 * Created by tomas on 4/13/2016.
 */
public class WindowDataHandler {

    private HandlerDB handlerDB;

    public WindowDataHandler() {
        handlerDB = new HandlerDB("localhost:3306","employees","root","");
    }


}
