package view.tutores;

import java.io.IOException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ConsultasBBDD;
import model.TutorCentro;

public class ControladorTC {

	@FXML
	ImageView logo;

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
	TextField empresa;


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

	@FXML
	TextField nifTutor;

	@FXML
	Button buscar;

	Main controlador;
	Stage ventana;

	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}

	public void initialize() {

		ConsultasBBDD mostrar = new ConsultasBBDD();

		tutoresc.setItems(mostrar.filtroTutor(NIF_AL.getText(), empresa.getText()));


		ColNIF.setCellValueFactory(new PropertyValueFactory<TutorCentro, String>("NIF_TC"));
		ColNombre.setCellValueFactory(new PropertyValueFactory<TutorCentro, String>("nombre"));
		ColTelefono.setCellValueFactory(new PropertyValueFactory<TutorCentro,String>("telefono"));
		ColMail.setCellValueFactory(new PropertyValueFactory<TutorCentro,String>("email"));
		ColCentro.setCellValueFactory(new PropertyValueFactory<TutorCentro,String>("cod_centro"));

	}


	public void filtrar(ActionEvent event) {

		ConsultasBBDD mostrar = new ConsultasBBDD();

		tutoresc.setItems(mostrar.filtroTutor(NIF_AL.getText(), empresa.getText()));

	}

	public void buscar(ActionEvent event) {

		ConsultasBBDD buscar = new ConsultasBBDD();

		tutoresc.setItems(buscar.buscarTutor(nifTutor.getText()));

	}

	public void crearTutorC(ActionEvent event) throws IOException {

		controlador.tutoresCrearVent();
	}

	public void modificarTutorC(ActionEvent event) throws IOException{

		TutorCentro selectedTutor= tutoresc.getSelectionModel().getSelectedItem();

		if (selectedTutor != null) {

			controlador.tutoresModVent(selectedTutor);
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

	public void volver(ActionEvent event) {
		controlador.menuVent();
	}
}
