package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LoginBBDD;


public class ControladoraLogin {
	
	@FXML 
	Button login;
	
	@FXML
	TextField user;
	
	@FXML 
	TextField pass;
	
	@FXML
	Button registrar;
	


public void menu(ActionEvent event) throws IOException{
	
		LoginBBDD login = new LoginBBDD();
	
		String usuario = user.getText();
		String password = pass.getText();
		
		String usuarioVerificado = login.comprobarUser(usuario);
		
		if (usuarioVerificado != null) {
			
			String passVerificada = login.comprobarPass(usuario);
		
			if (passVerificada.equals(password)) {
				
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/InterfazMenu.fxml"));
		        AnchorPane ventanaDos = (AnchorPane) loader.load();
		        Stage ventana = new Stage();
		        ventana.setTitle("Menu Principal");
		        Scene scene = new Scene(ventanaDos);
		        ventana.setScene(scene);
		        ventana.show();
			}
			
			else {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Mensaje de error");
				alert.setHeaderText("� Contrase�a incorrecta !");
				alert.setContentText("Por favor, introduzca una contrase�a v�lida.");
				alert.showAndWait();
			}
		}
		
		else {
		
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Mensaje de error");
			alert.setHeaderText("� Usuario no registrado !");
			alert.setContentText("Por favor, introduzca un usuario ya registrado o cree un nuevo usuario.");
			alert.showAndWait();
		}
	}


	public void registrar(ActionEvent event) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/InterfazRegistro.fxml"));
	    AnchorPane ventanaDos = (AnchorPane) loader.load();
	    Stage ventana = new Stage();
	    ventana.setTitle("Nuevo usuario");
	    Scene scene = new Scene(ventanaDos);
	    ventana.setScene(scene);
	    ventana.show();
	   
	   
	}

}
