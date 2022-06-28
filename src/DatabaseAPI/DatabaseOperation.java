package DatabaseAPI;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class DatabaseOperation implements Closeable {
    public static DatabaseOperation database;
    private static Connection connection;

    private DatabaseOperation(String connectionURL){
        try {
            connection = DriverManager.getConnection(connectionURL);
            System.out.println("Connection Established Successfully");
        } catch (SQLException var7) {
            var7.printStackTrace();
        }
    }

    public static DatabaseOperation getInstance(String connectionURL){
        if(database == null){
            database = new DatabaseOperation(connectionURL);
        }
        return database;
    }

    public void executeStatement(String st){
        if(connection != null){
            try{
                Statement statement = connection.createStatement();
                statement.executeUpdate(st);
            }catch(SQLException couldNotExecute){
                couldNotExecute.printStackTrace();
            }
        }
    }

     void registerUserDatabase(String email, String hash) throws SQLException {
        //Check if user already exists in Database
        String checkUserExistStatement = "SELECT email FROM Auth WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(checkUserExistStatement);
        preparedStatement.setString(1, email);
        ResultSet foundEmails = preparedStatement.executeQuery();

        //Check if there's a next value, if there is, that means the user already exists
        if(foundEmails.next()){
            throw new SQLException("User Already Exists.");
        }

        //Try to register the user
        String registerUserStatement = "INSERT INTO Auth (email, hash) VALUES (?, ?)";
        PreparedStatement preparedRegisterUser = connection.prepareStatement(registerUserStatement);

        //Set the first question mark
        preparedRegisterUser.setString(1, email);

        //Set the second question mark
        preparedRegisterUser.setString(2, hash);

        try{
            //Try to insert the user into the database
            preparedRegisterUser.executeUpdate();
        }catch (SQLException sqlException) //Override the sql exception message
        {
            throw new SQLException("Unable to register user.");
        }
    }

    public void removeAllFromTable(String table) throws SQLException {
        String removeFromTable = "DELETE FROM " + table;
        Statement removeFromTableStatement = connection.createStatement();
        removeFromTableStatement.executeUpdate(removeFromTable);
    }

    /**
     *
     * @param email email for login
     * @param hash hash for login
     * @return the associated id, if the user was not found "000000" is returned
     * @throws SQLException
     */
     String loginUser(String email, String hash) throws SQLException {
        String loginUserQuery = "SELECT id, email, hash FROM Auth WHERE email = ? AND hash = ?";
        PreparedStatement loginStatement = connection.prepareStatement(loginUserQuery);
        loginStatement.setString(1, email);
        loginStatement.setString(2, hash);

        ResultSet loginResult = loginStatement.executeQuery();

        //Check if there's a row in the database
        if(!loginResult.next()){
            return "000000";
        }else{
            return loginResult.getString("id");
        }
    }

    public void insertFileData(String userid, String gameName,String filename, String hash) throws SQLException {
         String insertData = "INSERT INTO ReferenceFiles (id, gameName, fileName, hash) VALUES (?, ?, ?, ?)";
         PreparedStatement insertDataStatement = connection.prepareStatement(insertData);
         insertDataStatement.setString(1, userid);
         insertDataStatement.setString(2, gameName);
         insertDataStatement.setString(3, filename);
         insertDataStatement.setString(4, hash);
         insertDataStatement.executeUpdate();
    }

    static HashMap<String, String> deserializeData(String userId, String gameName) throws SQLException {

         HashMap<String, String> fileHashData = new HashMap<>();

         String getDataString = "SELECT id, gameName, fileName, hash FROM ReferenceFiles WHERE id = ? AND gameName = ?";
         PreparedStatement getDataQuery = connection.prepareStatement(getDataString);
         getDataQuery.setString(1, userId);
         getDataQuery.setString(2, gameName);
         ResultSet dataFound = getDataQuery.executeQuery();

         while(dataFound.next()){
            fileHashData.put(dataFound.getString("fileName"), dataFound.getString("hash"));
         }

         return fileHashData;
    }

    @Override
    public void close() throws IOException {
        if(connection != null){
            try{
                connection.close();
                System.out.println("Connection closed successfully.");
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
    }
}
