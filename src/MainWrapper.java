import java.io.IOException;

public class MainWrapper {
    public static void main(String[] args) throws Exception {
        /*
                AntiCheat antiCheat = new AntiCheat(
                "C:\\Users\\rana_\\IdeaProjects\\anticheat-jar\\anticheat-jar\\testData",
                "referenceTest.txt");
                antiCheat.start();
         */
        /*
         AntiCheatFrame antiCheatFrame = new AntiCheatFrame( "C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu","referenceTest.txt" );
         antiCheatFrame.initWindow();
         */

        DatabaseOperation testDatabase = DatabaseOperation.getInstance("jdbc:sqlserver://poromtest.mssql.somee.com;database=poromtest;user=PoromK_SQLLogin_1;password=prnclvbss7;encrypt=true;trustServerCertificate=true;loginTimeout=30;");
        testDatabase.executeStatement("CREATE TABLE Auth (id INTEGER IDENTITY(1,1) not NULL PRIMARY KEY, local VARCHAR(MAX) not NULL, domain VARCHAR(MAX) not NULL,  hash VARCHAR(MAX) not NULL)");
        try{
            UserAuth.registerUser("Poromkamal123@gmail.com","@123SexyGuy",testDatabase);
        }
        catch(Exception e){
            try{
                testDatabase.close();
            }catch(IOException r){
                r.printStackTrace();
            }
            e.printStackTrace();
        }



    }
}