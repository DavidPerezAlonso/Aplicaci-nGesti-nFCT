package view.tutores;

import java.sql.SQLException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ConexionBBDD;

public class ControladorCrearTC {

	@FXML
	ImageView logo;

	@FXML
	Button crear;

	@FXML
	Button limpiar;

	@FXML
	Button volver;

	@FXML
	TextField NIF_TC;

	@FXML
	TextField nombre;

	@FXML
	TextField telefono;

	@FXML
	TextField email;

	@FXML
	Text cod_centro;

	Main controlador;
	Stage ventana;

	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;

	}

	public void crearTutorC() throws SQLException {

		ConexionBBDD insertar = new ConexionBBDD();

		if(!NIF_TC.getText().equals("") && NIF_TC.getText() != null && !nombre.getText().equals("") && nombre.getText() != null && !telefono.getText().equals("") && telefono.getText() != null && !email.getText().equals("") && email.getText() != null) {
			String niftexto = NIF_TC.getText();
			String nomtexto = nombre.getText();
			String teltexto = telefono.getText();
			String mailtexto = email.getText();
			String centrotexto = cod_centro.getText();

			insertar.insertarTutorC(niftexto, nomtexto, teltexto, mailtexto, centrotexto);

        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tutor centro registrado.");
			alert.setHeaderText(null);
			alert.setContentText("¡ El nuevo tutor del centro se ha registrado correctamente !");
			alert.showAndWait();
		}

		else {

			Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Mensaje de error");
	    	alert.setHeaderText("¡ Faltan datos !");
	    	alert.setContentText("Por favor, rellene todos los campos correctamente.");
	    	alert.showAndWait();
		}


	}

	public void limpiarTexto() {

		NIF_TC.setText("");
		nombre.setText("");
		telefono.setText("");
		email.setText("");

	}

	public void volver(ActionEvent event) {
		controlador.tutoresVent();
	}
}
