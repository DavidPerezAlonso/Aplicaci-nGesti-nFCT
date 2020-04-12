package view.alumnos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Alumno;
import model.AlumnoExcel;
import model.Ciclo;
import model.ConexionBBDD;
import model.ConsultasBBDD;
import model.LeerFicherosExcel;

public class ControladorAlumno {

	@FXML
	ImageView logo;

	@FXML
	Button nuevo;

	@FXML
	Button modificar;

	@FXML
	Button filtrar;

	@FXML
	Button volver;

	@FXML
	Button btn_importar;

	@FXML
	ComboBox<String> cmb_curso;

	@FXML
	ComboBox<String> cmb_ciclo;

	@FXML
	TextField empresa;

	@FXML
	TextField tutor;

	@FXML
	TableView<Alumno> alumno;

	@FXML
	private TableColumn<Alumno, String> ColNIF;

	@FXML
	private TableColumn<Alumno, String> ColNombre;

	@FXML
	private TableColumn<Alumno, String> ColApellidos;

	@FXML
	private TableColumn<Alumno, String> ColDireccion;

	@FXML
	private TableColumn<Alumno, String> ColCiudad;

	@FXML
	private TableColumn<Alumno, String> ColCP;

	@FXML
	private TableColumn<Alumno, String> ColProvincia;

	@FXML
	private TableColumn<Alumno, String> ColTelefono;

	@FXML
	private TableColumn<Alumno, String> ColMail;

	@FXML
	TextField nifAlumno;

	@FXML
	Button buscar;

	Main controlador;

	Stage ventana;

	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}


	public void initialize() {

		ConsultasBBDD filtrado = new ConsultasBBDD();

		// Inicializa el combobox ciclos
		ObservableList <Ciclo> ciclos = filtrado.buscarCiclo("");

		ObservableList<String> options = FXCollections.observableArrayList();
		for(Ciclo c: ciclos){
			options.add(c.getNombre());
		}
		cmb_ciclo.setItems(options);

		// Inicializa el combobox cursos
		ObservableList <String> cursos = filtrado.buscarCursos();
		cmb_curso.setItems(cursos);


		alumno.setItems(filtrado.filtroAlumnos("", "", empresa.getText(), tutor.getText()));

		ColNIF.setCellValueFactory(new PropertyValueFactory<Alumno, String>("NIF"));
		ColNombre.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
		ColApellidos.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellidos"));
		ColDireccion.setCellValueFactory(new PropertyValueFactory<Alumno,String>("direccion"));
		ColCiudad.setCellValueFactory(new PropertyValueFactory<Alumno,String>("ciudad"));
		ColCP.setCellValueFactory(new PropertyValueFactory<Alumno,String>("cp"));
		ColProvincia.setCellValueFactory(new PropertyValueFactory<Alumno,String>("provincia"));
		ColTelefono.setCellValueFactory(new PropertyValueFactory<Alumno,String>("telefono"));
		ColMail.setCellValueFactory(new PropertyValueFactory<Alumno,String>("email"));

	}


public void filtrar(ActionEvent event) {

	ConsultasBBDD filtrado = new ConsultasBBDD();

	String ciclo = "";
	String curso = "";

	if(cmb_curso.getSelectionModel().getSelectedIndex() >= 0)
		curso = cmb_curso.getSelectionModel().getSelectedItem();

	if(cmb_ciclo.getSelectionModel().getSelectedIndex() >= 0)
		ciclo = cmb_ciclo.getSelectionModel().getSelectedItem();

	alumno.setItems(filtrado.filtroAlumnos(curso, ciclo, empresa.getText(), tutor.getText()));

}

public void buscar(ActionEvent event) {

	ConsultasBBDD buscar = new ConsultasBBDD();

	alumno.setItems(buscar.buscarAlumno(nifAlumno.getText()));

}

public void crearAlumno(ActionEvent event) throws IOException{

	controlador.alumnoCrearVent();

}

public void modificarAlumno(ActionEvent event) throws IOException{

	Alumno selectedAlumno = alumno.getSelectionModel().getSelectedItem();

	if (selectedAlumno != null) {

		controlador.alumnoModVent(selectedAlumno);
    }

    else
    {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Mensaje de error");
    	alert.setHeaderText("¡ Alumno no seleccionado !");
    	alert.setContentText("Por favor, seleccione un alumno a modificar de la tabla.");
    	alert.showAndWait();
    }

}

public void volver(ActionEvent event) {
	controlador.menuVent();
}


/* ------------- Método que realiza la acción de importar alumnos de un fichero de excel -----------
 * Lee los alumnos del fichero de excel
 * Inserta los alumnos en la base de datos
 * Muestra un mensaje con los alumnos insertados */
public void importar() throws InvalidFormatException, IOException, SQLException{

	ArrayList<AlumnoExcel> listaAlumnos = new ArrayList<AlumnoExcel>();

	// Realiza la lectura del  fichero excel y crea una lista de alumnos
	LeerFicherosExcel excel = new LeerFicherosExcel();
	listaAlumnos = excel.ImportarAlumnos();

	int alumnosexcel = listaAlumnos.size();

	if(alumnosexcel > 0){

		ConexionBBDD con = new ConexionBBDD();
		int contador = 0;

		for(AlumnoExcel ae: listaAlumnos){

			System.out.println(ae.toString());
			int resultado = con.insertarAlumno(ae.getNIF(), ae.getNombre(), ae.getApellidos(), ae.getDireccion(), ae.getCiudad(), ae.getCp(), ae.getProvincia(), ae.getTelefono(), ae.getEmail(), ae.getCiclo(), ae.getCurso());
			if( resultado == 1)
				contador++;
			else{

				/* Se genera un mensaje de error diferente para las situaciones de posibles fallos en la inserción
				 -1 ya existe un alumno con ese NIF
				 * -2 Ha habido algún problema en la inserción con la base de datos
				 * -3 El alumno está insertado pero no está "matriculado" */
				Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Error");
		    	alert.setHeaderText("Error en Importación");
		    	switch(resultado){

		    	case -1:
		    		alert.setContentText("Ya existe un alumno con DNI: " + ae.getNIF());
		    		break;

		    	case -2:
		    		alert.setContentText("Ha habido algún problema en la inserción del alumno con DNI: " + ae.getNIF());
		    		break;

		    	case -3:
		    		alert.setContentText("El alumno con DNI: " + ae.getNIF() + " se ha grabado pero no matriculado");
		    		break;

		    	}

		    	alert.showAndWait();
			}

		}

		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informacion");
    	alert.setHeaderText("Importación completada");
    	alert.setContentText("Se han  insertado " + contador + " alumnos en la base de datos");
    	alert.showAndWait();

    	ConsultasBBDD filtrado = new ConsultasBBDD();
    	alumno.setItems(filtrado.filtroAlumnos("", "", empresa.getText(), tutor.getText()));


	}
	else{

		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error en Importación");
    	alert.setHeaderText("¡ Fichero vacío !");
    	alert.setContentText("Por favor, el fichero excel está vacío o hay algún  error en formato.");
    	alert.showAndWait();
	}


}

}
