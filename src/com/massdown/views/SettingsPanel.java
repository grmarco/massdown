/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.views;

import es.gmarco.massdown.recursos.Actualiza;
import es.gmarco.massdown.recursos.Configuracion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Guillermo
 */
public class SettingsPanel extends javax.swing.JPanel {
    
    public Configuracion config = new Configuracion();
    private MainWindow mw;
    
    public SettingsPanel(MainWindow mw) {
        initComponents();
        
        this.mw = mw;
        
        lblRuta.setText(Configuracion.getDirectorioDeDescarga());
        
        lblVersionActual.setText("Current version "+Configuracion.getVersion());
        
        if(Configuracion.isDescargaEnCola()) {
            rbDescargarCola.setSelected(true);
        } else {
            rbDescargarALaVez.setSelected(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpModoDescarga = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnSeleccionarRutaDescarga = new javax.swing.JButton();
        lblRuta = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rbDescargarCola = new javax.swing.JRadioButton();
        rbDescargarALaVez = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        btnComprobarActualizaciones = new javax.swing.JButton();
        lblVersionActual = new javax.swing.JLabel();

        setBackground(new java.awt.Color(56, 56, 56));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/massdown/img/icon.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(230, 126, 34));
        jLabel3.setText("by grmarco");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("change download path");

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(231, 76, 60));
        jLabel6.setText("massdown");

        btnSeleccionarRutaDescarga.setText("Select download path");
        btnSeleccionarRutaDescarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarRutaDescargaActionPerformed(evt);
            }
        });

        lblRuta.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblRuta.setForeground(new java.awt.Color(255, 255, 255));
        lblRuta.setText("./");
        lblRuta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("download mode");

        rbDescargarCola.setBackground(new java.awt.Color(56, 56, 56));
        btngrpModoDescarga.add(rbDescargarCola);
        rbDescargarCola.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        rbDescargarCola.setForeground(new java.awt.Color(153, 153, 153));
        rbDescargarCola.setText("download queued");
        rbDescargarCola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescargarColaActionPerformed(evt);
            }
        });

        rbDescargarALaVez.setBackground(new java.awt.Color(56, 56, 56));
        btngrpModoDescarga.add(rbDescargarALaVez);
        rbDescargarALaVez.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        rbDescargarALaVez.setForeground(new java.awt.Color(153, 153, 153));
        rbDescargarALaVez.setText("download all at once");
        rbDescargarALaVez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescargarALaVezActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("check for updates");

        btnComprobarActualizaciones.setText("Check updates");
        btnComprobarActualizaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobarActualizacionesActionPerformed(evt);
            }
        });

        lblVersionActual.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblVersionActual.setForeground(new java.awt.Color(153, 153, 153));
        lblVersionActual.setText("Current version 1.0");
        lblVersionActual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnComprobarActualizaciones)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblVersionActual))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbDescargarCola)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbDescargarALaVez)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSeleccionarRutaDescarga, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarRutaDescarga)
                    .addComponent(lblRuta))
                .addGap(30, 30, 30)
                .addComponent(jLabel9)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDescargarCola)
                    .addComponent(rbDescargarALaVez))
                .addGap(30, 30, 30)
                .addComponent(jLabel10)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComprobarActualizaciones)
                    .addComponent(lblVersionActual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rbDescargarALaVezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescargarALaVezActionPerformed
        config.setDescargaEnCola(false);       
    }//GEN-LAST:event_rbDescargarALaVezActionPerformed

    private void rbDescargarColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescargarColaActionPerformed
        config.setDescargaEnCola(true);
        if(!mw.gestorDescargas.getDescargasEnCurso().isEmpty()) {
            JOptionPane.showMessageDialog(new JOptionPane(),
            "This setting does not apply to current downloads",
            "Massdown message",            
            JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_rbDescargarColaActionPerformed

    private void btnSeleccionarRutaDescargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarRutaDescargaActionPerformed
         JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (selectorArchivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            config.setDirectorioDeDescarga(String.valueOf(selectorArchivo.getSelectedFile()));
            lblRuta.setText(Configuracion.getDirectorioDeDescarga());
            lblRuta.setToolTipText(Configuracion.getDirectorioDeDescarga());
        }
    }//GEN-LAST:event_btnSeleccionarRutaDescargaActionPerformed

    private void btnComprobarActualizacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobarActualizacionesActionPerformed
        try {
            new Actualiza(false);
        } catch (IOException ex) {
            Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnComprobarActualizacionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprobarActualizaciones;
    private javax.swing.JButton btnSeleccionarRutaDescarga;
    private javax.swing.ButtonGroup btngrpModoDescarga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblRuta;
    private javax.swing.JLabel lblVersionActual;
    private javax.swing.JRadioButton rbDescargarALaVez;
    private javax.swing.JRadioButton rbDescargarCola;
    // End of variables declaration//GEN-END:variables
}
