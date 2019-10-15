package Forms_Handling;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jonac
 */
public class figuras {
    private final Color color;
    private final ArrayList<Integer> xpos=new ArrayList<>();
    private final ArrayList<Integer> ypos=new ArrayList<>();
    private static final int MIN_VERTICES=3;
    private static final int MAX_VERTICES=20;
    private static final Random RANDOM=new Random();

    public figuras(Color color,int pTopY, int pTopX, int pBottomY, int pBottomX ) {
        if(color==Color.white){
            System.out.println("whito drivu");
            
        }
        this.color = color;
        
        generarVertices(pTopY, pTopX, pBottomY, pBottomX);
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

    public Color getColor() {
        return color;
    }     
    
}
