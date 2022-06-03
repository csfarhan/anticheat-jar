import java.util.ArrayList;
import java.util.HashMap;

public class Comparer {
    public static ArrayList<String> generateOutput(HashMap<String, String> referenceData, HashMap<String, String> newData){

        ArrayList<String> foundPaths = new ArrayList<>();

        for(String key: referenceData.keySet()){
            //Check if key exists in new data
            if(newData.containsKey(key)){
                //Check its hash
                if(!referenceData.get(key).equals(newData.get(key))){
                    //They have unequal hash
                    foundPaths.add(key);
                }
            }
            //Indicates a new file has been added or renamed
            else{
                foundPaths.add(key);
            }
        }

        return foundPaths;
    }

}
