package view.asignar;

import java.io.IOException;
import java.sql.SQLException;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Asignar;
import model.ConexionBBDD;

public class ControladorAsignar {
	
	@FXML
	Button asignar;
	
	@FXML 
	Button limpiar;
	
	@FXML
	Button consultar;
	
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
	
	@FXML
	TextField NIF_Consulta;
	
public void asignar() throws SQLException {
        
		ConexionBBDD insertar = new ConexionBBDD();
		
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

			insertar.asignarPracticas(fitexto, fftexto, hdiatexto, totaltexto, himtexto, hfmtexto, hittexto, hfttexto, nifatexto, niftctexto, niftetexto, convtexto);
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
		
		NIF_AL.setText("");
		nconv.setText("");
		NIF_TC.setText("");
		NIF_TE.setText("");
		fechaInicio.setText("");
		fechaFin.setText("");
		horasDia.setText("");
		horasTotal.setText("");
		horaInicioM.setText("");
		horaFinM.setText("");
		horaInicioT.setText("");
		horaFinT.setText("");
	
	}
	
	
	public void consultaAsig(ActionEvent event) throws IOException{
		
		ConexionBBDD mostrar = new ConexionBBDD();
		
		ObservableList<Asignar> datosConsulta = mostrar.consultaAsig(NIF_Consulta.getText());
		
		Asignar selectedAsig = datosConsulta.get(0);
	
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/asignar/ModificarAsignacion.fxml"));
	    GridPane ventanaDos = (GridPane) loader.load();
	    Stage ventana = new Stage();
	    ventana.setTitle("ModificarAsignacion");
	    Scene scene = new Scene(ventanaDos);
	    
	    ControladorModificacion controladoraVentana2 = loader.getController();
	    controladoraVentana2.setDatos(selectedAsig);
		    
	    ventana.setScene(scene);
	    ventana.show();
  }
	
	
}
