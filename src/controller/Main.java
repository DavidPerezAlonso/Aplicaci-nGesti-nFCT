package controller;

import controller.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Alumno;
import model.Asignar;
import model.Ciclo;
import model.Empresa;
import model.TutorCentro;
import view.ControladoraCambiarPass;
import view.ControladoraLogin;
import view.ControladoraRegistro;
import view.Menu;
import view.alumnos.ControladorAlumno;
import view.alumnos.ControladorCrearA;
import view.alumnos.ControladorModificarA;
import view.anexos.ControladoraAnexos;
import view.asignar.ControladorAsignar;
import view.asignar.ControladorAsignar2;
import view.asignar.ControladorModificacion;
import view.ciclos.ControladorCiclos;
import view.ciclos.ControladorCrearC;
import view.ciclos.ControladorModificarC;
import view.empresas.ControladorCrearE;
import view.empresas.ControladorCrearTE;
import view.empresas.ControladorEmpresa;
import view.empresas.ControladorModificarE;
import view.empresas.ControladorModificarTE;
import view.tutores.ControladorCrearTC;
import view.tutores.ControladorModificarTC;
import view.tutores.ControladorTC;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {

	private static Stage primaryStage;
	private AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;
		loginVent();
	}

	public void cerrarAplicacion() {

		primaryStage.close();
	}

	public void loginVent() {

		try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/Login.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("GestionFCTs");
            primaryStage.setScene(scene);

            ControladoraLogin controller = loader.getController();
            controller.setVentana(this, primaryStage);


            primaryStage.show();

           } catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void menuVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/InterfazMenu.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Menu principal");
	        primaryStage.setScene(scene);

	        Menu controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void registroVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/InterfazRegistro.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Nuevo usuario");
	        primaryStage.setScene(scene);

	        ControladoraRegistro controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void cambiarPassVent() {

			try {

		        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/InterfazCambiarPass.fxml"));
		        rootLayout = (AnchorPane) loader.load();
		        Scene scene = new Scene(rootLayout);
				primaryStage.setTitle("Cambiar contraseña");
		        primaryStage.setScene(scene);

		        ControladoraCambiarPass controller = loader.getController();
		        controller.setVentana(this, primaryStage);


		        primaryStage.show();

		        } catch(Exception e) {
					e.printStackTrace();
				}

		}

	public void alumnoVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/alumnos/InterfazAlumnos.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Alumnos");
	        primaryStage.setScene(scene);

	        ControladorAlumno controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void alumnoCrearVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/alumnos/CrearAlumno.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Crear alumno");
	        primaryStage.setScene(scene);

	        ControladorCrearA controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void alumnoModVent(Alumno selectedAlumno) {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/alumnos/ModificarAlumno.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Modificar alumno");
	        primaryStage.setScene(scene);

	        ControladorModificarA controller = loader.getController();
		    controller.setDatos(selectedAlumno);
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void anexosVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/anexos/InterfazAnexos.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Anexos");
	        primaryStage.setScene(scene);

	        ControladoraAnexos controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void asignarVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/asignar/InterfazAsignar2.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Asignar prácticas");
	        primaryStage.setScene(scene);

	        ControladorAsignar2 controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void asignarModVent(Asignar datosConsulta) {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/asignar/ModificarAsignacion.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Modificar prácticas");
	        primaryStage.setScene(scene);

	        ControladorModificacion controller = loader.getController();
			controller.setDatos(datosConsulta);
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void ciclosVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/ciclos/InterfazCiclos.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Ciclos");
	        primaryStage.setScene(scene);

	        ControladorCiclos controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void ciclosCrearVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/ciclos/CrearCiclo.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Crear nuevo registro ciclo");
	        primaryStage.setScene(scene);

	        ControladorCrearC controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void ciclosModVent(Ciclo selectedCiclo) {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/ciclos/ModificarCiclo.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Modificar registro ciclo");
	        primaryStage.setScene(scene);

	        ControladorModificarC controller = loader.getController();
		    controller.setDatos(selectedCiclo);
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void empresaVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/empresas/InterfazEmpresas.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Empresas");
	        primaryStage.setScene(scene);

	        ControladorEmpresa controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void empresaCrearVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/empresas/CrearEmpresa.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Nuevo registro empresa");
	        primaryStage.setScene(scene);

	        ControladorCrearE controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void empresaModVent(Empresa selectedEmpresa) {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/empresas/ModificarEmpresa.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Nuevo registro empresa");
	        primaryStage.setScene(scene);

	        ControladorModificarE controller = loader.getController();
		    controller.setDatos(selectedEmpresa);
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void empresaTutVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/empresas/CrearTutorEmpresa.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Nuevo registro tutor de empresa");
	        primaryStage.setScene(scene);

	        ControladorCrearTE controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void empresaTutModVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/empresas/ModificarTutorEmpresa.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Modificar registro tutor de empresa");
	        primaryStage.setScene(scene);

	        ControladorModificarTE controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void tutoresVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/tutores/InterfazTutores.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Tutores Centro");
	        primaryStage.setScene(scene);

	        ControladorTC controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void tutoresModVent(TutorCentro selectedTutor) {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/tutores/ModificarTutorCentro.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Modificar registro tutor centro");
	        primaryStage.setScene(scene);

	        ControladorModificarTC controller = loader.getController();
	    	controller.setDatos(selectedTutor);
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public void tutoresCrearVent() {

		try {

	        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/tutores/CrearTutorCentro.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Nuevo registro tutor centro");
	        primaryStage.setScene(scene);

	        ControladorCrearTC controller = loader.getController();
	        controller.setVentana(this, primaryStage);


	        primaryStage.show();

	        } catch(Exception e) {
				e.printStackTrace();
			}

	}

	public static void main(String[] args) {
		launch(args);
	}


}
