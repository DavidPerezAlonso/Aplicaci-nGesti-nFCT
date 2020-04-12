package view.asignar;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Alumno;
import model.Asignar;
import model.Ciclo;
import model.ConexionBBDD;
import model.ConsultasBBDD;
import model.Empresa;
import model.TutorCentro;
import model.TutorEmpresa;

public class ControladorAsignar2 {

	@FXML
	private ImageView logo;

	@FXML
	private Button asignar;

	@FXML
	private Button limpiar;

	@FXML
	private Button consultar;

	@FXML
	private Button volver;


	@FXML
	private ComboBox<String> cmb_ciclo;

	@FXML
	private ComboBox<String> cmb_curso;

	@FXML
	private TableView<Alumno> tab_alumno;

	@FXML
	private TableColumn<Alumno, String> col_NIFalumno;

	@FXML
	private TableColumn<Alumno, String> col_Nombrealumno;

	@FXML
	private TableColumn<Alumno, String> col_Apellidoalumno;

	@FXML
	private TableView<Empresa> tab_empresa;

	@FXML
	private TableColumn<Empresa, String> col_CIFEmpresa;

	@FXML
	private TableColumn<Empresa, String> col_NConvEmpresa;

	@FXML
	private TableColumn<Empresa, String> col_NombreEmpresa;

	@FXML
	private TableView<TutorCentro> tab_TutorCentro;

	@FXML
	private TableColumn<TutorCentro, String> col_NombreTC;
	@FXML
	private TableColumn<TutorCentro, String> col_NIFTC;

	@FXML
	private TableView<TutorEmpresa> tab_TutorEmpresa;

	@FXML
	private TableColumn<TutorEmpresa, String> col_NombreTE;

	@FXML
	private TableColumn<TutorEmpresa, String> col_NIFTE;


	@FXML
	private DatePicker fechaInicio;

	@FXML
	private DatePicker fechaFin;

	@FXML
	private TextField horasDia;

	@FXML
	private TextField horasTotal;

	@FXML
	private TextField horaInicioM;

	@FXML
	private TextField horaFinM;

	@FXML
	private TextField horaInicioT;

	@FXML
	private TextField horaFinT;

	@FXML
	private TextField NIF_Consulta;

	Main controlador;
	Stage ventana;





	public void setVentana(Main controlador, Stage ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
	}


	// Método que inicializa la pantalla...Tiene que cargar los combobox y todos los alumnos y empresas
	public void initialize(){

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


		// Inicializa tabla Alumnos
		tab_alumno.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE);
		tab_alumno.setItems(filtrado.filtroAlumnos("", "", "", ""));

		col_NIFalumno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("NIF"));
		col_Nombrealumno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
		col_Apellidoalumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellidos"));


		// Inicializa tabla Empresa
		tab_empresa.setItems(filtrado.filtroEmpresa("", "", ""));

		col_CIFEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("CIF"));
		col_NConvEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nConv"));
		col_NombreEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa,String>("nombre"));

		// Inicializa tabla TutorCentro
		ObservableList<TutorCentro> tutores = filtrado.filtroTutor("", "");
		tab_TutorCentro.setItems(tutores);

		col_NombreTC.setCellValueFactory(new PropertyValueFactory<TutorCentro, String>("nombre"));
		col_NIFTC.setCellValueFactory(new PropertyValueFactory<TutorCentro, String>("NIF_TC"));

	}


public void asignar() throws SQLException {

		ConexionBBDD insertar = new ConexionBBDD();


		String nconv = "";
		String NIF_TC = "";
		String NIF_TE = "";

		ObservableList<Alumno> alumnos = tab_alumno.getSelectionModel().getSelectedItems();

		nconv = tab_empresa.getSelectionModel().getSelectedItem().getnConv();
		NIF_TC = tab_TutorCentro.getSelectionModel().getSelectedItem().getNIF_TC();
		NIF_TE = tab_TutorEmpresa.getSelectionModel().getSelectedItem().getNIF_TE();



		if(alumnos.size() > 0 && !nconv.equals("") && !NIF_TC.equals("") && !NIF_TE.equals("") && !fechaInicio.getValue().equals("") && fechaInicio.getValue() != null && !fechaFin.getValue().equals("") && fechaFin.getValue() != null && !horasDia.getText().equals("") && horasDia.getText() != null && !horasTotal.getText().equals("") && horasTotal.getText() != null) {

			// Comprueba que la fecha fin es posterior a la de inicio
			if(ChequearFecha() == false)
				return;

			String hdiatexto = horasDia.getText();
			// Comprueba que las horas sean 8h. se muestra un  mensaje de aviso
			if(Integer.valueOf(hdiatexto) > 8){
				if( !MensajedeConfirmacion("Se recomienda que las FCTs duren un máximo de 8 horas/día y has introducido más, ¿correcto?"))
					return;
			}

			String totaltexto = horasTotal.getText();
			if(Integer.valueOf(totaltexto) > 370){
				if( !MensajedeConfirmacion("El módulo de FCTs es de 370 horas y has introducido más, ¿correcto?"))
					return;
			}

			String convtexto = nconv;
			String niftctexto = NIF_TC;
			String niftetexto = NIF_TE;
			DateTimeFormatter isoFecha = DateTimeFormatter.ISO_LOCAL_DATE;
			String fitexto = fechaInicio.getValue().format(isoFecha);
			String fftexto = fechaFin.getValue().format(isoFecha);

			String himtexto = horaInicioM.getText();
			String hfmtexto = horaFinM.getText();
			String hittexto = horaInicioT.getText();
			String hfttexto = horaFinT.getText();

			for(Alumno a: alumnos){
				String nifatexto = a.getNIF();
				insertar.asignarPracticas(fitexto, fftexto, hdiatexto, totaltexto, himtexto, hfmtexto, hittexto, hfttexto, nifatexto, niftctexto, niftetexto, convtexto);

			}
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Practicas asignadas.");
			alert.setHeaderText(null);
			alert.setContentText("¡ La asignación de prácticas se ha registrado correctamente para los "+ alumnos.size() +" alumnos !");
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

	public void limpiarTexto() {


		tab_alumno.getSelectionModel().clearSelection();
		tab_empresa.getSelectionModel().clearSelection();
		tab_TutorEmpresa.getSelectionModel().clearSelection();
		tab_TutorCentro.getSelectionModel().clearSelection();
		fechaInicio.setValue(null);
		fechaFin.setValue(null);
		horasDia.setText("");
		horasTotal.setText("");
		horaInicioM.setText("");
		horaFinM.setText("");
		horaInicioT.setText("");
		horaFinT.setText("");

	}


	public void consultaAsig(ActionEvent event) throws IOException{

		ConexionBBDD mostrar = new ConexionBBDD();

		if(!NIF_Consulta.getText().equals("") && NIF_Consulta != null) {

			Asignar datosConsulta = mostrar.consultaAsig(NIF_Consulta.getText());

			controlador.asignarModVent(datosConsulta);
		}
			else {

				Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Mensaje de error");
			   	alert.setHeaderText("¡ Campo de NIF vacío !");
			   	alert.setContentText("Por favor, introduzca un NIF para consultar o modificar su asignación.");
			   	alert.showAndWait();
			}
  }

	public void volver(ActionEvent event) {
		controlador.menuVent();
	}




	public void MostrarTutorEmpresa(){

			ConsultasBBDD con = new ConsultasBBDD();
			String nConv =tab_empresa.getSelectionModel().getSelectedItem().getnConv();

			ObservableList<TutorEmpresa> tutores = con.buscarTutorEmpresa(nConv);
			if(tutores.size() == 0){
				Alert alert = new Alert(AlertType.WARNING);
			    alert.setTitle("Aviso!");
			   	alert.setHeaderText("¡ Tutores de Empresa !");
			   	alert.setContentText("La empresa seleccionada no tiene tutores dados de alta");
			   	alert.showAndWait();
			}
			tab_TutorEmpresa.setItems(tutores);

			col_NombreTE.setCellValueFactory(new PropertyValueFactory<TutorEmpresa, String>("nombre"));
			col_NIFTE.setCellValueFactory(new PropertyValueFactory<TutorEmpresa, String>("NIF_TE"));


	}

	public void FiltrarAlumno(){

		ConsultasBBDD filtrado = new ConsultasBBDD();

		String ciclo = "";
		String curso = "";

		if(cmb_curso.getSelectionModel().getSelectedIndex() >= 0)
			curso = cmb_curso.getSelectionModel().getSelectedItem();

		if(cmb_ciclo.getSelectionModel().getSelectedIndex() >= 0)
			ciclo = cmb_ciclo.getSelectionModel().getSelectedItem();

		tab_alumno.setItems(filtrado.filtroAlumnos(curso, ciclo,"", ""));
	}

	public boolean ChequearFecha(){


		if(fechaInicio.getValue().isAfter(fechaFin.getValue())){
			Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("ERROR!");
		   	alert.setHeaderText("¡ Fechas de FCTs !");
		   	alert.setContentText("La fecha de fin no puede ser inferior a la de inicio de las FCTs");
		   	alert.showAndWait();
		   	return false;
		}

		return true;

	}

	public boolean MensajedeConfirmacion(String Mensaje){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Asignación de FCTs");
		alert.setHeaderText("Aviso, necesita confirmación");
		alert.setContentText(Mensaje);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
		   return false;
		}
	}
}
