package view;

import java.io.IOException;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Menu {
	
	@FXML
	Button GenerarAnexos;
	
	@FXML
	Button GestionAlumnos;
	
	@FXML
	Button GestionEmpresas;
	
	@FXML
	Button GestionCiclos;
	
	@FXML
	Button GestionTutores;
	
	@FXML
	Button AsignarPracticas;
	
	@FXML
	Button Logout;
	
	public void initialize() {
		
	}
	
public void intefazAlumnos(ActionEvent event) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/alumnos/InterfazAlumnos.fxml"));
        AnchorPane ventanaDos = (AnchorPane) loader.load();
        Stage ventana = new Stage();
        ventana.setTitle("Alumnos");
        Scene scene = new Scene(ventanaDos);
        ventana.setScene(scene);
        ventana.show();
	}

public void intefazEmpresas(ActionEvent event) throws IOException{
	
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/empresas/InterfazEmpresas.fxml"));
    AnchorPane ventanaDos = (AnchorPane) loader.load();
    Stage ventana = new Stage();
    ventana.setTitle("Empresas");
    Scene scene = new Scene(ventanaDos);
    ventana.setScene(scene);
    ventana.show();
}

public void intefazCiclos(ActionEvent event) throws IOException{
	
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/ciclos/InterfazCiclos.fxml"));
    AnchorPane ventanaDos = (AnchorPane) loader.load();
    Stage ventana = new Stage();
    ventana.setTitle("Ciclos");
    Scene scene = new Scene(ventanaDos);
    ventana.setScene(scene);
    ventana.show();
}

public void intefazTutores(ActionEvent event) throws IOException{
	
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/tutores/InterfazTutores.fxml"));
    AnchorPane ventanaDos = (AnchorPane) loader.load();
    Stage ventana = new Stage();
    ventana.setTitle("Tutores");
    Scene scene = new Scene(ventanaDos);
    ventana.setScene(scene);
    ventana.show();
}

public void intefazAsignar(ActionEvent event) throws IOException{
	
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/asignar/InterfazAsignar.fxml"));
    AnchorPane ventanaDos = (AnchorPane) loader.load();
    Stage ventana = new Stage();
    ventana.setTitle("Asignar");
    Scene scene = new Scene(ventanaDos);
    ventana.setScene(scene);
    ventana.show();
}
	
}
