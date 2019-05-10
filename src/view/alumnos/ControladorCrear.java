package view.alumnos;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.ConexionBBDD;

public class ControladorCrear {

	@FXML
	Button crear;
	
	@FXML
	Button limpiar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField NIF;
	
	@FXML
	TextField nombre;
	
	@FXML
	TextField apellidos;
	
	@FXML
	TextField direccion;
	
	@FXML
	TextField ciudad;
	
	@FXML
	TextField cp;
	
	@FXML
	TextField provincia;
	
	@FXML
	TextField telefono;
	
	@FXML
	TextField email;
	
	
	public void crearAlumno() throws SQLException {
		ConexionBBDD insertar = new ConexionBBDD();
        
		String niftexto = NIF.getText();
        String nomtexto = nombre.getText();
        String apetexto = apellidos.getText();
        String dirtexto = direccion.getText();
        String ciutexto = ciudad.getText();
        String cptexto = cp.getText();
        String provtexto = provincia.getText();
        String teltexto = telefono.getText();
        String mailtexto = email.getText();
        
        String tabla = "ALUMNOS";
        
        insertar.insertarAlumno(niftexto, nomtexto, apetexto, dirtexto, ciutexto, cptexto, provtexto, teltexto, mailtexto, tabla);
        
	}
	
	
	public void limpiarTexto() {
		
		NIF.setText("");
		nombre.setText("");
		apellidos.setText("");
		direccion.setText("");
		ciudad.setText("");
		cp.setText("");
		provincia.setText("");
		telefono.setText("");
		email.setText("");
		
	}
	
}
