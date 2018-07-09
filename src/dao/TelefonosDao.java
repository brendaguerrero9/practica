/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Telefonos;
import interfaz.Dao;

/**
 *
 * @author Brenda
 */
public class TelefonosDao implements Dao <Telefonos> {

    private static final String SQL_INSERT = "INSERT INTO telefon (id,marca,modelo,compania) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE telefon SET marca = ?, modelo = ?,compania = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM telefon WHERE id = ?";
    private static final String SQL_READ = "SELECT * FROM telefon WHERE id = ?";
    private static final String SQL_READALL = "SELECT * FROM telefon"; 
     private static final Conexion con = Conexion.conectar();
    
    
    @Override
    public boolean create(Telefonos g) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1,g.getId());
            ps.setString(2,g.getMarca());
            ps.setString(3,g.getModelo());
            ps.setString(4,g.getCompania());
            if (ps.executeUpdate() > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TelefonosDao.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            con.CerrarConexion();
        }
        return false;
        
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TelefonosDao.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            con.CerrarConexion();
        }
        return false;  
    }

    @Override
    public boolean update(Telefonos c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getId());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1,c.getMarca());
            ps.setString(2,c.getModelo());
            ps.setString(3,c.getCompania());
            ps.setString(4,c.getId());
            System.out.println(c.toString());
            if (ps.executeUpdate() > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TelefonosDao.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            con.CerrarConexion();
        }
        return false;
        
    }

    @Override
    public Telefonos read(Object key) {
        Telefonos f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                f = new Telefonos(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TelefonosDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.CerrarConexion();
        }
        
        return f;
        
    }

    @Override
    public ArrayList<Telefonos> readAll() {
        ArrayList<Telefonos> all = new ArrayList();
        Statement s;
        ResultSet rs;
        
        try{
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            
            while(rs.next()){
                all.add(new Telefonos(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TelefonosDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.CerrarConexion();
        }
        
        return all;
        
    }
    
}
