package view;


import java.io.IOException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.LoginBBDD;


public class ControladoraLogin {

	@FXML
	Button login;

	@FXML
	TextField user;

	@FXML
	PasswordField pass;


	@FXML
	Button registrar;

	@FXML
	Button cambiar;

	Main controlador;

	Stage ventana;

	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}


public void menu(ActionEvent event) throws IOException{

		LoginBBDD login = new LoginBBDD();

		String usuario = user.getText();
		String password = pass.getText();

		String usuarioVerificado = login.comprobarUser(usuario);

		if (usuarioVerificado != null) {

			String passVerificada = login.comprobarPass(usuario);

			if (passVerificada.equals(password)) {

				controlador.menuVent();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Login correcto.");
				alert.setHeaderText(null);
				alert.setContentText("¡ Bienvenido " + usuario + " !");
				alert.showAndWait();
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
			alert.setContentText("Por favor, introduzca un usuario ya registrado o cree un nuevo usuario.");
			alert.showAndWait();
		}
	}


	public void registrar(ActionEvent event) throws IOException{

		controlador.registroVent();


	}

	public void cambiarPass(ActionEvent event) throws IOException{

		controlador.cambiarPassVent();

	}

}
