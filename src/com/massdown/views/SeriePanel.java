/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.Capitulo;
import com.massdown.Serie;
import com.massdown.Servidor;
import com.massdown.Utilidades;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class SeriePanel extends javax.swing.JPanel implements Runnable {

    private Serie serie;
    
    public SeriePanel(final MainWindow ventanaPrincipal, final String nombreSerie) {
        
        initComponents();
        
        Thread cargadorCapitulosEnInterfaz = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    ventanaPrincipal.MostarPBar(true);
                    
                    serie = new Serie(nombreSerie);                    
                    lblDescripcion.setText(serie.descripcionSerie);
                    lblTitulo.setText(serie.nombreSerie);
                    iconSerie.setIcon(escalarImagen(ImageIO.read(new URL(serie.urlImagen)), 0.9));
                    
                    serie.ObtenerCapitulos();
                   
                    DefaultListModel listModel = new DefaultListModel();
                    lstCapitulos.setModel(listModel);
                    
                    for(int i = 0 ; i < serie.nombreCapitulos.size() ; i++) {
                        String capitulo = serie.nombreCapitulos.get(i);
                        listModel.addElement(capitulo);  
                        
                    }   
                    lstCapitulos.setSelectedIndex(0);
                    ventanaPrincipal.MostarPBar(false);
                    
                    interrupt();
                } catch (IOException ex) {
                    ventanaPrincipal.MostarPBar(false);
                    Logger.getLogger(SeriePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        };
        
        cargadorCapitulosEnInterfaz.start();
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lblDescripcion = new javax.swing.JTextPane();
        iconSerie = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCapitulos = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstServidores = new javax.swing.JList();
        lblServerSeleccionado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(56, 56, 56));

        jScrollPane1.setBorder(null);

        lblDescripcion.setBackground(new java.awt.Color(56, 56, 56));
        lblDescripcion.setBorder(null);
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(204, 204, 204));
        lblDescripcion.setText("  ");
        jScrollPane1.setViewportView(lblDescripcion);

        lblTitulo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText(" ");

        jScrollPane2.setBackground(new java.awt.Color(56, 56, 56));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lstCapitulos.setBackground(new java.awt.Color(56, 56, 56));
        lstCapitulos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lstCapitulos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstCapitulos.setForeground(new java.awt.Color(255, 255, 255));
        lstCapitulos.setFixedCellHeight(30);
        lstCapitulos.setSelectionBackground(new java.awt.Color(231, 76, 60));
        lstCapitulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCapitulosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstCapitulos);

        jScrollPane3.setBackground(new java.awt.Color(56, 56, 56));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lstServidores.setBackground(new java.awt.Color(56, 56, 56));
        lstServidores.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lstServidores.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstServidores.setForeground(new java.awt.Color(204, 204, 204));
        lstServidores.setFixedCellHeight(30);
        lstServidores.setSelectionBackground(new java.awt.Color(230, 126, 34));
        jScrollPane3.setViewportView(lstServidores);

        lblServerSeleccionado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblServerSeleccionado.setForeground(new java.awt.Color(204, 204, 204));
        lblServerSeleccionado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblServerSeleccionado.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(iconSerie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblServerSeleccionado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(iconSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lblServerSeleccionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                        .addGap(23, 23, 23))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lstCapitulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCapitulosMouseClicked

        new Thread(){

            @Override
            public void run() {
                
                
                try {
                    Capitulo capitulo = serie.CrearObjetoCapitulo(lstCapitulos.getSelectedIndex());
                    capitulo.ObtenerServidoresCapitulo();
                    DefaultListModel listModel = new DefaultListModel();
                    lblServerSeleccionado.setText((String) lstCapitulos.getSelectedValue());
                    lstServidores.setModel(listModel);

                    for(int i = 0 ; i < capitulo.servidoresConElCapitulo.size() ; i++) {
                        Servidor servidor = capitulo.servidoresConElCapitulo.get(i);
                        listModel.addElement(" Option"+ 1 + " " + servidor.idiomaCapitulo + " " + servidor.tieneSubtitulos);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(SeriePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
  
            }

        }.start();
    }//GEN-LAST:event_lstCapitulosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconSerie;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane lblDescripcion;
    private javax.swing.JLabel lblServerSeleccionado;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList lstCapitulos;
    private javax.swing.JList lstServidores;
    // End of variables declaration//GEN-END:variables
    
    private ImageIcon escalarImagen(Image src, double scale) {
        int w = (int)(scale*src.getWidth(this));
        int h = (int)(scale*src.getHeight(this));
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        g2.drawImage(src, 0, 0, w, h, this);
        g2.dispose();
        return new ImageIcon(dst);
    }
    
    @Override
    public void run() {
        
    }
}
