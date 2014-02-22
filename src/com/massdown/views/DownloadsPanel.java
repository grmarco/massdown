/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.GestorDescargas;
import com.massdown.UnaDescarga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

                Thread agregadorDescarga = new Thread() {

                    JProgressBar pbDescarga;
                    JLabel lblTiempo;
                    JLabel lblTituloCap;
                    JLabel lblEstatus;
                    JLabel lblVelocidadDescarga;                                                

                    public void CrearComponentesGraficos() {
                        
                        pbDescarga = new JProgressBar();
                        lblTiempo = new JLabel();
                        lblTituloCap = new JLabel();
                        lblEstatus = new JLabel();
                        lblVelocidadDescarga = new JLabel();
                        
                        pbDescarga.setMaximum((int) descargaAAgregar.getTamanoTotal());
                        
                        AplicarEstiloALosComponentes();
                        
                        contenedorDescargas.add(lblTituloCap);
                        contenedorDescargas.add(lblEstatus);
                        contenedorDescargas.add(lblTiempo);
                        contenedorDescargas.add(pbDescarga);
                    }
                    
                    public void AplicarEstiloALosComponentes() {
                        lblTituloCap.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
                        lblTituloCap.setForeground(new java.awt.Color(255, 255, 255));
                        lblEstatus.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                        lblEstatus.setForeground(new java.awt.Color(153, 153, 153));
                        lblTiempo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                        lblTiempo.setForeground(new java.awt.Color(153, 153, 153));
                    }
                    
                    public void ResfrescarDatosComponentesGraficos() {
                        lblTiempo.setText(String.valueOf(descargaAAgregar.getTime()));
                        lblTituloCap.setText(descargaAAgregar.getNombreArchivoDescargando());
                        lblEstatus.setText(String.valueOf("Descargado "+descargaAAgregar.getTamanoDescargado()+" de "+descargaAAgregar.getTamanoTotal() + " a "+descargaAAgregar.getVelocidadDescarga()+"kbps"));
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
