package view.empresas;

import java.io.IOException;
import java.sql.SQLException;

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
import javafx.scene.input.MouseEvent;
import model.ConexionBBDD;
import model.TutorEmpresa;

public class ControladorModificarTE {
	
	@FXML
	ImageView logo;
	
	@FXML
	Button modificar;
	
	@FXML
	Button reestablecer;
	
	@FXML
	Button filtrar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField nombre;
	
	@FXML
	TextField NIF_TE;
	
	@FXML
	TextField telefono;
	
	@FXML
	TextField email;
	
	@FXML 
	TextField nconv;
	
	@FXML 
	TextField nconvFiltro;
	
	@FXML 
	TableView<TutorEmpresa> tutoresEmpresa;
	
	@FXML
	private TableColumn<TutorEmpresa, String> ColNIF;
	
	@FXML
	private TableColumn<TutorEmpresa, String> ColNombre;
	
	@FXML
	private TableColumn<TutorEmpresa, String> ColTelefono;
	
	@FXML
	private TableColumn<TutorEmpresa, String> ColMail;
	
	@FXML
	private TableColumn<TutorEmpresa, String> ColConv;
	
	TutorEmpresa selectedTutorEmpresa;
	

	public void initialize() {
		
		ColNIF.setCellValueFactory(new PropertyValueFactory<TutorEmpresa, String>("NIF_TE"));
		ColNombre.setCellValueFactory(new PropertyValueFactory<TutorEmpresa,String>("nombre"));
		ColTelefono.setCellValueFactory(new PropertyValueFactory<TutorEmpresa,String>("telefono"));
		ColMail.setCellValueFactory(new PropertyValueFactory<TutorEmpresa,String>("email"));
		ColConv.setCellValueFactory(new PropertyValueFactory<TutorEmpresa, String>("nconv"));

	}
	
	public void filtrar(ActionEvent event) {
		
		ConexionBBDD mostrar = new ConexionBBDD();
		
		tutoresEmpresa.setItems(mostrar.consultaTE(nconvFiltro.getText()));
		
	}	
	
	public void seleccionTE(MouseEvent seleccion){
		
	this.selectedTutorEmpresa = tutoresEmpresa.getSelectionModel().getSelectedItem();
	
	nombre.setText(selectedTutorEmpresa.getNombre());
	NIF_TE.setText(selectedTutorEmpresa.getNIF_TE());
	telefono.setText(selectedTutorEmpresa.getTelefono());
	email.setText(selectedTutorEmpresa.getEmail());
	nconv.setText(selectedTutorEmpresa.getNconv());
	
	}
	
	public void modificarTutorE() throws SQLException {
        
		ConexionBBDD modificar = new ConexionBBDD();
		
		if(!NIF_TE.getText().equals("") && NIF_TE.getText() != null && !nombre.getText().equals("") && nombre.getText() != null && !telefono.getText().equals("") && telefono.getText() != null && !email.getText().equals("") && email.getText() != null && !nconv.getText().equals("") && nconv.getText() != null) {
			String niftexto = NIF_TE.getText();
			String nomtexto = nombre.getText();
			String teltexto = telefono.getText();
			String mailtexto = email.getText();
			String convtexto = nconv.getText();
			
			String NIFAnterior = this.selectedTutorEmpresa.getNIF_TE();

			modificar.modificarTutorE(niftexto, nomtexto, teltexto, mailtexto, convtexto, NIFAnterior);
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
		
		System.out.println("Ha entrado" + selectedTutorEmpresa.getNombre());
		
		NIF_TE.setText(selectedTutorEmpresa.getNIF_TE());
		nombre.setText(selectedTutorEmpresa.getNombre());
		telefono.setText(selectedTutorEmpresa.getTelefono());
		email.setText(selectedTutorEmpresa.getEmail());
		nconv.setText(selectedTutorEmpresa.getNconv());
		
	}
}
