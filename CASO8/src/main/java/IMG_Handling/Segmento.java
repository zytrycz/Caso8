/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMG_Handling;

import java.util.Random;
import Forms_Handling.figuras;
import java.awt.AWTError;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
 
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

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
    private HashMap<Color,Integer> colors=new HashMap<>();//por mientras lo manejare en la tabla
    private int prob;//probabilidad de que se eliga
    private final int cantFiguras=4;
    public static final int MAX=100;
    private static final int SAMPLES_NUMBER=6;
    private static final int SAMPLES_ZONES=5;
    private static final int MAX_PERCENTAJE_REDUCTION=33;
    private static final Random RANDOM=new Random();
    private final ArrayList<figuras> figures=new ArrayList<>();
    private static BufferedImage img;
    private static final int MOVABLEPIXELS=8;
    
    public Segmento(int pTopY, int pTopX, int pBottomY, int pBottonX, BufferedImage pImg) {
        this.TopY=pTopY;
        this.TopX=pTopX;
        this.BottomX=pBottonX;
        this.BottomY=pBottomY;
        //System.out.println("X :"+Integer.toString(TopY));
        prob=MAX;
        img=pImg;
        
    }

    public ArrayList<figuras> getFigures() {
        return figures;
    }

    public int getBottomY() {
        return BottomY;
    }

    public int getTopX() {
        return TopX;
    }

    public int getBottomX() {
        return BottomX;
    }

    public int getTopY() {
        return TopY;
    }

    public int getCantFiguras() {
        return cantFiguras;
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
        tempBottomX=TopX+MOVABLEPIXELS;
        tempBottomY=TopY+MOVABLEPIXELS;        
        //System.out.println(1.0);
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);
        
        //2-Esquina Superior Der
        tempTopX=BottomX-MOVABLEPIXELS;tempBottomX=BottomX;//mueve los 2 X
       // System.out.println(2.0);
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);
        
        //3-Esquina Inferior Der
        tempTopY=BottomY-MOVABLEPIXELS;tempBottomY=BottomY;//mueve los 2 Y
       //System.out.println(3.0);
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);
        
        //4-Esquina Inferior Izq
        tempTopX=TopX;tempBottomX=TopX+MOVABLEPIXELS;
        //System.out.println(4.0);
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);
        
        //5-Centro
        tempTopX=TopX+(MOVABLEPIXELS/2);
        tempBottomX=BottomX-(MOVABLEPIXELS/2);
        tempTopY=TopY+(MOVABLEPIXELS/2);
        tempBottomY=BottomY-(MOVABLEPIXELS/2);
        //System.out.println(5.0);
        distributionFromFrame(tempTopY,tempTopX, tempBottomY, tempBottomX);        
    }
        
    public Color colorInPos(int pX, int pY){//retorna el color en una posicion dada
        //ya CONVERTIDO AL DICCIONARIO NUESTRO 
        Color color= new Color(img.getRGB(pX, pY));//falta convertir
        return color;
    }
    
    public void distributionFromFrame(int pTopY, int pTopX, int pBottomY, int pBottomX){
        //le pedimos el cuandrante de donde vamos a sacar las muestras
        int randX,randY;
        Color gottenColor;
        /*System.out.println("Distribution");
        System.out.println(pTopY);
        System.out.println(pTopX);
        System.out.println(pBottomY);
        System.out.println(pBottomX);*/
        
        for(int counter=0;counter<SAMPLES_NUMBER;counter++){//el numero de samples establecidos
            randX=RANDOM.nextInt((pBottomX-pTopX))+pTopX;
            randY=RANDOM.nextInt((pBottomY-pTopY))+pTopY;//tengo que corroborar en ejecucion que asi
            //este bien hecha la resta
            gottenColor=colorInPos(randX, randY);//obtiene el color correspondiente desde la table
            attachColorToTable(gottenColor);//añade el color a la tabla para su posterior analisis
        }        
    }
    
    public void attachColorToTable(Color color){//meter el color a la tabla de todos los colores
        if(colors.containsKey(color)){
            int oldValue=colors.get(color);
            colors.replace(color, oldValue,oldValue+1);//aumenta la cantidad que hay en el "valor"
        }else{
            colors.put(color, 1);//1 porque es la primera vez que se ve dicho color
        }
    }
    
    public double whiteRepresentation(){//para ver si se le crearan figuras o no
        //get el de blanco
        return SAMPLES_NUMBER*SAMPLES_ZONES;
    }
    
    public void addFigure(Color color){//añade una figura aleatoria al segmento
        if(cantFiguras>0){//si le quedan figuras que pueda añadir
            this.figures.add(new figuras(color, TopY, TopX, BottomY, BottomX));
        }        
    }
    
    public void fillWithFigure(){//toma el top 3 de colores y genera figuras aleatorias con ellos
        ArrayList<Color> colorToChoose= createTopTable();
        if(colorToChoose!=null){//ah no ser que haya mas blanco que otra cosa
        Color colorChosen;
        int chosenPos;
           // System.out.println("IMG_Handling.Segmento.fillWithFigure()");
        while(colorToChoose.size()>0){
            chosenPos=RANDOM.nextInt(colorToChoose.size());
            colorChosen=colorToChoose.remove(chosenPos);
            addFigure(colorChosen);
        }
        }
    }
    
    public ArrayList<Color> createTopTable(){
        //armar tabla de cantidad de figuras
        colors=topColors(colors);//ordena los colores para en orden de representacion
        
        int topColors=SAMPLES_NUMBER/2;
        topColors=SAMPLES_NUMBER/2;
        ArrayList<Color> chosenColors=new ArrayList<>();
        int ammountOfChosenColotoMake=0;
        for (Map.Entry<Color, Integer> entry : colors.entrySet()) {
            if(entry.getKey().getBlue()>250 & entry.getKey().getGreen()>250 &
                    entry.getKey().getRed()>250){
                continue;
            }
            int val = entry.getValue();
            //crear los porcentajes e insertar dicho color ese porcentaje de veces
            // x la cantidad de figura hacibles para saber cuantas figuras de cada color se haran
            ammountOfChosenColotoMake= val;
            System.out.println(ammountOfChosenColotoMake);
            while(ammountOfChosenColotoMake>0){//añadira la cantidad de veces que sea porcentaje ese color
                chosenColors.add(entry.getKey());
                ammountOfChosenColotoMake--;
            }
            if (topColors<=0) {
                break;//solo nos interesan los primeros 3
            }
            topColors--;
        }
        return chosenColors;
    }
    
    public HashMap<Color,Integer> topColors(HashMap<Color,Integer> unsortedMap){
       // System.out.println("BeforeSort: " + unsortedMap.toString());
        HashMap<Color, Integer> sortedMap = unsortedMap
        .entrySet()
        .stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .collect(
            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                LinkedHashMap::new));
       // System.out.println("AfterSort: " + sortedMap.toString());
        
        return sortedMap;
    }
    
    public void fillSegmento(){
        fillTable();
        fillWithFigure();
        colors.clear();
    }
}
