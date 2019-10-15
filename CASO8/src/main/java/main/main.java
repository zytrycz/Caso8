/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Forms_Handling.HTML_Writter;
import IMG_Handling.IMG_Drawer;
import IMG_Handling.IMG_Goal;
import java.io.IOException;

/**
 *
 * @author jonac
 */
public class main {
    public static void main(String args[]) throws IOException/*throws IOException*/ {
                
        IMG_Goal imgGoal=new IMG_Goal();
        imgGoal.readIMG("C:\\Users\\jonac\\Documents\\GitHub\\Caso8\\CASO8\\src\\main\\java\\IMG_Handling\\walle3.jpg");
        imgGoal.makeSegmentos();
        imgGoal.fillWithAttemptForm();
        
        IMG_Drawer dibujante=new IMG_Drawer(1024,1024);
        dibujante.draw_IMG_so_far(imgGoal.segmentos);
        
        String baseBody= "<div id='fadein'> \n",finalBody="</div>";
        int imgCounter=1;
        String id="f",baseID="f";
        String baseHtmlImgClass= "<img class= '$id'";
        String finalHtmlImgCLas= "src='$dir'> \n";
        String baseCssId="#";
        String delayDetails= " { \n -webkit-animation-delay: -#s;\n } \n";
        String CssBody="";

        while(imgCounter<IMG_Drawer.ID){
            id+=Integer.toString(imgCounter);
            baseBody+=baseHtmlImgClass.replace("$id", id);
            baseBody+=finalHtmlImgCLas.replace("$dir", "C:\\Users\\jonac\\Documents\\GitHub\\Caso8\\CASO8\\IMG_"
                    +Integer.toString(imgCounter)+".png");
            CssBody+=baseCssId+id + delayDetails.replace("#", Integer.toString(imgCounter));
            imgCounter++;
            id=baseID;
        }
        baseBody+=finalBody;
        HTML_Writter.cWriter (CssBody, Integer.toString(IMG_Drawer.ID));
        HTML_Writter.mWriter(baseBody);
    }
}
