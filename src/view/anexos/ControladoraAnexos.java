package view.anexos;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.AnexoI;
import model.Centro;
import model.ConexionBBDD;
import model.DatosColegio;
import model.DatosEmpresa;
import model.Empresa;

public class ControladoraAnexos {

	@FXML
	Button generar;
	
	@FXML
	Button volver;
	
	@FXML
	TextField nombreAnexo;
	
	@FXML
	TextField nconv;
	
	@FXML
	RadioButton anex1;
	
	@FXML
	RadioButton anex2;
	
	@FXML
	RadioButton anex3;
	
	@FXML
	RadioButton anex4;
	
	@FXML
	RadioButton todos;
	
	Empresa empresaAnex;
	Centro centroAnex;
	
	
	public void generar() throws SQLException, FileNotFoundException, DocumentException  {
				
		if (!nombreAnexo.getText().equals("") && nombreAnexo.getText() != null) {
		
			if (!nconv.getText().equals("") && nconv.getText() != null) {
				
				ConexionBBDD datos = new ConexionBBDD();
				
				centroAnex = datos.anexoCentro();
				empresaAnex = datos.anexoEmpresa(nconv.getText());
				DatosColegio coledata = new DatosColegio(centroAnex.getTitular(),centroAnex.getNIF_TIT(),centroAnex.getNombre(),centroAnex.getCod_centro(),centroAnex.getProvincia(),centroAnex.getDireccion(),centroAnex.getCp(),centroAnex.getCIF(),centroAnex.getTelefono(),centroAnex.getFax(),centroAnex.getCiudad());
				DatosEmpresa empdata = new DatosEmpresa(empresaAnex.getRepresentante(), empresaAnex.getNIF_REP(),empresaAnex.getNombre(),empresaAnex.getnConv(),empresaAnex.getProvincia(),empresaAnex.getDireccion(),empresaAnex.getCp(),empresaAnex.getCIF(),empresaAnex.getTelefono(),empresaAnex.getFax(),empresaAnex.getCiudad());
			
				AnexoI anexoI = new AnexoI(coledata, empdata);

				anexoI.generarAnexoI(nombreAnexo.getText(),""); 
			}	
		
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Mensaje de error");
				alert.setHeaderText("¡ Campo vacío !");
				alert.setContentText("Por favor, introduzca el número de convenio.");
				alert.showAndWait();
			} 
		}
		
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Mensaje de error");
			alert.setHeaderText("¡ Campo vacío !");
			alert.setContentText("Por favor, introduzca el nombre del anexo.");
			alert.showAndWait();
		}
	}
}
