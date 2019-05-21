package view.alumnos;

import java.sql.SQLException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Alumno;
import model.ConexionBBDD;

public class ControladorModificarA {
	
	@FXML
	ImageView logo;
	
	@FXML
	Button modificar;
	
	@FXML
	Button restablecer;
	
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
	
	Alumno datosmodificar;
	
	Main controlador;
	
	Stage ventana;
	
	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}
    

	public void modificarAlumno() throws SQLException {
        
		ConexionBBDD  modificar = new ConexionBBDD();
		
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
	        
	        modificar.modificarAlumno(niftexto, nomtexto, apetexto, dirtexto, ciutexto, cptexto, provtexto, teltexto, mailtexto);
	        
	        Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Modificación realizada.");
			alert.setHeaderText(null);
			alert.setContentText("¡ El alumno " + nomtexto + " " + apetexto + " se ha modificado correctamente !");
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
		
		NIF.setText(datosmodificar.getNIF());
		nombre.setText(datosmodificar.getNombre());
		apellidos.setText(datosmodificar.getApellidos());
		direccion.setText(datosmodificar.getDireccion());
		ciudad.setText(datosmodificar.getCiudad());
		cp.setText(datosmodificar.getCp());
		provincia.setText(datosmodificar.getProvincia());
		telefono.setText(datosmodificar.getTelefono());
		email.setText(datosmodificar.getEmail());
		
	}
	
	public void setDatos(Alumno datosmodificar) {
		
		this.datosmodificar = datosmodificar;
		 
		NIF.setText(datosmodificar.getNIF());
		nombre.setText(datosmodificar.getNombre());
		apellidos.setText(datosmodificar.getApellidos());
		direccion.setText(datosmodificar.getDireccion());
		ciudad.setText(datosmodificar.getCiudad());
		cp.setText(datosmodificar.getCp());
		provincia.setText(datosmodificar.getProvincia());
		telefono.setText(datosmodificar.getTelefono());
		email.setText(datosmodificar.getEmail());
		
	}
	
	public void volver(ActionEvent event) {
		controlador.alumnoVent();
	}
}
