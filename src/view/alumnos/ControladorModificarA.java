package view.alumnos;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Alumno;
import model.ConexionBBDD;

public class ControladorModificarA {
	
	@FXML
	Button modificar;
	
	@FXML
	Button restablecer;
	
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
	
	Alumno datosmodificar;
    

	public void modificarAlumno() throws SQLException {
        
		String niftexto = NIF.getText();
        String nomtexto = nombre.getText();
        String apetexto = apellidos.getText();
        String dirtexto = direccion.getText();
        String ciutexto = ciudad.getText();
        String cptexto = cp.getText();
        String provtexto = provincia.getText();
        String teltexto = telefono.getText();
        String mailtexto = email.getText();
        
        ConexionBBDD.modificarAlumno(niftexto, nomtexto, apetexto, dirtexto, ciutexto, cptexto, provtexto, teltexto, mailtexto);
        
	}
	
	public void reestablecer() {
		
		NIF.setText(datosmodificar.getNIF());
		nombre.setText(datosmodificar.getNombre());
		apellidos.setText(datosmodificar.getApellidos());
		direccion.setText(datosmodificar.getDireccion());
		ciudad.setText(datosmodificar.getCiudad());
		cp.setText(datosmodificar.getCp());
		provincia.setText(datosmodificar.getProvincia());
		telefono.setText(datosmodificar.getTelefono());
		email.setText(datosmodificar.getEmail());
		
	}
	
	public void setDatos(Alumno datosmodificar) {
		 
		NIF.setText(datosmodificar.getNIF());
		nombre.setText(datosmodificar.getNombre());
		apellidos.setText(datosmodificar.getApellidos());
		direccion.setText(datosmodificar.getDireccion());
		ciudad.setText(datosmodificar.getCiudad());
		cp.setText(datosmodificar.getCp());
		provincia.setText(datosmodificar.getProvincia());
		telefono.setText(datosmodificar.getTelefono());
		email.setText(datosmodificar.getEmail());
		
	}
}
