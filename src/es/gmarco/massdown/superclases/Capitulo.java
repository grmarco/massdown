/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.gmarco.massdown.superclases;

public class Capitulo {
    
    private String titulo;      
    private int numCapitulo;
    private int numTemporada;
    private String idStreamCloud;
    private String ficheroStreamCloud;
    private double progresoDeDescarga = 0.0;
    
    public Capitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdStreamCloud() {
        return idStreamCloud;
    }

    public void setIdStreamCloud(String idStreamCloud) {
        this.idStreamCloud = idStreamCloud;
    }

    public String getFicheroStreamCloud() {
        return ficheroStreamCloud;
    }

    public void setFicheroStreamCloud(String ficheroStreamCloud) {
        this.ficheroStreamCloud = ficheroStreamCloud;
    }     

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumCapitulo() {
        return numCapitulo;
    }

    public void setNumCapitulo(int numCapitulo) {
        this.numCapitulo = numCapitulo;
    }

    public int getNumTemporada() {
        return numTemporada;
    }

    public void setNumTemporada(int numTemporada) {
        this.numTemporada = numTemporada;
    }

    public double getProgresoDeDescarga() {
        return progresoDeDescarga;
    }

    public void setProgresoDeDescarga(double progresoDeDescarga) {
        this.progresoDeDescarga = progresoDeDescarga;
    }
    
    
    
}
