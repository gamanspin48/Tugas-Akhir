
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teguh Ikhlas
 */
public class Wazan {
    
    private String nama;
    private String jenis;
    private String polaFiilMadhi;
    private String polaFiilMudhari;
    private final String[] dhomir = {"huwa","huwa","huwa","humaA","humaA","humo","humo","humo","humo","hiya","humaA","humaA","hun~a","hun~a",">anota",">anotumaA",">anotumaA",">anotumo",
        ">anotumo",">anotumo",">anoti",">anoti",">anoti",">anotumaA",">anotumaA",">anotun~a",">anotun~a",">anaA",">anaA","naHonu"};
//    private final String[] dhomir = {"huwa","huwa","huwa","humaA","humo","humo","humo","hiya","humaA","hun~a",">anota",">anotumaA",">anotumo",">anotumo",
//                                ">anoti",">anoti",">anotumaA",">anotun~a",">anaA",">anaA","naHonu"};
    private final String[] fiil = {"madhi","mudhari"};
    
    private final String[] listAwalan = {"ya","ta",">a","na"};
    private final String[] listAkhiran = {"A","woA","to","taA","na","ta","tumaA","tumo","ti",
     "tun~a","tu","naA","Ani","wona","yona","wA","wA@"};
    

    
//    private final String[] dhomir = {"huwa",
//                                    "humaA",
//                                    "humaA",
//                                    "humaA",
//                                    "humo",
//                                    "humo",
//                                    "humo",
//                                    "humo",
//                                    "hiya",
//                                    "hiya",
//                                    "humaA",
//                                    "humaA",
//                                    "humaA",
//                                    "huna~a",
//                                    ">anota",
//                                    ">anotumaA",
//                                    ">anotumaA",
//                                    ">anotumaA",
//                                    ">anotumaA",
//                                    ">anotumo",
//                                    ">anotumo",
//                                    ">anotumo",
//                                    ">anotumo",
//                                    ">anoti",
//                                    ">anoti",
//                                    ">anotumaA",
//                                    ">anotumaA",
//                                    ">anotumaA",
//                                    ">anotun~a",
//                                    ">anaA",
//                                    ">anaA",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu",
//                                    "naHonu"};
//
//    private final String[] fiil = {"madhi","mudhari","fail","maful","amr","nahyi"};
////    -	Ani
////A = !	wna
////wA = W!	yna
////to = t	yo
////taA	ni
////na	taAni
////tumaA	yni
////pN	tayoni
////ti	
////tu	
////naA	
//    private final String[] listAwalan = {"ya","ta",">a","na","ma",">u","laAta"};
//    private final String[] listAkhiran = {"A","woA","to","taA","na","ta","tumaA","tumo","ti",
//     "tun~a","tu","naA","Ani","wona","yona"};
//    
    private Pola[][] pola = new Pola[this.dhomir.length][this.fiil.length];
    private final String path = "E:\\Kokodingan\\TA\\list teks\\";

    public Wazan() {
    }
    
    
    
    public Wazan(String nama,String harokatMadhi,String harokatMudhari) {
        
        this.nama = nama;
        this.polaFiilMadhi = harokatMadhi;
        this.polaFiilMudhari = harokatMudhari;
        initWazanMadhi(harokatMadhi);
        initWazanMudhari(harokatMudhari);
     //   System.out.println(pola[7][1]);
        
    }

    public Wazan(String nama, String jenis, String polaFiilMadhi, String polaFiilMudhari) {
        this.nama = nama;
        this.jenis = jenis;
        this.polaFiilMadhi = polaFiilMadhi;
        this.polaFiilMudhari = polaFiilMudhari;
        initWazanMadhi(polaFiilMadhi);
        initWazanMudhari(polaFiilMudhari);
    }
    
    
    
    private void initWazanMadhi(String harokat){
        
         String[] listAkhiranMadhi = {"","","","A","A","woA","wA","wA","wA@","to","taA","taA","na","na","ta","tumaA","tumaA","tumo","tumo","tum","ti","ti","ti","tumaA","tumaA",
        "tun~a","tun~a","tu","tu","naA"};
         String[] listHarokatAkhirMadhi = {"a","a","a","a","a","u","u","u","u","a","a","a","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o"};
        
         
        for (int i = 0; i < dhomir.length; i++) {
            
                if(this.jenis.equals("aktif"))    
                    pola[i][0] = new Pola("", "a"+harokat+listHarokatAkhirMadhi[i],listAkhiranMadhi[i], dhomir[i], fiil[0]);
                else
                    pola[i][0] = new Pola("", "u"+harokat+listHarokatAkhirMadhi[i],listAkhiranMadhi[i], dhomir[i], fiil[0]);
     
                
            
        }
        
        
    }
    
    private void initWazanMudhari(String harokat){
         
        String[] listAwalanMudhari = {"ya","ya","ya","ya","ya","ya","ya","ya","ya","ta","ta","ta","ya","ya","ta","ta","ta","ta","ta","ta","ta","ta","ta","ta","ta","ta","ta",">a","{","na"};
        String[] listAwalanMudhariPasif = {"yu","yu","yu","yu","yu","yu","yu","yu","yu","tu","tu","tu","yu","yu","tu","tu","tu","tu","tu","tu","tu","tu","tu","tu","tu","tu","tu",">u","{","nu"};
         String[] listAkhiranMudhari = {"","","","Ani","A","wona","wona","wna","wA@","","Ani","A","na","","","Ani","A","wona","wna","wA@","yona","yna","yA@","Ani","A","na","","","",""};
         String[] listHarokatAkhirMudhari = {"u","a","a","a","a","u","u","u","u","u","a","a","o","o","u","a","a","u","u","u","i","i","i","a","a","o","o","u","u","u"};
        
                     

        for (int i = 0; i < dhomir.length; i++) {
            
           if (jenis.equals("aktif")){
               
                if (dhomir[i].equalsIgnoreCase(">anota")||dhomir[i].equalsIgnoreCase("hiya")){
                
                pola[i][1] = new Pola(listAwalanMudhari[i],"o"+harokat+listHarokatAkhirMudhari[i],listAkhiranMudhari[i], ">anota/hiya", fiil[1]);
                
                }else{
                
                    pola[i][1] = new Pola(listAwalanMudhari[i],"o"+harokat+listHarokatAkhirMudhari[i],listAkhiranMudhari[i], dhomir[i], fiil[1]);
                
                }
               
           }else{
               
                if (dhomir[i].equalsIgnoreCase(">anota")||dhomir[i].equalsIgnoreCase("hiya")){
                
                    pola[i][1] = new Pola(listAwalanMudhariPasif[i],"o"+harokat+listHarokatAkhirMudhari[i],listAkhiranMudhari[i], ">anota/hiya", fiil[1]);
                
                }else{
                    
                    pola[i][1] = new Pola(listAwalanMudhariPasif[i],"o"+harokat+listHarokatAkhirMudhari[i],listAkhiranMudhari[i], dhomir[i], fiil[1]);
                
                }
               
           }
        }
        
        
    }
    
//    private void initWazanMadhi(String harokat){
//        
//         String[] listAkhiranMadhi = getTextFromFile("akhiranMadhi.txt");
//         String[] listHarokatAkhirMadhi = getTextFromFile("harokatAkhirMadhi.txt");
//        
//         
//        for (int i = 0; i < dhomir.length; i++) {
//            
//            pola[i][0] = new Pola("", "a"+harokat+listHarokatAkhirMadhi[i],listAkhiranMadhi[i], dhomir[i], fiil[0]);
//            
//        }
//        
//    }
//    
//    private void initWazanMudhari(String harokat){
//        
//        String[] listAwalanMudhari = getTextFromFile("awalanMudhari.txt");
//        String[] listAkhiranMudhari = getTextFromFile("akhiranMudhari.txt");
//        String[] listHarokatAkhirMudhari = getTextFromFile("harokatAkhirMudhari.txt");
//        
//         
//        for (int i = 0; i < dhomir.length; i++) {
//            
//            if (dhomir[i].equalsIgnoreCase(">anota")||dhomir[i].equalsIgnoreCase("hiya")){
//                
//                pola[i][1] = new Pola(listAwalanMudhari[i],"o"+harokat+listHarokatAkhirMudhari[i],listAkhiranMudhari[i], ">anota/hiya", fiil[1]);
//                
//            }else{
//                
//                pola[i][1] = new Pola(listAwalanMudhari[i],"o"+harokat+listHarokatAkhirMudhari[i],listAkhiranMudhari[i], dhomir[i], fiil[1]);
//                
//            }
//        }
//        
//    }
//    
//    private void initWazanFail(){
//        
//        String[] listAkhiranFail = getTextFromFile("akhiranFail.txt");
//        String[] listHarokatAkhirFail = getTextFromFile("harokatAkhirFail.txt");
//        
//         
//        for (int i = 0; i < dhomir.length; i++) {
//                
//            pola[i][2] = new Pola("","aAi"+listHarokatAkhirFail[i],listAkhiranFail[i],dhomir[i], fiil[2]);
//                
//          
//        }
//        
//    }
//    
//    private void initWazanMaful(){
//        
//        String[] listAkhiranMaful = getTextFromFile("akhiranMaful.txt");
//        String[] listHarokatAkhirMaful = getTextFromFile("harokatAkhirMaful.txt");
//        
//         
//        for (int i = 0; i < dhomir.length; i++) {
//                
//            pola[i][3] = new Pola("ma","uwo"+listHarokatAkhirMaful[i],listAkhiranMaful[i],dhomir[i], fiil[3]);
//                
//          
//        }
//        
//    }
//    
//    private void initWazanAmr(String awalan,String harokat){
//        
//        String[] listAkhiranAmr = getTextFromFile("akhiranAmr.txt");
//        String[] listHarokatAkhirAmr = getTextFromFile("harokatAkhirAmr.txt");
//        
//         
//        for (int i = 0; i < dhomir.length; i++) {
//                
//            pola[i][4] = new Pola(awalan,"o"+harokat+listHarokatAkhirAmr[i],listAkhiranAmr[i],dhomir[i], fiil[4]);
//                
//          
//        }
//        
//    }
//    
//    private void initWazanNahyi(String harokat){
//        
//        String[] listAkhiranNahyi = getTextFromFile("akhiranNahyi.txt");
//        String[] listHarokatAkhirNahyi = getTextFromFile("harokatAkhirNahyi.txt");
//        
//         
//        for (int i = 0; i < dhomir.length; i++) {
//                
//            pola[i][5] = new Pola("laAta","o"+harokat+listHarokatAkhirNahyi[i],listAkhiranNahyi[i],dhomir[i], fiil[5]);
//                
//          
//        }
//        
//    }
//    

    public String getNama() {
        return nama;
    }
    
    
    
    public String[] getCocokWazan(String polaCari){
        
        String[] hasil = null;
        boolean found = false;
        //System.out.println(polaCari);
        for (int i = 0; i < dhomir.length; i++) {
            
            for (int j = 0; j < fiil.length; j++) {
               // System.out.println(polaCari);
                if (pola[i][j].getPola().equals(polaCari)){
                    
                   // System.out.println("ketemu");
                    found = true;
                    hasil = new String[3];
                    hasil[0] = nama;
                    hasil[1] = pola[i][j].getDhomir();
                    hasil[2] = pola[i][j].getFiil();
                    
                }
                
                
            }
            
            if(found)
                break;
            
        }
        
        return hasil;
        
    }
    
    public boolean isPolaSama(String polaMadhi,String polaMudhari){
        
        return this.polaFiilMadhi.equals(polaMadhi) && this.polaFiilMudhari.equals(polaMudhari);
        
    }
    
    public String getPola(){
        
        return polaFiilMadhi+polaFiilMudhari;
        
    }
    
   public String[] getTextFromFile(String namaFile){
       
       String[] hasil = null;
       String fileName = path+namaFile;
       
       StringBuilder sb = new StringBuilder();
        try {
            // Use this for reading the data.
            byte[] buffer = new byte[1000];

            FileInputStream inputStream = 
                new FileInputStream(fileName);

            // read fills buffer with data and returns
            // the number of bytes read (which of course
            // may be less than the buffer size, but
            // it will never be more).
            int total = 0;
            int nRead = 0;
            while((nRead = inputStream.read(buffer)) != -1) {
                // Convert to String so we can display it.
                // Of course you wouldn't want to do this with
                // a 'real' binary file.
                sb.append(new String(buffer));
                sb.append(",");
                //System.out.println(new String(buffer));
                total += nRead;
            }   

            // Always close files.
            inputStream.close();        

            System.out.println("Read " + total + " bytes");
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
       hasil = sb.toString().split(",");
       return hasil;
   } 
    
   
    
}
