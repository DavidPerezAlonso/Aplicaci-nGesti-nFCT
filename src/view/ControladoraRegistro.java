package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.LoginBBDD;

public class ControladoraRegistro {
	
	private String claveReg = "";
	private static Connection conexion;
	
	@FXML
	TextField user;
	
	@FXML
	TextField pass;
	
	@FXML
	TextField claveRegistro;
	
	@FXML
	Button registrar;
	
	public ControladoraRegistro() {

		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("src/model/datos.ini");
			if (miFichero.exists()){
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				claveReg=propiedades.getProperty("clave_reg");
		
			}
			else
				System.out.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public void registrar(ActionEvent event) throws IOException, SQLException  {
		
		LoginBBDD login = new LoginBBDD();
		
		String usuario = user.getText();
		String password = pass.getText();
		String clave_reg = claveRegistro.getText();
		
		if (clave_reg.equals(claveReg)) {
			
			login.crearUsuario(usuario, password);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Usuario registrado.");
			alert.setHeaderText(null);
			alert.setContentText("¡ El usuario se ha registrado correctamente !");
			alert.showAndWait();
		}
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Mensaje de error");
			alert.setHeaderText("¡ Clave de registro inválida !");
			alert.setContentText("Por favor, introduzca una clave válida. Si no conoce la clave solícitela en su centro");
			alert.showAndWait();
		}
	}
}
