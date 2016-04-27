package glprojekt.api.database;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by tomas on 4/7/2016.
 */
public class HandlerDB {

    private final String dateFix = "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false";

    private String url;
    private String database;
    private String user;
    private String password;
    private String driver;
    private ResultSet result;
    private Connection dbConnection;
    private PreparedStatement statement;
    
    private int lastInsertedId;

    /**
     *
     * @param url Address to mysql server
     * @param database Name of database
     * @param user name of login user
     * @param password password
     */
    public HandlerDB(String url, String database, String user, String password) {
        this.url = "jdbc:mysql://" + url + "/";
        this.database = database;
        this.user = user;
        this.password = password;
        this.driver = "com.mysql.jdbc.Driver";
    }

    public boolean connect() {
        try {
            dbConnection = DriverManager.getConnection(url+database+dateFix,user,password);
        } catch (SQLException e) {
            //System.out.println("error" +e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void disconnect(){
        if(dbConnection != null){
            try{
                 dbConnection.close();
            }catch(Exception ex){
                System.out.println(ex);
            }
            
        }
    }

    /**
     * Pouzivat na SELECT. Vysledok je HashMap. Kluc je nazov stlpca a hodnota je ArrayList stringov hodnot v danom stlpci.
     * @param query
     * @return
     */
    public HashMap<String, ArrayList<String>> executeForResult(String query) throws DBHandlerException {
        Statement st;
        ResultSet res = null;

        HashMap<String,ArrayList<String>> result = new LinkedHashMap<>();

        try {

            if(connect()){
                st = dbConnection.createStatement();
                res = st.executeQuery(query);

                ResultSetMetaData rsmd = res.getMetaData();
                int maxColumn = rsmd.getColumnCount();

                for(int i = 1; i <= maxColumn; i++){

                    String columnName = rsmd.getColumnName(i);
                    ArrayList<String> values = new ArrayList<>();

                    while(res.next()){
                        values.add(res.getString(columnName));
                    }

                    if(values.size() == 0){
                        throw new DBHandlerException("Empty set with query "+query);
                    }

                    else{
                        result.put(columnName,values);
                    }


                    revertResultSet(res);

                }

                disconnect();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public HashMap<String,String> executeForSingleLine(String query) throws DBHandlerException{
        HashMap<String, ArrayList<String>> dbResult = executeForResult(query);
        HashMap<String,String> result = new HashMap<>();
        
        String key = "error";
        String value = "error in parsing";
        
        for(String tmpKey : dbResult.keySet()){
            key = tmpKey;
            value = dbResult.get(key).get(0);
            result.put(key, value);
        }             
        
        return result;
    }

    /**
     * Pouzivat na manipulaciu s datami, cize INSERT, UPDATE a DELETE
     * @param query
     */
    public boolean executeManipulate(String query){
        Statement st;
        try{

            if(connect()){
                st = dbConnection.createStatement();
                st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);

                ResultSet resultSet = st.getGeneratedKeys();
                if(resultSet.next()){
                    lastInsertedId = resultSet.getInt(1);
                }
                resultSet.close();

                disconnect();
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public void prepareStatement(String query) throws DBHandlerException {
        if(connect()){
            try {
                statement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new DBHandlerException("Failed to connect!");
        }
    }

    public void updateStatement(int position, String value){
        if(statement != null){
            try {
                statement.setString(position,value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    public void updateStatement(String... value){
        if(statement != null){
            try{

                for(int i = 1; i <= value.length; i++){
                    statement.setString(i,value[i-1]);
                }

            } catch(SQLException e){
                e.printStackTrace();
            }

        }
    }
    
    public boolean executeUpdate(){
         if(statement != null){
            try {
                statement.executeUpdate();   
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         return false;
    }
    

    public void executeStatement() throws DBHandlerException {
        if(statement != null){
            try {
                statement.executeUpdate();
                
                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next()){
                    lastInsertedId = resultSet.getInt(1);
                }
                resultSet.close();
                
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new DBHandlerException("No statement created! Use prepareStatement first!");
        }
    }
    //vrati arraylist stringov contract info
     public List<String> getContractInfo(){
         
        List<String> list = new ArrayList();
        try{
              if(connect()){
                  prepareStatement("SELECT *from contract");
                  result = statement.executeQuery();
                  while(result.next()){
                      int id = result.getInt("ContractID");
                      String name = result.getString("contract_type");
                      list.add(id+" "+name);
                  }
                    return list;
              }
             
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
      //vrati arraylist stringov possition info
     public List<String> getPossInfo(){
         
        List<String> list = new ArrayList();
        try{
              if(connect()){
                  prepareStatement("SELECT *from position");
                  result = statement.executeQuery();
                  while(result.next()){
                      int id = result.getInt("PositionID");
                      String name = result.getString("positionName");
                      list.add(id+" "+name);
                  }
                    return list;
              }
             
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
     
     //prijme datum z formularu a mm/dd/yyyy a zmeni ho na format yyyy/mm/dd
      public String parseDateFromField(String date){
        try {
                DateFormat dffrom = new SimpleDateFormat("MM/dd/yy");
                DateFormat dfto = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date to = dffrom.parse(date);
                String s = dfto.format(to);
                return s;
               
            } catch (ParseException ex) {
                System.out.println(ex);
            }
        return null;
    }
      public int getLastID(){
           int id = 0;
            try{
              if(connect()){
                  prepareStatement("SELECT employeeID from employee order by employeeID desc limit 1");
                  result = statement.executeQuery();
                  while(result.next()){
                      id = result.getInt("employeeID");
                  }
                    return id;
              }
             
        }catch(Exception ex){
            System.out.println(ex);
        }
            return -1;
           
        
    }

    private void revertResultSet(ResultSet set) throws SQLException {
        while(set.previous());
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLastId() {
        return lastInsertedId;
    }
    
    
    public class DBHandlerException extends Exception {

        public DBHandlerException(String message){
            super(message);
        }

    }
}
