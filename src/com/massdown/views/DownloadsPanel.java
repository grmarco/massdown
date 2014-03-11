/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.gestordescarga.UnaDescarga;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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

            final ArrayList<UnaDescarga> descargasEnCurso = mw.gestorDescargas.getDescargasEnCurso();
            final int numeroDescargasEnCuso = descargasEnCurso.size(); 
            
            if(numeroDescargasEnCuso <= 0) {
                JLabel labelAviso = new JLabel();
                labelAviso.setFont(new java.awt.Font("Segoe UI Light", 0, 32)); // NOI18N
                labelAviso.setForeground(new java.awt.Color(205, 205, 205));
                labelAviso.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
                labelAviso.setText("no pending downloads");
                contenedorDescargas.add(labelAviso);
                
            }

            for(int i = 0 ; i < numeroDescargasEnCuso ; i++) {
                
                final UnaDescarga descargaAAgregar = descargasEnCurso.get(i);
                final int numeroVuelta = i;
                
                Thread agregadorDescarga = new Thread() {

                    JProgressBar pbDescarga;
                    JLabel lblTituloCap;
                    JLabel lblEstatus;
                    JLabel lblVelocidadDescarga;      
                    JButton btnPararDescarga;
                    JLabel lblThumbNail;
                    
                    public synchronized void CrearComponentesGraficos() {
                        
                        pbDescarga = new JProgressBar();
                        lblTituloCap = new JLabel();
                        lblEstatus = new JLabel();
                        btnPararDescarga = new JButton();
                        lblThumbNail = new JLabel();                                                
                        
                        pbDescarga.setMaximum(100);
                        
                        AplicarEstiloALosComponentes();                                                                   
                        
                        
                        GridBagConstraints c = new GridBagConstraints();
                        int lugarDePartida = (numeroVuelta != 1) ? numeroVuelta * 100 : 100;

                        c.gridy = lugarDePartida;
                        c.anchor = GridBagConstraints.NORTHWEST;
                        
                        
                        
                        // Ponemos la imagen de la serie en el capitulo que se
                        // está descargando
                        lblThumbNail.setIcon(descargaAAgregar.getThumbNail());
                        c.gridx = 0;
                        c.gridheight = 100;
                        c.insets = new Insets(20, 0, 0, 15);
                        contenedorDescargas.add(lblThumbNail, c);
                       
                        c.weightx = mw.getWidth();
                        c.insets = new Insets(8, 0, 0, 0);
                        c.gridheight = 1;
                        c.gridx = 1;
                        

                        contenedorDescargas.add(lblTituloCap, c);
                        
                        c.gridy = numeroVuelta+lugarDePartida+1;
                        contenedorDescargas.add(lblEstatus, c);
                        
                        c.gridy = numeroVuelta+lugarDePartida+2;                        
                        contenedorDescargas.add(btnPararDescarga, c);
                        
                        c.gridy = numeroVuelta+lugarDePartida+3;
                        c.fill = GridBagConstraints.HORIZONTAL;
                        contenedorDescargas.add(pbDescarga, c);
                        
                        btnPararDescarga.addActionListener(new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(java.awt.event.ActionEvent evt) {                                
                                contenedorDescargas.remove(lblTituloCap);
                                contenedorDescargas.remove(lblEstatus);
                                contenedorDescargas.remove(btnPararDescarga);
                                contenedorDescargas.remove(pbDescarga);
                                contenedorDescargas.remove(lblThumbNail);
                                contenedorDescargas.repaint();
                                contenedorDescargas.revalidate();
                                descargasEnCurso.remove(descargaAAgregar);
                                mw.ActualizarLabelDescargas();
                                descargaAAgregar.setTerminate(true);
                            }
                        });
                    }
                    
                    public synchronized void AplicarEstiloALosComponentes() {
                        lblTituloCap.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
                        lblTituloCap.setForeground(new java.awt.Color(255, 255, 255));
                        lblTituloCap.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
                        
                        lblEstatus.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
                        lblEstatus.setForeground(new java.awt.Color(180, 180, 180));
                        lblEstatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));

                        
                        
                        btnPararDescarga.setBackground(new java.awt.Color(231, 76, 60));
                        btnPararDescarga.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
                        btnPararDescarga.setForeground(new java.awt.Color(51, 51, 51));
                        btnPararDescarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/cancelar.png"))); // NOI18N
                        btnPararDescarga.setText("Remove");
                        btnPararDescarga.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                        btnPararDescarga.setFocusable(false);
                        btnPararDescarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btnPararDescarga.setMargin(new java.awt.Insets(10, 14, 2, 14));
                        btnPararDescarga.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    }
                    
                    public void ResfrescarDatosComponentesGraficos() {
                        lblTituloCap.setText(descargaAAgregar.getNombreArchivo());                        
                        String textoEstatus = "";
                          
                        
                        if(descargaAAgregar.getTamanoDescargado() != descargaAAgregar.getTamanoTotal() && descargaAAgregar.getPorcentajeDescargado() != -1) {
                            lblEstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/loading.png")));
                            textoEstatus = "Downloading "
                                                            +(Math.rint(descargaAAgregar.getTamanoDescargado()*10)/10)
                                                            +"MB of "+(Math.rint(descargaAAgregar.getTamanoTotal()*10)/10) 
                                                            + "MB at "+descargaAAgregar.getVelocidadDescarga()+"kbps"
                                                            + " - "+ (int) (descargaAAgregar.getTiempoRestante())+" minutes to end";
                        }
                        else if(descargaAAgregar.getPorcentajeDescargado() == -1) {
                            lblEstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/espera.png")));
                            textoEstatus = "Waiting...";
                        }
                        else {
                            lblEstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/listo.png")));
                            textoEstatus = "Done!";
                        }
                        
                        lblEstatus.setText(textoEstatus);
                        pbDescarga.setValue((int) descargaAAgregar.getPorcentajeDescargado());
                        
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
                                    sleep(400);
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

        contenedorDescargas = new javax.swing.JPanel();

        setBackground(new java.awt.Color(56, 56, 56));

        contenedorDescargas.setBackground(new java.awt.Color(56, 56, 56));
        contenedorDescargas.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(contenedorDescargas, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(contenedorDescargas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedorDescargas;
    // End of variables declaration//GEN-END:variables
}
