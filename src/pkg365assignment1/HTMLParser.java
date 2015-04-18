package pkg365assignment1;

import java.io.IOException;
import java.util.logging.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

//Reaches out to wikipedia article
//Parses Data
//Clean up input
//Splits parsed data into String[]
public class HTMLParser {
    private String[] str;
    
    public HTMLParser(){};
    
    public boolean setArray(String url){
        //parse html to return paragraphs without the <sup> tags
        try {
            url = url.trim();
            Document doc = Jsoup.connect(url).get();
            doc.select("sup").remove();
            Elements words = doc.select("p");
            String text = words.text().toString();

            //clean up by removing punctuation
            text = text.replaceAll("\\."," "); 
            text = text.replaceAll("'", " ");  
            text = text.replaceAll("\\?", " ");            
            text = text.replaceAll("!", " ");           
            text = text.replaceAll(":", " ");            
            text = text.replaceAll(";", " ");            
            text = text.replaceAll("\\(", " ");            
            text = text.replaceAll("\\)", " ");           
            text = text.replaceAll("\\[", " ");            
            text = text.replaceAll("\\]", " "); 
            text = text.replaceAll("\"", " ");
            text = text.replaceAll(",", " ");
            text = text.replaceAll("/", " ");
            text = text.replaceAll("-", " ");
            text = text.replaceAll("–", " ");
            //split into an array
            str = text.split("\\s+");
            return true;
        }//end try block
        
        catch (IOException ex) {
            //Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }//end catch block
    }//end getArray method
    
    public String[] getArray(){
        return str;
    }//end getArray method
}//end HTMLParser
