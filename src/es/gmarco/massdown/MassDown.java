/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.gmarco.massdown;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MassDown extends JFrame {

    private Serie serie;
    private ArrayList capitulosAMostrar = new ArrayList();
    
    public MassDown() {
        initComponents();
       
    }

    //Codigo generado por el dise�ador de interfaces
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblIconSerie = new javax.swing.JLabel();
        lblTituloSerie = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JEditorPane();
        txtSerie = new javax.swing.JTextField();
        scrollpane = new javax.swing.JScrollPane();
        contenedorDescargas = new javax.swing.JToolBar();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        btnDescargar = new javax.swing.JButton();
        cbmTemporadas = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCapitulos = new javax.swing.JList();
        lblDeTituloCapitulos = new javax.swing.JLabel();
        pbarCargaCapitulos = new javax.swing.JProgressBar();
        lblCargaCaps = new javax.swing.JLabel();

        jSeparator3.setBackground(new java.awt.Color(239, 64, 54));
        jSeparator3.setForeground(new java.awt.Color(239, 64, 54));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MassDown por @gmarco_");
        setMinimumSize(new java.awt.Dimension(800, 480));

        jPanel3.setBackground(new java.awt.Color(30, 30, 30));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Dotum", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("MassDown");

        jSeparator2.setBackground(new java.awt.Color(239, 64, 54));
        jSeparator2.setForeground(new java.awt.Color(239, 64, 54));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Ajustes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Cat�logo de series");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 689, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel1.setBackground(new java.awt.Color(53, 50, 48));

        jPanel2.setBackground(new java.awt.Color(239, 64, 54));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(190, 291));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Info de la serie");

        lblTituloSerie.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblTituloSerie.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloSerie.setText("-");
        lblTituloSerie.setMaximumSize(new java.awt.Dimension(6, 166));

        txtDescripcion.setEditable(false);
        txtDescripcion.setMaximumSize(new java.awt.Dimension(6, 2147483647));
        txtDescripcion.setOpaque(false);
        jScrollPane4.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIconSerie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4)
                    .addComponent(lblTituloSerie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIconSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSerie.setFont(new java.awt.Font("Dotum", 0, 36)); // NOI18N
        txtSerie.setText("introduce una serie y pulsa intro");
        txtSerie.setToolTipText("");
        txtSerie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSerieFocusGained(evt);
            }
        });
        txtSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSerieKeyPressed(evt);
            }
        });

        scrollpane.setBackground(new java.awt.Color(255, 255, 255));
        scrollpane.setOpaque(false);

        contenedorDescargas.setFloatable(false);
        contenedorDescargas.setOrientation(javax.swing.SwingConstants.VERTICAL);
        contenedorDescargas.setRollover(true);
        scrollpane.setViewportView(contenedorDescargas);

        jPanel5.setBackground(new java.awt.Color(239, 64, 54));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(210, 270));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Opciones");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Temporada");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Idioma");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Espa�ol" }));

        btnDescargar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDescargar.setText("Descargar");
        btnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarActionPerformed(evt);
            }
        });

        cbmTemporadas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbmTemporadas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cbmTemporadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbmTemporadasMouseClicked(evt);
            }
        });
        cbmTemporadas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmTemporadasItemStateChanged(evt);
            }
        });
        cbmTemporadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmTemporadasActionPerformed(evt);
            }
        });
        cbmTemporadas.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                cbmTemporadasVetoableChange(evt);
            }
        });

        lstCapitulos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lstCapitulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstCapitulos.setToolTipText("Pulsa Ctrl + Click para sleccionar m�s de un capitulo");
        jScrollPane1.setViewportView(lstCapitulos);

        lblDeTituloCapitulos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDeTituloCapitulos.setForeground(new java.awt.Color(255, 255, 255));
        lblDeTituloCapitulos.setText("Cap�tulos");

        pbarCargaCapitulos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pbarCargaCapitulosStateChanged(evt);
            }
        });

        lblCargaCaps.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblCargaCaps.setForeground(new java.awt.Color(255, 255, 255));
        lblCargaCaps.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cbmTemporadas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDescargar))
                    .addComponent(pbarCargaCapitulos, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblDeTituloCapitulos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCargaCaps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(cbmTemporadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDeTituloCapitulos)
                    .addComponent(lblCargaCaps, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(pbarCargaCapitulos, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDescargar)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSerie, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                    .addComponent(scrollpane))
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSerie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                        .addGap(28, 28, 28))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Cuando ponemos el foco sobre la caja de texto se selecciona todo (facilidad de uso)
    private void txtSerieFocusGained(java.awt.event.FocusEvent evt) {
       txtSerie.selectAll();
    }

    //Capturamos el evento de pulsar tecla sobre la caja de texto de "buscar serie"
    private void txtSerieKeyPressed(java.awt.event.KeyEvent evt) {
        
        //Obtenemos la tecla que se ha pulsado
        int key = evt.getKeyCode();
        
        //Si es intro ejecutamos acciones si no, nada.
        if (key == KeyEvent.VK_ENTER) {
            
            //
            cbmTemporadas.removeAllItems();
            cbmTemporadas.addItem("Escoge temporada");
            
            ImageIcon iconSerie;
            int numTemporada = 1;
            
            try {
                //Instanciamos la serie introducida
                serie = new Serie(String.valueOf("http://www.seriesyonkis.com/serie/" + txtSerie.getText().replaceAll(" ", "-")));
                 System.out.println(serie.obtenerMp4DelCapitulo("93mfgkfa1k2j", "Falling_Skies_1x05.avi"));
                iconSerie = escalarImagen(ImageIO.read(serie.getUrlIconSerie()), 0.75);
                lblIconSerie.setIcon(iconSerie);
                lblTituloSerie.setText(serie.getTituloSerie());               
                txtDescripcion.setText(serie.getDescripcionSerie());                
                
                for(int i = 0 ; i < serie.ObtenerNumTemporadas() ; i++) {
                    cbmTemporadas.addItem("Temporada " + numTemporada);
                    numTemporada += 1;                            
                }
                
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(new JOptionPane(), "Nombre de serie invalido. \n Prueba a poner el nombre completo con sus espacios. \n Ej: en vez 'BigBang' poner 'the big bang theory'.");
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(new JOptionPane(), "Error: " + ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(MassDown.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(new JOptionPane(), "Opcion no disponible aun :(");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbmTemporadasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmTemporadasItemStateChanged
        
     
    }//GEN-LAST:event_cbmTemporadasItemStateChanged

    private void cbmTemporadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbmTemporadasMouseClicked
        
    }//GEN-LAST:event_cbmTemporadasMouseClicked

    private void cbmTemporadasVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_cbmTemporadasVetoableChange
        
    }//GEN-LAST:event_cbmTemporadasVetoableChange


    private void btnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarActionPerformed
        agregarDescarga agregarDescarga = new agregarDescarga(lstCapitulos.getSelectedIndex());
        agregarDescarga.start();
    }//GEN-LAST:event_btnDescargarActionPerformed

    private void cbmTemporadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmTemporadasActionPerformed
        if(cbmTemporadas.getSelectedIndex() > 0) {
            capitulosAMostrar.clear();
            
            
            capitulosAMostrar = serie.getListaNombreCapitulos(cbmTemporadas.getSelectedIndex() - 1);
            serie.iniciarThreadListarCapitulos(cbmTemporadas.getSelectedIndex() - 1);
            serie.setIntervaloDeCargaPBar(100 / capitulosAMostrar.size());
            iniciarThreadCargaCapitulos();
        
        }
    }//GEN-LAST:event_cbmTemporadasActionPerformed

    private void pbarCargaCapitulosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pbarCargaCapitulosStateChanged
        if(pbarCargaCapitulos.getValue() == 100) {
            cbmTemporadas.setEnabled(true);
        } else {
          cbmTemporadas.setEnabled(false);  
        }
            
    }//GEN-LAST:event_pbarCargaCapitulosStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(new JOptionPane(), "Opcion no disponible aun :(");
    }//GEN-LAST:event_jButton1ActionPerformed



    
    
    public static void main(String[] args) throws Exception {
        final MassDown gui = new MassDown(); 
        
        
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MassDown.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //gui.iniciarThreadCargaCapitulos();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.setVisible(true);
                
            }
        });
                
    }
    
public void iniciarThreadCargaCapitulos() {
    new comprobandoCargaCapitulos().start();
}
class comprobandoCargaCapitulos extends Thread {
    @Override
    public void run() {

       
       System.out.println("---");
       pbarCargaCapitulos.setValue(0);
       serie.setProgresoActualDeCargaPBar(0);
       DefaultListModel listaCapitulo = new DefaultListModel();
       listaCapitulo.clear();

        while(!serie.isFinCargaCapitulos()) {    
           try {
               sleep(1000);
               pbarCargaCapitulos.setValue(serie.getProgresoActualDeCargaPBar()); 
               //lblCargaCaps.setText("(Cargando " + miMp4.getProgresoActualDeCargaPBar() + "%)");
               int capituloActual = serie.getProgresoActualDeCargaPBar() / serie.getIntervaloDeCargaPBar();
               lblCargaCaps.setText("(Cargado " + capituloActual + " de " + capitulosAMostrar.size() + " caps)");
               
               if(pbarCargaCapitulos.getValue() == 100) {
                   lstCapitulos.setModel(listaCapitulo);
                  // lstCapitulos.setSelectionInterval(0, capitulosAMostrar.size());
                    for(int i = 0 ; i <= capitulosAMostrar.size() - 1 ; i++ ) {
                         listaCapitulo.addElement(capitulosAMostrar.get(i));
                    }
                    pbarCargaCapitulos.setValue(100);
                    cbmTemporadas.setEnabled(true);
                    lblCargaCaps.setText("(Cargado " + capitulosAMostrar.size() + " de " + capitulosAMostrar.size() + " caps)");
                    serie.setProgresoActualDeCargaPBar(100);    
                    serie.getInfoCapsStreamCloud().values();
                }
           }  catch (InterruptedException ex) {
               
                System.out.println("error");
            } 
           
            
            
        }
        
       
    }
}

class agregarDescarga extends Thread {
    
    private int time;
    private JProgressBar pbDescarga;
    private JLabel lblTiempo;
    private int capituloSeleccionado;
    private JLabel lblTituloCap;
    private JLabel lblEstatus ;
    private JButton btnPararDescarga;
    private Download download;
    private JLabel lblVelocidadDescarga;
    
    
    public agregarDescarga(int capituloSeleccionado) {
        this.capituloSeleccionado = capituloSeleccionado;
    }
    
    public void run() {
        File rutaDeDescarga = new File(serie.getTituloSerie());
        
        if(!rutaDeDescarga.exists()) {            
            rutaDeDescarga.mkdirs();
        }
        
        Object[] idsVideos = serie.getInfoCapsStreamCloud().keySet().toArray();
        Object[] nombresVideos = serie.getInfoCapsStreamCloud().values().toArray();
        try {
            String urlADescargar = serie.obtenerMp4DelCapitulo(idsVideos[capituloSeleccionado].toString(), nombresVideos[capituloSeleccionado].toString());
       
            
        download = new Download(rutaDeDescarga);
        pbDescarga = new JProgressBar();
        lblTiempo = new JLabel();
        lblTituloCap = new JLabel(lstCapitulos.getSelectedValue().toString());
        lblEstatus = new JLabel();
        lblVelocidadDescarga = new JLabel();
        btnPararDescarga = new JButton("Parar");
        
        
        contenedorDescargas.add(lblTituloCap);
        contenedorDescargas.add(lblEstatus);
        contenedorDescargas.add(lblTiempo); 
        contenedorDescargas.add(lblVelocidadDescarga);
        //contenedorDescargas.add(btnPararDescarga);
        contenedorDescargas.add(pbDescarga);
        
        
        
        

            download.loadURL(urlADescargar);
            URLConnection con = download.openConexion();
            double size = (double) ((double) (con.getContentLength() / 1000) / 1000);
            pbDescarga.setMaximum(con.getContentLength());                                                  
            
            
            time = 0;
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int minutos = (time > 60) ? time / 60 : 0 ;
                    int segundo =  time - minutos * 60;
                    int horas = 0;
                    time++;

                    lblTiempo.setText( minutos + " min." + segundo + " seg.");
                    //System.out.println("Time " + time + " - " + download.isTerminate());
                }
            });
            
            download.descarga(pbDescarga, lblEstatus, timer, lstCapitulos.getSelectedValue().toString(), lblVelocidadDescarga);
        } catch (IOException ex) {
            new agregarDescarga(capituloSeleccionado);
            Logger.getLogger(MassDown.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MassDown.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JOptionPane(), "Error en al agregar descarga, vuelve a intentarlo. \n Si el error persiste significa que no es compatible con MassDown :(");
            
        }
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescargar;
    private javax.swing.JComboBox cbmTemporadas;
    private javax.swing.JToolBar contenedorDescargas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblCargaCaps;
    private javax.swing.JLabel lblDeTituloCapitulos;
    private javax.swing.JLabel lblIconSerie;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloSerie;
    private javax.swing.JList lstCapitulos;
    private javax.swing.JProgressBar pbarCargaCapitulos;
    private javax.swing.JScrollPane scrollpane;
    private javax.swing.JEditorPane txtDescripcion;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
