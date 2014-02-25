/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.busqueda.Busqueda;
import com.massdown.gestordescarga.UnaDescarga;
import com.massdown.views.libs.WrapLayout;
import com.sun.java.swing.plaf.motif.MotifScrollBarUI;
import java.awt.Color;
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
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.plaf.synth.SynthScrollBarUI;

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
        pnlDestacasScroll.getHorizontalScrollBar().setUnitIncrement(30);
         ScrollBarUI scrollBarStyle = new MetalScrollBarUI(){
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(new Color(56,56,56));
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(new Color(56,56,56));
                return button;
            }
        };
        pnlDestacasScroll.getHorizontalScrollBar().setUI(scrollBarStyle);
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
                 final String descripcion = datosSerie[3];
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
                        
                        lblSerie.setText((tituloSerie.toCharArray().length < 20) ? tituloSerie : tituloSerie.substring(0, 20)+"...");
                        lblSerie.setToolTipText(tituloSerie);
                        lblSerie.setIcon(escalarImagen(ImageIO.read(new URL(urlImagenSerie)), 0.7));
                        
                        AplicarEstiloALosComponentes();                                                                   
                        //pnlResultados.setLayout(new WrapLayout(WrapLayout.LEFT, 30, 30));
                        
                        pnlResultados.add(lblSerie);                        
                        pnlResultados.revalidate();
                        pnlResultados.repaint();
                        pnlDestacasScroll.repaint();
                        pnlDestacasScroll.revalidate();
                        
                        
                        
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
    
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSearch = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();
        pnlDestacasScroll = new javax.swing.JScrollPane();
        pnlResultados = new javax.swing.JPanel();

        pnlSearch.setBackground(new java.awt.Color(56, 56, 56));

        lblTitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(231, 76, 60));
        lblTitulo.setText("Featured");

        lblTitulo1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblTitulo1.setForeground(new java.awt.Color(230, 126, 34));
        lblTitulo1.setText("on the last day");

        pnlDestacasScroll.setBackground(new java.awt.Color(56, 56, 56));
        pnlDestacasScroll.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDestacasScroll.setMaximumSize(new java.awt.Dimension(810, 202));
        pnlDestacasScroll.setPreferredSize(new java.awt.Dimension(810, 202));

        pnlResultados.setBackground(new java.awt.Color(56, 56, 56));
        pnlResultados.setMaximumSize(new java.awt.Dimension(810, 202));
        pnlResultados.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                pnlResultadosAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        pnlResultados.setLayout(new java.awt.GridLayout(1, 0, 25, 25));
        pnlDestacasScroll.setViewportView(pnlResultados);

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDestacasScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo1)
                        .addGap(0, 358, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(lblTitulo1))
                .addGap(15, 15, 15)
                .addComponent(pnlDestacasScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlResultadosAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_pnlResultadosAncestorMoved
        
    }//GEN-LAST:event_pnlResultadosAncestorMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JScrollPane pnlDestacasScroll;
    private javax.swing.JPanel pnlResultados;
    private javax.swing.JPanel pnlSearch;
    // End of variables declaration//GEN-END:variables
}
