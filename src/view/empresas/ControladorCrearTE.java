package view.empresas;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.ConexionBBDD;

public class ControladorCrearTE {
	
	@FXML
	ImageView logo;
	
	@FXML
	Button crear;
	
	@FXML
	Button limpiar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField NIF_TE;
	
	@FXML
	TextField nombre;
	
	@FXML
	TextField telefono;
	
	@FXML
	TextField email;
	
	@FXML 
	TextField nconv;
	
public void crearTutorE() throws SQLException {
        
		ConexionBBDD insertar = new ConexionBBDD();
		
		if(!NIF_TE.getText().equals("") && NIF_TE.getText() != null && !nombre.getText().equals("") && nombre.getText() != null && !telefono.getText().equals("") && telefono.getText() != null && !email.getText().equals("") && email.getText() != null && !nconv.getText().equals("") && nconv.getText() != null) {
			String niftexto = NIF_TE.getText();
			String nomtexto = nombre.getText();
			String teltexto = telefono.getText();
			String mailtexto = email.getText();
			String convtexto = nconv.getText();

			insertar.insertarTutorE(niftexto, nomtexto, teltexto, mailtexto, convtexto);
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
	
	NIF_TE.setText("");
	nombre.setText("");
	telefono.setText("");
	email.setText("");
	nconv.setText("");

}

}
