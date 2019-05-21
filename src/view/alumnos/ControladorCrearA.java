package view.alumnos;

import java.io.IOException;
import java.sql.SQLException;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
	
	@FXML 
	ChoiceBox<String> ciclo;
	
	@FXML 
	ChoiceBox<String> curso;
	
	@FXML 
	Button agregarCurso;
	
	@FXML
	TextField inicio;
	
	@FXML
	TextField fin;
	
	Main controlador;
	
	Stage ventana;
	
	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}
	
	public void initialize() {
		
		ConexionBBDD listar = new ConexionBBDD();
		
		ciclo.setItems(listar.anexoCicloNom());
		curso.setItems(listar.anexoCurso());
}
	

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
	        
        	String ciclotexto = ciclo.getSelectionModel().getSelectedItem();
        	String cursotexto = curso.getSelectionModel().getSelectedItem();
	        
	        if (ciclotexto != null && cursotexto != null) {
	        	
	        	String clavetexto = insertar.claveCiclo(ciclotexto);
	        
	        	insertar.insertarAlumno(niftexto, nomtexto, apetexto, dirtexto, ciutexto, cptexto, provtexto, teltexto, mailtexto, clavetexto, cursotexto);
	        	
	        	Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alumno registrado.");
				alert.setHeaderText(null);
				alert.setContentText("¡ El nuevo alumno se ha registrado correctamente !");
				alert.showAndWait();
	        }
	        
	        else {
	        	
				Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Mensaje de error");
		    	alert.setHeaderText("¡ Campos incompletos !");
		    	alert.setContentText("Por favor, seleccione una opción en los campos \"Ciclo\" y \"Curso\" para continuar.");
		    	alert.showAndWait();
	        }
	        
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
	
	public void agregarCurso() {
		
		ObservableList<String> nuevo = FXCollections.observableArrayList();
		
		String fechas = inicio.getText() + "/" + fin.getText();
		
		nuevo.add(fechas);
		curso.setItems(nuevo);
		
	}
	
	public void volver(ActionEvent event) {
		controlador.alumnoVent();
	}
}
