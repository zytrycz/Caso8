package IMG_Handling;

import Forms_Handling.figuras;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jonac
 */
public class IMG_Drawer {
    public static int ID=2;
    private static final String BASE= "IMG_#.png";
    private static BufferedImage img;
    private static Graphics2D img2D;
    //private static int lastVisited=0;
    
    public IMG_Drawer(int xSize, int ySize){//establece tamaños para dibujar la imagen
        img=new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
        
        img2D= img.createGraphics();
        img2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        img2D.setBackground(Color.black);//poner toda la imagen en blanco
        img2D.fillRect(0, 0, xSize, ySize);
    }
    
    public void draw_IMG_so_far(Segmento[][] segmentos){
        //System.out.println("IMG_Handling.IMG_Drawer.draw_IMG_so_far()");
        for(int i=0;i<segmentos.length;i++){
            for(int j=0;j<segmentos.length;j++){
                //System.out.println("IMG_Handling.IMG_Drawer.draw_IMG_so_far()");
                draw_Segment(segmentos[i][j]);
            }
        }
        /*for(;lastVisited<segmentos.size();lastVisi0ed++){
            draw_Segment(segmentos.get(lastVisited));
        }*///dibuja todos los segmentos en el current buffered img
        flush_IMG();
        //pasa el current bufferred img a un archivo.png
    }
    
    public void draw_Segment(Segmento segmento){//dibuja todas las figuras de un
        //segmento dado
        segmento.getFigures().forEach((figure) -> {
            draw_Form(figure);
        });
    }
    
    public void draw_Form(figuras figure){
        int cantPoints=figure.getXpos().size();
        int[] posX=new int[cantPoints];
        int[] posY=new int[cantPoints];
        for (int counter=0; counter<cantPoints;counter++) {//no hay manera en java de convertir
            //arraylist a int [] por lo que se hara manual, en especial considerando que
            //"DrawPolygon" no acepta cosas distintas de int[]
            posX[counter]=figure.getXpos().get(counter);
            posY[counter]=figure.getYpos().get(counter);
            //System.out.println("IMG_Handling.IMG_Drawer.draw_Form()");
        }        
        img2D.setColor(figure.getColor());
        img2D.drawPolygon(posX,posY,cantPoints);
        img2D.setColor(figure.getColor());
        img2D.fillPolygon(posX,posY,cantPoints);
       // System.out.println("Figura dibujada"+Integer.toString(cantPoints));
        
    }
    
    public void flush_IMG(){//crea la imagen que se guardara para meter en el HTML despues
         try {
             //Graphics2D graphic = image.createGraphics();
             File output = new File(BASE.replace("#", (Integer.toString(ID))));//pone el ID actual
             ID++;//aumenta el ID para la siguiente ronda
             ImageIO.write(img, "png", output);//crea el file imagen
             System.out.println("IMAGEN ARMADA");
        } catch(IOException log) {
             System.out.println("NO se pudo armar");
        }
    }
    
}
