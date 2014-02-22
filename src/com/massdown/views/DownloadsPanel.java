/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.GestorDescargas;
import com.massdown.UnaDescarga;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Guillermo
 */
public class DownloadsPanel extends javax.swing.JPanel {

    MainWindow mw;
    
    public DownloadsPanel(MainWindow mw) {
        initComponents();
        this.mw = mw;
            
       new MuestraDescargas().start();
        
        
    }

    
    class MuestraDescargas extends Thread {        
        
        @Override
        public void run() {  

            ArrayList<UnaDescarga> descargasEnCurso = mw.gestorDescargas.getDescargasEnCurso();
            int numeroDescargasEnCuso = descargasEnCurso.size();    

            for(int i = 0 ; i < numeroDescargasEnCuso ; i++) {
                
                final UnaDescarga descargaAAgregar = descargasEnCurso.get(i);
                final int numeroVuelta = i;
                
                Thread agregadorDescarga = new Thread() {

                    JProgressBar pbDescarga;
                    JLabel lblTituloCap;
                    JLabel lblEstatus;
                    JLabel lblVelocidadDescarga;      
                    JButton btnPararDescarga;
                    JLabel lblParaPonerEspacio;
                    
                    
                    public void CrearComponentesGraficos() {
                        
                        pbDescarga = new JProgressBar();
                        lblTituloCap = new JLabel();
                        lblEstatus = new JLabel();
                        btnPararDescarga = new JButton();
                        lblParaPonerEspacio = new JLabel();
                        
                        pbDescarga.setMaximum((int) descargaAAgregar.getTamanoTotal());
                        
                        AplicarEstiloALosComponentes();                                                                   
                        
                        contenedorDescargas.add(lblTituloCap);
                        contenedorDescargas.add(lblEstatus);
                        contenedorDescargas.add(btnPararDescarga);
                        contenedorDescargas.add(lblParaPonerEspacio);
                        contenedorDescargas.add(pbDescarga);
                        
                        btnPararDescarga.addActionListener(new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(java.awt.event.ActionEvent evt) {                                
                                contenedorDescargas.remove(lblTituloCap);
                                contenedorDescargas.remove(lblEstatus);
                                contenedorDescargas.remove(btnPararDescarga);
                                contenedorDescargas.remove(lblParaPonerEspacio);
                                contenedorDescargas.remove(pbDescarga);
                                contenedorDescargas.repaint();
                                contenedorDescargas.revalidate();
                                descargaAAgregar.setTerminate(true);
                            }
                        });
                    }
                    
                    public void AplicarEstiloALosComponentes() {
                        lblTituloCap.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
                        lblTituloCap.setForeground(new java.awt.Color(255, 255, 255));
                        lblTituloCap.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
                        
                        lblEstatus.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
                        lblEstatus.setForeground(new java.awt.Color(180, 180, 180));
                        lblEstatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
                        
                        lblParaPonerEspacio.setBackground(new java.awt.Color(56, 56, 56));
                        lblParaPonerEspacio.setForeground(new java.awt.Color(56, 56, 56));
                        lblParaPonerEspacio.setFont(new java.awt.Font("Segoe UI Semilight", 0, 5)); // NOI18N
                        lblParaPonerEspacio.setText("pepe");
                        
                        
                        btnPararDescarga.setBackground(new java.awt.Color(231, 76, 60));
                        btnPararDescarga.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
                        btnPararDescarga.setForeground(new java.awt.Color(51, 51, 51));
                        btnPararDescarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/cancelar.png"))); // NOI18N
                        btnPararDescarga.setText("Stop");
                        btnPararDescarga.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                        btnPararDescarga.setFocusable(false);
                        btnPararDescarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btnPararDescarga.setMargin(new java.awt.Insets(10, 14, 2, 14));
                        btnPararDescarga.setMaximumSize(new java.awt.Dimension(65, 21));
                        btnPararDescarga.setPreferredSize(new java.awt.Dimension(300, 21));
                        btnPararDescarga.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    }
                    
                    public void ResfrescarDatosComponentesGraficos() {
                        lblTituloCap.setText(descargaAAgregar.getNombreArchivoDescargando());
                        
                        String textoEstatus = "";
                        
                        if(descargaAAgregar.getTamanoDescargado() != descargaAAgregar.getTamanoTotal()) {
                            textoEstatus = "Downloading "
                                                            +descargaAAgregar.getTamanoDescargado()
                                                            +"MB of "+descargaAAgregar.getTamanoTotal() 
                                                            + "MB at "+descargaAAgregar.getVelocidadDescarga()+"kbps"
                                                            + " - "+ (int) (descargaAAgregar.getTiempoRestante())+" minutes to end";
                        } else {
                            lblEstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/listo.png")));
                            textoEstatus = "Done!";
                            contenedorDescargas.remove(btnPararDescarga);
                        }
                        
                        lblEstatus.setText(textoEstatus);
                        pbDescarga.setValue((int) descargaAAgregar.getTamanoDescargado());
                        
                    }

                    @Override
                    public void run() {
                        super.run();
                        
                        int numDeVueltas = 0;
                        
                        while(true) {
                            
                            
                            if(numDeVueltas == 0) {
                                numDeVueltas++;
                                CrearComponentesGraficos();                                    
                            } else {                                
                                ResfrescarDatosComponentesGraficos();
                                try {
                                    sleep(300);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(DownloadsPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }   
                            
                        }
                    }                                                                        
                };
                agregadorDescarga.start();
            }            
        }        
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedorDescargas = new javax.swing.JToolBar();

        setBackground(new java.awt.Color(56, 56, 56));

        contenedorDescargas.setBackground(new java.awt.Color(56, 56, 56));
        contenedorDescargas.setBorder(null);
        contenedorDescargas.setFloatable(false);
        contenedorDescargas.setForeground(new java.awt.Color(255, 255, 255));
        contenedorDescargas.setOrientation(javax.swing.SwingConstants.VERTICAL);
        contenedorDescargas.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(contenedorDescargas, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedorDescargas, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar contenedorDescargas;
    // End of variables declaration//GEN-END:variables
}
