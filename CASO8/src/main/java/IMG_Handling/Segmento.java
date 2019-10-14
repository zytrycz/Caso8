/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMG_Handling;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author jonac
 */
public class Segmento {
    //para no tener que acceder a las posiciones mediante acceso al array mejor tener 
    //cada uno de las posiciones relevantes en una variable
    private int TopY;
    private int BottomY;
    private int TopX;//lefmostX
    private int BottomX;//rightmostX
    private final Map<Integer,Integer> colors=new Hashtable<>();//por mientras lo manejare en la tabla
    private int prob;//probabilidad de que se eliga
    public static final int MAX=100;
    private static final int SAMPLES_NUMBER=6;
    private static final int SAMPLES_ZONES=5;
    private static final int MAX_PERCENTAJE_REDUCTION=33;
    private static final Random RANDOM=new Random();
    
    public Segmento(int pTopY, int pTopX, int pBottomY, int pBottonX) {
        this.TopY=pTopY;
        this.TopX=pTopX;
        this.BottomX=pBottonX;
        this.BottomY=pBottomY;
        prob=MAX;
    }

    public void setTopY(int TopY) {
        this.TopY = TopY;
    }

    public void setBottomY(int BottomY) {
        this.BottomY = BottomY;
    }

    public void setTopX(int TopX) {
        this.TopX = TopX;
    }

    public void setBottomX(int BottomX) {
        this.BottomX = BottomX;
    }
    
    public void seekViability(){//Reduciremos un maximo de un 33% en caso de que todas las muestras
        //sean blancas para que en caso de 3 pasadas dar siempre el 33 nos quede una posibilidad minuscula
        //sin embargo tambien mayor a 0 por lo que no descartamos el segmento
        int chance=RANDOM.nextInt(MAX);
        if(chance>=prob){//entonces va a empezar a revisar
            fillTable();//llena la tabla para hacer el analisis
            
            /*
            Basado en que porcentaje de blanco tiene se disminuira el porcentaje de 
            probabilidad del segmento
            */
            
            prob= (int) (MAX_PERCENTAJE_REDUCTION*whiteRepresentation());//reducir el porcentaje de
            //posibilidad del actual segmento basado en que tanto blanco le salio
        }
    }
    
    public void fillTable(){//dice que llenara los valores del hash para ver que hace
        colors.clear();//vaciarla para que no se revuelva con el ultimo analisis
        //cada una de las siguientes sera para asegurarnos que explore bien el cuadro
        int tempTopX, tempTopY, tempBottomX, tempBottomY;//ya que las variables se usaran
        //mas adelante mejor hacerlo asi para lograr mas calculos con menos numeros alambrados
        
        //1-Esquina Superior Izq        
        tempTopX=TopX;tempTopY=TopY;
        tempBottomX=BottomX/2;
        tempBottomY=BottomY/2;        
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);
        
        //2-Esquina Superior Der
        tempTopX=BottomX/2;tempBottomX=BottomX;//mueve los 2 X
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);
        
        //3-Esquina Inferior Der
        tempTopY=BottomY/2;tempBottomY=BottomY;//mueve los 2 Y
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);
        
        //4-Esquina Inferior Izq
        tempTopX=TopX;tempBottomX=BottomX/2;
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);
        
        //5-Centro
        tempTopX=((BottomX/2)/2);
        tempBottomX=BottomX-(TopX-tempTopX);
        tempTopY=((TopY/2)/2);
        tempBottomY=BottomY-(TopY-tempTopY);
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);        
    }
    

    
    public int colorInPos(int X, int Y){//retorna el color en una posicion dada
        //ya CONVERTIDO AL DICCIONARIO NUESTRO 
        return X*Y;
    }
    
    public void distributionFromFrame(int pTopY, int pTopX, int pBottomY, int pBottomX){
        //le pedimos el cuandrante de donde vamos a sacar las muestras
        int randX,randY,gottenColor;
        for(int counter=0;counter<SAMPLES_NUMBER;counter++){//el numero de samples establecidos
            randX=RANDOM.nextInt((pBottomX-pTopX)+1)+pTopX;
            randY=RANDOM.nextInt((pBottomY-pTopY)+1)+pTopY;//tengo que corroborar en ejecucion que asi
            //este bien hecha la resta
            gottenColor=colorInPos(randX, randY);//obtiene el color correspondiente desde la table
            attachColorToTable(gottenColor);//añade el color a la tabla para su posterior analisis
        }        
    }
    
    public void attachColorToTable(int color){//meter el color a la tabla de todos los colores
        if(colors.containsKey(color)){
            int oldValue=colors.get(color);
            colors.replace(color, oldValue,oldValue+1);//aumenta la cantidad que hay en el "valor"
        }else{
            colors.put(color, 1);//1 porque es la primera vez que se ve dicho color
        }
    }
    
    public double whiteRepresentation(){
        //get el de blanco
        return SAMPLES_NUMBER*SAMPLES_ZONES;
    }
}
