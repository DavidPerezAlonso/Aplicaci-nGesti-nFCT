package view.empresas;

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
import model.Empresa;

public class ControladorEmpresa {

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
	Button crearTE;

	@FXML
	Button modificarTE;

	@FXML
	Button btn_importar;

	@FXML
	TextField tutor;

	@FXML
	TextField conv;

	@FXML
	TextField ciclo;

	@FXML
	private TableView<Empresa> empresas;

	@FXML
	private TableColumn<Empresa, String> ColConv;

	@FXML
	private TableColumn<Empresa, String> ColNombre;

	@FXML
	private TableColumn<Empresa, String> ColCIF;

	@FXML
	private TableColumn<Empresa, String> ColRepresentante;

	@FXML
	private TableColumn<Empresa, String> ColNIF_Rep;

	@FXML
	private TableColumn<Empresa, String> ColDireccion;

	@FXML
	private TableColumn<Empresa, String> ColCP;

	@FXML
	private TableColumn<Empresa, String> ColCiudad;

	@FXML
	private TableColumn<Empresa, String> ColProvincia;

	@FXML
	private TableColumn<Empresa, String> ColPais;

	@FXML
	private TableColumn<Empresa, String> ColTelefono;

	@FXML
	private TableColumn<Empresa, String> ColFax;

	@FXML
	private TableColumn<Empresa, String> ColFecha_Conv;

	@FXML
	TextField convenio;

	@FXML
	Button buscar;

	Main controlador;
	Stage ventana;

	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}


	public void initialize() {

		// Carga los datos de empresas en la tabla
		ConsultasBBDD mostrar = new ConsultasBBDD();
		empresas.setItems(mostrar.filtroEmpresa(tutor.getText(), conv.getText(), ciclo.getText()));

		ColConv.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nConv"));
		ColRepresentante.setCellValueFactory(new PropertyValueFactory<Empresa,String>("representante"));
		ColNIF_Rep.setCellValueFactory(new PropertyValueFactory<Empresa,String>("NIF_REP"));
		ColCIF.setCellValueFactory(new PropertyValueFactory<Empresa,String>("CIF"));
		ColNombre.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nombre"));
		ColDireccion.setCellValueFactory(new PropertyValueFactory<Empresa,String>("direccion"));
		ColCP.setCellValueFactory(new PropertyValueFactory<Empresa,String>("cp"));
		ColCiudad.setCellValueFactory(new PropertyValueFactory<Empresa,String>("ciudad"));
		ColProvincia.setCellValueFactory(new PropertyValueFactory<Empresa,String>("provincia"));
		ColPais.setCellValueFactory(new PropertyValueFactory<Empresa,String>("pais"));
		ColTelefono.setCellValueFactory(new PropertyValueFactory<Empresa,String>("telefono"));
		ColFax.setCellValueFactory(new PropertyValueFactory<Empresa,String>("fax"));
		ColFecha_Conv.setCellValueFactory(new PropertyValueFactory<Empresa,String>("fecha_conv"));

	}


	public void filtrar(ActionEvent event) {

		ConsultasBBDD mostrar = new ConsultasBBDD();

		empresas.setItems(mostrar.filtroEmpresa(tutor.getText(), conv.getText(), ciclo.getText()));

	}

	public void buscar(ActionEvent event) {

		ConsultasBBDD buscar = new ConsultasBBDD();

		empresas.setItems(buscar.buscarEmpresa(convenio.getText()));

	}

	public void crearEmpresa(ActionEvent event) throws IOException{

		controlador.empresaCrearVent();
	}

	public void modificarEmpresa(ActionEvent event) throws IOException{


		Empresa selectedEmpresa = empresas.getSelectionModel().getSelectedItem();

		if (selectedEmpresa != null) {

			controlador.empresaModVent(selectedEmpresa);
		}

		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Mensaje de error");
			alert.setHeaderText("ˇ Empresa no seleccionada !");
			alert.setContentText("Por favor, seleccione una empresa a modificar de la tabla.");
			alert.showAndWait();

		}
	}

	public void crearTutorEmpresa(ActionEvent event) throws IOException{

		controlador.empresaTutVent();
	}

	public void modificarTutorEmpresa(ActionEvent event) throws IOException{

		controlador.empresaTutModVent();
	}

	public void volver(ActionEvent event) {
		controlador.menuVent();
	}


	public void importarEmpresas(){


			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Informacion");
	    	alert.setHeaderText("Importación Empresas");
	    	alert.setContentText("FUNCIONALIDAD NO OPERATIVA");
	    	alert.showAndWait();



	}
}



