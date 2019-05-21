package view.asignar;

import java.sql.SQLException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Asignar;
import model.Ciclo;
import model.ConexionBBDD;

public class ControladorModificacion {
	
	@FXML
	ImageView logo;

	@FXML
	Button modificar;
	
	@FXML 
	Button reestablecer;
	
	@FXML
	Button volver;
	
	@FXML
	TextField NIF_AL;
	
	@FXML
	TextField nconv;
	
	@FXML
	TextField NIF_TC;
	
	@FXML
	TextField NIF_TE;
	
	@FXML
	TextField fechaInicio;
	
	@FXML
	TextField fechaFin;
	
	@FXML
	TextField horasDia;
	
	@FXML
	TextField horasTotal;

	@FXML
	TextField horaInicioM;
	
	@FXML
	TextField horaFinM;
	
	@FXML
	TextField horaInicioT;
	
	@FXML
	TextField horaFinT;
	
	Asignar datosmodificar;
	
	Main controlador;
	Stage ventana;
	
	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}
	

	public void modificarAsignacion() throws SQLException {
		
		ConexionBBDD modificar = new ConexionBBDD();
        
		if(!NIF_AL.getText().equals("") && NIF_AL.getText() != null && !nconv.getText().equals("") && nconv.getText() != null && !NIF_TC.getText().equals("") && NIF_TE.getText() != null && !fechaInicio.getText().equals("") && fechaInicio.getText() != null && !fechaFin.getText().equals("") && fechaFin.getText() != null && !horasDia.getText().equals("") && horasDia.getText() != null && !horasTotal.getText().equals("") && horasTotal.getText() != null && !horaInicioM.getText().equals("") && horaInicioM.getText() != null && !horaFinM.getText().equals("") && horaFinM.getText() != null && !horaInicioT.getText().equals("") && horaInicioT.getText() != null && !horaFinT.getText().equals("") && horaFinT.getText() != null) {
			String nifatexto = NIF_AL.getText();
			String convtexto = nconv.getText();
			String niftctexto = NIF_TC.getText();
			String niftetexto = NIF_TE.getText();
			String fitexto = fechaInicio.getText();
			String fftexto = fechaFin.getText();
			String hdiatexto = horasDia.getText();
			String totaltexto = horasTotal.getText();
			String himtexto = horaInicioM.getText();
			String hfmtexto = horaFinM.getText();
			String hittexto = horaInicioT.getText();
			String hfttexto = horaFinT.getText();
			
			String AL_Anterior = datosmodificar.getNIF_AL();

			modificar.modificarPracticas(nifatexto, convtexto, niftctexto, niftetexto, fitexto, fftexto, hdiatexto, totaltexto, himtexto, hfmtexto, hittexto, hfttexto, AL_Anterior);
			
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Prácticas modificadas.");
			alert.setHeaderText(null);
			alert.setContentText("¡ Los datos modificados han sido registrados !");
			alert.showAndWait();
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
		
		NIF_AL.setText(datosmodificar.getNIF_AL());
		nconv.setText(datosmodificar.getNconv());
		NIF_TC.setText(datosmodificar.getNIF_TC());
		NIF_TE.setText(datosmodificar.getNIF_TE());
		fechaInicio.setText(datosmodificar.getFechaInicio());
		fechaFin.setText(datosmodificar.getFechaFin());
		horasDia.setText(datosmodificar.getHorasDia());
		horasTotal.setText(datosmodificar.getHorasTotales());
		horaInicioM.setText(datosmodificar.getHoraInicioM());
		horaFinM.setText(datosmodificar.getHoraFinM());
		horaInicioT.setText(datosmodificar.getHoraInicioT());
		horaFinT.setText(datosmodificar.getHoraFinT());
		
	}
	
	public void setDatos(Asignar datosmodificar) {
		
		this.datosmodificar = datosmodificar;
		 
		NIF_AL.setText(datosmodificar.getNIF_AL());
		nconv.setText(datosmodificar.getNconv());
		NIF_TC.setText(datosmodificar.getNIF_TC());
		NIF_TE.setText(datosmodificar.getNIF_TE());
		fechaInicio.setText(datosmodificar.getFechaInicio());
		fechaFin.setText(datosmodificar.getFechaFin());
		horasDia.setText(datosmodificar.getHorasDia());
		horasTotal.setText(datosmodificar.getHorasTotales());
		horaInicioM.setText(datosmodificar.getHoraInicioM());
		horaFinM.setText(datosmodificar.getHoraFinM());
		horaInicioT.setText(datosmodificar.getHoraInicioT());
		horaFinT.setText(datosmodificar.getHoraFinT());
		
	}
	
	public void volver(ActionEvent event) {
		controlador.asignarVent();
	}
}
