/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.busqueda.Busqueda;
import com.massdown.views.libs.WrapLayout;
import es.gmarco.massdown.recursos.MetodosUtiles;
import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.metal.MetalScrollBarUI;

/**
 *
 * @author Guillermo
 */
public class SearchPanel extends javax.swing.JPanel {

    private final MainWindow mw;
    private Busqueda busqueda;
    
    public SearchPanel(final MainWindow mw,final String consulta) {
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
        
        new Thread() {

            @Override
            public void run() {
                super.run(); 
                mw.MostarPBar(true);
                try {
                    busqueda = new Busqueda((consulta.startsWith("search series")) ? "" : consulta);
                } catch (IOException ex) {
                    Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                new MuestraResultados().start();
            }
                        
        }.start();
        
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
                 final String tipoSerie = datosSerie[4];
                final int numeroVuelta = i;
                
                Thread agregadorResultados = new Thread() {
                    
                    JLabel lblSerie;                    
                                        
                    public synchronized void CrearComponentesGraficos() throws MalformedURLException, IOException {
                        
                        lblSerie = new JLabel();
                        
                        lblSerie.addMouseListener(new java.awt.event.MouseAdapter() {
                            @Override
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                mw.getTxtBusqueda().setText(urlSerie.replaceAll("-", " ").replaceAll(".seriespepito.com", "").replaceAll("http://", "").replaceAll("/", ""));
                                mw.IrATab(mw.getLblParaIrAlTabSerie());
                               
                            }
                            @Override
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                lblSerie.setForeground(new java.awt.Color(241, 196, 15));
                            }
                            @Override
                            public void mouseExited(java.awt.event.MouseEvent evt) {
                                lblSerie.setForeground(new java.awt.Color(204, 204, 204));
                            }
                        });
                        
                        lblSerie.setText((tituloSerie.toCharArray().length < 20) ? tituloSerie : tituloSerie.substring(0, 20)+"...");
                        lblSerie.setToolTipText(tituloSerie);
                        lblSerie.setIcon(MetodosUtiles.escalarImagen(ImageIO.read(new URL(urlImagenSerie)), 0.7, mw));
                        
                        AplicarEstiloALosComponentes();                                                                   
                        //pnlResultados.setLayout(new WrapLayout(WrapLayout.LEFT, 30, 30));
                        
                        switch(tipoSerie) {
                            case "destacada":
                                pnlDestacadas.add(lblSerie);                        
                                pnlDestacadas.revalidate();
                                pnlDestacadas.repaint(); 
                                break;
                            case "noDestacada":
                                pnlResultados.setLayout(new WrapLayout(WrapLayout.LEFT, 30, 30));
                                pnlResultados.add(lblSerie);  
                                pnlResultados.revalidate();
                                pnlResultados.repaint(); 
                                break;
                        }
                        
                        
                        
                        
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
                            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    
                    
                };
                agregadorResultados.start();
            }
            mw.MostarPBar(false);
        }        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSearch = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        pnlDestacasScroll = new javax.swing.JScrollPane();
        pnlDestacadas = new javax.swing.JPanel();
        pnlResultados = new javax.swing.JPanel();
        lblTitulo2 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(536, 459));
        setMinimumSize(new java.awt.Dimension(536, 459));

        pnlSearch.setBackground(new java.awt.Color(56, 56, 56));

        lblTitulo1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        lblTitulo1.setForeground(new java.awt.Color(230, 126, 34));
        lblTitulo1.setText("on the last day");

        pnlDestacasScroll.setBackground(new java.awt.Color(56, 56, 56));
        pnlDestacasScroll.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnlDestacasScroll.setMaximumSize(new java.awt.Dimension(536, 459));
        pnlDestacasScroll.setMinimumSize(new java.awt.Dimension(536, 459));
        pnlDestacasScroll.setPreferredSize(new java.awt.Dimension(810, 202));

        pnlDestacadas.setBackground(new java.awt.Color(56, 56, 56));
        pnlDestacadas.setMaximumSize(new java.awt.Dimension(536, 459));
        pnlDestacadas.setMinimumSize(new java.awt.Dimension(536, 459));
        pnlDestacadas.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                pnlDestacadasAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        pnlDestacadas.setLayout(new java.awt.GridLayout(1, 0, 5, 5));
        pnlDestacasScroll.setViewportView(pnlDestacadas);

        pnlResultados.setBackground(new java.awt.Color(56, 56, 56));
        pnlResultados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlResultadosLayout = new javax.swing.GroupLayout(pnlResultados);
        pnlResultados.setLayout(pnlResultadosLayout);
        pnlResultadosLayout.setHorizontalGroup(
            pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
        pnlResultadosLayout.setVerticalGroup(
            pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 79, Short.MAX_VALUE)
        );

        lblTitulo2.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        lblTitulo2.setForeground(new java.awt.Color(231, 76, 60));
        lblTitulo2.setText("Featured");

        lblTitulo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(192, 57, 43));
        lblTitulo.setText("search results");

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(lblTitulo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo1)
                        .addGap(0, 245, Short.MAX_VALUE))
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                        .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlResultados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlDestacasScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo1)
                    .addComponent(lblTitulo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDestacasScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
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

    private void pnlDestacadasAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_pnlDestacadasAncestorMoved
        
    }//GEN-LAST:event_pnlDestacadasAncestorMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JPanel pnlDestacadas;
    private javax.swing.JScrollPane pnlDestacasScroll;
    private javax.swing.JPanel pnlResultados;
    private javax.swing.JPanel pnlSearch;
    // End of variables declaration//GEN-END:variables
}
