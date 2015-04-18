package pkg365assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
This class will open the file in which the wikipedia pages are stored and
it will place them into a neat list.
*/

public class FileIO {
    private String fileLocation;    
    
    //FIleIO Constructor
    public FileIO(String fileLocation){
        this.fileLocation = fileLocation;
    }//end FileIO()
    
    //setFileLocation
    public void setFileLocation(String fileLocation){
        this.fileLocation = fileLocation;
    }//end setFileLocation
    
    //getFileLocation
    public String getFileLocation(){
        return fileLocation;
    }//end getFileLocation
    
    //getArray
    public String[] getArray(){
        FileReader file; 
        String str = "";
        
        try {
            file = new FileReader(fileLocation);       
            BufferedReader in = new BufferedReader(file);
            String temp;
            while ((temp = in.readLine()) != null) {
              str = str + temp;
            }//end while loop
            in.close();
            
            //DEBUGGING
            //System.out.println(str);
        }//end try block
        catch (IOException e) {
            System.out.println("ERROR: Problem Reading File");
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, e);
        }//end catch block
        
        //DEBUGGING
        //System.out.println(Arrays.toString(str.split(",")));
        return str.split(",");
    }//end list method    
}//endFileIO
