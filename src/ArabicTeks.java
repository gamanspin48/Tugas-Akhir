
import edu.stanford.nlp.international.arabic.Buckwalter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import org.jqurantree.analysis.AnalysisTable;
import org.jqurantree.arabic.encoding.EncodingType;
import org.jqurantree.search.SearchOptions;
import org.jqurantree.search.TokenSearch;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teguh Ikhlas
 */
public class ArabicTeks {
    
     private String tokenBuck;
     private String tokenArab;
     private String awalan;
     private String akhiran;
     private String tengah;
     private String root;
     private String harokat;
     private String lemma;
     private String tempWazan;
     private String wazan;
     private String dhomir;
     private String fiil;
     private int indexWazan;
     private String ket = "";
     
     private Wazan[] listWazan = {
            new Wazan("1","aktif","a","u"),
            new Wazan("2","aktif","a","i"),
            new Wazan("3","aktif","a","a"),
            new Wazan("4","aktif","i","a"),
            new Wazan("5","aktif","u","u"),
            new Wazan("6","aktif","i","i"),
            new Wazan("pasif","pasif","i","a")
            };
     

     
    // private final String[] listAwalan = {"ya","ta",">a","na","yu","tu",">u","nu"};
     private final String[] listAwalan = {"ya","ta",">a","na","{"};
     private final String[] listAkhiran = {"A","woA","wA","wA@","to","taA","na","ta","tumaA","tumo","ti",
     "tun~a","tu","Ani","wona","wna","yona","yna","yA@","naA"};
    // private boolean isArabTeks;

    public ArabicTeks(String token) {
        
        Arrays.sort(listAkhiran,new java.util.Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        // TODO: Argument validation (nullity, length)
        return s2.length() - s1.length();// comparision
    }});
        Buckwalter bw = new Buckwalter();
        String convertBuck = bw.unicodeToBuckwalter(token);
        String convertUni = bw.buckwalterToUnicode(token);
             
        if (token.equals(convertBuck)){
            
          //  System.out.println("teks adalah buckwalter");
            //isArabTeks = false;
            tokenBuck = token;
            tokenArab = convertUni;
            
        }else{
            
          //  System.out.println("teks adalah arab");
           // isArabTeks = true;
            tokenBuck = convertBuck;
            tokenArab = token;
            
        }
        
      
        this.awalan = calcAwalan();
        this.akhiran = calcAkhiran();
        this.tengah = calcTengah();
      //  System.out.println(this.tengah);
//        if (this.tengah == null){
//            
//            System.out.println("inputan invalid");
//            
//        }
 //       System.out.println(this.tengah.length());
       
       if (this.tengah.length()>=6){
          // System.out.println("masuk");
            this.harokat = calcHarokat();
        this.root = calcRoot();
          String[] w = calcTempWazan();
       if (w!= null){
           
           //System.out.println("tidak null");
            this.tempWazan = w[0];
            this.dhomir = w[1];
            this.fiil = w[2];
           this.wazan = calcWazan();
       }
           
       }else{
           
           this.tempWazan = null;
           
       }
        
        
        //System.out.println(this.toString());
        
    }

    public ArabicTeks(String token,String ket) {
        
        Arrays.sort(listAkhiran,new java.util.Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        // TODO: Argument validation (nullity, length)
        return s2.length() - s1.length();// comparision
    }});
        Buckwalter bw = new Buckwalter();
        String convertBuck = bw.unicodeToBuckwalter(token);
        String convertUni = bw.buckwalterToUnicode(token);
             
        if (token.equals(convertBuck)){
            
            //System.out.println("teks adalah buckwalter");
            //isArabTeks = false;
            tokenBuck = token;
            tokenArab = convertUni;
            
        }else{
            
            //System.out.println("teks adalah arab");
           // isArabTeks = true;
            tokenBuck = convertBuck;
            tokenArab = token;
            
        }
        
      
        this.awalan = calcAwalan();
        this.akhiran = calcAkhiran();
        this.tengah = calcTengah();
        
//        if (this.tengah == null){
//            
//            System.out.println("inputan invalid");
//            
//        }
       
       // System.out.println(this.tengah);
       if (this.tengah.length()>=6){
           
            this.harokat = calcHarokat();
          String[] w = calcTempWazan();
        if (w!= null){

             this.tempWazan = w[0];
             this.dhomir = w[1];
             this.fiil = w[2];
        }
           
       }else if (this.tengah.length()==5){
           
         //  System.out.println("masuk");
           char[] newTengah = new char[6];
           int index = 0;
           for (int i = 0; i < 6; i++) {
               
               if(i==1){
                   
                   newTengah[i] = 'o';
                   
               }else{
                   
                   newTengah[i] = this.tengah.charAt(index);
                   index++;
                   
               }
               
           }
           
           this.tengah = String.valueOf(newTengah);
        //   System.out.println("tengah = "+this.tengah);
             this.harokat = calcHarokat();
            String[] w = calcTempWazan();
          if (w!= null){

               this.tempWazan = w[0];
               this.dhomir = w[1];
               this.fiil = w[2];
          }
           
       }else{
           
           this.tempWazan = null;
           
       }
        
    }

    public ArabicTeks(String tokenBuck, String root, String wazan, String dhomir, String fiil) {
        this.tokenBuck = tokenBuck;
        this.tokenArab =  new Buckwalter().buckwalterToUnicode(tokenBuck);
        this.root = root;
        this.tempWazan = wazan;
        this.wazan = wazan;
        this.dhomir = dhomir;
        this.fiil = fiil;
        this.awalan = calcAwalan();
        this.akhiran = calcAkhiran();
        this.tengah = calcTengah();
    //    System.out.println(this.awalan);
      //  System.out.println(this.tengah);
       // System.out.println(this.akhiran);
       // this.harokat = calcHarokat();
       // System.out.println(getPola());
        
//        if (this.tengah == null){
//            
//            System.out.println("inputan invalid");
//            
//        }
       
        //this.harokat = calcHarokat();
        this.root = calcRoot();
    }
    
    
    
    
    public boolean isLemma(){
        
     //   System.out.println(tokenBuck.length());
        return tokenBuck.length() <= 6;
        
    }
    
    public boolean isAnomally(){
        
        return (this.tokenBuck.length() >= 11 && this.akhiran.equals(""));
        
    }
    
    public boolean isComplete(){
        
        return this.harokat!=null && this.wazan!=null && this.dhomir!=null && this.fiil!=null;
        
    }

    public String getTokenBuck() {
        return tokenBuck;
    }

    public String getTokenArab() {
        return tokenArab;
    }
    
    private String calcAwalan(){
        
        String hasil = "";
        
        if (!isLemma()){
            
            String tempAwalan = tokenBuck.substring(0,2);
          //  System.out.println("tempAwalan = "+tempAwalan);
            for(String item : listAwalan){
                
                if (item.equals(tempAwalan)){
    
                    hasil = item;
                    
                }
            }
            
        }
        return hasil;
        
        
    }
    
    private String calcAkhiran(){
        
        String hasil = "";
        
        if (!isLemma()){
            
            int minLetterAkhiran = tokenBuck.length()-5;
            String tempAkhiran;
            boolean found = false;
            
            for (int i = minLetterAkhiran; i < tokenBuck.length(); i++) {
                
                tempAkhiran = tokenBuck.substring(i,tokenBuck.length());
                
                
                for (String akhiran : listAkhiran){
                    
                    if (tempAkhiran.equals(akhiran)){
                        
                        hasil = akhiran;
                        found = true;
                        break;
                        
                    }
                    
                }
                
                if (found)
                    break;
                
            }
            
        }
        
        return hasil;
        
    }
    
    private String calcTengah(){
        
        String hasil = tokenBuck;
        
        if (!isLemma()){
            
            if(!awalan.equals("") && !akhiran.equals("")){
              
                hasil = hasil.replace(awalan, "");
                
                if (hasil.length()<=6){
                    
                    hasil = this.awalan+hasil;
                    this.awalan = "";
                    
                }
                
                hasil = hasil.replace(akhiran, "");
                
                
            }else if (!awalan.equals("")){
                
               
                hasil = hasil.replace(awalan, "");
              
                
            }else{
                
               hasil = replaceLast(hasil,akhiran,"");
              //  hasil = hasil.replace(akhiran, "");
                
                
            }
            
            
        }
        
       
        
        
        return hasil;
        
    }
    
     private String replaceLast(String string, String from, String to) {
        int lastIndex = string.lastIndexOf(from);
         if (lastIndex < 0) return string;
         String tail = string.substring(lastIndex).replaceFirst(from, to);
         return string.substring(0, lastIndex) + tail;
    }
    
    private String calcRoot(){
        
        String hasil = "";
       // System.out.println(this.tengah);
        
        hasil = String.valueOf(tengah.charAt(0))+String.valueOf(tengah.charAt(2))+String.valueOf(tengah.charAt(4));
                
        return hasil;
        
    }
    
    private String calcHarokat(){
        
        String hasil = "";
        
        //System.out.println(this.tengah.length());
        hasil = String.valueOf(tengah.charAt(1))+String.valueOf(tengah.charAt(3))+String.valueOf(tengah.charAt(5));
        
        return hasil;
        
    }
    
    private String[] calcTempWazan(){
        
        String[] hasil = null;
        
        for (int i = 0; i < listWazan.length; i++) {
            
            String[] tempHasil = listWazan[i].getCocokWazan(awalan+"|"+harokat+"|"+akhiran);
            
            if (tempHasil!=null){
                hasil = tempHasil;
               try{
                   
                    
                    this.indexWazan = Integer.parseInt(hasil[0])-1;
                    break;
                   
               }catch(NumberFormatException e){
                   this.indexWazan = this.listWazan.length-1;
               }
                
            }
            
        }
        
        return hasil;
        
    }
    
    private String calcWazan(){
        
        String hasil = "tidak terdefinisi";
        
        
        String[] w = calcTempWazan();
        String tempWazan = w[0];
        this.dhomir = w[1];
        this.fiil = w[2];
        
       if (tempWazan.equals("5")){
           
           hasil = tempWazan;
           
       }else{
           
            if (this.fiil.equalsIgnoreCase("madhi")){
                    
                
                TokenSearch search = new TokenSearch(EncodingType.Buckwalter,
				SearchOptions.RemoveDiacritics);

                search.findSubstring(this.root);
                AnalysisTable table = search.getResults();
                
                for (int i = 0; i < table.getRowCount(); i++) {
                    
                    ArabicTeks newArab = new ArabicTeks(table.getString(i, "Token"),"testing");
                   //  System.out.println(newArab.getTokenBuck());
                 
                    if (newArab.isValidInput() && newArab.getFiil().equals("mudhari") && !newArab.isAnomally()){
                        
                         
                       // System.out.println(newArab.getTokenBuck());
                       //   System.out.println(newArab.getPola());
                        hasil = newArab.getWazanAsli(String.valueOf(this.harokat.charAt(1)), String.valueOf(newArab.getHarokat().charAt(1)));
                        break;
                            
                        
                    }
                    
                }
                
//                if(tempWazan.equals("1")||tempWazan.equals("2")||tempWazan.equals("3")){
//                    
//                    
//                    
//                    
//                }
            
            }else{

//                  TokenSearch search = new TokenSearch(EncodingType.Buckwalter,
//				SearchOptions.RemoveDiacritics);
//
//                search.findSubstring(this.root);
//                AnalysisTable table = search.getResults();
//                
//                for (int i = 0; i < table.getRowCount(); i++) {
//                    
//                    ArabicTeks newArab = new ArabicTeks(table.getString(i, "Token"),"testing");
//                   // System.out.println(newArab.getTokenBuck());
//                    if (newArab.isValidInput() && newArab.getFiil().equals("madhi")){
//                        
//                        
//                        hasil = newArab.getWazanAsli(String.valueOf(newArab.getHarokat().charAt(1)), String.valueOf(this.harokat.charAt(1)));
//                        break;
//                            
//                        
//                    }
//                    
//                }
                ArabicTeks newArab;
              //  System.out.println(this.tokenBuck);
                if (isLemma()){
                    
                 //   System.out.println(this.tengah);
               //     System.out.println("lemma = "+new Kamus().getLemma(this.tengah));
                    newArab = new ArabicTeks(new Kamus().getLemma(this.tengah), "testing");
                    
                }else{
                    
                    
                    //System.out.println(this.awalan+this.tengah);
                   // System.out.println("lemma = "+new Kamus().getLemma(this.awalan+this.tengah));
                    newArab = new ArabicTeks(new Kamus().getLemma(this.awalan+this.tengah), "testing");
                        
                    
                    
                }
               // System.out.println(this.harokat);
                //System.out.println(newArab.getHarokat());
               try{
                   
                    hasil = newArab.getWazanAsli(String.valueOf(newArab.getHarokat().charAt(1)), String.valueOf(this.harokat.charAt(1)));
                   
               }catch(NullPointerException e){
                   
                   hasil = "tak terdefinisi";
                   
               }

            }
           
       }
        
        
        
        return hasil;
        
    }
    
    public String getWazanAsli(String polaMadhi,String polaMudhari){
        
        String hasil = "tidak terdefinisi";
        
        for (int i = 0; i < listWazan.length; i++) {
            
            if (listWazan[i].isPolaSama(polaMadhi, polaMudhari)){
                hasil = listWazan[i].getNama();
                break;
            }
            
        }
        
        return hasil;
        
    }
    
    public boolean isTeksSama(ArabicTeks teks){
        
        return this.dhomir.equalsIgnoreCase(teks.getDhomir())&&
                this.fiil.equalsIgnoreCase(teks.getFiil()) &&
                this.wazan.equalsIgnoreCase(teks.getWazan());
        
    }

    public String getAwalan() {
        return awalan;
    }

    public String getAkhiran() {
        return akhiran;
    }

    public String getTengah() {
        return tengah;
    }
    
    public boolean isValidInput(){
        
        return this.tempWazan != null;
        
    }
    
    

    public String getTempWazan() {
        return tempWazan;
    }

    public String getFiil() {
        return fiil;
    }

    public String getHarokat() {
        return harokat;
    }

    public String getWazan() {
        return wazan;
    }

    public String getDhomir() {
        return dhomir;
    }
    
    
    public String getPola(){
        
        return awalan+"|"+harokat+"|"+akhiran;
        
    }

    public void setKet(String ket) {
        this.ket = this.ket +ket;
    }
    
    
    
    public String toCsv(String no){
        Buckwalter bw = new Buckwalter();
        
        String hasil1;
        
        try{
            
            hasil1 = no+','+
                tokenArab+','+
                tokenBuck+','+
                bw.buckwalterToUnicode(awalan)+','+
                bw.buckwalterToUnicode(akhiran)+','+
                awalan+"|"+harokat+"|"+akhiran+','+
                bw.buckwalterToUnicode(root)+','+
                this.wazan+','+
                bw.buckwalterToUnicode(this.dhomir)+','+
                this.fiil+','+
                listWazan[indexWazan].getPola()+','+'\n';
            
        }catch(NullPointerException e){
            
     
            
            hasil1 = no+','+
                tokenArab+','+
                tokenBuck+','+
                bw.buckwalterToUnicode(awalan)+','+
                bw.buckwalterToUnicode(akhiran)+','+
                awalan+"|"+harokat+"|"+akhiran+','+
                root+','+
                this.wazan+','+
                this.dhomir+','+
                this.fiil+','+
                listWazan[indexWazan].getPola()+','+'\n';
            
        }
        
        
        
        return hasil1;
    }

    @Override
    public String toString() {
        Buckwalter bw = new Buckwalter();
        
        if (!isValidInput()){
            
            return "input error";
            
        }else{
        
            return "Arab\t\t  "+ tokenArab+": \n"+
                    "BuckWalter\t : "+tokenBuck+"\n"+
                    "Awalan\t\t"+bw.buckwalterToUnicode(awalan)+": \n"+
                    "Akhiran\t\t"+bw.buckwalterToUnicode(akhiran)+": \n"+
                    "Pola\t\t : "+awalan+"|"+harokat+"|"+akhiran+"\n"+
                    "Root\t\t"+bw.buckwalterToUnicode(root)+": \n"+
                    "Wazan\t\t : "+this.wazan+"\n"+
                    "Dhomir\t\t"+bw.buckwalterToUnicode(this.dhomir)+": \n"+
                    "Fiil\t\t : "+this.fiil+"\n"+
                    "Pola Wazan\t : "+listWazan[indexWazan].getPola()
                    ;
        }
    }
    
    
    
    
    
}
