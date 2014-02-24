/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.busqueda.Busqueda;
import com.massdown.gestordescarga.UnaDescarga;
import com.massdown.views.libs.WrapLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Guillermo
 */
public class SearchView extends javax.swing.JPanel {

    private MainWindow mw;
    private Busqueda busqueda;
    
    public SearchView(MainWindow mw, String consulta) {
        initComponents();
        this.mw = mw;
        try {
            busqueda = new Busqueda((consulta.startsWith("search series")) ? "" : consulta);
        } catch (IOException ex) {
            Logger.getLogger(SearchView.class.getName()).log(Level.SEVERE, null, ex);
        }
        new MuestraResultados().start();
    }
    
    class MuestraResultados extends Thread {        
        
        @Override
        public void run() {  

            ArrayList<String[]> resultados = busqueda.getResultadosDeLaBusqueda();
            int numeroResultados = resultados.size();    

            for(int i = 0 ; i < numeroResultados ; i++) {
                
                final String[] datosSerie = resultados.get(i);
                final String tituloSerie = datosSerie[0]; 
                final String urlSerie = datosSerie[1];
                final String urlImagenSerie = datosSerie[2];
                final int numeroVuelta = i;
                
                Thread agregadorResultados = new Thread() {
                    
                    JLabel lblSerie;                    
                                        
                    public synchronized void CrearComponentesGraficos() throws MalformedURLException, IOException {
                        
                        lblSerie = new JLabel();
                        
                        lblSerie.addMouseListener(new java.awt.event.MouseAdapter() {
                            @Override
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                mw.getPnlPrincipal().removeAll();
                                mw.getPnlPrincipal().add(new SeriePanel(mw, urlSerie.replaceAll(" ", "-").replaceAll("/serie/", "")));
                                mw.getPnlPrincipal().repaint();
                                mw.getPnlPrincipal().revalidate();
                               
                            }
                            @Override
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                lblSerie.setForeground(new java.awt.Color(231,76,60));
                            }
                            @Override
                            public void mouseExited(java.awt.event.MouseEvent evt) {
                                lblSerie.setForeground(new java.awt.Color(204, 204, 204));
                            }
                        });
                        
                        lblSerie.setText((tituloSerie.toCharArray().length < 20) ? tituloSerie : tituloSerie.substring(0, 10)+"...");
                        lblSerie.setToolTipText(tituloSerie);
                        lblSerie.setIcon(escalarImagen(ImageIO.read(new URL(urlImagenSerie)), 1));
                        
                        AplicarEstiloALosComponentes();                                                                   
                        pnlResultados.setLayout(new WrapLayout(WrapLayout.LEFT, 30, 30));
                        
                        pnlResultados.add(lblSerie);                        
                        pnlResultados.revalidate();
                        pnlResultados.repaint();
                        
                        
                        
                    }
                    
                    public synchronized void AplicarEstiloALosComponentes() {
                        lblSerie.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
                        lblSerie.setForeground(new java.awt.Color(204, 204, 204));
                        lblSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblSerie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        lblSerie.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        lblSerie.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    }                                        

                    @Override
                    public void run() {
                        super.run(); 
                        try {
                            CrearComponentesGraficos();
                        } catch (IOException ex) {
                            Logger.getLogger(SearchView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    
                    
                };
                agregadorResultados.start();
            }            
        }        
    }
    
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlResultados = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(56, 56, 56));

        pnlResultados.setBackground(new java.awt.Color(56, 56, 56));

        javax.swing.GroupLayout pnlResultadosLayout = new javax.swing.GroupLayout(pnlResultados);
        pnlResultados.setLayout(pnlResultadosLayout);
        pnlResultadosLayout.setHorizontalGroup(
            pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );
        pnlResultadosLayout.setVerticalGroup(
            pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(pnlResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlResultados;
    // End of variables declaration//GEN-END:variables
}
