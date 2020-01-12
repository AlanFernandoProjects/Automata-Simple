package automata;

import java.io.*;

public class archivo {
    
    //Variable de tipo texto que almacena las lineas leaidas en el ciclo while para retornar la cadena
    String Texto = ""; 
    
    //Metodo para leer el archivo segun la ruta del archivo especificada
    public String leerArchivo(String direccion)
    {
        try
        {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while((bfRead=bf.readLine())!= null){
                //Haz el ciclo mientras BfRead Tiene Datos
                temp = temp + bfRead; //Guardado el texto del archivo
            }  
            Texto=temp;
        }
        catch(Exception e)
        {
            System.err.println("NO SE ENCONTRO EL ARCHIVO");
        }
        return Texto;
    }       
}
