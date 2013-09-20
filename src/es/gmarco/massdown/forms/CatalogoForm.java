/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.gmarco.massdown.forms;

import es.gmarco.massdown.clases_escalables.MetodosUtiles;
import es.gmarco.massdown.superclases.Main;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Guillermo
 */
public class CatalogoForm extends javax.swing.JFrame {

    private Main programaPrincipal;
    private int numPagina = 0;
    private String letra;
    
    public CatalogoForm(Main mn) {
        this.programaPrincipal = mn;
        
        initComponents();
        MostrarLetrasDelAbecedario();
        
        try {
            CargarSeriesPagina("0-9");
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CatalogoForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setVisible(true);
        
        new Thread() {

            @Override
            public void run() {
                try {
                    ObtenerListaDeSeriesDestacadas();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(CatalogoForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CatalogoForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }.start();
        
    }

    
    private void MostrarLetrasDelAbecedario() {
        char[] letras = MetodosUtiles.DevuelveAbecedario();
        
        for(char letra : letras) {
            cmbLetras.addItem(Character.toUpperCase(letra));        
        }
    }
    
    private void CargarSeriesPagina(String pagina) throws MalformedURLException, IOException {
        String codigoFuente = MetodosUtiles.ObtenerCodigoFuente("http://www.seriesyonkis.com/lista-de-series/" + pagina + "/" + numPagina)[1];
        
        int nodo = codigoFuente.indexOf("<ul id=\"list-container\"");
        
        DefaultListModel lst = new DefaultListModel();
                
        while(nodo >= 0) {
            try {
                
            
            String[] serie = MetodosUtiles.ObtenerCadenaEntreTags(codigoFuente, 
                    "<li>  <a href=\"", 
                    "\" title=\"", 22, 0, nodo);
            nodo = Integer.parseInt(serie[1]);
            serie[0] = serie[0].replaceAll("-", " ");
            
            String[] palabrasDelTitulo = serie[0].split(" ");
            

            for(int i = 0 ; i < palabrasDelTitulo.length ; i++) {
                char[] letrasDeLaPalabra = palabrasDelTitulo[i].toCharArray();  
                if(letrasDeLaPalabra.length > 0) {
                    letrasDeLaPalabra[0] = Character.toUpperCase(letrasDeLaPalabra[0]);                
                    palabrasDelTitulo[i] = String.copyValueOf(letrasDeLaPalabra);
                }
            }                       
            

            
            serie[0] = Arrays.toString(palabrasDelTitulo)
                                  .replace(",", "")  
                                  .replace("[", "")  
                                  .replace("]", "");
            
            
            if(lst.contains(serie[0])) {
                break;
            }
                
            lst.addElement(serie[0]);
            } catch (StringIndexOutOfBoundsException ex) {
                lst.removeElementAt(lst.getSize());
                break;
            }
                                                              
        }
        
        lstSeriePagina.setModel(lst);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblCargaCaps = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstDestacados = new javax.swing.JList();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        cmbLetras = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstSeriePagina = new javax.swing.JList();
        jSeparator5 = new javax.swing.JSeparator();
        lblPagina = new javax.swing.JLabel();
        btnAdelante = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setTitle("Catalogo de series");
        setIconImage(new ImageIcon(getClass().getResource("/es/gmarco/massdown/recursos/icon.png")).getImage());

        jPanel5.setBackground(new java.awt.Color(30, 30, 30));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(210, 270));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Series populares");

        lblCargaCaps.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblCargaCaps.setForeground(new java.awt.Color(255, 255, 255));
        lblCargaCaps.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lstDestacados.setFont(new java.awt.Font("Nirmala UI", 1, 11)); // NOI18N
        lstDestacados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstDestacados.setVerifyInputWhenFocusTarget(false);
        lstDestacados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstDestacadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstDestacados);

        jSeparator6.setBackground(new java.awt.Color(243, 156, 18));
        jSeparator6.setForeground(new java.awt.Color(243, 156, 18));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblCargaCaps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCargaCaps, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel1.setBackground(new java.awt.Color(53, 50, 48));

        cmbLetras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbLetras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0-9" }));
        cmbLetras.setMaximumSize(new java.awt.Dimension(39, 21));
        cmbLetras.setMinimumSize(new java.awt.Dimension(39, 21));
        cmbLetras.setPreferredSize(new java.awt.Dimension(39, 21));
        cmbLetras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLetrasActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lstSeriePagina.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        lstSeriePagina.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstSeriePagina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstSeriePaginaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstSeriePagina);

        jSeparator5.setBackground(new java.awt.Color(243, 156, 18));
        jSeparator5.setForeground(new java.awt.Color(243, 156, 18));

        lblPagina.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPagina.setText("Pagina 0 - Letra 0-9");

        btnAdelante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdelante.setText(">");
        btnAdelante.setMaximumSize(new java.awt.Dimension(49, 21));
        btnAdelante.setMinimumSize(new java.awt.Dimension(49, 21));
        btnAdelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdelanteActionPerformed(evt);
            }
        });

        btnAtras.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAtras.setText("<");
        btnAtras.setMaximumSize(new java.awt.Dimension(49, 21));
        btnAtras.setMinimumSize(new java.awt.Dimension(49, 21));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPagina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPagina)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lstDestacadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDestacadosMouseClicked
        programaPrincipal.setTextoTxtSerie((String) lstDestacados.getSelectedValue());
        programaPrincipal.CargarSerie((String) lstDestacados.getSelectedValue());
        if(evt.getClickCount() >= 2)
            programaPrincipal.requestFocus();
    }//GEN-LAST:event_lstDestacadosMouseClicked

    private void lstSeriePaginaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstSeriePaginaMouseClicked
        programaPrincipal.setTextoTxtSerie((String) lstSeriePagina.getSelectedValue());
        programaPrincipal.CargarSerie((String) lstSeriePagina.getSelectedValue());
        if(evt.getClickCount() >= 2)
            programaPrincipal.requestFocus();
    }//GEN-LAST:event_lstSeriePaginaMouseClicked

    private void cmbLetrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLetrasActionPerformed
        
        lblPagina.setText("Pagina " + numPagina / 100 + " - Letra " + cmbLetras.getSelectedItem());
        letra = String.valueOf(cmbLetras.getSelectedItem());
        new Thread() {

            @Override
            public void run() {
                try {
                    CargarSeriesPagina(String.valueOf(cmbLetras.getSelectedItem()));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(CatalogoForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CatalogoForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }.start();
            

    }//GEN-LAST:event_cmbLetrasActionPerformed

    private void btnAdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdelanteActionPerformed
        
        try {            
            numPagina = numPagina + 100;        
            lblPagina.setText("Pagina " + numPagina / 100 + " - Letra " + cmbLetras.getSelectedItem());
            CargarSeriesPagina(letra);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            numPagina = numPagina - 100;
            lblPagina.setText("Pagina " + numPagina / 100 + " - Letra " + cmbLetras.getSelectedItem());
            JOptionPane.showMessageDialog(new JOptionPane(), "No hay mas paginas");
        }
    }//GEN-LAST:event_btnAdelanteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        try {            
            numPagina = numPagina - 100;        
            lblPagina.setText("Pagina " + numPagina / 100 + " - Letra " + cmbLetras.getSelectedItem());
            CargarSeriesPagina(letra);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            numPagina = numPagina + 100;
            lblPagina.setText("Pagina " + numPagina / 100 + " - Letra " + cmbLetras.getSelectedItem());
            JOptionPane.showMessageDialog(new JOptionPane(), "No hay mas paginas");
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private ArrayList<String> ObtenerListaDeSeriesDestacadas() throws MalformedURLException, IOException {
        
        String codigoFuente = MetodosUtiles.ObtenerCodigoFuente("http://www.seriesyonkis.com/series-mas-vistas")[1];
        int nodo = codigoFuente.indexOf("vistas ayer</h1>");
        
        DefaultListModel lst = new DefaultListModel();
                
        while(nodo >= 0) {
            
            String[] serie = MetodosUtiles.ObtenerCadenaEntreTags(codigoFuente, 
                    "<img width=\"100\" height=\"144\" class=\"img-shadow\"", 
                    "/> </a> <strong>  <a href=\"", 55, 0, nodo);
            nodo = Integer.parseInt(serie[1]);
            serie[0] = serie[0].replaceAll("-", " ")
                    .substring(serie[0].indexOf("\" alt=\""))
                    .replaceAll("\" alt=\"", "")
                    .replaceAll("\" ", "");
            
            String[] palabrasDelTitulo = serie[0].split(" ");
            

            for(int i = 0 ; i < palabrasDelTitulo.length ; i++) {
                char[] letrasDeLaPalabra = palabrasDelTitulo[i].toCharArray();  
                if(letrasDeLaPalabra.length >= 0) {
                    letrasDeLaPalabra[0] = Character.toUpperCase(letrasDeLaPalabra[0]);                
                    palabrasDelTitulo[i] = String.copyValueOf(letrasDeLaPalabra);
                }
            }                       

            
            serie[0] = Arrays.toString(palabrasDelTitulo)
                                  .replace(",", "")  
                                  .replace("[", "")  
                                  .replace("]", "");
            
            if(lst.contains(serie[0])) {
                break;
            }
                
            lst.addElement(serie[0]);
                                                              
        }
        
        lstDestacados.setModel(lst);
        
        
        return null;        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdelante;
    private javax.swing.JButton btnAtras;
    private javax.swing.JComboBox cmbLetras;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblCargaCaps;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JList lstDestacados;
    private javax.swing.JList lstSeriePagina;
    // End of variables declaration//GEN-END:variables
}
