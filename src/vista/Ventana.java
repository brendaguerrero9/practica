/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.TelefonosDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Telefonos;

/**
 *
 * @author Brenda
 */
public class Ventana extends JFrame {
    public JLabel lblId, lblMarca, lblModelo, lblCompania; 
    public JTextField id,marca,modelo,compania;
    public JTable resultados;
    public JPanel table;
    public JButton buscar,eliminar, insertar,limpiar,actualizar;
    private static final int ANCHOC = 130, ALTOC = 30;
    DefaultTableModel tm;
    
    public Ventana(){
        super("TELEFONOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblId);
        container.add(lblMarca);
        container.add(lblModelo);
        container.add(lblCompania);
        container.add(id);
        container.add(marca);
        container.add(modelo);
        container.add(compania);
        container.add(buscar);
        container.add(eliminar);
        container.add(insertar);
        container.add(limpiar);
        container.add(actualizar);
        container.add(table);
        setSize(600,600);
        eventos();
        
    }
    
    private void agregarLabels(){
        lblId = new JLabel ("ID");
        lblMarca = new JLabel ("MARCA");
        lblModelo = new JLabel ("MODELO");
        lblCompania = new JLabel ("COMPANIA");
        lblId.setBounds(10,10,ANCHOC,ALTOC);
        lblMarca.setBounds(10,60,ANCHOC,ALTOC);
        lblModelo.setBounds(300,60,ANCHOC,ALTOC);
        lblCompania.setBounds(250,100,ANCHOC,ALTOC);
    }
    
    private void formulario(){
        id = new JTextField();
        marca = new JTextField();
        modelo = new JTextField();
        compania = new JTextField();
        resultados = new JTable();
        buscar = new JButton ("BUSCAR");
        buscar = new JButton ("BUSCAR");
        eliminar = new JButton ("ELIMINAR");
        insertar = new JButton ("INSERTAR");
        limpiar = new JButton ("LIMPIAR");
        actualizar = new JButton ("ACTUALIZAR");
        
        table = new JPanel();
        
        id.setBounds(140,10,ANCHOC,ALTOC);
        marca.setBounds(150,60,ANCHOC,ALTOC);
        modelo.setBounds(380,60,ANCHOC,ALTOC);
        compania.setBounds(330,100,ANCHOC,ALTOC);
        
        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    private void llenarTabla(){
        tm = new DefaultTableModel() {
            public Class <?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return String.class;
                }
            }
        };
        
        tm.addColumn("id");
        tm.addColumn("marca");
        tm.addColumn("modelo");
        tm.addColumn("compania");
        
        TelefonosDao tf = new TelefonosDao();
        ArrayList<Telefonos> telefonoss = tf.readAll();
        
        for(Telefonos te : telefonoss){
            System.out.println(te.toString());
            tm.addRow(new Object[]{te.getId(),te.getMarca(),te.getModelo(),te.getCompania()});
        }
        resultados.setModel(tm);
    }
    
    private void eventos(){
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                TelefonosDao td = new TelefonosDao();
                Telefonos t = new Telefonos(id.getText(),marca.getText(),modelo.getText(),compania.getText());
                
                if (td.create(t)){
                    JOptionPane.showMessageDialog(null, "Telefono registrado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al registrar");
                }
                
            }
            
        });
        
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                TelefonosDao td = new TelefonosDao();
                Telefonos t = new Telefonos(id.getText(),marca.getText(),modelo.getText(),compania.getText());
                
                if(td.update(t)){
                    JOptionPane.showMessageDialog(null,"telefono modificado");
                    limpiarCampos();
                    llenarTabla();
                } else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un error al modificarlo");
                }
            }
            
        });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                TelefonosDao td = new TelefonosDao();
                Telefonos t = new Telefonos(id.getText(),marca.getText(),modelo.getText(),compania.getText());
                
                if(td.delete(id.getText())){
                    JOptionPane.showMessageDialog(null,"telefono eliminado");
                }else{
                    JOptionPane.showMessageDialog(null, "no se pudo eliminar");
                }
            }
            
        });
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                TelefonosDao td = new TelefonosDao();
                Telefonos t = td.read(id.getText());
                
                if (t == null){
                    JOptionPane.showMessageDialog( null, "Telefono no encontrado");
                } else{
                    id.setText(t.getId());
                    marca.setText(t.getMarca());
                    modelo.setText(t.getModelo());
                    compania.setText(t.getCompania());
                }
                
            }
            
        });
        
        limpiar.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
            
        });
    }
    
    public void limpiarCampos(){
        id.setText("");
        marca.setText("");
        modelo.setText("");
        compania.setText("");
    }
}
