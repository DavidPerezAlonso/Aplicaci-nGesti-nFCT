package view.tutores;

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
import model.ConexionBBDD;
import model.TutorCentro;
import view.ciclos.ControladorModificarC;

public class ControladorTC {

	@FXML
	Button nuevo;
	
	@FXML 
	Button modificar;
	
	@FXML
	Button filtrar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField NIF_AL;
	
	@FXML
	TextField nconv;
	
	
	@FXML 
	TableView<TutorCentro> tutoresc;
	
	@FXML
	private TableColumn<TutorCentro, String> ColNIF;
	
	@FXML
	private TableColumn<TutorCentro, String> ColNombre;
	
	@FXML
	private TableColumn<TutorCentro, String> ColTelefono;
	
	@FXML
	private TableColumn<TutorCentro, String> ColMail;
	
	@FXML
	private TableColumn<TutorCentro, String> ColCentro;
	
	
	public void initialize() {
		
		ColNIF.setCellValueFactory(new PropertyValueFactory<TutorCentro, String>("NIF_TC"));
		ColNombre.setCellValueFactory(new PropertyValueFactory<TutorCentro, String>("nombre"));
		ColTelefono.setCellValueFactory(new PropertyValueFactory<TutorCentro,String>("telefono"));
		ColMail.setCellValueFactory(new PropertyValueFactory<TutorCentro,String>("email"));
		ColCentro.setCellValueFactory(new PropertyValueFactory<TutorCentro,String>("cod_centro"));
		
	}
	

	public void filtrar(ActionEvent event) {
	
		ConexionBBDD mostrar = new ConexionBBDD();
	
		tutoresc.setItems(mostrar.ConsultaTC());
	
	}	

	public void crearTutorC(ActionEvent event) throws IOException {
	
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/tutores/CrearTutorCentro.fxml"));
		GridPane ventanaDos = (GridPane) loader.load();
		Stage ventana = new Stage();
		ventana.setTitle("CrearTutor");
		Scene scene = new Scene(ventanaDos);
		ventana.setScene(scene);
		ventana.show();
	}

	public void modificarTutorC(ActionEvent event) throws IOException{
	
		TutorCentro selectedTutor= tutoresc.getSelectionModel().getSelectedItem();
	
		if (selectedTutor != null) {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/tutores/ModificarTutorCentro.fxml"));
			GridPane ventanaDos = (GridPane) loader.load();
			Stage ventana = new Stage();
			ventana.setTitle("ModificarTutor");
	    	Scene scene = new Scene(ventanaDos);
	    
	    	ControladorModificarTC controladoraVentana2 = loader.getController();
	    	controladoraVentana2.setDatos(selectedTutor);
	    
	    	ventana.setScene(scene);
	    	ventana.show();
		}
	
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Mensaje de error");
			alert.setHeaderText("¡ Tutor no seleccionado !");
			alert.setContentText("Por favor, seleccione un tutor a modificar de la tabla.");
			alert.showAndWait();
		}

	}
}
