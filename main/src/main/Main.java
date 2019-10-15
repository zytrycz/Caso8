/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import bits.Bits;

/**
 *
 * @author brymo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Bits prueba = new Bits();
        int[] prb = new int[] {255,25600,32768,32896,35723,49151,52945,64154,65280,65407,65535,65535,1644912,2003199,2142890,2263842,
                                                    3050327,3100495,3329330,3978097,4251856,4286945,4620980,4734347,15308410,15631086,15657130,15761536,15787660};
        prueba.generarTabla(prb);
        prueba.generarBits();
        
        System.out.print("Helloooo");
    }
    
}
