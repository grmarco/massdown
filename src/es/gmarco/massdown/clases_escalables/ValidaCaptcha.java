/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.gmarco.massdown.clases_escalables;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;


import javax.swing.*;

/**
 *
 * @author Guillermo
 */
public class ValidaCaptcha extends JFrame {
    
    private JLabel lblImagenCaptcha;
    private JButton btnValidarCaptcha;
    private JTextField txtTextoDeLaImagen;
    private String urlCaptcha, hashCaptcha, urlCapitulo, urlImagenCaptcha, codigoStream;    
    
    public ValidaCaptcha(String urlCaptcha, String hashCaptcha, String urlCapitulo, String urlImagenCaptcha) throws MalformedURLException, IOException {
        
        this.urlCaptcha = urlCaptcha;
        this.hashCaptcha = hashCaptcha;
        this.urlCapitulo = urlCapitulo;
        this.urlImagenCaptcha = urlImagenCaptcha;               
        
        ImageIcon imagenAMostrarLbl = new ImageIcon(ImageIO.read(new URL(urlImagenCaptcha)));
        
        System.out.println(urlImagenCaptcha);
        
        setLayout(new FlowLayout());
        add(lblImagenCaptcha = new JLabel());
        
        lblImagenCaptcha.setIcon(imagenAMostrarLbl);
        add(txtTextoDeLaImagen = new JTextField(25));
        add(btnValidarCaptcha = new JButton("OK"));
        btnValidarCaptcha.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    EscuchaBoton(evt);
                } catch (Exception ex) {
                    Logger.getLogger(ValidaCaptcha.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        setSize(360, 130);
        setIconImage(new ImageIcon(getClass().getResource("/es/gmarco/massdown/recursos/icon.png")).getImage());
        setResizable(false);
        setType(Type.POPUP);
        setAlwaysOnTop(true);
        setFocusCycleRoot(true);
        setTitle("Introduce el captcha para continuar");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
   
        public void EscuchaBoton(ActionEvent e) throws Exception {
           EnviarCaptcha(urlCaptcha, hashCaptcha, txtTextoDeLaImagen.getText(), urlCapitulo);
           setVisible(false);
        }        
    
        public void EnviarCaptcha(String url, String id, String textoDeLaImagen, String urlCapitulo) {
                
                String codigoFuente;
                
                Map<String, String> datosForm = new HashMap<>();
                datosForm.put("recaptcha_challenge_field", id);
                datosForm.put("recaptcha_response_field", textoDeLaImagen);
        try {
            codigoFuente = MetodosUtiles.doSubmit(url, datosForm);
        
                int primerNodo = codigoFuente.indexOf("<textarea rows=\"5\" cols=\"100\"");
                int segundoNodo = codigoFuente.indexOf("</textarea>");

                String hashEnviar = codigoFuente.substring(primerNodo + 30, segundoNodo);
                System.out.println(hashEnviar);

                Map<String, String> OtrosDatosForm = new HashMap<>();
                OtrosDatosForm.put("recaptcha_challenge_field", hashEnviar);
                OtrosDatosForm.put("recaptcha_response_field", "manual_challenge");
                MetodosUtiles.doSubmit("http://www.seriescoco.com" + urlCapitulo, OtrosDatosForm);
                this.codigoStream = "http://www.seriescoco.com" + urlCapitulo;
                
        } catch (Exception ex) {
            JOptionPane frame = new JOptionPane();
            JOptionPane.showMessageDialog(frame, "Error en el captcha, vuelve a intentarlo.");
            codigoStream = "relleno para quitar el sleep(1)";
        }
               
       }

    public String getCodigoStream() {
        return codigoStream;
    }

    public void setCodigoStream(String codigoStream) {
        this.codigoStream = codigoStream;
    }
        
    }
  