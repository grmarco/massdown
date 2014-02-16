package com.massdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;



public class Utilidades {
    
    public static String[] ObtenerCodigoFuente(String url) throws MalformedURLException, IOException {
        URL siteUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
        conn.setRequestProperty("Accept-Charset", "ISO-8859-1");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("ISO-8859-1")));
        String line;
       
        StringBuilder Fuente = new StringBuilder();
        while((line = in.readLine()) != null) {
            line = new String(line.getBytes(), "ISO-8859-1");
            Fuente.append(line);
        }
        in.close();
        
        //Devuelve dos valores, el codigo fuente y la url del codigo fuente.
        String valoresADevolver[] = {String.valueOf(conn.getURL().toString().getBytes("ISO-8859-1")), Fuente.toString()};

                
        return valoresADevolver;
    }
    
    public static String[] ObtenerCadenaEntreTags(String codigoAAnalizar, String primerTag, String segundoTag, int ajustePrimerElemento, int ajusteSegundoElemento, int nodoInicio) {
        
        String elementoEntreTags[] = {"", ""};
        int primerNodo ;
        int segundoNodo;
        
        primerNodo = codigoAAnalizar.indexOf(primerTag, nodoInicio);
        segundoNodo = codigoAAnalizar.indexOf(segundoTag, primerNodo);

        
        elementoEntreTags[0] = codigoAAnalizar.substring(
                primerNodo + ajustePrimerElemento, 
                segundoNodo - ajusteSegundoElemento);
        elementoEntreTags[1] = String.valueOf(segundoNodo);
        
        return elementoEntreTags; 
    }
    
    
    

    public static String getFinalRedirectedUrl(String url) throws MalformedURLException, ProtocolException, IOException
    {
        HttpURLConnection connection;
        String finalUrl = url;
        do {
                connection = (HttpURLConnection) new URL(finalUrl)
                .openConnection();
                connection.setInstanceFollowRedirects(false);
                connection.setUseCaches(false);
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode >=300 && responseCode <400)
                {
                    String redirectedUrl =    connection.getHeaderField("Location");
                    if(null== redirectedUrl)
                        break;
                    finalUrl =redirectedUrl;
                    System.out.println( "redirected url: " + finalUrl);
                }
                else
                    break;
            }while (connection.getResponseCode() != HttpURLConnection.HTTP_OK);
            connection.disconnect();
        
      
        return finalUrl;
    }


    
}
