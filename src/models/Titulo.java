package models;

import com.google.gson.annotations.SerializedName;

import javax.management.RuntimeErrorException;

public class Titulo {

   // @SerializedName("Title") no me hace falta las anotaciones con el record
    private String nombre ;
    //@SerializedName("Year") no me hace falta las anotaciones con el record
    private int fechaDeLanzamiento;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeEvaluaciones;
    private int duracionEnMinutos;

    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(TituloOmdb tituloOmdb) {
        this.nombre = tituloOmdb.title();
        this.fechaDeLanzamiento = Integer.valueOf(tituloOmdb.year());
        if (tituloOmdb.runtime().contains("N/A")){
            //throw new RuntimeErrorException("No pude convertir la duracion," +
            //        "porque contiene un N/A");
        }
        String minutos = tituloOmdb.runtime().substring(0,3).replace(" ","");
        this.duracionEnMinutos = Integer.valueOf(minutos);
    }

    @Override
    public String toString() {
        return
                "(nombre=" + nombre +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento +
                ", duracion=" + duracionEnMinutos+")";
    }
}
