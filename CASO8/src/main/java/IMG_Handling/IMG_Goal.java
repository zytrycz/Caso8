/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMG_Handling;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author jonac
 */
public class IMG_Goal {//img goal es la creacion durante el genetico
    //maneja los poligonos actuales 
    
    public BufferedImage img;
    private Graphics2D ddGra;
    private static final int LARGO=16;
    private static final int MAXLARGO=1024;
    public Segmento[][] segmentos=new Segmento[MAXLARGO/LARGO][MAXLARGO/LARGO];//conjunto de todos los segmentos de la imagen

    public IMG_Goal() {        
    }
    
    
    
    public void readIMG(String filename)throws IOException{//lee la imagen que se le pasa
        File f = null;//read image
        try{
            f = new File(filename);
            img = ImageIO.read(f);
            ddGra= img.createGraphics();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void makeSegmentos(){//probare que me saca las zonas de los segmentos correctamente
        for(int xIndex=0,xSegmentoIndex=0;xIndex<MAXLARGO;xIndex+=LARGO,xSegmentoIndex++){
            for(int yIndex=0,ySegmentoIndex=0;yIndex<MAXLARGO;yIndex+=LARGO,ySegmentoIndex++){
                segmentos[xSegmentoIndex][ySegmentoIndex]=new Segmento(yIndex, xIndex, yIndex+LARGO, xIndex+LARGO,img);
            }
        }
        
    }
    
    public void printSegmentos(){//fue usada para verificar que las posiciones estuviesen bien
        //String s= "Area delimitada en XT xt YT yt XB xb YB yb";
        //String sbase=s;
        
        for(int i=0;i<segmentos.length;i++){
            for(int j=0;j<segmentos.length;j++){
                Segmento chosen=segmentos[i][j];
                Color color=new Color(img.getRGB(chosen.getTopX(), chosen.getTopY()));
               System.out.println(color );
            }
        }
    }
    
    public void fillWithRandomForms(){//fue usada para experimentar 
        //respecto a la creacion de imagenes
        Random rand=new Random();
        int r,g,b;
        Color rColor;        
        for(int i=0;i<segmentos.length;i++){
            for(int j=0;j<segmentos.length;j++){
                r=rand.nextInt(225);
                g=rand.nextInt(225);
                b=rand.nextInt(225);
                rColor=new Color(r, g, b);
                segmentos[i][j].addFigure(rColor);
            }
        }
    }    
    
    public void fillWithAttemptForm(){//fue usada para experimentar 
        //con datos sacados de la imagen
        //respecto a la creacion de imagenes
        for(int i=0;i<segmentos.length;i++){
            for(int j=0;j<segmentos.length;j++){
                segmentos[i][j].fillSegmento();
            }
            System.out.println("IMG_Handling.IMG_Goal.fillWithAttemptForm()");
        }
    }    
    
    public void updateSegmentos(){
        int tempLargo=LARGO,tempoAlto=LARGO;
        for(int largoIndex=0; largoIndex<MAXLARGO;largoIndex++){
            
        }
    }
    
    //Leer IMG
    
}
