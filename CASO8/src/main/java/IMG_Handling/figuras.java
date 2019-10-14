/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMG_Handling;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jonac
 */
public class figuras {
    private final int color;
    private final ArrayList<Integer> xpos=new ArrayList<>();
    private final ArrayList<Integer> ypos=new ArrayList<>();
    private static final int MIN_VERTICES=3;
    private static final int MAX_VERTICES=20;
    private static final Random RANDOM=new Random();

    public figuras(int color) {
        this.color = color;
    }
    
    public void generarVertices(int pTopY, int pTopX, int pBottomY, int pBottomX){
        int cantVertices=RANDOM.nextInt(MAX_VERTICES-MIN_VERTICES+1)+MIN_VERTICES;
        //elije la cantidad de vertices que va a tener el poligono
        int tempX, tempY;
        for(int vexCounter=0; vexCounter<cantVertices;vexCounter++){//genera ubicaciones aleatorias
            //para cada uno de los vertices
            tempX=RANDOM.nextInt(pBottomX-pTopX+1)+pTopX;
            tempY=RANDOM.nextInt(pBottomY-pTopY+1)+pTopY;
            xpos.add(tempX);//los añade a las posiciones de la figura
            ypos.add(tempY);
        }
    }

    public ArrayList<Integer> getXpos() {
        return xpos;
    }

    public ArrayList<Integer> getYpos() {
        return ypos;
    }

    public int getColor() {
        return color;
    }
    
      
    
}
