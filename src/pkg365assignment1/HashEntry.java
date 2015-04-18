package pkg365assignment1;

public class HashEntry {
        final String key;
        int value;
        HashEntry next;
        
        //HashEntry Constructor
        public HashEntry(String k, int v){
            key = k;
            value = v;
        }//end entry method
        
        //getValue
        public int getValue(){
            return value;
        }//end getValue
        
        //setValue
        public void setValue(int value){
            this.value = value;
        }//end setValue
        
        //getKey
        public String getKey(){
            return key;
        }//end getKey
}//end Entry class