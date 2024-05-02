import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Titulo;
import models.TituloOmdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
        public static void main (String[]args) throws IOException, InterruptedException {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.omdbapi.com/?t=matrix&apikey=e1bc4c5f"))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers
                            .ofString());

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

    }
}