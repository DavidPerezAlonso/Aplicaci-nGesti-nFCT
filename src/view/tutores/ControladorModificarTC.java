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
import model.Ciclo;
import model.ConexionBBDD;
import model.TutorCentro;

public class ControladorModificarTC {
	
	@FXML
	ImageView logo;

	@FXML
	Button modificar;
	
	@FXML
	Button reestablecer;
	
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
	
	TutorCentro datosmodificar;
	
	Main controlador;
	Stage ventana;
	
	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}
	
	public void modificarTutorC() throws SQLException {
        
		ConexionBBDD modificar = new ConexionBBDD();
		
		if(!NIF_TC.getText().equals("") && NIF_TC.getText() != null && !nombre.getText().equals("") && nombre.getText() != null && !telefono.getText().equals("") && telefono.getText() != null && !email.getText().equals("") && email.getText() != null) {
			String niftexto = NIF_TC.getText();
			String nomtexto = nombre.getText();
			String teltexto = telefono.getText();
			String mailtexto = email.getText();

			modificar.modificarTutorC(niftexto, nomtexto, teltexto, mailtexto);
			
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tutor centro modificado.");
			alert.setHeaderText(null);
			alert.setContentText("¡ Los datos del tutor del centro " + nomtexto + " han sido modificados correctamente !");
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
	
	public void reestablecer() {
		
		NIF_TC.setText(datosmodificar.getNIF_TC());
		nombre.setText(datosmodificar.getNombre());
		telefono.setText(datosmodificar.getTelefono());
		email.setText(datosmodificar.getEmail());
		
	}
	
	public void setDatos(TutorCentro datosmodificar) {
		
		this.datosmodificar = datosmodificar;
		 
		NIF_TC.setText(datosmodificar.getNIF_TC());
		nombre.setText(datosmodificar.getNombre());
		telefono.setText(datosmodificar.getTelefono());
		email.setText(datosmodificar.getEmail());
	}
	
	public void volver(ActionEvent event) {
		controlador.tutoresVent();
	}
}
