package view.empresas;

import java.io.IOException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.ConexionBBDD;
import model.Empresa;

public class ControladorEmpresa {
	
	@FXML
	Button nuevo;
	
	@FXML 
	Button modificar;
	
	@FXML
	Button filtrar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField tutorEmpresa;
	
	@FXML
	TextField tutor;
	
	@FXML
	TextField conv;
	
	@FXML
	TextField ciudad;
	
	@FXML 
	TableView<Empresa> empresas;
	
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
	
	
public void initialize() {
		
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
	
	ConexionBBDD mostrar = new ConexionBBDD();
	
	empresas.setItems(mostrar.ConsultaE());
	
}	

public void crearEmpresa(ActionEvent event) throws IOException{
	
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/empresas/CrearEmpresa.fxml"));
    GridPane ventanaDos = (GridPane) loader.load();
    Stage ventana = new Stage();
    ventana.setTitle("CrearEmpresa");
    Scene scene = new Scene(ventanaDos);
    ventana.setScene(scene);
    ventana.show();
}

public void modificarEmpresa(ActionEvent event) throws IOException{
	
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/empresas/ModificarEmpresa.fxml"));
    GridPane ventanaDos = (GridPane) loader.load();
    Stage ventana = new Stage();
    ventana.setTitle("ModificarEmpresa");
    Scene scene = new Scene(ventanaDos);
    ventana.setScene(scene);
    ventana.show();
}

}


