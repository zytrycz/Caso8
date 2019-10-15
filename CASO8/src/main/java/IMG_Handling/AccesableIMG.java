/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMG_Handling;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author jonac
 */
public class AccesableIMG {
    public ArrayList<Segmento> segmentos;//conjunto de todos los segmentos de la imagen
    public BufferedImage img;
    private static final int LARGO=16;
    private static final int MAXLARGO=1024;
    
    public void readIMG(String filename)throws IOException{//lee la imagen que se le pasa
        File f = null;//read image
        try{
            f = new File(filename);
            img = ImageIO.read(f);
        }catch(IOException e){
            System.out.println(e);
        }

        // some code goes here..
        
    }
    
    public void updateSegmentos(){
        int tempLargo=LARGO,tempoAlto=LARGO;
        for(int largoIndex=0; largoIndex<MAXLARGO;largoIndex++){
            
        }
    }
}
