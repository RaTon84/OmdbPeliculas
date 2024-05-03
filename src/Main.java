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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
        public static void main (String[]args) throws IOException, InterruptedException {
            Scanner inputTeclado = new Scanner(System.in);
            List<Titulo> titulos = new ArrayList<>();
            /*transformar de json a entidad*/
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//los atributos empiezan con mayuscula
                    .setPrettyPrinting()//ver ordenado un json
                    .create();
            while(true){
                System.out.println("ingrese la pelicula");
                String input = inputTeclado.nextLine().replace(" ", "+");
                if (input.equalsIgnoreCase("salir")){
                    break;
                }
                /*armado de consulta web*/
                try {
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("https://www.omdbapi.com/?t=" + input + "&apikey=e1bc4c5f"))
                            .build();
                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers
                                    .ofString());
                    /*retorno de datos*/
                    String json = response.body();
                    //System.out.println(response.body());

                    /*transformar de json a entidad*/
                    TituloOmdb mitituloOmdb = gson.fromJson(json, TituloOmdb.class);
                    //System.out.println("Omdb = " + mitituloOmdb);
                    Titulo miTitulo = new Titulo(mitituloOmdb);
                    //System.out.println("Titulo =" + miTitulo);

                    titulos.add(miTitulo);
                } catch (NumberFormatException e) {
                    System.out.println("Ocurrió un error: ");
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error en la URI, verifique la dirección.");
                }
            }

            System.out.println(titulos);
            /*escribir en un archivo de escritura*/
            File file = new File("peliculas.txt");
            FileWriter escritura = new FileWriter(file);
            escritura.write(gson.toJson(titulos));
            //siempre cerrar escritura
            escritura.close();
            /*catch (ErrorEnConversionDeDuracionException e){
                System.out.println(e.getMessage());
            }*/
        }
    }
