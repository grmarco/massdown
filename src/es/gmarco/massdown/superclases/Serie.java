/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.gmarco.massdown.superclases;

import es.gmarco.massdown.clases_escalables.ValidaCaptcha;
import es.gmarco.massdown.clases_escalables.MetodosUtiles;
import java.io.*;
import static java.lang.Thread.yield;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guillermo
 */
public class Serie {
    
    private String urlSerie;
    //Codigo fuente de la pagina donde se encunetra la serie
    private String codigoFuente;
    //Las temporadas se encuentra entre <table></table> cada tabla se almacena aquí:
    private ArrayList<String> codigosFuentesTemporadas = new ArrayList<>();
    private String codigoFuenteTemporada;
    
    private ArrayList<Capitulo> capitulos = new ArrayList<>();
    
    private int intervaloDeCargaPBar = 0;
    private int progresoActualDeCargaPBar = 0;
    
    private int primerNodoTemporadas = 0;
    private int segundoNodoTemporadas = 0;
    private int primerNodoCapitulos;
    private int segundoNodoCapitulos = 0;
   
    private boolean isRunningThreadIndexarDatosCapitulos = false;
    private boolean finCargaCapitulos = true;
    
    private boolean errorIndexandoDatosCapitulos;
    
    Serie(String urlSerie) throws IOException {
        this.urlSerie = urlSerie;
        this.codigoFuente = MetodosUtiles.ObtenerCodigoFuente(this.urlSerie)[1];
    }
                                                   
    public String ObtenerCadenaEntreTags(String codigoAAnalizar, String primerTag, String segundoTag, int parametroPrimerNodo, int parametroSegundoNodo, int ajustePrimerElemento, int ajusteSegundoElemento, String capitulosOTemporadas) {

        int primerNodo = (parametroPrimerNodo == 0) ? 0 : parametroPrimerNodo;
        int segundoNodo = (parametroSegundoNodo == 0) ? 0 : parametroSegundoNodo;
        String elementoEntreTags;

        if(parametroSegundoNodo > 0) {
            primerNodo = codigoAAnalizar.indexOf(primerTag, segundoNodo);
        } else {
            primerNodo = codigoAAnalizar.indexOf(primerTag);
        }
            segundoNodo = codigoAAnalizar.indexOf(segundoTag, primerNodo);


    switch (capitulosOTemporadas) {
        case "capitulos":
            this.segundoNodoCapitulos = segundoNodo;
            this.primerNodoCapitulos = primerNodo;
            this.segundoNodoCapitulos = (segundoNodo > 0) ? segundoNodo : 0;
            break;
        case "temporadas":
            this.segundoNodoTemporadas = segundoNodo;
            this.primerNodoTemporadas = primerNodo;
            this.segundoNodoTemporadas = (segundoNodo > 0) ? segundoNodo : 0;
            break;
    }
        elementoEntreTags = codigoAAnalizar.substring(primerNodo + ajustePrimerElemento, segundoNodo - ajusteSegundoElemento);
        return elementoEntreTags;            
    }

    public void iniciarThreadListarCapitulos(int numTemporada) {
        IndexarDatosCapitulos obtenedorListaCapitulos = new IndexarDatosCapitulos(numTemporada);
        
        if(isRunningThreadIndexarDatosCapitulos == true) {
            IndexarDatosCapitulos.interrupted();
        }
        
        obtenedorListaCapitulos.start();
        isRunningThreadIndexarDatosCapitulos = true;
    }
    
    class IndexarDatosCapitulos extends Thread {

        private int numeroTemporada;

        public IndexarDatosCapitulos(int numeroTemporada) {
            this.numeroTemporada = numeroTemporada;
            getListaNombreCapitulos(numeroTemporada);
        }                   
        
        public ArrayList getListaNombreCapitulos(int numeroTemporada) {
        
            codigoFuenteTemporada = codigosFuentesTemporadas.get(numeroTemporada);

            capitulos.clear();

            int numCapitulo = 0;

            segundoNodoCapitulos = 1;

            while(segundoNodoCapitulos > 0) {
                try {

                String nombreCapitulo;        
                nombreCapitulo = ObtenerCadenaEntreTags(codigoFuenteTemporada, "class=\"episode-title\"", "</td>  <td>", 0, segundoNodoCapitulos, 9, 0, "capitulos");
                nombreCapitulo = ObtenerCadenaEntreTags(nombreCapitulo, "<strong> " , "</a>", 0, 0, 9, 0, "");
                nombreCapitulo = nombreCapitulo.replaceAll("</strong>", "");

                Capitulo nuevoCapitulo = new Capitulo(nombreCapitulo);

                //Este bucle se acaba cuando encuentra un capitulo con el mismo nombre en el ArrayList
            if(numCapitulo > 1) {
                    if(capitulos.get(0).getTitulo().equals(nuevoCapitulo.getTitulo())) {
                        break; 
                    }

                }            

                capitulos.add(nuevoCapitulo);

                numCapitulo++;

                } catch(IndexOutOfBoundsException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }                         
            return capitulos;        
        }
        
        
        @Override
        public void run() {
            int numCapitulo = 0;
            while(isRunningThreadIndexarDatosCapitulos) {                            
                if(!errorIndexandoDatosCapitulos) {
                    codigoFuenteTemporada = codigosFuentesTemporadas.get(numeroTemporada );
                    finCargaCapitulos = false;
                    System.out.println(finCargaCapitulos);
                    segundoNodoCapitulos = 0;
                    primerNodoCapitulos = 0;
                    numCapitulo = 0;
                    
                }
                System.out.println(codigoFuenteTemporada);
                do {
                    String capitulo;
                    String currentUrl;        
                    String codigoFuenteCap; 
                    String codigoDeHostigsStreamCloud;
                    String urlCapituloPaso1;
                    String urlCapituloPaso2;
                    String codigoStream;
                    String urlCaptcha;
                    String codigoCaptcha;
                    String urlImagenCaptcha;
                    String hashCaptcha;
                    ValidaCaptcha validador;
                    String sacarEnlaceStreamcloudAdbooth;
                    String urlDeStreamCloud;
                    
                    String idVideo;
                    String nombreVideo;

                    int nodoAnterior;

                    try {
                        nodoAnterior = segundoNodoCapitulos;
                        capitulo = ObtenerCadenaEntreTags(codigoFuenteTemporada, "<a href=\"", ">", 0, segundoNodoCapitulos, 9, 1, "capitulos");
                        codigoFuenteCap = MetodosUtiles.ObtenerCodigoFuente(capitulo)[1];


                    try {  

                        codigoDeHostigsStreamCloud = ObtenerCadenaEntreTags(codigoFuenteCap, "\"> <a href=\"", "en streamcloud\" target=\"_blank\"", 0, codigoFuenteCap.indexOf("<span class=\"server streamcloud\"></span>"), 0, 0, "cspitulos");
                        urlCapituloPaso1 = ObtenerCadenaEntreTags(codigoDeHostigsStreamCloud, "\"> <href=\"", "\" title=\"", 0, 0, 13, 0, "");
                        System.out.println(urlCapituloPaso1);
                        urlCapituloPaso2 = ObtenerCadenaEntreTags(MetodosUtiles.ObtenerCodigoFuente("http://www.seriescoco.com" + urlCapituloPaso1)[1], "</a> </td> <td> <a href=\"", "target=\"_blank\"> <span class=\"server streamcloud\">", 0, 0, 25, 2, "");
                        System.out.println(urlCapituloPaso2);
                        codigoStream = MetodosUtiles.ObtenerCodigoFuente("http://www.seriescoco.com" + urlCapituloPaso2)[1];
                        currentUrl = MetodosUtiles.ObtenerCodigoFuente("http://www.seriescoco.com" + urlCapituloPaso2)[0];

                    } catch(StringIndexOutOfBoundsException ex) {
                        errorIndexandoDatosCapitulos = true;
                        capitulos.remove(numCapitulo);
                        JOptionPane.showMessageDialog(new JOptionPane(), "El capitulo " +  (progresoActualDeCargaPBar / intervaloDeCargaPBar) + " de esta temporada no es compatible." );
                        break;
                    }

                        try {
                            if (codigoStream.indexOf("SeriesCoco") > 0) {                        
                                urlCaptcha = ObtenerCadenaEntreTags(codigoStream, "<noscript>", "\" height=\"300\" width=\"500\" frameborder=\"0\"></iframe>", 0, 0, 28, 0, "");
                                codigoCaptcha = MetodosUtiles.ObtenerCodigoFuente(urlCaptcha)[1];                        
                                urlImagenCaptcha = "http://www.google.com/recaptcha/api/" + ObtenerCadenaEntreTags(codigoCaptcha, "<img width=\"300\" height=\"57\" alt=\"\" src=\"", "></center>", 0, 0, 41, 1,"");
                                hashCaptcha = ObtenerCadenaEntreTags(codigoCaptcha, "recaptcha_challenge_field\" value=\"", "<center><img width=\"300", 0, 0, 34, 2,"");
                                System.out.println(hashCaptcha);
                                validador = new ValidaCaptcha(urlCaptcha, hashCaptcha, urlCapituloPaso2, urlImagenCaptcha);

                                while(validador.getCodigoStream() == null) {
                                    Thread.sleep(1);
                                }

                                codigoStream = MetodosUtiles.ObtenerCodigoFuente(validador.getCodigoStream())[1];
                                currentUrl = MetodosUtiles.ObtenerCodigoFuente(validador.getCodigoStream())[0];
                                errorIndexandoDatosCapitulos = false;
                            } 

                        } catch (IOException | InterruptedException e) {
                            //progresoActualDeCargaPBar = 0;
                            segundoNodoCapitulos = nodoAnterior;
                            errorIndexandoDatosCapitulos = true;
                            break;

                        }

                    if (currentUrl.indexOf("adbooth") > 0) {
                        sacarEnlaceStreamcloudAdbooth = String.valueOf(currentUrl) + "/FINURL";
                        System.out.println(currentUrl);
                        urlDeStreamCloud = ObtenerCadenaEntreTags(sacarEnlaceStreamcloudAdbooth, "&url=", "/FINURL", 0, 0, 5, 0, "");
                        System.out.println(urlDeStreamCloud);
                        codigoStream = MetodosUtiles.ObtenerCodigoFuente(urlDeStreamCloud)[1];
                    }

                    idVideo = ObtenerCadenaEntreTags(codigoStream, "<input type=\"hidden\" name=\"id\" value=\"", "<input type=\"hidden\" name=\"fname\"", 0, 0, 38, 8, "");
                    System.out.println(idVideo);

                    nombreVideo = ObtenerCadenaEntreTags(codigoStream, "name=\"fname\" value=\"", "<input type=\"hidden\" name=\"referer\"", 0, 0, 20, 8, "");
                    System.out.println(nombreVideo);

                    
                    capitulos.get(numCapitulo).setFicheroStreamCloud(nombreVideo);
                    capitulos.get(numCapitulo).setIdStreamCloud(idVideo);
                    
                    System.out.println(numCapitulo);
                    
                    numCapitulo++;
                    
                    progresoActualDeCargaPBar = intervaloDeCargaPBar + progresoActualDeCargaPBar;

                    System.out.println("---------------------------");

                    } catch(IndexOutOfBoundsException | IOException ex) {

                        finCargaCapitulos = true;
                        progresoActualDeCargaPBar = 100;
                        System.out.println(finCargaCapitulos);
                        isRunningThreadIndexarDatosCapitulos = false;
                        yield();
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        break;

                    }
                } while(segundoNodoCapitulos > 0);

            }
        }
    }
    
    public String obtenerMp4DelCapitulo(String idVideo, String nombreVideo) throws Exception {
            
        String UrlVideo = "http://streamcloud.eu/" + idVideo;
        Map<String, String> datosForm = new HashMap<>();
        String codigoDepuesDeSubmit;
        String UrlDelMp4;

        datosForm.put("op", "download1");
        datosForm.put("url_login", "");
        datosForm.put("id", idVideo);
        datosForm.put("fname", nombreVideo);
        datosForm.put("refer", "");
        datosForm.put("hash", "");
        datosForm.put("imhuman", "Watch video now");

        codigoDepuesDeSubmit = MetodosUtiles.doSubmit(UrlVideo, datosForm);

        System.out.println(codigoDepuesDeSubmit);

        UrlDelMp4 = ObtenerCadenaEntreTags(codigoDepuesDeSubmit, "file: \"", "\",", 0, 0, 7, 0, "");

        return UrlDelMp4;            
            
    }
    
    public int getNumeroDeTemporadas() {                       
        
        int numTemporadas = 0;
        this.segundoNodoTemporadas = 0;
        codigosFuentesTemporadas.clear();                

        do {
            try {
                
                this.codigoFuenteTemporada = ObtenerCadenaEntreTags(this.codigoFuente, "<h3 class=\"season\"", "</tbody>", 0, this.segundoNodoTemporadas, 0, 0, "temporadas");
                if(this.codigoFuenteTemporada.indexOf("class=\"season_title\">Extras") < 0) {
                    this.codigosFuentesTemporadas.add(codigoFuenteTemporada);
                    numTemporadas++;
                }
                         
            } catch(IndexOutOfBoundsException ex) {
               break;
            }

        } while(this.segundoNodoTemporadas > 0);

        return numTemporadas;        
    } 
    
    public String getTituloSerie() {        
        return ObtenerCadenaEntreTags(codigoFuente, "<h1 class=\"underline\" title=\"", "\">", 1, 1, 29, 0, "");    
    }
       
    public URL getUrlIconSerie() throws MalformedURLException {
        URL urlDelIconSerie = new URL (ObtenerCadenaEntreTags(codigoFuente, "<img src=\"http://s.staticyonkis.com/img/series/", "\" alt=\"", 1, 1, 10, 0, ""));
        return urlDelIconSerie;        
    }
    
    public String getDescripcionSerie() {
        return ObtenerCadenaEntreTags(codigoFuente, "description\">  <p>", "a href=", 1, 1, 18, 1, "");        
    }
           
    public String getUrlSerie() {
        return urlSerie;
    }

    public String getCodigoFuente() {
        return codigoFuente;
    }

    public boolean isFinCargaCapitulos() {
        return finCargaCapitulos;
    }

    public int getIntervaloDeCargaPBar() {
        return intervaloDeCargaPBar;
    }

    public void setIntervaloDeCargaPBar(int intervaloDeCargaPBar) {
        this.intervaloDeCargaPBar = intervaloDeCargaPBar;
    }

    public int getProgresoActualDeCargaPBar() {
        return progresoActualDeCargaPBar;
    }

    public void setProgresoActualDeCargaPBar(int progresoActualDeCargaPBar) {
        this.progresoActualDeCargaPBar = progresoActualDeCargaPBar;
    }

    public boolean isIsRunningThreadIndexarDatosCapitulos() {
        return isRunningThreadIndexarDatosCapitulos;
    }

    public void setIsRunningThreadIndexarDatosCapitulos(boolean isRunningThreadIndexarDatosCapitulos) {
        this.isRunningThreadIndexarDatosCapitulos = isRunningThreadIndexarDatosCapitulos;
    }

    public ArrayList<Capitulo> getCapitulos() {
        return capitulos;
    }

    
    
}
