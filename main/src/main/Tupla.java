/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author brymo
 */
public class Tupla {
    private int value;
    private int reps;
    private float percent;

    

    public Tupla(int value, int reps) {
        this.value = value;
        this.reps = reps;
    }
    
    public Tupla(int value, float percent){
        this.value = value;
        this.percent = percent;
    }
    
    //getters
    public int getValue() {
        return value;
    }

    public int getReps() {
        return reps;
    }
    
    public float getPercent() {
        return percent;
    }
}
