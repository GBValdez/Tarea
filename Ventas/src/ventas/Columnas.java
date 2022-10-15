/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author hnunez
 */
public class Columnas extends javax.swing.JFrame {
    
    DefaultTableModel dtm;
    

    /**
     * Creates new form JFraLibros
     */
    public Columnas() {
        initComponents();
        dtm = (DefaultTableModel) this.jTblLibros.getModel();
        String[] valores = {"Activo","Inactivo","Cancelado"};
        JComboBox Job = new JComboBox(valores);
        TableColumn To= this.jTblLibros.getColumnModel().getColumn(5);
        TableCellEditor tce= new DefaultCellEditor (Job);
        To.setCellEditor(tce);
        
        String[] valores2 = {"Corte","Apretar","Electrica","Golpear"
                    ,"Medicion","Manual"};
        JComboBox Job2 = new JComboBox(valores2);
        TableColumn To2 = this.jTblLibros.getColumnModel().getColumn(3);
        TableCellEditor tce2 = new DefaultCellEditor (Job2);
        To2.setCellEditor(tce2);
    }
    
    //Metodos creados por el programador 
    private void habilitarBotones(){
    
        this.jBtnAgragar.setEnabled(true);
        this.jBtnGuardar.setEnabled(true);
        this.jBtnLimpiar.setEnabled(true);
        this.jBtnMostrar.setEnabled(true);
    }
    private void crearArchivo() throws IOException{
        File Archivo = new File("C:\\Ventas\\Archivo.txt");
        
        if(Archivo.exists()){
           habilitarBotones();        
        }else {
            JOptionPane.showMessageDialog(null, "No se encuentra el archivo, se creara" , "Gestios de Archivos", 1);
            Archivo.createNewFile();
            habilitarBotones();
        }
    }
    
    private void guardarArchivo(){
        try {
            
            FileWriter salvarArchivo = new FileWriter("C:\\Ventas\\Archivo.txt",true);
           
             
            for (int i=0; i < this.jTblLibros.getRowCount(); i++){                
                salvarArchivo.write(dtm.getValueAt(i, 0).toString() + "|");
                salvarArchivo.write(dtm.getValueAt(i, 1).toString() + "|");
                salvarArchivo.write(dtm.getValueAt(i, 2).toString() + "|");
                salvarArchivo.write(dtm.getValueAt(i, 3).toString() + "|");
                salvarArchivo.write(dtm.getValueAt(i, 4).toString() + "|");
                salvarArchivo.write(dtm.getValueAt(i, 5).toString() + "\n");
            }
            salvarArchivo.close();
            /*
            String au = "Archivo.txt";
            File arcLectura = new File(au);
            salvarArchivo.close();
            salvarArchivo = new FileWriter("Temporal.txt",true);
            try {
                Scanner lecturaArchivo = new Scanner(arcLectura);
                while(lecturaArchivo.hasNextLine()){
                    String linea= lecturaArchivo.nextLine();
                    String [] Partes = linea.split("\\|");
                    boolean Sobreescrita=true;
                    for (int i=0; i < this.jTblLibros.getRowCount(); i++){                
                        if (dtm.getValueAt(i, 0).toString() != Partes[0])
                        {
                            Sobreescrita=false;
        
                        }
                    }
                    if (Sobreescrita){
                        salvarArchivo.write(Partes[0] + "|");
                        salvarArchivo.write(Partes[1] + "|");
                        salvarArchivo.write(Partes[2] + "|");
                        salvarArchivo.write(Partes[3] + "|");
                        salvarArchivo.write(Partes[4]+ "|");
                        salvarArchivo.write(Partes[5] + "\n");
                    }
             }
                }catch (FileNotFoundException e) {
                     JOptionPane.showMessageDialog(null, "Error" + e);
                }
            
            
            salvarArchivo.close();
            
            FileWriter salvarArchivo2= new FileWriter("Archivo.txt");
            au = "Temporal.txt";
            arcLectura = new File(au);
            try {
               Scanner lecturaArchivo = new Scanner(arcLectura);
               while(lecturaArchivo.hasNextLine()){
                   String linea= lecturaArchivo.nextLine();
                   String [] Partes = linea.split("\\|");
                   salvarArchivo2.write(Partes[0] + "|");
                   salvarArchivo2.write(Partes[1] + "|");
                   salvarArchivo2.write(Partes[2] + "|");
                   salvarArchivo2.write(Partes[3] + "|");
                   salvarArchivo2.write(Partes[4]+ "|");
                   salvarArchivo2.write(Partes[5] + "\n");
               }
               salvarArchivo2.close();
            }catch (FileNotFoundException e) {
                 JOptionPane.showMessageDialog(null, "Error" + e);
            }
            */
            JOptionPane.showMessageDialog(null, "Datos Almacenados","Gestion de Archivos", 1);
            limpiarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex);
            
        }
    }
    
    private void mostrarArchivo(){
        String id, Nombre, Marca,Tipo,Unidad,Status,linea;
        
        String au = "C:\\Ventas\\Archivo.txt";
        
        File arcLectura = new File(au);
        try {
           Scanner lecturaArchivo = new Scanner(arcLectura);
           limpiarTabla();
           while(lecturaArchivo.hasNextLine()){
               linea= lecturaArchivo.nextLine();
               String [] Partes = linea.split("\\|");
               id = Partes[0];
               Nombre = Partes[1];
               Marca = Partes[2];
               Tipo = Partes[3];
               Unidad = Partes[4];
               Status = Partes[5];
               dtm.addRow(new Object[]{id, Nombre, Marca, Tipo, Unidad,Status});
           }
        }catch (FileNotFoundException e) {
             JOptionPane.showMessageDialog(null, "Error" + e);
        }
        this.jBtnAgragar.setEnabled(false);
        this.jBtnGuardar.setEnabled(false);
        this.jBtnLimpiar.setEnabled(true);
        this.jBtnMostrar.setEnabled(false);
        
    
    }
    private void limpiarTabla(){
        this.jBtnAgragar.setEnabled(true);
        this.jBtnGuardar.setEnabled(true);
        this.jBtnLimpiar.setEnabled(true);
        this.jBtnMostrar.setEnabled(true);
    int fila = this.jTblLibros.getRowCount();
    for (int i = fila -1; i>=0;i--){
        dtm.removeRow(i);
    }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jBtnAgragar = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnMostrar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblLibros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registro de herramientas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jBtnAgragar.setText("Agregar");
        jBtnAgragar.setEnabled(false);
        jBtnAgragar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgragarActionPerformed(evt);
            }
        });

        jBtnGuardar.setText("Guardar");
        jBtnGuardar.setEnabled(false);
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnMostrar.setText("Mostrar");
        jBtnMostrar.setEnabled(false);
        jBtnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMostrarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.setEnabled(false);
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnAgragar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnAgragar)
                .addGap(18, 18, 18)
                .addComponent(jBtnGuardar)
                .addGap(24, 24, 24)
                .addComponent(jBtnMostrar)
                .addGap(18, 18, 18)
                .addComponent(jBtnLimpiar)
                .addGap(39, 39, 39))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTblLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Marca", "Tipo", "Unidad_Medida", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTblLibros);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAgragarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgragarActionPerformed
        // TODO add your handling code here:
        dtm.addRow(new Object[]{"","","","",""});
    }//GEN-LAST:event_jBtnAgragarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        guardarArchivo();
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMostrarActionPerformed
        mostrarArchivo();
    }//GEN-LAST:event_jBtnMostrarActionPerformed

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        limpiarTabla();
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            crearArchivo();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex);
        }       
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAgragar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JButton jBtnMostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblLibros;
    // End of variables declaration//GEN-END:variables
}
