/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.gestordescarga.UnaDescarga;
import com.massdown.views.libs.ReproductorDeVideo;
import es.gmarco.massdown.recursos.Configuracion;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
                    JButton btnReproducirDescarga;
                    JLabel lblThumbNail;
                    
                    public synchronized void CrearComponentesGraficos() {
                        
                        pbDescarga = new JProgressBar();
                        lblTituloCap = new JLabel();
                        lblEstatus = new JLabel();
                        btnPararDescarga = new JButton();
                        lblThumbNail = new JLabel();                                                
                        btnReproducirDescarga = new JButton();
                        
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
                        c.gridwidth = 1;
                        c.insets = new Insets(20, 0, 0, 15);
                        contenedorDescargas.add(lblThumbNail, c);
                       
                        c.gridwidth = 2;
                        c.weightx = mw.getWidth();
                        c.insets = new Insets(8, 0, 0, 0);
                        c.gridheight = 1;
                        c.gridx = 1;
                        

                        contenedorDescargas.add(lblTituloCap, c);
                        
                        c.gridy = numeroVuelta+lugarDePartida+1;
                        contenedorDescargas.add(lblEstatus, c);
                        
                        c.gridwidth = 1;
                        c.weightx = 70;
                        c.gridy = numeroVuelta+lugarDePartida+2; 
                        c.gridx = 2;
                        contenedorDescargas.add(btnPararDescarga, c);                        
                        c.gridx = 1;   
                        c.weightx = 80;
                        c.anchor = c.WEST;
                        //contenedorDescargas.add(btnReproducirDescarga, c);
                        
                        c.gridwidth = 2;
                        c.gridx = 1;
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
                                //contenedorDescargas.remove(btnReproducirDescarga);
                                contenedorDescargas.repaint();
                                contenedorDescargas.revalidate();
                                descargasEnCurso.remove(descargaAAgregar);
                                mw.ActualizarLabelDescargas();
                                descargaAAgregar.setTerminate(true);
                            }
                        });
                        
                        
                        btnReproducirDescarga.addActionListener(new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(java.awt.event.ActionEvent evt) {                                
                                ReproductorDeVideo rpv = new ReproductorDeVideo();
                                System.out.println("URL: " +descargaAAgregar.getUrl().toString());
                                ReproductorDeVideo.urlDelArchivo = descargaAAgregar.getUrl().toString();
                                rpv.Iniciar();
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

                        //0, 153, 51
                        btnReproducirDescarga.setBackground(new java.awt.Color(0, 153, 51));
                        btnReproducirDescarga.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
                        btnReproducirDescarga.setForeground(new java.awt.Color(51, 51, 51));
                        btnReproducirDescarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/play.png"))); // NOI18N
                        btnReproducirDescarga.setText("Play");
                        btnReproducirDescarga.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                        btnReproducirDescarga.setFocusable(false);
                        btnReproducirDescarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btnReproducirDescarga.setMargin(new java.awt.Insets(10, 14, 2, 14));
                        btnReproducirDescarga.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                        btnReproducirDescarga.setPreferredSize(new Dimension(80, 20));
                        
                        
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
                        btnPararDescarga.setPreferredSize(new Dimension(70, 20));
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
                .addComponent(contenedorDescargas, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(contenedorDescargas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedorDescargas;
    // End of variables declaration//GEN-END:variables
}
