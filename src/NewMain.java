/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import edu.stanford.nlp.international.arabic.Buckwalter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.jqurantree.analysis.AnalysisTable;
import org.jqurantree.arabic.encoding.EncodingType;
import org.jqurantree.search.SearchOptions;
import org.jqurantree.search.TokenSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 *
 * @author Teguh Ikhlas
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    
   
    
    public static void main(String[] args) {
        // TODO code application logic here
        
//        try(Workbook wb = WorkbookFactory.create(new File("E:\\Kokodingan\\TA\\data.xls"))) {
//            
//            Sheet sheet = wb.getSheetAt(3);
//            
//            int rowStart = sheet.getFirstRowNum();
//            int rowEnd = sheet.getLastRowNum();
//            
//            for (int i = rowStart; i < rowEnd; i++) {
//                
//                Row row = sheet.getRow(i);
//                
//                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
//                    
//                    Cell cell = row.getCell(j);
//                    System.out.println(cell.getStringCellValue());
//                    
//                }
//                System.out.println("------------------------");
//                
//            }
//            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
        
        ArrayList<ArabicTeks> dataTest = new ArrayList<>();
        ArrayList<ArabicTeks> dataTestObj = new ArrayList<>();
        
        String csvFile = "C:\\Users\\ASUS R.O.G\\Downloads\\Data Test - Sheet4.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
  
                // use comma as separator
                String[] column = line.split(cvsSplitBy);
                String[] buckSplit = column[3].split("/");
                
                if (!column[0].equals("No")){
                   
                    
                    ArabicTeks dataBaru = new ArabicTeks(buckSplit[0], buckSplit[1], column[7],column[6] ,column[5]);
                   
                    dataTest.add(dataBaru);
                    
                    //System.out.println(dataBaru);
                    
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
        
        int salahInput = 0;
        int salahDhomir = 0;
        int salahFiil = 0;
        int salahWazan = 0;
        int salah = 0;
        float benar = 0;
        float jml = dataTest.size();
        
        float jmlSoft = jml*3;
        float benarSoft = 0;
        
        for(ArabicTeks data : dataTest){
            
            ArabicTeks testing = new ArabicTeks(data.getTokenBuck());
            
            System.out.println(testing.getTokenArab());
            //System.out.println(testing.getTengah());
           if (testing.isValidInput()){
               
                if (testing.isTeksSama(data)){
                
                benar++;
                testing.setKet("Benar");
                
            }else{
                
                salah++;
                if(!testing.getDhomir().equalsIgnoreCase(data.getDhomir())){
                    System.out.println("SALAH DHOMIR");
                    testing.setKet("|SALAH DHOMIR");
                    System.out.println(testing.getDhomir()+","+data.getDhomir());
                    salahDhomir++;
                }
                if(!testing.getFiil().equalsIgnoreCase(data.getFiil())){
                    System.out.println("SALAH FIIL");
                    testing.setKet("|SALAH FIIL");
                    System.out.println(testing.getFiil()+","+data.getFiil());
                    salahFiil++;
                }
                if(!testing.getWazan().equalsIgnoreCase(data.getWazan())){
                    System.out.println("SALAH WAZAN");
                    testing.setKet("|SALAH WAZAN");
                    System.out.println(testing.getWazan()+","+data.getWazan());
                    salahWazan++;
                }
            }
               
           }else{
               
               salah++;
               salahInput++; 
               System.out.println("SALAH INPUT");
               
           }
           
           if(testing.getDhomir().equalsIgnoreCase(data.getDhomir())){
                    
                    benarSoft++;
           }
            if(testing.getFiil().equalsIgnoreCase(data.getFiil())){
               benarSoft++;
            }
            if(testing.getWazan().equalsIgnoreCase(data.getWazan())){
                benarSoft++;
            }
           
            dataTestObj.add(testing);
        }
        
        System.out.println("Akurasi Streak = "+ ((float)(benar/jml))*100+"%");
        System.out.println("Akurasi Longgar = "+((float)(benarSoft/jmlSoft))*100+"%");
        System.out.println("jumlah benar = "+benar);
        System.out.println("jumlah salah = "+salah);
        System.out.println("salah dhomir = "+salahDhomir);
        System.out.println("salah fiil = "+salahFiil);
        System.out.println("salah wazan = "+salahWazan);
        System.out.println("salah input = "+salahInput);
        
                     String header = "No,Arab,Buckwalter,Awalan,Akhiran,Pola,Root,Wazan,Dhomir,Fiil,Pola Wazan,Keterangan\n";
         writeToCsv(dataTestObj,header,"hasil.csv");
         ArrayList<String> dataTest2 =  new Kamus().getFiilShahih();
         ArrayList<ArabicTeks> dataTestObj2 = new ArrayList<>();
         
         for(String arabTest : dataTest2){
             
            // System.out.println(arabTest);
             dataTestObj2.add(new ArabicTeks(arabTest));
             
         }
         writeToCsv(dataTestObj2,header,"hasil2.csv");
         
         ArrayList<ArabicTeks> dataHasil = new ArrayList<>();
         ArrayList<ArabicTeks> dataGagal = new ArrayList<>();
         
         for(ArabicTeks arabHasil : dataTestObj2){
             
             if(arabHasil.isComplete())
                 dataHasil.add(arabHasil);
             else
                 dataGagal.add(arabHasil);             
         }
        writeToCsv(dataHasil,header,"hasil3.csv");
        writeToCsv(dataGagal,header,"gagal.csv");
        float totalFiil = dataTestObj2.size(); 
        float totalGagal = totalFiil - dataHasil.size();
        float totalGagalP = totalGagal / totalFiil;
        float totalBerhasil = dataHasil.size();
        float totalBerhasilP = totalBerhasil / totalFiil;
        System.out.println("Total Fiil = "+ totalFiil);
        System.out.println("Fiil Gagal = "+totalGagal+" ("+(totalGagalP*100)+") %");
        System.out.println("Fiil Berhasil = "+(totalBerhasil)+" ("+(totalBerhasilP*100)+") %");
        
        ArrayList<ArabicTeks> dataGagalPasif = new ArrayList<>();
        ArrayList<ArabicTeks> dataGagalAktif = new ArrayList<>();
        
        for (ArabicTeks gagal : dataGagal){
            
            //System.out.println(gagal.getTokenBuck());
            if (Kamus.isPassive(gagal.getTokenBuck())){
                
                dataGagalPasif.add(gagal);
                //System.out.println("pasif");
                
            }else{
                
                dataGagalAktif.add(gagal);
               // System.out.println("bukan pasif");
                
            }
            
        }
        writeToCsv(dataGagalPasif,header,"gagal pasif.csv");
        writeToCsv(dataGagalAktif,header,"gagal bukan pasif.csv");
        
   //     System.out.println(new Buckwalter().buckwalterToUnicode("SadaquwA@"));
        //ArabicTeks tes = new ArabicTeks("nafidato");
        
        
        //System.out.println(tes);
        
   //     System.out.println(new Kamus().getLemma("qatalo"));
       // System.out.println(replaceLast("qatalota","ta",""));
      //  System.out.println(new Kamus().getLemma("taDoHaku"));
//           TokenSearch search = new TokenSearch(EncodingType.Buckwalter,
//				SearchOptions.RemoveDiacritics);
//          search.findSubstring("nfd");
//                AnalysisTable table = search.getResults();
//                for (int i = 0; i < table.getRowCount(); i++) {
//                    
//                    System.out.println(table.getString(i, "Token"));
//            
//        }
        
      
    }
    
    private static void writeToCsv(ArrayList<ArabicTeks> data,String header,String fileName){
        
         try (PrintWriter writer = new PrintWriter(new File(fileName))) {
           
             StringBuilder sb = new StringBuilder();
             

             sb.append(header);
             
             for (int i = 0; i < data.size(); i++) {
                 
                 sb.append(data.get(i).toCsv(String.valueOf(i+1)));
                 
             }
             
             writer.write(sb.toString());
             
//              return "Arab\t\t  "+ tokenArab+": \n"+
//                    "BuckWalter\t : "+tokenBuck+"\n"+
//                    "Awalan\t\t"+bw.buckwalterToUnicode(awalan)+": \n"+
//                    "Akhiran\t\t"+bw.buckwalterToUnicode(akhiran)+": \n"+
//                    "Pola\t\t : "+awalan+"|"+harokat+"|"+akhiran+"\n"+
//                    "Root\t\t"+bw.buckwalterToUnicode(root)+": \n"+
//                    "Wazan\t\t : "+this.wazan+"\n"+
//                    "Dhomir\t\t"+bw.buckwalterToUnicode(this.dhomir)+": \n"+
//                    "Fiil\t\t : "+this.fiil+"\n"+
//                    "Pola Wazan\t : "+listWazan[indexWazan].getPola()
//                    ;
             
//        StringBuilder sb = new StringBuilder();
//        sb.append("id");
//        sb.append(',');
//        sb.append("Name");
//        sb.append('\n');
//
//        sb.append("1");
//        sb.append(',');
//        sb.append("Prashant Ghimire");
//        sb.append('\n');
//
//        writer.write(sb.toString());
//
//        System.out.println("done!");

      } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
      }
        
    }
    
   
    
}
