package view.ciclos;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Alumno;
import model.Ciclo;
import model.ConexionBBDD;
import model.ConsultasBBDD;
import view.alumnos.ControladorModificarA;

public class ControladorCiclos {

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
	TextField familiaprof;

	@FXML
	TextField nombre;

	@FXML
	TextField empresa;

	@FXML
	TableView<Ciclo> ciclos;

	@FXML
	private TableColumn<Ciclo, String> ColClave;

	@FXML
	private TableColumn<Ciclo, String> ColNombre;

	@FXML
	private TableColumn<Ciclo, String> ColFamilia;

	@FXML
	private TableColumn<Ciclo, String> ColClave_Fam;

	@FXML
	private TableColumn<Ciclo, String> ColCap_Term;

	@FXML
	private TableColumn<Ciclo, String> ColAct_FP;

	@FXML
	private TableColumn<Ciclo, String> ColCrit;

	@FXML
	TextField clave;

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

		ciclos.setItems(mostrar.filtroCiclo(familiaprof.getText(), nombre.getText(), empresa.getText()));

		ColClave.setCellValueFactory(new PropertyValueFactory<Ciclo, String>("clave"));
		ColNombre.setCellValueFactory(new PropertyValueFactory<Ciclo, String>("nombre"));
		ColFamilia.setCellValueFactory(new PropertyValueFactory<Ciclo,String>("familia"));
		ColClave_Fam.setCellValueFactory(new PropertyValueFactory<Ciclo,String>("clave_fam"));
		ColCap_Term.setCellValueFactory(new PropertyValueFactory<Ciclo,String>("capacidades"));
		ColAct_FP.setCellValueFactory(new PropertyValueFactory<Ciclo,String>("actividades"));
		ColCrit.setCellValueFactory(new PropertyValueFactory<Ciclo,String>("criterios"));


	}

	public void buscar(ActionEvent event) {

		ConsultasBBDD buscar = new ConsultasBBDD();

		ciclos.setItems(buscar.buscarCiclo(clave.getText()));

	}

	public void filtrar(ActionEvent event) {

		ConsultasBBDD mostrar = new ConsultasBBDD();

		ciclos.setItems(mostrar.filtroCiclo(familiaprof.getText(), nombre.getText(), empresa.getText()));

	}

	public void crearCiclo(ActionEvent event) throws IOException{

		controlador.ciclosCrearVent();
	}

	public void modificarCiclo(ActionEvent event) throws IOException{

		Ciclo selectedCiclo = ciclos.getSelectionModel().getSelectedItem();

		if (selectedCiclo != null) {

			controlador.ciclosModVent(selectedCiclo);
		}
	    else
	    {
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Mensaje de error");
	    	alert.setHeaderText("� Ciclo no seleccionado !");
	    	alert.setContentText("Por favor, seleccione un ciclo a modificar de la tabla.");
	    	alert.showAndWait();
	    }

	}

	public void volver(ActionEvent event) {
		controlador.menuVent();
	}

}
