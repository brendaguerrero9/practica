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
public class Companias {
    private int id;
    private String nomCompania;

    public Companias() {
    }

    public Companias(int id, String nomCompania) {
        this.id = id;
        this.nomCompania = nomCompania;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCompania() {
        return nomCompania;
    }

    public void setNomCompania(String nomCompania) {
        this.nomCompania = nomCompania;
    }
    
    
}
