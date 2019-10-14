/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bits;

/**
 *
 * @author brymo
 */

import java.util.ArrayList;
import java.util.Arrays;
import main.Tupla;

public class Bits {
    private ArrayList<Tupla> goalTable = new ArrayList<>();

    public ArrayList<Tupla> getGoalTable() {
        return goalTable;
    }
    
    private void generarBits(){
        /*this function will generate the bit code for the individual based on the table*/
    
    }
    
    public void generarTabla(int[] pColors){
            /*this function will generate the bittable for the relative population*/
        ArrayList<Tupla> colorReps = new ArrayList<>();
        int[] colors =  pColors;
        Arrays.sort(colors);
        int value = colors[0];
        int reps =1;
        int total =  colors.length;
        //Fist we cout the colors reps
        for (int index = 1; index < total; index++ ){
               if (value == colors[index]){
                   reps++;
               }
               else{
                   Tupla colorTup = new Tupla(value, reps);
                   colorReps.add(colorTup);
                   reps = 1;
               }
               value = colors[index];
        }
        
        //Now we calculate the % that will be assignet to each color, we get it bassed on the size of the color array
        //if it represents 5% of the total lenght, it will represnt the 5% of the 256 bit combinations
        for (Tupla colorRep : colorReps) {
            int percent = (colorRep.getReps()*100)/total;
            Tupla toAdd = new Tupla(colorRep.getValue(), percent);
            goalTable.add(toAdd);
        }
        
    }
    /*
    * Sort str[] or int[]
    * Once sorted create a tuples array (value, cant)
    * based on that array it can be assigned a % of the total number that could be represented (256) 
    */
    
}
