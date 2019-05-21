package view.ciclos;

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
import model.Ciclo;
import model.ConexionBBDD;

public class ControladorCrearC {
	
	@FXML
	ImageView logo;
	
	@FXML
	Button crear;
	
	@FXML
	Button limpiar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField clave;
	
	@FXML
	TextField nombre;
	
	@FXML
	TextField familia;
	
	@FXML
	TextField clave_fam;
	
	@FXML
	TextField capacidades;
	
	@FXML
	TextField actividades;
	
	@FXML
	TextField criterios;

	
	Ciclo datosmodificar;
	
	Main controlador;
	Stage ventana;
	
	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}
	
	
	public void crearCiclo() throws SQLException {
        
		ConexionBBDD insertar = new ConexionBBDD();
		
		if(!clave.getText().equals("") && clave.getText() != null && !nombre.getText().equals("") && nombre.getText() != null && !familia.getText().equals("") && familia.getText() != null && !clave_fam.getText().equals("") && clave_fam.getText() != null && !capacidades.getText().equals("") && capacidades.getText() != null && !actividades.getText().equals("") && actividades.getText() != null && !criterios.getText().equals("") && criterios.getText() != null) {
		String clavetexto = clave.getText();
        String nomtexto = nombre.getText();
        String famtexto = familia.getText();
        String cftexto = clave_fam.getText();
        String captexto = capacidades.getText();
        String acttexto = actividades.getText();
        String crittexto = criterios.getText();
        
        insertar.insertarCiclo(clavetexto, nomtexto, famtexto, cftexto, captexto, acttexto, crittexto);
		}
		
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Mensaje de error");
	    	alert.setHeaderText("� Faltan datos !");
	    	alert.setContentText("Por favor, rellene todos los campos correctamente.");
	    	alert.showAndWait();
		} 
		
        
	}

	public void limpiarTexto() {
		
		clave.setText("");
		nombre.setText("");
		familia.setText("");
		clave_fam.setText("");
		capacidades.setText("");
		actividades.setText("");
		criterios.setText("");
		
	}
	
	public void volver(ActionEvent event) {
		controlador.ciclosVent();
	}
}
