/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaz.Dao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Companias;

/**
 *
 * @author Brenda
 */
public class CompaniaDao implements Dao <Companias>{
    private static final String SQL_READALL = "SELECT * FROM companias";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Companias g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Companias c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Companias read(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Companias> readAll() {
        ArrayList <Companias> com = new ArrayList();
        Statement s;
        ResultSet rs;
        
        try {
            s = con.getCnx().createStatement();
             rs= s.executeQuery(SQL_READALL);
             while(rs.next())
             {
               com.add(new Companias(rs.getInt("id"), rs.getString("nomCompania")));
             }
        } catch (Exception ex) {
            Logger.getLogger(CompaniaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return com; 
    }
    
}
