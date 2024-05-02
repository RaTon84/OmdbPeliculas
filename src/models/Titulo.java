package models;

import com.google.gson.annotations.SerializedName;

public class Titulo {

    @SerializedName("Title")
    private String nombre ;
    @SerializedName("Year")
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
        String minutos = tituloOmdb.runtime().substring(0,3);
        this.duracionEnMinutos = Integer.valueOf(minutos);
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento +
                ", duracion=" + duracionEnMinutos;
    }
}
