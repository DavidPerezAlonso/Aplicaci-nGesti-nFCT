package view.alumnos;

import java.io.IOException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Alumno;
import model.ConexionBBDD;

public class ControladorAlumno {
	
	@FXML
	Button nuevo;
	
	@FXML 
	Button modificar;
	
	@FXML
	Button filtrar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField curso;
	
	@FXML
	TextField ciclo;
	
	@FXML
	TextField empresa;
	
	@FXML
	TextField tutor;
	
	@FXML 
	TableView<Alumno> alumno;
	
	@FXML
	private TableColumn<Alumno, String> ColNIF;
	
	@FXML
	private TableColumn<Alumno, String> ColNombre;
	
	@FXML
	private TableColumn<Alumno, String> ColApellidos;
	
	@FXML
	private TableColumn<Alumno, String> ColDireccion;
	
	@FXML
	private TableColumn<Alumno, String> ColCiudad;
	
	@FXML
	private TableColumn<Alumno, String> ColCP;
	
	@FXML
	private TableColumn<Alumno, String> ColProvincia;
	
	@FXML
	private TableColumn<Alumno, String> ColTelefono;
	
	@FXML
	private TableColumn<Alumno, String> ColMail;
	
	
public void initialize() {
		
		ColNIF.setCellValueFactory(new PropertyValueFactory<Alumno, String>("NIF"));
		ColNombre.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
		ColApellidos.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellidos"));
		ColDireccion.setCellValueFactory(new PropertyValueFactory<Alumno,String>("direccion"));
		ColCiudad.setCellValueFactory(new PropertyValueFactory<Alumno,String>("ciudad"));
		ColCP.setCellValueFactory(new PropertyValueFactory<Alumno,String>("cp"));
		ColProvincia.setCellValueFactory(new PropertyValueFactory<Alumno,String>("provincia"));
		ColTelefono.setCellValueFactory(new PropertyValueFactory<Alumno,String>("telefono"));
		ColMail.setCellValueFactory(new PropertyValueFactory<Alumno,String>("email"));
		
	}
	

public void filtrar(ActionEvent event) {
	
	ConexionBBDD mostrar = new ConexionBBDD();
	
	alumno.setItems(mostrar.ConsultaA());
	
}	

public void crearAlumno(ActionEvent event) throws IOException{
	
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/alumnos/CrearAlumno.fxml"));
    GridPane ventanaDos = (GridPane) loader.load();
    Stage ventana = new Stage();
    ventana.setTitle("CrearAlumno");
    Scene scene = new Scene(ventanaDos);
    ventana.setScene(scene);
    ventana.show();
}

public void modificarAlumno(ActionEvent event) throws IOException{
	
	Alumno selectedAlumno = alumno.getSelectionModel().getSelectedItem();
	
	if (selectedAlumno != null) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/alumnos/ModificarAlumno.fxml"));
	    GridPane ventanaDos = (GridPane) loader.load();
	    Stage ventana = new Stage();
	    ventana.setTitle("ModificarAlumno");
	    Scene scene = new Scene(ventanaDos);
	    
	    ControladorModificarA controladoraVentana2 = loader.getController();
	    controladoraVentana2.setDatos(selectedAlumno);
	    
	    ventana.setScene(scene);
	    ventana.show();
    }
	
    else
    {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Mensaje de error");
    	alert.setHeaderText("¡ Alumno no seleccionado !");
    	alert.setContentText("Por favor, seleccione un alumno a modificar de la tabla.");
    	alert.showAndWait();
    }

}

}
