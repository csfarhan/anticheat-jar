package DataStructs;

import java.util.Locale;

public class Filehash {
     private String fileName;
     private String hash;
    public Filehash(String fileN, String hash){
        this.fileName = fileN;
        this.hash = hash;
    }

    public String getHash(){
        return this.hash;
    }

    public String getFileName(){
        return this.fileName;
    }

    @Override
    public String toString(){
        return fileName + "," + hash;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) {
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        if(this.getHash().equals(((Filehash)obj).getHash())){
            return true;
        }
        return false;
    }
}
