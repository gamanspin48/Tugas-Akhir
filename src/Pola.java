/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teguh Ikhlas
 */
public class Pola {
    
    private String awalan;
    private String harokat;
    private String akhiran;
    private String dhomir;
    private String fiil;
   

    public Pola() {
    }
    
    

    public Pola(String awalan, String akhiran) {
        this.awalan = awalan;
        this.akhiran = akhiran;
    }
    
    public Pola(String awalan, String horokat, String akhiran) {
        this.awalan = awalan;
        this.harokat = horokat;
        this.akhiran = akhiran;
    }

    public Pola(String awalan, String akhiran, String dhomir, String fiil) {
        this.awalan = awalan;
        this.akhiran = akhiran;
        this.dhomir = dhomir;
        this.fiil = fiil;
    }

    public Pola(String awalan, String harokat, String akhiran, String dhomir, String fiil) {
        this.awalan = awalan;
        this.harokat = harokat;
        this.akhiran = akhiran;
        this.dhomir = dhomir;
        this.fiil = fiil;
    }
    
    
    public String getPola(){
        
        return awalan+"|"+harokat+"|"+akhiran;
        
    }

    public String getDhomir() {
        return dhomir;
    }

    public String getFiil() {
        return fiil;
    }
    
    
    

    @Override
    public String toString() {
        return awalan+"|"+harokat+"|"+akhiran+"\n"+
                "dhomir : "+this.dhomir+"\n"+
                "fiil : "+this.fiil+"\n";
        
    }

   
    
    
    
}
