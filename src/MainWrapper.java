import java.io.IOException;

public class MainWrapper {
    public static void main(String[] args) {
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
        testDatabase.executeStatement("CREATE TABLE TESTTABLETWO (id INTEGER not NULL,  first VARCHAR(255),  last VARCHAR(255),  age INTEGER,  PRIMARY KEY ( id ))");
        try{
            testDatabase.close();
        }catch(IOException e){
            e.printStackTrace();
        }


    }
}