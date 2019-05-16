package view.anexos;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Alumno;
import model.AnexoI;
import model.Asignar;
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
	ChoiceBox<String> ciclos;
	
	@FXML 
	ChoiceBox<String> cursos;
	
	@FXML
	Button filtrar;
	
	@FXML
	TableView<Alumno> alumnos;
	
	@FXML
	TableColumn<Alumno, String> ColNIF;
	
	@FXML
	TableColumn<Alumno, String> ColNom;
	
	@FXML
	TableColumn<Alumno, String> ColApe;
	
	@FXML
	Text textoEmpresa;
	
	@FXML
	Text textoTE;
	
	@FXML
	Text textoTC;
	
	@FXML
	TextField nombreAnexo;
	
	@FXML
	RadioButton anex1;
	
	@FXML
	RadioButton anex2;
	
	@FXML
	RadioButton todos;
	
	Empresa empresaAnex;
	Centro centroAnex;

	String ciclo;
	String curso;
	
	
	public void initialize() {
		
		ConexionBBDD listar = new ConexionBBDD();
		
		ciclos.setItems(listar.anexoCiclo());
		cursos.setItems(listar.anexoCurso());
		
		ColNIF.setCellValueFactory(new PropertyValueFactory<Alumno, String>("NIF"));
		ColNom.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
		ColApe.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellidos"));
		
	}
	
	public void filtrar(ActionEvent event) {
		
		ConexionBBDD mostrar = new ConexionBBDD();

		ciclo = ciclos.getSelectionModel().getSelectedItem();
		curso = cursos.getSelectionModel().getSelectedItem();
		
		if (ciclo != null || curso != null)	{
			
			if (ciclo != null) {
			
				if (curso != null)
					alumnos.setItems(mostrar.AnexoAlumno(ciclo, curso));
				
					else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Mensaje de error");
						alert.setHeaderText("¡ Curso no seleccionado !");
						alert.setContentText("Por favor, seleccione un curso para filtrar.");
						alert.showAndWait();
					}
			}
		
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Mensaje de error");
					alert.setHeaderText("¡ Ciclo no seleccionado !");
					alert.setContentText("Por favor, seleccione un ciclo para filtrar.");
					alert.showAndWait();
					}
		}
		
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Mensaje de error");
				alert.setHeaderText("¡ Opciones no seleccionadas !");
				alert.setContentText("Por favor, seleccione un ciclo y un curso para filtrar.");
				alert.showAndWait();
			}
		
	}
	
	public void generar() throws SQLException, FileNotFoundException, DocumentException  {
				
		Alumno selectedAlumno = alumnos.getSelectionModel().getSelectedItem();
		ObservableList<Asignar> asigAnex = FXCollections.observableArrayList();
		
		if (!nombreAnexo.getText().equals("") && nombreAnexo.getText() != null) {
		
			if (selectedAlumno != null) {
				
				ConexionBBDD datos = new ConexionBBDD();
				
				centroAnex = datos.anexoCentro();
				asigAnex = datos.consultaAsig(selectedAlumno.getNIF());
				empresaAnex = datos.anexoEmpresa(nconv.getText());
				DatosColegio coledata = new DatosColegio(centroAnex.getTitular(),centroAnex.getNIF_TIT(),centroAnex.getNombre(),centroAnex.getCod_centro(),centroAnex.getProvincia(),centroAnex.getDireccion(),centroAnex.getCp(),centroAnex.getCIF(),centroAnex.getTelefono(),centroAnex.getFax(),centroAnex.getCiudad());
				DatosEmpresa empdata = new DatosEmpresa(empresaAnex.getRepresentante(), empresaAnex.getNIF_REP(),empresaAnex.getNombre(),empresaAnex.getnConv(),empresaAnex.getProvincia(),empresaAnex.getDireccion(),empresaAnex.getCp(),empresaAnex.getCIF(),empresaAnex.getTelefono(),empresaAnex.getFax(),empresaAnex.getCiudad());
			
				AnexoI anexoI = new AnexoI(coledata, empdata);

				anexoI.generarAnexoI(nombreAnexo.getText(),""); 
			}	
		
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Mensaje de error");
				alert.setHeaderText("¡ Alumno no seleccionado !");
				alert.setContentText("Por favor, seleccione un alumno de la tabla.");
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
