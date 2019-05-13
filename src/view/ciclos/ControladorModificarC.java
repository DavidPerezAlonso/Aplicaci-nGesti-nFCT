package view.ciclos;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Alumno;
import model.Ciclo;
import model.ConexionBBDD;

public class ControladorModificarC {

	@FXML
	Button modificar;
	
	@FXML
	Button restablecer;
	
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
    

	public void modificarCiclo() throws SQLException {
		
		ConexionBBDD modificar = new ConexionBBDD();
        
		if(!clave.getText().equals("") && clave.getText() != null && !nombre.getText().equals("") && nombre.getText() != null && !familia.getText().equals("") && familia.getText() != null && !clave_fam.getText().equals("") && clave_fam.getText() != null && !capacidades.getText().equals("") && capacidades.getText() != null && !actividades.getText().equals("") && actividades.getText() != null && !criterios.getText().equals("") && criterios.getText() != null) {
		String clavetexto = clave.getText();
        String nomtexto = nombre.getText();
        String famtexto = familia.getText();
        String cftexto = clave_fam.getText();
        String captexto = capacidades.getText();
        String acttexto = actividades.getText();
        String crittexto = criterios.getText();
        
        modificar.modificarCiclo(clavetexto, nomtexto, famtexto, cftexto, captexto, acttexto, crittexto);
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
		
		clave.setText(datosmodificar.getClave());
		nombre.setText(datosmodificar.getNombre());
		familia.setText(datosmodificar.getFamilia());
		clave_fam.setText(datosmodificar.getClave_fam());
		capacidades.setText(datosmodificar.getCapacidades());
		actividades.setText(datosmodificar.getActividades());
		criterios.setText(datosmodificar.getCriterios());

		
	}
	
	public void setDatos(Ciclo datosmodificar) {
		
		this.datosmodificar = datosmodificar;
		 
		clave.setText(datosmodificar.getClave());
		nombre.setText(datosmodificar.getNombre());
		familia.setText(datosmodificar.getFamilia());
		clave_fam.setText(datosmodificar.getClave_fam());
		capacidades.setText(datosmodificar.getCapacidades());
		actividades.setText(datosmodificar.getActividades());
		criterios.setText(datosmodificar.getCriterios());
		
	}
}
