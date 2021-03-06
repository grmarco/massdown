/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import com.massdown.core.Capitulo;
import com.massdown.core.Serie;
import com.massdown.core.Servidor;
import es.gmarco.massdown.recursos.MetodosUtiles;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.metal.MetalScrollBarUI;



public class SeriePanel extends javax.swing.JPanel {

    private Serie serie;
    private Capitulo capituloSeleccionado;
    private MainWindow mw;
    
    
    public SeriePanel(final MainWindow mw, final String urlSerie) {
        
        this.mw = mw;
        initComponents();                                      
        
        Thread cargadorCapitulosEnInterfaz = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    mw.MostarPBar(true);
                    //Como se pasa la informacion de la url una panel a otro
                    // por medio de txtbusqueda se cambia a blanco la fuente 
                    // para que no se vea la url que queda muy feo,
                    // luego lo volvemos a cambiar
                    mw.getTxtBusqueda().setForeground(new java.awt.Color(255, 255, 255));
                    
                    serie = new Serie(urlSerie);      
                    String descripcion = serie.getDescripcionSerie();
                    String tituloSerie = serie.getNombreSerie();
                    
                    // Como seriesblanco tiene un sistema ´básico antibots hay que establcer el useragent para que nos deje obtener la conexión
                    URL url = new URL(serie.getUrlImagen());
                    URLConnection conn = url.openConnection();
                    conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");                                                                        
                    Icon imagenSerie = MetodosUtiles.escalarImagen(ImageIO.read(conn.getInputStream()), 0.4, mw);
                    
                    serie.getCapitulos();
                    
                    /* Setteamos la información obtenida de los servidores 
                    por la clase Serie en los distintos elementos gráficos
                    y volvemos a poner bien el color de la fuente de la 
                    caja de texto
                    */
                    mw.getTxtBusqueda().setText(tituloSerie);
                    mw.getTxtBusqueda().setForeground(new java.awt.Color(0, 0, 0));
                    lblTitulo.setText(tituloSerie);
                    lblDescripcion.setText(descripcion);
                    iconSerie.setIcon(imagenSerie);
                    
                    
                   
                    DefaultListModel listModel = new DefaultListModel();
                    lstCapitulos.setModel(listModel);
                    
                    for(int i = serie.getCapitulos().size()-1 ; i > 0 ; i--) {
                        String[] capitulo = serie.getCapitulos().get(i);
                        listModel.addElement(capitulo[0].replaceAll("¿Quieres escribir comentarios? Necesitas ser usuario registrado en SeriesPepito para poder escribir comentarios, si ya lo eres puedes entrar con tu usuario aquí y si no... ¡a que esperas! ¡regístrate aquí!. Entrar en SeriesPepito", ""));  
                        
                    }   
                    lstCapitulos.setSelectedIndex(0);
                    CargarListaServidores();
                    mw.MostarPBar(false);
                    
                    interrupt();
                } catch (IOException ex) {
                    mw.MostarPBar(false);
                    mw.IrATab(mw.getBtnSearch());
                    Logger.getLogger(SeriePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        };
        
        cargadorCapitulosEnInterfaz.start();
        
        
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
        scrollDescripcion.getVerticalScrollBar().setUI(scrollBarStyle);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconSerie = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCapitulos = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstServidores = new javax.swing.JList();
        lblServerSeleccionado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        scrollDescripcion = new javax.swing.JScrollPane();
        lblDescripcion = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(56, 56, 56));

        lblTitulo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText(" ");

        jScrollPane2.setBackground(new java.awt.Color(56, 56, 56));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lstCapitulos.setBackground(new java.awt.Color(56, 56, 56));
        lstCapitulos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lstCapitulos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstCapitulos.setForeground(new java.awt.Color(255, 255, 255));
        lstCapitulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lstServidores.setBackground(new java.awt.Color(56, 56, 56));
        lstServidores.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lstServidores.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstServidores.setForeground(new java.awt.Color(204, 204, 204));
        lstServidores.setFixedCellHeight(30);
        lstServidores.setSelectionBackground(new java.awt.Color(230, 126, 34));
        lstServidores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstServidoresMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lstServidores);

        lblServerSeleccionado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblServerSeleccionado.setForeground(new java.awt.Color(204, 204, 204));
        lblServerSeleccionado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblServerSeleccionado.setText(" ");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/back.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("(double click to download)");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("(one click to show the downloads)");

        scrollDescripcion.setBorder(null);
        scrollDescripcion.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDescripcion.setMaximumSize(new java.awt.Dimension(21, 22));
        scrollDescripcion.setPreferredSize(new java.awt.Dimension(21, 22));

        lblDescripcion.setBackground(new java.awt.Color(56, 56, 56));
        lblDescripcion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(204, 204, 204));
        lblDescripcion.setMaximumSize(new java.awt.Dimension(21, 22));
        lblDescripcion.setMinimumSize(new java.awt.Dimension(21, 22));
        lblDescripcion.setPreferredSize(new java.awt.Dimension(21, 22));
        scrollDescripcion.setViewportView(lblDescripcion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(iconSerie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(lblServerSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(9, 9, 9)
                        .addComponent(lblServerSeleccionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(iconSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(scrollDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(45, 45, 45))))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void CargarListaServidores() {
        Thread cargaListaServidores = new Thread(){

            @Override
            public void run() {
                
                mw.MostarPBar(true);
                try {
                    capituloSeleccionado = serie.CrearObjetoCapitulo(serie.getCapitulos().size() -1- lstCapitulos.getSelectedIndex());
                    capituloSeleccionado.ObtenerServidoresCapitulo();
                    DefaultListModel listModel = new DefaultListModel();
                    lblServerSeleccionado.setText((String) lstCapitulos.getSelectedValue());
                    lstServidores.setModel(listModel);
                    
                    if(capituloSeleccionado.servidoresConElCapitulo.size() <= 0) {
                        listModel.addElement("there were no downloads option");
                    }
                    
                    for(int i = 0 ; i < capituloSeleccionado.servidoresConElCapitulo.size() ; i++) {
                        Servidor servidor = capituloSeleccionado.servidoresConElCapitulo.get(i);
                        listModel.addElement("Download option "+ (i + 1) + " - " + servidor.idiomaCapitulo + "  " + servidor.tieneSubtitulos);
                        
                    }                                        
                    mw.MostarPBar(false);
                } catch (IOException ex) {
                    mw.MostarPBar(false);
                    Logger.getLogger(SeriePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
  
            }

        };
        cargaListaServidores.start();
    }
    
    private void lstCapitulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCapitulosMouseClicked
        CargarListaServidores();        
    }//GEN-LAST:event_lstCapitulosMouseClicked

    private void lstServidoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstServidoresMouseClicked
        if(evt.getClickCount() == 2) {
            new Thread() {

                @Override
                public void run() {
                    super.run(); 
                    mw.MostarPBar(true);
                    final Servidor servidor = capituloSeleccionado.servidoresConElCapitulo.get(lstServidores.getSelectedIndex());                                        
                    mw.gestorDescargas.addDescarga(servidor, capituloSeleccionado);                                                 
                    mw.ActualizarLabelDescargas();
                    mw.MostarPBar(false);
                }
                
            }.start();
            
        }
    }//GEN-LAST:event_lstServidoresMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        mw.IrATab(mw.getBtnSearch()); 
        mw.getTxtBusqueda().setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconSerie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane lblDescripcion;
    private javax.swing.JLabel lblServerSeleccionado;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList lstCapitulos;
    private javax.swing.JList lstServidores;
    private javax.swing.JScrollPane scrollDescripcion;
    // End of variables declaration//GEN-END:variables
 
}
