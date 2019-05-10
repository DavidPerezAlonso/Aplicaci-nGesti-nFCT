package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConexionBBDD {
	
	private String bd;
	private String url= "";
	private String usr = "";
	private String pwd = "";
	private static Connection conexion;
	

	public ConexionBBDD()  {
		
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("src/model/datos.ini");
			if (miFichero.exists()){
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				url=propiedades.getProperty("url");
				usr=propiedades.getProperty("usr");
				pwd=propiedades.getProperty("pwd");
			}
			else
				System.out.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url, usr, pwd);
			
			if(!conexion.isClosed()) {
				System.out.println("Conexión establecida");
				//conexion.close();
			}
			else
				System.out.println("Fallo en Conexión");	
			

		}catch (Exception e) {
			System.out.println("ERROR en conexión con ORACLE");	
			e.printStackTrace();
		}
	}
	
	public static ObservableList Consulta() {
		
		 ObservableList<Alumno> data = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM LAURAK.ALUMNOS" );
				while(rset.next()) {
					Alumno datos = (new Alumno ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
			
		}
	
	public static void insertarAlumno(String NIF, String nombre, String apellidos, String direccion, String ciudad, String cp, String provincia, String telefono, String email, String tabla) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				stmt.executeUpdate("INSERT INTO LAURAK.ALUMNOS VALUES ('"+ NIF + "','" + nombre + "','" + apellidos + "','" + direccion + "','" + ciudad + "','" + cp + "','" + provincia + "','" + telefono + "','" + email + "')");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void modificarAlumno(String NIF, String nombre, String apellidos, String direccion, String ciudad, String cp, String provincia, String telefono, String email, String tabla) throws SQLException{
		
		Statement stmt = conexion.createStatement();
		
		try {
			stmt.executeUpdate("UPDATE LAURAK.ALUMNOS SET NIF_AL='"+ NIF + "',NOMBRE='" + nombre + "',APELLIDOS='" + apellidos + "',DIRECCION='" + direccion + "',CIUDAD='" + ciudad + "',CP='" + cp + "',PROVINCIA='" + provincia + "',TELEFONO='" + telefono + "',EMAIL='" + email + "' WHERE NIF_AL='" + NIF + "'");
			
			}catch(SQLException s) {
				s.printStackTrace();
			}

			stmt.close();
	}

}
