package view;

import java.sql.SQLException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LoginBBDD;

public class ControladoraCambiarPass {

	
	@FXML
	TextField user;
	
	@FXML
	TextField passAct;
	
	@FXML
	TextField passNue;

	@FXML
	TextField passNueConf;
	
	@FXML
	Button cambiar;
	
	@FXML
	Button volver;
	
	@FXML
	ImageView logo;
	
	Main controlador;
	
	Stage ventana;
	
	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}
	
	public void volver(ActionEvent event) {
		controlador.loginVent();
	}
	
	
	public void cambiarPass(ActionEvent event) throws SQLException {
		
		LoginBBDD cambio = new LoginBBDD();
		
		String usuarioVerificado = cambio.comprobarUser(user.getText());
		
		if (usuarioVerificado != null) {
			
			String passVerificada = cambio.comprobarPass(user.getText());
			
			if (passVerificada.equals(passAct.getText())) {
				
				if (passNue.getText().equals(passNueConf.getText())) {
					
					cambio.cambiarPass(passNue.getText());
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Contraseña modificada.");
					alert.setHeaderText(null);
					alert.setContentText("¡ La contraseña se ha modificado con éxito !");
					alert.showAndWait();
					
					user.setText("");
					passAct.setText("");
					passNue.setText("");
					passNueConf.setText("");
				}
				
				else {
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Mensaje de error");
					alert.setHeaderText("¡ Las nuevas contraseñas no coinciden !");
					alert.setContentText("Vuelva a introducir las nuevas contraseñas.");
					alert.showAndWait();	
					
					passNue.setText("");
					passNueConf.setText("");
				}
			}
			
			else {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Mensaje de error");
				alert.setHeaderText("¡ Contraseña incorrecta !");
				alert.setContentText("Por favor, introduzca una contraseña válida.");
				alert.showAndWait();
			}
		}
		
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Mensaje de error");
			alert.setHeaderText("¡ Usuario no registrado !");
			alert.setContentText("Por favor, introduzca un usuario registrado.");
			alert.showAndWait();
		}
	}
}
