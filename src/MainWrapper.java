import DatabaseAPI.DatabaseOperation;
import DatabaseAPI.User;

import java.util.Scanner;

public class MainWrapper {
    public static void main(String[] args) throws Exception {
        /*
        AntiCheatFrame antiCheatFrame = new AntiCheatFrame( "C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu","referenceTest.txt" );
        antiCheatFrame.initWindow();
        DatabaseOperation testDatabase = DatabaseOperation.getInstance("jdbc:sqlserver://poromtest.mssql.somee.com;database=poromtest;user=PoromK_SQLLogin_1;password=prnclvbss7;encrypt=true;trustServerCertificate=true;loginTimeout=30;");
        testDatabase.executeStatement("CREATE TABLE ReferenceFiles (id INTEGER not NULL PRIMARY KEY, fileName VARCHAR(MAX) not NULL,  hash VARCHAR(MAX) not NULL)");
         */

        DatabaseOperation testDatabase = DatabaseOperation.getInstance("jdbc:sqlserver://poromtest.mssql.somee.com;database=poromtest;user=PoromK_SQLLogin_1;password=prnclvbss7;encrypt=true;trustServerCertificate=true;loginTimeout=30;");
        //testDatabase.executeStatement("CREATE TABLE ReferenceFiles (id INTEGER not NULL, gameName VARCHAR(MAX) not NULL,fileName VARCHAR(MAX) not NULL,  hash VARCHAR(MAX) not NULL)");

        User.loginUser("PoromKamal@gmail.com", "@Porom2002", testDatabase);

        System.out.println(User.verifyFiles("Valorant", "C:\\Users\\rana_\\IdeaProjects\\anticheat-jar\\anticheat-jar\\testData"));
    }

}