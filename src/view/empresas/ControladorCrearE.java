package view.empresas;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.ConexionBBDD;

public class ControladorCrearE {
	
	@FXML
	Button crear;
	
	@FXML
	Button limpiar;
	
	@FXML 
	Button volver;
	
	@FXML 
	TextField n_conv;
	
	@FXML 
	TextField nombre;
	
	@FXML
	TextField CIF;
	
	@FXML 
	TextField representante;
	
	@FXML 
	TextField NIF_REP;
	
	@FXML 
	TextField direccion;
	
	@FXML 
	TextField cp;
	
	@FXML 
	TextField ciudad;
	
	@FXML 
	TextField provincia;
	
	@FXML 
	TextField pais;
	
	@FXML 
	TextField telefono;
	
	@FXML 
	TextField fax;
	
	@FXML 
	TextField fecha_conv;
	
	public void crearEmpresa() throws SQLException {
        
		ConexionBBDD insertar = new ConexionBBDD();
		
		if(!n_conv.getText().equals("") && n_conv.getText() != null && !representante.getText().equals("") && representante.getText() != null && !NIF_REP.getText().equals("") && NIF_REP.getText() != null && !CIF.getText().equals("") && CIF.getText() != null && !nombre.getText().equals("") && nombre.getText() != null && !direccion.getText().equals("") && direccion.getText() != null && !cp.getText().equals("") && cp.getText() != null && !ciudad.getText().equals("") && ciudad.getText() != null  && !provincia.getText().equals("") && provincia.getText() != null && !pais.getText().equals("") && pais.getText() != null && !telefono.getText().equals("") && telefono.getText() != null && !fax.getText().equals("") && fax.getText() != null && !fecha_conv.getText().equals("") && fecha_conv.getText() != null) {
		String convtexto = n_conv.getText();
		String reptexto = representante.getText();
        String nifreptexto = NIF_REP.getText();
        String ciftexto = CIF.getText();
        String nomtexto = nombre.getText();
        String dirtexto = direccion.getText();
        String cptexto = cp.getText();
        String ciutexto = ciudad.getText();
        String provtexto = provincia.getText();
        String paistexto = pais.getText();
        String teltexto = telefono.getText();
        String faxtexto = fax.getText();
        String fechatexto = fecha_conv.getText();
        
        insertar.insertarEmpresa(convtexto, reptexto, nifreptexto, ciftexto, nomtexto, dirtexto, cptexto, ciutexto, provtexto, paistexto, teltexto, faxtexto, fechatexto);
        
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
		
		n_conv.setText("");
		representante.setText("");
		NIF_REP.setText("");
		CIF.setText("");
		nombre.setText("");
		direccion.setText("");
		cp.setText("");
		ciudad.setText("");
		provincia.setText("");
		pais.setText("");
		telefono.setText("");
		fax.setText("");
		fecha_conv.setText("");
		
	}	

}
