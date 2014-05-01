package com.massdown.views.libs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 
 
 
public class ReproductorDeVideo extends Application {
    
    public static String urlDelArchivo;
    private Stage primaryStage;
    
	@Override 
        public void start(final Stage primaryStage) {
            this.primaryStage = primaryStage;
            final WebView root = new WebView();
            root.getEngine().loadContent(
            "<!DOCTYPE html><html><head><style>"
                    + "body { background-color: black;  }"
                    + "video {position: absolute; bottom: 3px;right: 10px;left: 10px;}"
                    + "</style></head><body>"+
            "<video width='97%' height='100%'controls='controls'>" +
            "<source src='"+ urlDelArchivo +"'/>" + 
            "Your browser does not support the video tag." + 
            "</video></body></html>");
            primaryStage.setScene(new Scene(root, 800, 455));
            
            Platform.setImplicitExit(false);
            
        }
        
        public void Mostrar() {
            
        }
        public void Iniciar() {
            Application.launch();            
            primaryStage.show();
        }
}
