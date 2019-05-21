package view.anexos;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import controller.Main;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Alumno;
import model.AnexoI;
import model.AnexoII;
import model.Asignar;
import model.Centro;
import model.Ciclo;
import model.ConexionBBDD;
import model.DatosAlumnos;
import model.DatosColegio;
import model.DatosEmpresa;
import model.Empresa;
import model.TutorCentro;
import model.TutorEmpresa;

public class ControladoraAnexos {
	
	@FXML
	ImageView logo;

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
	Text TextoEmpresa;
	
	@FXML
	Text TextoTC;
	
	@FXML
	Text TextoTE;
	
	@FXML
	TextField nombreAnexo;
	
	@FXML
	RadioButton anex1;
	
	@FXML
	RadioButton anex2;
	
	@FXML
	RadioButton anex3;
	
	@FXML
	RadioButton anex4;
	
	@FXML
	RadioButton anex5;
	
	@FXML
	RadioButton anex6;
	
	@FXML
	RadioButton anex7;
	
	@FXML
	RadioButton anex8;
	
	@FXML
	RadioButton anex9;
	
	@FXML
	RadioButton todos;
	
	@FXML
	ToggleGroup anexos;
	
	Alumno selectedAlumno;
	Centro centroAnex;
	Asignar asigAnex;
	Empresa empresaAnex;
	TutorCentro tutorAnex;
	TutorEmpresa tutorEmpAnex;
	Ciclo cicloAnex;

	String ciclo;
	String curso;
	
	Main controlador;
	Stage ventana;
	
	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}
	
	
	
	public void initialize() {
		
		ConexionBBDD listar = new ConexionBBDD();
		
		ciclos.setItems(listar.anexoCicloNom());
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
	
	public void mostrarDatos() {
		
		selectedAlumno = alumnos.getSelectionModel().getSelectedItem();
		
		ConexionBBDD datos = new ConexionBBDD();
		
		centroAnex = datos.anexoCentro();
		asigAnex = datos.consultaAsig(selectedAlumno.getNIF());
		empresaAnex = datos.anexoEmpresa(asigAnex.getNconv());
		tutorAnex = datos.anexoTC(asigAnex.getNIF_TC());
		tutorEmpAnex = datos.anexoTE(asigAnex.getNIF_TE());
		cicloAnex = datos.anexoCiclo(ciclo);
		
		TextoEmpresa.setText(empresaAnex.getNombre());
		TextoTC.setText(tutorAnex.getNombre());
		TextoTE.setText(tutorEmpAnex.getNombre());
		
	}
	
	public void generar() throws SQLException, FileNotFoundException, DocumentException  {
			
		
		if (!nombreAnexo.getText().equals("") && nombreAnexo.getText() != null) {
		
			if (selectedAlumno != null) {
				

				
				
				DatosColegio coledata = new DatosColegio(centroAnex.getTitular(),centroAnex.getNIF_TIT(),centroAnex.getNombre(),centroAnex.getCod_centro(),centroAnex.getProvincia(),centroAnex.getDireccion(),centroAnex.getCp(),centroAnex.getCIF(),centroAnex.getTelefono(),centroAnex.getFax(),centroAnex.getCiudad(), centroAnex.getDAT());
				DatosEmpresa empdata = new DatosEmpresa(empresaAnex.getRepresentante(), empresaAnex.getNIF_REP(),empresaAnex.getNombre(),empresaAnex.getnConv(),empresaAnex.getProvincia(),empresaAnex.getDireccion(),empresaAnex.getCp(),empresaAnex.getCIF(),empresaAnex.getTelefono(),empresaAnex.getFax(),empresaAnex.getCiudad());
				DatosAlumnos alumdata = new DatosAlumnos(curso, cicloAnex.getClave(), ciclo,asigAnex.getFechaInicio(), asigAnex.getFechaFin(),"5", asigAnex.getHoraInicioM(),asigAnex.getHoraFinM(), asigAnex.getHoraInicioT() ,asigAnex.getHoraFinT(), Integer.parseInt(asigAnex.getHorasDia()), Integer.parseInt(asigAnex.getHorasTotales()), empresaAnex.getCiudad(), empresaAnex.getDireccion(), selectedAlumno.getNombre(), selectedAlumno.getApellidos(), selectedAlumno.getNIF(), tutorAnex.getNombre(), tutorAnex.getNIF_TC(), tutorEmpAnex.getNombre());
				
				if (anex1.isSelected()) {
					
					AnexoI anexoI = new AnexoI(coledata, empdata);
					anexoI.generarAnexoI(nombreAnexo.getText() + "(Anexo 1)","");
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Anexo generado.");
					alert.setHeaderText(null);
					alert.setContentText("¡ El anexo " + nombreAnexo.getText() + "(" + anex1.getText() + ") se ha generado correctamente !");
					alert.showAndWait();
				}
				
					else if (anex2.isSelected()){
						AnexoII anexoII = new AnexoII(coledata, empdata, alumdata);
						anexoII.generarAnexoII(nombreAnexo.getText() + "(Anexo 2)","");
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Anexo generado.");
						alert.setHeaderText(null);
						alert.setContentText("¡ El anexo " + nombreAnexo.getText() + "(" + anex2.getText() + ") se ha generado correctamente !");
						alert.showAndWait();
					}
				
						else if (todos.isSelected()) {
								AnexoI anexoI = new AnexoI(coledata, empdata);
								AnexoII anexoII = new AnexoII(coledata, empdata, alumdata);
								anexoI.generarAnexoI(nombreAnexo.getText() + "(Anexo 1)","");
								anexoII.generarAnexoII(nombreAnexo.getText() + "(Anexo 2)","");
								
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Anexos generados.");
								alert.setHeaderText(null);
								alert.setContentText("¡ Todos los anexos se han generado correctamente !");
								alert.showAndWait();
						}
						
						else {
							
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Mensaje de error");
							alert.setHeaderText("¡ Anexo no disponible !");
							alert.setContentText("El anexo seleccionado no se encuentra aún disponible. Por favor seleccione un nuevo anexo.");
							alert.showAndWait();
						}
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
	
	public void volver(ActionEvent event) {
		controlador.menuVent();
	}
}
