import javax.swing.plaf.nimbus.State;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    /*
    /**
     * Execute an SQL statement on the database
     * @param command
     */
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
