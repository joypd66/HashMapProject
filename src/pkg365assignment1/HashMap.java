package pkg365assignment1;

public class HashMap {
    private String url;
    private int SIZE;
    private HashEntry[] table;
    
    
    //HashMap Constructor
    public HashMap(String url, int size){
        this.url  = url.trim();
        this.SIZE = size * 2;
        this.table = new HashEntry[this.SIZE];
    }//end HashMap constructor
    
    //getURL method
    public String getURL(){
        return url;
    }//end get URL
    
    //return HashEntry for a particular stirng
    public HashEntry get(String k){
        //normalize the text
        k = k.trim();
        k = k.toLowerCase();
        
        int hash = Math.abs(k.hashCode()) % SIZE;
        HashEntry entry = table[hash];
        
        //if the entry exists do this
        while(entry != null){
            if(entry.key.equals(k)){
                return entry;
            }//end if statement
            entry = entry.next;
        }//end while loop
        
        //if the entry does not exist return null
        return null;
    }//end get method
    
    //add something to the hashmap
    public void put(String k, int v){
        //normalize the text
        k = k.trim();
        k = k.toLowerCase();
        
        //use the built in java hashcode
        int hash = Math.abs(k.hashCode()) % SIZE;
        HashEntry entry = table[hash];
        
        //if entry is not null
        if(entry != null){
            //if it is looking at the correct entry for that hashcode
            if(entry.key.equals(k)){
                entry.value = v;
            }//end if statement
            
            //if there is a collision find the next available slot
            else{
                while(entry.next != null){
                    entry = entry.next;
                }//end while loop
                HashEntry entryInOldBucket = new HashEntry(k,v);
                
                //create a new entry for it
                entry.next = entryInOldBucket;
            }//end else statement
        }//end if statement
        
        //if entry is null create a new bucket
        else{
            HashEntry entryInNewBucket = new HashEntry(k,v);
            table[hash] = entryInNewBucket;
        }//end else statement
    }//end put method
}//end HashMap class
