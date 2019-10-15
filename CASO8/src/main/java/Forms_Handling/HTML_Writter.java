/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms_Handling;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author jonac
 */
public class HTML_Writter {
    public static final String HTML_TEMPLATE= "<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<title>Genetic Images</title>\n" +
"<link rel=\'stylesheet\' href=\'stylesheet.css\' >"+
"</head>\n" +
"<body>$body\n" +
"</body>\n" +
"</html>";
    
    public static final String CSS_TEMPLATE= ".fadein {\n" +
"    position:absolute;\n" +
"    top:250px;\n" +
"    left:50%;\n" +
"    margin:auto;\n" +
"}\n" +
".fadein img {\n" +
"    position:absolute;\n" +
"    left:100px;\n" +
"    top:0;\n" +
"    -webkit-animation-name: fade;\n" +
"    -webkit-animation-iteration-count: infinite;\n" +
"    -webkit-animation-duration: $durations;\n" +
"    animation-name: fade;\n" +
"    animation-iteration-count: infinite;\n" +
"    animation-duration: $durations;\n" +
"}\n" +
"\n" +
"@-webkit-keyframes fade {\n" +
"    0% {opacity: 0;}\n" +
"    20% {opacity: 1;}\n" +
"    33% {opacity: 1;}\n" +
"    53% {opacity: 0;}\n" +
"    100% {opacity: 0;}\n" +
"}\n" +
"@keyframes fade {\n" +
"    0% {opacity: 0;}\n" +
"    20% {opacity: 1;}\n" +
"    33% {opacity: 1;}\n" +
"    53% {opacity: 0;}\n" +
"    100% {opacity: 0;}\n" +
"}\n";
    
    public HTML_Writter(){
    };
    
    public static void mWriter(String body) throws IOException{
        File htmlfile;
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("filename.html"), "utf-8"))) {
              writer.write(HTML_TEMPLATE.replace("$body",body));
              htmlfile= new File("filename.html");
            Desktop.getDesktop().browse(htmlfile.toURI());}
        catch(IOException e){
            System.err.println("fuck");
        }
    }
    
    public static void cWriter(String CssBody,String imgQuantity){
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("stylesheet.css"), "utf-8"))) {
              writer.write(CSS_TEMPLATE.replace("$duration",imgQuantity)+CssBody);}
        catch(IOException e){
            System.err.println("fuck");
        }
    }
    
    
    
}
    

