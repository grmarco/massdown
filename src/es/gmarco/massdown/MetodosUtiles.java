
package es.gmarco.massdown;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MetodosUtiles {
    
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
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;

        while((line = in.readLine())!=null) {
            Fuente.append(line);
        }
        in.close();

        return Fuente.toString();        
    }
    
    public static String[] ObtenerCodigoFuente(String url) throws MalformedURLException, IOException {
        
        URL siteUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        
        StringBuilder Fuente = new StringBuilder();
        while((line = in.readLine())!=null) {
            Fuente.append(line);
        }
        in.close();
        
        //Devuelve dos valores, el codigo fuente y la url del codigo fuente.
        String valoresADevolver[] = {String.valueOf(conn.getURL()), Fuente.toString()};

                
        return valoresADevolver;
    }
    
}
