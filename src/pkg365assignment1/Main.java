package pkg365assignment1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends javax.swing.JFrame{
    private static final String fileLocation = "/home/pjoy/NetBeansProjects/365Assignment1/webpages.txt";
    private static List<HashMap> list = new ArrayList<HashMap>();
    private static HTMLParser parser = new HTMLParser();
    private static javax.swing.JButton compareButton = new javax.swing.JButton();
    private static javax.swing.JLabel loadingLabel = new javax.swing.JLabel();
    private static javax.swing.JLabel compareLabel = new javax.swing.JLabel();
    private static javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    private static javax.swing.JTextField urlEntry = new javax.swing.JTextField();
    private static javax.swing.JLabel urlLabel = new javax.swing.JLabel();
    private static String newURL = "";
    //private static boolean action = false;
    
    public Main(){
        initComponents();
    }//end Main 
    
    private void initComponents(){
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        urlEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlEntryActionPerformed(evt);
            }
        });

        urlLabel.setText("Enter URL:");

        compareButton.setText("Compare");
        compareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareButtonActionPerformed(evt);
            }
        });

        compareLabel.setText("Most similar to:");
        loadingLabel.setText("Loading:");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(urlEntry)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(urlLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(compareLabel)
                            .addComponent(compareButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loadingLabel))
                        .addGap(0, 322, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(urlLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(urlEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compareButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compareLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadingLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }//end init components               
    
    private void urlEntryActionPerformed(ActionEvent evt) {
        
    }//end urlEntryActionPerformed
        
    private void compareButtonActionPerformed(ActionEvent evt){
        newURL = urlEntry.getText().trim();        
        loadingLabel.setEnabled(true);
        compareLabel.setEnabled(false);
        
        if(urlEntry.getText().trim().equals("")){
            loadingLabel.setText("Error: " + "No URL Entered");
        }//end else statement
        
        else{
            //action = true;
            loadingLabel.setText("Loading: " + newURL); 
        }//end else statement        
        //get and parse new URL        
        boolean pageRead = parser.setArray(newURL);
        
        //check to see if the url was read
//        if(pageRead == false){
//            loadingLabel.setText("Error: Unable to read URL.");
//        }//end if statement
        
        
        String[] newPage = parser.getArray();
        
        int max = 0;
        int indexOfMaxSum = -1;
        //Start Comparing
        for(int i = 0; i < list.size(); i++){
            int tempSum = 0;            
            loadingLabel.setText("Loading: Comparing to " + list.get(i).getURL());
            
            //check value of each word in each hash map.
            for(int j = 0; j< newPage.length; j++){
                if(list.get(i).get(newPage[j]) != null){
                    tempSum = tempSum + 1;
                }//end if statement
            }//end for loop
            
            if(tempSum > max){
                max = tempSum;
                indexOfMaxSum = i;
            }//end if statement
        }//end for loop
        
        if(indexOfMaxSum > -1){
            //loadingLabel.setText("Loading: Max = " + max + " i = " + indexOfMaxSum);
            loadingLabel.setText("Loading: Done");
            loadingLabel.setEnabled(false);
            compareLabel.setText("Most similar to: " + list.get(indexOfMaxSum).getURL());
            compareLabel.setEnabled(true);
        }//end if statement
        
        else{
            loadingLabel.setText("ERROR: index out of bounds | indexOfMaxSum < 0");
        }//end else
    }//end compareButtonActionPerformed
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        //Initiate JFrame
        compareButton.setEnabled(false);
        compareLabel.setEnabled(false);
        urlLabel.setEnabled(false);
        urlEntry.setEnabled(false);
        loadingLabel.setText("Loading: " + fileLocation);
        
        //1)Create a list to hold HashMap objects
        //Private Static sent to top of class
        
        //2)Get string of pages [THIS IS WORKING]
        FileIO file = new FileIO(fileLocation);
        String[] pages = file.getArray();
        
        //3)Setup parser
        //Private Static sent to top of class

        //Create a HashMap object for each page & parse values into it.
        for(int i = 0; i < pages.length; i++){
            

            //4)call the parser [THIS IS WORKING]
            parser.setArray(pages[i]);
            String[] stringArray = parser.getArray();
            
            //5)create a new hashmap object
            //6)add the new hashmap to the hashmap list
            loadingLabel.setText("Loading: " + pages[i]);
            //compareLabel.setText("" + stringArray.length);
            HashMap hashMap = new HashMap(pages[i], stringArray.length);
            list.add(i, hashMap);

            //DEBUGGING
            //System.out.println(Arrays.toString(stringArray));
            //System.out.println(stringArray.length);
            //7)send each value of the string array to the hashmap

            for(int j = 0; j < stringArray.length; j++){
                //if it doesn't exist in the hashmap, add it to the hashmap with a frq of 1

                //DEBUGGING
                //System.out.println(j);
                //System.out.println(stringArray[j].trim());

                //there is a god damned collision already!
                if(list.get(i).get(stringArray[j]) == null){
                    //DEBUGGING
                    //System.out.println("If Null Put");
                    //System.out.println(stringArray[j].trim());

                    list.get(i).put(stringArray[j], 1);
                }//end if statement

                //else if it does exist in the hash map, request it's value and increment by 1.
                else{
                    //DEBUGGING
                    //System.out.println("Else Put");
                    //System.out.println(stringArray[j].trim());

                    int frq = list.get(i).get(stringArray[j]).getValue();
                    list.get(i).put(stringArray[j], frq + 1);
                }//end else loop
            }//end for loop
        }//end for loop

        //DEBUGGING
        //        for(int i = 0; i < pages.length; i++){
        //            System.out.println("Pages Size: " + pages.length);
        //            System.out.println(pages[i]);
        //            System.out.println();
        //        }//end for loop
        //        
        //        //DEBUGGING
        //        for(int i = 0; i < list.size(); i++){            
        //            System.out.println("List Size: " + list.size());            
        //            System.out.println(list.get(i).getURL());
        //        }

        //Let User Enter Data 
        loadingLabel.setText("Loading: Done");   
        compareButton.setEnabled(true);
        urlLabel.setEnabled(true);
        urlEntry.setEnabled(true);
        
//        int count = 0;
//        //check to see if newAction performed
//        while(action == false){
//            count++;            
//            compareLabel.setText("Count: " + count);
//        }//end while loop
    }//end main    
}//end Main Class
