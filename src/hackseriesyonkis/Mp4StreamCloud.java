/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hackseriesyonkis;

import java.io.*;
import static java.lang.Thread.yield;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Guillermo
 */
public class Mp4StreamCloud {

    private String codigoFuente;
    private int primerNodoTemporadas = 0;
    private int segundoNodoTemporadas = 0;
    private int primerNodoCapitulos = 0;
    private int segundoNodoCapitulos = 0;
    private URL currentUrl;        
    private String codigoFuenteTemporada;
    private ArrayList<String> codigosFuentesTemporadas = new ArrayList<>();
    private String urlSerie;
    private Hashtable<String, String> infoCapsStreamCloud = new Hashtable<>();
    private boolean runningThread = false;
    private boolean finCargaCapitulos = true;
    private int intervaloDeCargaPBar = 0;
    private int progresoActualDeCargaPBar = 0;
    private boolean error ;
    
    Mp4StreamCloud() {
    
    }
    

    public void setUrlSerie(String urlSerie) {
        this.urlSerie = urlSerie;
    }
    
    
    public static String doSubmit(String url, Map<String, String> data) throws Exception {
        
        URL siteUrl = new URL(url);
	HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
	
        conn.setRequestMethod("POST");
	conn.setDoOutput(true);
	conn.setDoInput(true);
		
	DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		
	Set keys = data.keySet();
	Iterator keyIter = keys.iterator();
	String content = "";
        
	for(int i=0; keyIter.hasNext(); i++) {            
            Object key = keyIter.next();
            if(i!=0) {
                content += "&";
            }
            content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
	}
        
	out.writeBytes(content);
	out.flush();
	out.close();
        
        StringBuilder Fuente = new StringBuilder();
                
        if(conn.getResponseCode() != 405) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line;
            
            while((line = in.readLine())!=null) {
                Fuente.append(line);
            }
            in.close();
            
         } else {
            
            Mp4StreamCloud m = new Mp4StreamCloud();                    
            Fuente.append(m.ObtenerCodigoFuente(String.valueOf(conn.getURL())));
         
         }
        
        return Fuente.toString();
        
    }

  public String obtenerMp4(String idVideo, String nombreVideo) throws Exception {
            
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
           
            codigoDepuesDeSubmit = doSubmit(UrlVideo, datosForm);
                        
            System.out.println(codigoDepuesDeSubmit);

            UrlDelMp4 = ObtenerCadenaEntreTags(codigoDepuesDeSubmit, "file: \"", "\",", 0, 0, 7, 0, "");
            
            return UrlDelMp4;            
            
        }
        
        public int ObtenerNumTemporadas() {                       
            int numTemporadas = 0;
            //codigosFuentesTemporadas.clear();
            codigosFuentesTemporadas.clear();
            this.segundoNodoTemporadas = 0;
            
            do 
            {
                try 
                {
                    this.codigoFuenteTemporada = ObtenerCadenaEntreTags(this.codigoFuente, "<h3 class=\"season\"", "</tbody>", 0, this.segundoNodoTemporadas, 0, 0, "temporadas");
                    if(this.codigoFuenteTemporada.indexOf("class=\"season_title\">Extras") < 0) {
                        this.codigosFuentesTemporadas.add(codigoFuenteTemporada);
                        numTemporadas++;
                    }
                } 
                catch(IndexOutOfBoundsException ex) 
                {
                   break;
                    
                }
                               

                
                } while(this.segundoNodoTemporadas > 0);

        return numTemporadas;        
        }
        
        
        
        
        
    public String ObtenerCodigoFuente(String url) throws MalformedURLException, IOException {
        URL siteUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        this.currentUrl = conn.getURL();
        StringBuilder Fuente = new StringBuilder();
        while((line = in.readLine())!=null) {
            Fuente.append(line);
        }
        in.close();
        return Fuente.toString();
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
    
class ObtenedorListaCapitulos extends Thread {

    private int numeroTemporada;

    public ObtenedorListaCapitulos(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }                   

    @Override
    public void run() {
        while(runningThread) {                            
            if(!error) {
                codigoFuenteTemporada = codigosFuentesTemporadas.get(numeroTemporada );
                finCargaCapitulos = false;
                System.out.println(finCargaCapitulos);
                segundoNodoCapitulos = 0;
                primerNodoCapitulos = 0;
            }
            System.out.println(codigoFuenteTemporada);
                do {
                String capitulo;
                String codigoFuenteCap; 
                String codigoDeHostigsStreamCloud;
                String urlCapituloPaso1;
                String urlCapituloPaso2 = "";
                String codigoStream = "";
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
                    codigoFuenteCap = ObtenerCodigoFuente(capitulo);
                    
                   
                try {  
                    
                    codigoDeHostigsStreamCloud = ObtenerCadenaEntreTags(codigoFuenteCap, "\"> <a href=\"", "en streamcloud\" target=\"_blank\"", 0, codigoFuenteCap.indexOf("<span class=\"server streamcloud\"></span>"), 0, 0, "cspitulos");
                    urlCapituloPaso1 = ObtenerCadenaEntreTags(codigoDeHostigsStreamCloud, "\"> <href=\"", "\" title=\"", 0, 0, 13, 0, "");
                    System.out.println(urlCapituloPaso1);
                    urlCapituloPaso2 = ObtenerCadenaEntreTags(ObtenerCodigoFuente("http://www.seriescoco.com" + urlCapituloPaso1), "</a> </td> <td> <a href=\"", "target=\"_blank\"> <span class=\"server streamcloud\">", 0, 0, 25, 2, "");
                    System.out.println(urlCapituloPaso2);
                    codigoStream = ObtenerCodigoFuente("http://www.seriescoco.com" + urlCapituloPaso2);
               
                } catch(StringIndexOutOfBoundsException ex) {
                    error = true;
                    
                    JOptionPane.showMessageDialog(new JOptionPane(), "El capitulo " +  (progresoActualDeCargaPBar / intervaloDeCargaPBar) + " de esta temporada no es compatible." );
                    break;
                }
               
                    try {
                        if (codigoStream.indexOf("SeriesCoco") > 0) {                        
                            urlCaptcha = ObtenerCadenaEntreTags(codigoStream, "<noscript>", "\" height=\"300\" width=\"500\" frameborder=\"0\"></iframe>", 0, 0, 28, 0, "");
                            codigoCaptcha = ObtenerCodigoFuente(urlCaptcha);                        
                            urlImagenCaptcha = "http://www.google.com/recaptcha/api/" + ObtenerCadenaEntreTags(codigoCaptcha, "<img width=\"300\" height=\"57\" alt=\"\" src=\"", "></center>", 0, 0, 41, 1,"");
                            hashCaptcha = ObtenerCadenaEntreTags(codigoCaptcha, "recaptcha_challenge_field\" value=\"", "<center><img width=\"300", 0, 0, 34, 2,"");
                            System.out.println(hashCaptcha);
                            validador = new ValidaCaptcha(urlCaptcha, hashCaptcha, urlCapituloPaso2, urlImagenCaptcha);

                            while(validador.getCodigoStream() == null) {
                                Thread.sleep(1);
                            }

                            codigoStream = ObtenerCodigoFuente(validador.getCodigoStream());
                            error = false;
                        } 
                        
                    } catch (IOException | InterruptedException e) {
                        //progresoActualDeCargaPBar = 0;
                        //infoCapsStreamCloud.clear();
                        segundoNodoCapitulos = nodoAnterior;
                        error = true;
                        break;
                        
                    }
                
                if (currentUrl.toString().indexOf("adbooth") > 0) {
                    ObtenerCodigoFuente("http://www.seriescoco.com" + urlCapituloPaso2);                    
                    sacarEnlaceStreamcloudAdbooth = String.valueOf(currentUrl) + "/FINURL";
                    System.out.println(currentUrl);
                    urlDeStreamCloud = ObtenerCadenaEntreTags(sacarEnlaceStreamcloudAdbooth, "&url=", "/FINURL", 0, 0, 5, 0, "");
                    System.out.println(urlDeStreamCloud);
                    codigoStream = ObtenerCodigoFuente(urlDeStreamCloud);
                }
                
                idVideo = ObtenerCadenaEntreTags(codigoStream, "<input type=\"hidden\" name=\"id\" value=\"", "<input type=\"hidden\" name=\"fname\"", 0, 0, 38, 8, "");
                System.out.println(idVideo);
                
                nombreVideo = ObtenerCadenaEntreTags(codigoStream, "name=\"fname\" value=\"", "<input type=\"hidden\" name=\"referer\"", 0, 0, 20, 8, "");
                System.out.println(nombreVideo);
                
                infoCapsStreamCloud.put(idVideo, nombreVideo);
                
                progresoActualDeCargaPBar = intervaloDeCargaPBar + progresoActualDeCargaPBar;
                
                System.out.println("---------------------------");
                
                } catch(IndexOutOfBoundsException | IOException ex) {
                    
                
                
                finCargaCapitulos = true;
                progresoActualDeCargaPBar = 100;
                System.out.println(finCargaCapitulos);
                runningThread = false;
                yield();
                Logger.getLogger(MassDown.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            } while(segundoNodoCapitulos > 0);

            
        }
    }
}

public void threadStop() {
    runningThread = false;
}

public void iniciarThreadListarCapitulos(int numTemporada) {
    ObtenedorListaCapitulos obtenedorListaCapitulos = new ObtenedorListaCapitulos(numTemporada);
    if(runningThread == true) {
        threadStop();
        ObtenedorListaCapitulos.interrupted();
    }
    obtenedorListaCapitulos.start();
    runningThread = true;
}
    
    public String getTituloSerie() {        
        return ObtenerCadenaEntreTags(codigoFuente, "<h1 class=\"underline\" title=\"", "\">", 1, 1, 29, 0, "");    
    }
    
    public ArrayList getListaNombreCapitulos(int numeroTemporada) {
        codigoFuenteTemporada = codigosFuentesTemporadas.get(numeroTemporada);
        //codigoFuenteTemporada = codigoFuenteTemporada.substring(640);
        ArrayList<String> capitulos = new ArrayList<>();
        capitulos.clear();
        int numCapitulo = 0;
        segundoNodoCapitulos = 1;
        int dondePararElBucle = 0;
        
         while(segundoNodoCapitulos > 0) {
            try {
            String nombreCapitulo = ObtenerCadenaEntreTags(codigoFuenteTemporada, "class=\"episode-title\"", "</td>  <td>", 0, segundoNodoCapitulos, 9, 0, "capitulos");        
            nombreCapitulo = ObtenerCadenaEntreTags(nombreCapitulo, "<strong> " , "</a>", 0, 0, 9, 0, "");
            nombreCapitulo = nombreCapitulo.replaceAll("</strong>", "");
            
            
//            
//            if(numCapitulo == 2) {
//                dondePararElBucle = segundoNodoCapitulos;
//                System.out.println("------------ \n " + dondePararElBucle + "\n ----------------------");
//            }
//System.out.println(segundoNodoCapitulos);
//            if(dondePararElBucle == segundoNodoCapitulos && numCapitulo > 2) {
//                break;
//            }
//            if(!capitulos.isEmpty())  { 
//                if(capitulos.get(1).equals(nombreCapitulo)) {
//                    System.out.println(capitulos.get(3));
//                    break;
//                }
//            }
              
                if(capitulos.contains(nombreCapitulo) && numCapitulo > 1) {
                      

                    System.out.println("mierda"); 
                    break;
                
                   
                }
            

                    capitulos.add(nombreCapitulo);
                    System.out.println(nombreCapitulo);
                    System.out.println(numCapitulo); 
                 
                
                
           
                
                
                
                
               
            
            
                        numCapitulo++;

            } catch(IndexOutOfBoundsException ex) {
                Logger.getLogger(MassDown.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
         
        
        
        return capitulos;        
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

    public void setCodigoFuente(String codigoFuente) {
        this.codigoFuente = codigoFuente;
    }

    public ArrayList<String> getCodigosFuentesTemporadas() {
        return codigosFuentesTemporadas;
    }

    public void addCodigosFuentesTemporadas(String codigosFuentesTemporadas) {
        this.codigosFuentesTemporadas.add(codigosFuentesTemporadas);
    }

    public Hashtable<String, String> getInfoCapsStreamCloud() {
        return infoCapsStreamCloud;
    }

    public boolean isFinCargaCapitulos() {
        return finCargaCapitulos;
    }

    public void setFinCargaCapitulos(boolean finCargaCapitulos) {
        this.finCargaCapitulos = finCargaCapitulos;
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
      
    
                          
}
