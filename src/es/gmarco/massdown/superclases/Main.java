/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.gmarco.massdown.superclases;

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

import es.gmarco.massdown.clases_escalables.Download;
import es.gmarco.massdown.clases_escalables.MetodosUtiles;
import es.gmarco.massdown.forms.AjustesForm;
import es.gmarco.massdown.forms.CatalogoForm;

public class Main extends JFrame {

    private Serie serie;
    
    
    public Main() {
        initComponents();
       
    }

    //Codigo generado por el diseñador de interfaces
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        separadorGeneral = new javax.swing.JSeparator();
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
        setIconImage(new ImageIcon(getClass().getResource("/es/gmarco/massdown/recursos/icon.png")).getImage());
        setMinimumSize(new java.awt.Dimension(800, 480));

        jPanel3.setBackground(new java.awt.Color(30, 30, 30));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Dotum", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("MassDown");

        separadorGeneral.setBackground(new java.awt.Color(239, 64, 54));
        separadorGeneral.setForeground(new java.awt.Color(239, 64, 54));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Ajustes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Catálogo de series");
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
                    .addComponent(separadorGeneral)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 605, Short.MAX_VALUE)
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
                .addComponent(separadorGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconSerie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblTituloSerie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(lblIconSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
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
        jPanel5.setPreferredSize(new java.awt.Dimension(290, 360));

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
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Español" }));

        btnDescargar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDescargar.setText("Descargar");
        btnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarActionPerformed(evt);
            }
        });

        cbmTemporadas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbmTemporadas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cbmTemporadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmTemporadasActionPerformed(evt);
            }
        });

        lstCapitulos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lstCapitulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstCapitulos.setToolTipText("Pulsa Ctrl + Click para sleccionar más de un capitulo");
        jScrollPane1.setViewportView(lstCapitulos);

        lblDeTituloCapitulos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDeTituloCapitulos.setForeground(new java.awt.Color(255, 255, 255));
        lblDeTituloCapitulos.setText("Capítulos");

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
                    .addComponent(pbarCargaCapitulos, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
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
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpane)
                    .addComponent(txtSerie))
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
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
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
            
            lblTituloSerie.setText("Cargando");
            lblIconSerie.setIcon(new ImageIcon());
            txtDescripcion.setText("");
            cbmTemporadas.removeAllItems();
            
            final Thread animacionDeCargando = MetodosUtiles.AnimacionDeCargando(lblTituloSerie, getSize());
            animacionDeCargando.start();
            
            new Thread() {
            @Override
            public void run() {                
            
            
            cbmTemporadas.addItem("Escoge temporada");
            
            ImageIcon iconSerie;
            int numTemporada = 1;
            
            try {
                
                serie = new Serie(String.valueOf("http://www.seriesyonkis.com/serie/" + txtSerie.getText().replaceAll(" ", "-")));
                
                iconSerie = escalarImagen(ImageIO.read(serie.getUrlIconSerie()), 0.75);
                lblIconSerie.setIcon(iconSerie);
                txtDescripcion.setText(serie.getDescripcionSerie());                
                lblTituloSerie.setText(serie.getTituloSerie());   
                
                animacionDeCargando.interrupt();
                
                for(int i = 0 ; i < serie.getNumeroDeTemporadas() ; i++) {
                    cbmTemporadas.addItem("Temporada " + numTemporada);
                    numTemporada += 1;                            
                }
            }

            catch (FileNotFoundException ex) {
                animacionDeCargando.interrupt();
                lblTituloSerie.setText("-");
                JOptionPane.showMessageDialog(new JOptionPane(), "Nombre de serie invalido. \n Prueba a poner el nombre completo con sus espacios. \n Ej: en vez 'BigBang' poner 'the big bang theory'.");                
                
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(new JOptionPane(), "Error: " + ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }.start();
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
        CatalogoForm catalogoForm = new CatalogoForm();
        catalogoForm.setLocation(getLocation().x + 70, getLocation().y + 70);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarActionPerformed
        agregarDescarga agregarDescarga = new agregarDescarga(lstCapitulos.getSelectedIndex());
        agregarDescarga.start();
    }//GEN-LAST:event_btnDescargarActionPerformed

    private void cbmTemporadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmTemporadasActionPerformed
        if(cbmTemporadas.getSelectedIndex() > 0) {                                                                      
            
            serie.iniciarThreadListarCapitulos(cbmTemporadas.getSelectedIndex() - 1);
            serie.setIntervaloDeCargaPBar(100 / serie.getCapitulos().size());
            new ComprobandoCargaCapitulos().start();
        
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
        AjustesForm ajustesForm = new AjustesForm();
        ajustesForm.setLocation(getLocation().x +70, getLocation().y + 70);
    }//GEN-LAST:event_jButton1ActionPerformed
         
class ComprobandoCargaCapitulos extends Thread {

    public ComprobandoCargaCapitulos() {
        
    }        
    
    @Override
    public void run() {
       
       System.out.println("---");
       
       pbarCargaCapitulos.setValue(0);
       serie.setProgresoActualDeCargaPBar(0);
       
       DefaultListModel listaCapitulo = new DefaultListModel();
       listaCapitulo.clear();
       
       ArrayList<Capitulo> capitulosAMostrar = serie.getCapitulos();
       int numeroDeCapitulosAMostrar = capitulosAMostrar.size();
       
        System.out.println(numeroDeCapitulosAMostrar);
        lstCapitulos.setModel(listaCapitulo);
        final Thread animacionDeCargando = MetodosUtiles.AnimacionDeCargando(listaCapitulo, getSize());
        animacionDeCargando.start();
       
        
        while(!serie.isFinCargaCapitulos()) {    
           try {
               sleep(1000);
               pbarCargaCapitulos.setValue(serie.getProgresoActualDeCargaPBar()); 
               //lblCargaCaps.setText("(Cargando " + miMp4.getProgresoActualDeCargaPBar() + "%)");
               int capituloActual = serie.getProgresoActualDeCargaPBar() / serie.getIntervaloDeCargaPBar();
               lblCargaCaps.setText("(Cargado " + capituloActual + " de " + numeroDeCapitulosAMostrar + " caps)");
                                                            
               
               if(pbarCargaCapitulos.getValue() == 100) {
                   
                    animacionDeCargando.interrupt();
                    listaCapitulo.clear();
                    
                    for(int i = 0 ; i <= numeroDeCapitulosAMostrar - 1 ; i++ ) {
                        listaCapitulo.addElement(capitulosAMostrar.get(i).getTitulo());
                    }
                    pbarCargaCapitulos.setValue(100);
                    cbmTemporadas.setEnabled(true);
                    lblCargaCaps.setText("(Cargado " + numeroDeCapitulosAMostrar + " de " + numeroDeCapitulosAMostrar + " caps)");
                    serie.setProgresoActualDeCargaPBar(100);  
                    
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
        
        Capitulo capituloADescargar = serie.getCapitulos().get(capituloSeleccionado);
        
        try {
        
        String urlADescargar = serie.obtenerMp4DelCapitulo(capituloADescargar.getIdStreamCloud(), capituloADescargar.getFicheroStreamCloud());
       
            
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
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JOptionPane(), "Error en al agregar descarga, vuelve a intentarlo. \n Si el error persiste significa que no es compatible con MassDown :(");
            
        }
    }
}

    public static void main(String[] args) throws Exception {
        final Main gui = new Main(); 
        
                
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.setVisible(true);
                
            }
        });
                
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
    private javax.swing.JSeparator separadorGeneral;
    private javax.swing.JEditorPane txtDescripcion;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
