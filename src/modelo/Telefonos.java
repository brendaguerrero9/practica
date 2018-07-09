/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Brenda
 */
public class Telefonos {
    private String id;
    private String marca;
    private String modelo;
    private String compania;

    public Telefonos() {
    }

    public Telefonos(String id, String marca, String modelo, String compania) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.compania = compania;
    }

    public Telefonos(String marca, String modelo, String compania) {
        this.marca = marca;
        this.modelo = modelo;
        this.compania = compania;
    }

    public Telefonos(String modelo, String compania) {
        this.modelo = modelo;
        this.compania = compania;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }
    
    
}


