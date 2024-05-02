import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Titulo;
import models.TituloOmdb;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
        public static void main (String[]args) throws IOException, InterruptedException {
            Scanner inputTeclado = new Scanner(System.in);
            System.out.println("ingrese la pelicula");
            String input = inputTeclado.nextLine().replace(" ", "+" );
            System.out.println(input);
            /*armado de consulta web*/
            try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.omdbapi.com/?t="+input+"&apikey=e1bc4c5f"))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers
                            .ofString());
            /*retorno de datos*/
            String json =response.body();
            System.out.println(response.body());

            /*transformar de json a entidad*/
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            TituloOmdb mitituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println("Omdb = " +mitituloOmdb);
            Titulo newTitulo = new Titulo(mitituloOmdb);
            System.out.println("Titulo =" + newTitulo);
            }catch (NumberFormatException e){
                System.out.println("Ocurrió un error: ");
                System.out.println(e.getMessage());
            }catch(IllegalArgumentException e){
                System.out.println("Error en la URI, verifique la dirección.");
            }/*catch (ErrorEnConversionDeDuracionException e){
                System.out.println(e.getMessage());
            }*/

            /*crear archivos de escritura*/
            File file = new File("C:\\miArchivo.txt");
            FileReader reader = new FileReader(file);

            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }
            reader.close();}

            /*escribir en un archivo de escritura*/
            File file = new File("C:\\salida.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("Hola mundo!");
            writer.close();

    }
