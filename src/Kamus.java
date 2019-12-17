
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teguh Ikhlas
 */
public class Kamus {
    
    private static final String path = "D:\\Program Files Laptop Opik\\Gdrive\\Folder Data Utama\\Kuliah\\S2\\Thesis\\program\\Quran Corpus - Data.csv";
     private final String[] listAkhiran = {"A","woA","wA","wA@","to","taA","na","ta","tumaA","tumo","ti",
     "tun~a","tu","Ani","wona","wna","yona","yna","naA"};

    public Kamus() {
    }
    
    public String getLemma(String tokenBuck){
        
        String hasil = "";
        
          BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null && hasil.equals("")) {
  
                // use comma as separator
                String[] column = line.split(cvsSplitBy);
                
                if (column[1].equals(tokenBuck)){
                    
                     String[] buckSplit = column[3].split("/");
                
             
                    for(String token : buckSplit){

                        if(token.contains("LEM")){

                           hasil = token.split(":")[1];
                           ArabicTeks tesArab = new ArabicTeks(hasil,"testing");
                           try{
                               
                               if (hasil.length()!=6 && !tesArab.isValidInput() && tesArab.getFiil().equalsIgnoreCase("madhi")){
                               
                                    hasil = "";
                               
                                }
                               
                           }catch(NullPointerException e){
                               
                               hasil = "";
                               
                           }

                        }

                    }
                    
                }
                
              
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
     
        return hasil;
        
    }
    
    public ArrayList<String> getFiilShahih(){
        
        ArrayList<String> hasil = new ArrayList<String>();
        boolean isVerb = false;
        boolean isMutal = false;
        boolean isValidLemma = false;
         
          BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
  
                // use comma as separator
                String[] column = line.split(cvsSplitBy);
                String[] buckSplit = column[3].split("/");
                
                   for(String token : buckSplit){

                        if(token.contains("POS")){

                           String pos = token.split(":")[1];
                           isVerb = pos.equals("V");
                           

                        }
                        if (token.contains("ROOT")){
                            
                           String root = token.split(":")[1];
                           isMutal = root.contains("A")||root.contains("w")||root.contains("y");
                            
                        }
                        if (token.contains("LEM")){
                            
                            String lem = token.split(":")[1];
                            isValidLemma = lem.length() <= 6;
                            
                        }
                
            
                
              
                    }
                   
                   if (isVerb && !isMutal && isValidLemma && column[1].length() >= 6 && !column[1].contains("~")){
                       
                       
                       String pron = br.readLine();
                       String[] column2 = pron.split(cvsSplitBy);
                       
                       if (column2[2].equals("PRON")&& isAkhiran(column2[1])){
                           
                            hasil.add(column[1]+column2[1]);
                            //System.out.println(column[1]+column2[1]); 
                           
                       }else{
                           
                          hasil.add(column[1]);
                          //System.out.println(column[1]); 
                           
                       }
                       
                           
                           
                       
                       
                   }
                   
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return hasil;
        
    }
    
    public int getTotalFiil(){
        
        int hasil = 0;
    
         
          BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
  
                // use comma as separator
                String[] column = line.split(cvsSplitBy);
                if (column[2].equals("V")) hasil++;
                   
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return hasil;
        
    }
    
    public static boolean isPassive(String fiil){
        
        boolean hasil = false;
        
         BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null && !hasil) {
  
                // use comma as separator
                String[] column = line.split(cvsSplitBy);
             
                if (column[1].equals(fiil)){
                    
                    String[] buckSplit = column[3].split("/");
                    for(String token : buckSplit){
                           
                
                        if(token.contains("PASS")){

                          hasil = true;
                          break;

                        }
                      
                
            
                
              
                    }
                    
                }
                   
                
                   
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return hasil;
        
    }
    
    public ArrayList<String> getListRoot(){
        
        ArrayList<String> hasil = new ArrayList<String>();
    
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
  
                // use comma as separator
                String[] column = line.split(cvsSplitBy);
                String[] buckSplit = column[3].split("/");
                
                   for(String token : buckSplit){

                        if (token.contains("ROOT")){
                            
                          String root = token.split(":")[1];
                          if (!hasil.contains(root))
                              hasil.add(root);
                            
                        }
              
                    }
                
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
       
        
        return hasil;
        
    }
    
//    private ArrayList<String> getPassiveVerb(){
//        
//        ArrayList<String> hasil = new ArrayList<String>();
//        
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";
//
//        try {
//
//            br = new BufferedReader(new FileReader(path));
//            while ((line = br.readLine()) != null && hasil.equals("")) {
//  
//                // use comma as separator
//                String[] column = line.split(cvsSplitBy);
//                
//                if (column[1].equals(tokenBuck)){
//                    
//                     String[] buckSplit = column[3].split("/");
//                
//             
//                    for(String token : buckSplit){
//
//                        if(token.contains("LEM")){
//
//                           hasil = token.split(":")[1];
//                           ArabicTeks tesArab = new ArabicTeks(hasil,"testing");
//                           try{
//                               
//                               if (hasil.length()!=6 && !tesArab.isValidInput() && tesArab.getFiil().equalsIgnoreCase("madhi")){
//                               
//                                    hasil = "";
//                               
//                                }
//                               
//                           }catch(NullPointerException e){
//                               
//                               hasil = "";
//                               
//                           }
//
//                        }
//
//                    }
//                    
//                }
//                
//              
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        
//        return hasil;
//        
//    }
    
    
    private boolean isAkhiran(String akhiran){
        
        boolean hasil = false;
        
        for(String item : listAkhiran){
            
            if (item.equals(akhiran)){
                
                hasil = true;
                break;
                
            }
            
        }
        
        return hasil;
        
    }
    
}
