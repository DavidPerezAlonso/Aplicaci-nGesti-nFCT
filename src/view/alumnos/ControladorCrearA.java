package view.alumnos;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import model.ConexionBBDD;

public class ControladorCrearA {
	
	@FXML
	ImageView logo;

	@FXML
	Button crear;
	
	@FXML
	Button limpiar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField NIF;
	
	@FXML
	TextField nombre;
	
	@FXML
	TextField apellidos;
	
	@FXML
	TextField direccion;
	
	@FXML
	TextField ciudad;
	
	@FXML
	TextField cp;
	
	@FXML
	TextField provincia;
	
	@FXML
	TextField telefono;
	
	@FXML
	TextField email;
	
	
	public void crearAlumno() throws SQLException {
		
		ConexionBBDD insertar = new ConexionBBDD();
        
		if(!NIF.getText().equals("") && NIF.getText() != null && !apellidos.getText().equals("") && apellidos.getText() != null && !direccion.getText().equals("") && direccion.getText() != null && !ciudad.getText().equals("") && ciudad.getText() != null && !cp.getText().equals("") && cp.getText() != null && !provincia.getText().equals("") && provincia.getText() != null && !telefono.getText().equals("") && telefono.getText() != null && !email.getText().equals("") && email.getText() != null) {
			String niftexto = NIF.getText();
	        String nomtexto = nombre.getText();
	        String apetexto = apellidos.getText();
	        String dirtexto = direccion.getText();
	        String ciutexto = ciudad.getText();
	        String cptexto = cp.getText();
	        String provtexto = provincia.getText();
	        String teltexto = telefono.getText();
	        String mailtexto = email.getText();
	        
	        insertar.insertarAlumno(niftexto, nomtexto, apetexto, dirtexto, ciutexto, cptexto, provtexto, teltexto, mailtexto);
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
		
		NIF.setText("");
		nombre.setText("");
		apellidos.setText("");
		direccion.setText("");
		ciudad.setText("");
		cp.setText("");
		provincia.setText("");
		telefono.setText("");
		email.setText("");
		
	}
	
}
