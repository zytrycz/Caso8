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

    public Tupla(int value, int reps) {
        this.value = value;
        this.reps = reps;
    }

    public int getValue() {
        return value;
    }

    public int getReps() {
        return reps;
    }
}
