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
    private ArrayList<Integer> poblacion = new ArrayList<Integer>();
    
    private ArrayList<Tupla> goalTable = new ArrayList<>();
    private ArrayList<Tupla> bitTable = new ArrayList<>();
    
    
    private ArrayList<Tupla> getGoalTable() {
        return goalTable;
    }
    
    
    
    public void generarBits(){
        int cantToCreate = (int) (Math.random()*15);
        for (int index = 0; index<cantToCreate; index++){
                    poblacion.add( (int) (Math.random()*256));
        }
        poblacion.sort(null);
        aproximar();
    }
    
   public void fitness(){
       ArrayList<Tupla> temp = new ArrayList<>();
        int value = poblacion.get(0);
        int reps =1;
        int indice =0;
        int total =  poblacion.size();
        for (int index = 1; index < total; index++ ){
               if (value <=  bitTable.get(indice).getReps()){
                   reps++;
               }
               else{
                   Tupla toAddd = new Tupla(value, reps);
                   temp.add(toAddd);
                   reps = 1;
                   indice++;
               }
               value =poblacion.get(index);
        }
        
        ArrayList<Tupla> subTable = new ArrayList<>();
        
        temp.stream().map((repe) -> {
            int percent = (repe.getReps()*100)/total;
            Tupla toAdd = new Tupla(repe.getValue(), percent);
            return toAdd;
        }).forEach((toAdd) -> {
            subTable.add(toAdd);
        });
        
        int cont =0;
       for (Tupla element : subTable){
           if ( Math.abs(element.getPercent() - goalTable.get(cont).getPercent() ) < 0.05){
                cont++;
           }
           else{
               if ( Math.abs(element.getPercent() - goalTable.get(cont).getPercent() ) > 0.05){
                   
           }
           }
       }
   }
    
    public void aproximar(){
        
    }
    
    public  int modifyBit(int number, int pos,  int bit) 
        { 
            int mask = 1 <<pos; 
            return (number & ~mask) |  
                   ((bit << pos) & mask); 
        } 
    
    public void reproduce(int pParent, int pParent2){
        /*this function will generate two posible new individuals*/
        int pivot = (int) (Math.random()*5);  //will e used as the cant of bits that will be taken from parent, the max value possible is 4, so it takes min 3 bits from the other parent
                                                                //taking on mind that mascara starts on 1, so the actual max with the shift would be 5
        int firstBorn = 0;
        int secondBorn = 0;
        for (int index = 0; index<pivot; index++){
            if (index<pivot){
                   firstBorn= modifyBit(firstBorn, index, ((pParent >> (index-1)) & 1));
                   secondBorn = modifyBit(secondBorn, index, ((pParent2 >> (index-1)) & 1)); 
            }
            else{
                   firstBorn= modifyBit(firstBorn, index, ((pParent2 >> (index-1)) & 1)); // keeps the
                   secondBorn = modifyBit(secondBorn, index, ((pParent >> (index-1)) & 1)); 
            }
        }
        firstBorn = mutar(firstBorn);
        secondBorn = mutar(secondBorn);
    }
    
    private int mutar(int pMutar){
        int numMutado = 0;
         if( Math.random()>0.5){
             int pivot = (int) (Math.random()*8);
             if ( ((pMutar >> (pivot-1)) & 1) == 1){
                 numMutado = modifyBit(pMutar, pivot, 0);
             }
             else{
                 numMutado = modifyBit(pMutar, pivot, 1);
             }
         }
         else{
             numMutado = pMutar;
         }
         return numMutado;
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
        colorReps.stream().map((colorRep) -> {
            int percent = (colorRep.getReps()*100)/total;
            Tupla toAdd = new Tupla(colorRep.getValue(), percent);
            return toAdd;
        }).forEach((toAdd) -> {
            goalTable.add(toAdd);
        });
        
        goalTable.stream().map((element) -> {
            float percent = element.getPercent();
            Tupla toAdd = new Tupla(element.getValue(), (int)Math.floor(256*percent/100));
            return toAdd;
        }).forEach((toAdd) -> {
            this.bitTable.add(toAdd);
        });
    }
    
    
    

    public Bits() {
    }
    
}
