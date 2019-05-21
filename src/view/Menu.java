package view;

import java.io.IOException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Menu {
	
	@FXML
	Button GenerarAnexos;
	
	@FXML
	Button GestionAlumnos;
	
	@FXML
	Button GestionEmpresas;
	
	@FXML
	Button GestionCiclos;
	
	@FXML
	Button GestionTutores;
	
	@FXML
	Button AsignarPracticas;
	
	@FXML
	Button Logout;
	
	@FXML
	ImageView logo;
	
	Main controlador;
	
	Stage ventana;
	
	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}
	
	
	public void initialize() {

	}
	
	public void intefazAlumnos(ActionEvent event) throws IOException{
		
		controlador.alumnoVent();
       
	}

	public void intefazEmpresas(ActionEvent event) throws IOException{
	
	controlador.empresaVent();
}

public void intefazCiclos(ActionEvent event) throws IOException{
	
	controlador.ciclosVent();
}

public void intefazTutores(ActionEvent event) throws IOException{
	
	controlador.tutoresVent();
}

public void intefazAsignar(ActionEvent event) throws IOException{
	
	controlador.asignarVent();
}

public void interfazAnexos(ActionEvent event) throws IOException{
	
	controlador.anexosVent();
}

public void logout(ActionEvent event) {
	controlador.cerrarAplicacion();
	
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Logout correcto.");
	alert.setHeaderText(null);
	alert.setContentText("¡ Hasta pronto !");
	alert.showAndWait();
}
	
}
