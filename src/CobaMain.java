
import edu.stanford.nlp.international.arabic.Buckwalter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teguh Ikhlas
 */
public class CobaMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        String[] puas = new Wazan().getTextFromFile("E:\\Kokodingan\\TA\\list teks\\akhiranMadhi.txt");
//        for(String p : puas){
//            
//            System.out.println(p);
//            
//        }
        Buckwalter bw = new Buckwalter();
        System.out.println(bw.buckwalterToUnicode("{$ohado"));
        
        
    }
    
}
