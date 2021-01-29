package archivos;

import java.io.*;
import java.util.HashMap;

public class Archivos {

    public static void crearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void escribirArchivo(String nombreArchivo, String contenido) {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(contenido);
            salida.close();
            System.out.println("Se ha escrito al archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void anexarArchivo(String nombreArchivo, String contenido) {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(contenido);
            salida.close();
            System.out.println("Se ha anexado informacion al archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static HashMap leerArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        HashMap<String,String> datos = new HashMap<>();

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while(lectura != null){
                String[] parts = lectura.split("=");
                if (parts[0].equals("host"))
                    datos.put(parts[0],parts[1]);
                if (parts[0].equals("db"))
                    datos.put(parts[0],parts[1]);
                if (parts[0].equals("user"))
                    datos.put(parts[0],parts[1]);
                if (parts[0].equals("password"))
                    datos.put(parts[0],parts[1]);
                lectura = entrada.readLine();
            }
            entrada.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
             return datos;

    }

}
