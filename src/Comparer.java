import java.util.ArrayList;
import java.util.SortedMap;

public class Comparer {
    public static ArrayList<String> generateOutput(SortedMap<String, String> referenceData, SortedMap<String, String> newData){

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
        System.out.println(foundPaths);
        return foundPaths;
    }
    public static void debug(SortedMap<String, String> reference, SortedMap<String, String> newData){
        System.out.println("Reference Data: ");
        for(String key: reference.keySet()){
            System.out.println(key+","+reference.get(key));
        }
        System.out.println("New Data: ");
        for(String key: newData.keySet()){
            System.out.println(key+","+newData.get(key));
        }
    }

}
