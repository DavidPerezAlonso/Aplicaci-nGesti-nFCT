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
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConexionBBDD {
	
	private String url= "";
	private static String usr = "";
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
	
	public static ObservableList<Alumno> ConsultaA() {
		
		 ObservableList<Alumno> data = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".ALUMNOS");
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
	
	public static ObservableList<Empresa> ConsultaE() {
		
		 ObservableList<Empresa> data = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".EMPRESAS");
				while(rset.next()) {
					Empresa datos = (new Empresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
		}
	
	public static ObservableList<Ciclo> ConsultaC() {
		
		 ObservableList<Ciclo> data = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".CICLOS");
				while(rset.next()) {
					Ciclo datos = (new Ciclo ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
		}
	
	public static ObservableList<TutorCentro> ConsultaTC() {
		
		 ObservableList<TutorCentro> data = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".TUTORES_CENTRO");
				while(rset.next()) {
					TutorCentro datos = (new TutorCentro ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
		}
	
	public static Asignar consultaAsig(String DNI) {
		
		 Asignar data = null;
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".SUPERVISAN WHERE NIF_AL='" + DNI + "'");
				while(rset.next()) {
					data = (new Asignar ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12)));

				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
		}
	
	public static ObservableList<TutorEmpresa> consultaTE(String nconv) {
		
		 ObservableList<TutorEmpresa> data = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".TUTORES_EMPRESA WHERE N_CONV='" + nconv + "'");
				while(rset.next()) {
					TutorEmpresa datos = (new TutorEmpresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
		}
	
	public static String claveCiclo(String nombre) {
		
			String clave = "";
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT CLAVE FROM " + usr + ".CICLOS WHERE NOMBRE='" + nombre + "'");
				while(rset.next()) {
					clave = (rset.getString(1));
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			
			return clave; 
		}
	
	public static void insertarAlumno(String NIF, String nombre, String apellidos, String direccion, String ciudad, String cp, String provincia, String telefono, String email, String clave, String curso) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("INSERT INTO " + usr + ".ALUMNOS VALUES ('"+ NIF + "','" + nombre + "','" + apellidos + "','" + direccion + "','" + ciudad + "','" + cp + "','" + provincia + "','" + telefono + "','" + email + "')");
				stmt.executeUpdate("INSERT INTO " + usr + ".CURSAN VALUES ('"+ curso + "','" + NIF + "','" + clave + "')");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void insertarCurso(String curso, String NIF_AL, String clave) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("INSERT INTO " + usr + ".CURSAN VALUES ('"+ curso + "','" + NIF_AL + "','" + clave + "')");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	
	public static void modificarAlumno(String NIF, String nombre, String apellidos, String direccion, String ciudad, String cp, String provincia, String telefono, String email) throws SQLException{
		
		Statement stmt = conexion.createStatement();
		
		try {
			stmt.executeUpdate("UPDATE " + usr + ".ALUMNOS SET NIF_AL='"+ NIF + "',NOMBRE='" + nombre + "',APELLIDOS='" + apellidos + "',DIRECCION='" + direccion + "',CIUDAD='" + ciudad + "',CP='" + cp + "',PROVINCIA='" + provincia + "',TELEFONO='" + telefono + "',EMAIL='" + email + "' WHERE NIF_AL='" + NIF + "'");
			
			}catch(SQLException s) {
				s.printStackTrace();
			}

			stmt.close();
	}
	
	public static void insertarEmpresa(String nConv, String representante, String NIF_REP, String CIF, String nombre, String direccion, String cp, String ciudad, String provincia, String pais, String telefono, String fax, String fecha_conv) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
			
				stmt.executeUpdate("INSERT INTO " + usr + ".EMPRESAS VALUES ('"+ nConv + "','" + representante + "','" + NIF_REP + "','" + CIF + "','" + nombre + "','" + direccion + "','" + cp + "','" + ciudad + "','" + provincia + "','" + pais + "','" + telefono + "','" + fax + "','" + fecha_conv + "')");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

			stmt.close();

	}
	
	public static void modificarEmpresa(String nConv, String representante, String NIF_REP, String CIF, String nombre, String direccion, String cp, String ciudad, String provincia, String pais, String telefono, String fax, String fecha_conv) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("UPDATE " + usr + ".ALUMNOS SET N_CONV='"+ nConv + "',REPRESENTANTE='" + representante + "',NIF_REP='" + NIF_REP + "',CIF='" + CIF + "',NOMBRE='" + nombre + "',DIRECCION='" + direccion + "',CP='" + cp + "',CIUDAD='" + ciudad + "',PROVINCIA='" + provincia + "',PAIS='" + pais + "',TELEFONO'" + telefono + "',FAX='" + fax + "',FECHA_CONV'" + fecha_conv + "' WHERE N_CONV='" + nConv + "'");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
	}
	
	public static void insertarCiclo(String clave, String nombre, String familia, String clave_fam, String capacidades, String actividades, String criterios) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("INSERT INTO " + usr + ".CICLOS VALUES ('"+ clave + "','" + nombre + "','" + familia + "','" + clave_fam + "','" + capacidades + "','" + actividades + "','" + criterios + "')");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void modificarCiclo(String clave, String nombre, String familia, String clave_fam, String capacidades, String actividades, String criterios) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("UPDATE " + usr + ".CICLOS SET CLAVE='"+ clave + "',NOMBRE='" + nombre + "',FAMILIA_PROF='" + familia + "',CLAVE_FAMILIA='" + clave_fam + "',CAP_TERM='" + capacidades + "',ACT_FORM_PROD='" + actividades + "',CRIT_EV='" + criterios + "' WHERE CLAVE='" + clave + "'");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void insertarTutorC(String NIF, String nombre, String telefono, String email, String cod_centro) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("INSERT INTO " + usr + ".TUTORES_CENTRO VALUES ('"+ NIF + "','" + nombre + "','" + telefono + "','" + email + "','" + cod_centro + "')");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void modificarTutorC(String NIF, String nombre, String telefono, String email) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("UPDATE " + usr + ".TUTORES_CENTRO SET NIF_TC='"+ NIF + "',NOMBRE='" + nombre + "',TELEFONO='" + telefono + "',EMAIL='" + email + "' WHERE NIF_TC='" + NIF + "'");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void asignarPracticas(String fechaInicio, String fechaFin, String horasDia, String horasTotal, String horaInicioM, String horaFinM, String horaInicioT, String horaFinT, String NIF_AL, String NIF_TC, String NIF_TE, String nconv) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("INSERT INTO " + usr + ".SUPERVISAN VALUES ('"+ fechaInicio + "','" + fechaFin + "','" + horasDia + "','" + horasTotal + "','" + horaInicioM + "','" + horaFinM + "','" + horaInicioT + "','" + horaFinT + "','" +  NIF_AL + "','" + NIF_TC + "','" + NIF_TE + "','" + nconv + "')");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void modificarPracticas(String NIF_AL, String nconv, String NIF_TC, String NIF_TE, String fechaInicio, String fechaFin, String horasDia, String horasTotal, String horaInicioM, String horaFinM, String horaInicioT, String horaFinT, String AL_Anterior) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("UPDATE " + usr + ".SUPERVISAN SET NIF_AL='"+ NIF_AL + "',NIF_TC='" + NIF_TC + "',NIF_TE='" + NIF_TE + "',N_CONV='" + nconv + "',FECHA_INICIO='" + fechaInicio + "',FECHA_FIN='" + fechaFin + "',HORAS_DIA='" + horasDia + "',HORAS_TOTALES='" + horasTotal + "',HORA_INICIO_M='" + horaInicioM + "',HORA_FIN_M='" + horaFinM + "',HORA_INICIO_T='" + horaInicioT + "',HORA_FIN_T='" + horaFinT + "' WHERE NIF_AL='" + AL_Anterior +"'");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void insertarTutorE(String NIF, String nombre, String telefono, String email, String nconv) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("INSERT INTO " + usr + ".TUTORES_EMPRESA VALUES ('"+ NIF + "','" + nombre + "','" + telefono + "','" + email + "','" + nconv + "')");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static void modificarTutorE(String NIF, String nombre, String telefono, String email, String nconv, String NIF_Anterior) throws SQLException{
		
		Statement stmt = conexion.createStatement();

			try {
				
				stmt.executeUpdate("UPDATE " + usr + ".TUTORES_EMPRESA SET NIF_TE='"+ NIF + "',NOMBRE='" + nombre + "',TELEFONO='" + telefono + "',EMAIL='" + email + "',N_CONV='" + nconv + "' WHERE NIF_TE='" + NIF_Anterior + "'");
				
				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				
	}
	
	public static Empresa anexoEmpresa(String nconv) {
		
		 Empresa data = null;
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".EMPRESAS WHERE N_CONV='" + nconv + "'");
				while(rset.next()) {
					data = (new Empresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13)));
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;

		}
	
	public static TutorCentro anexoTC(String NIF_TC) {
		
		 TutorCentro data = null;
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".TUTORES_CENTRO WHERE NIF_TC='" + NIF_TC + "'");
				while(rset.next()) {
					data = (new TutorCentro ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;

		}
	
	public static TutorEmpresa anexoTE(String NIF_TE) {
		
		 TutorEmpresa data = null;
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".TUTORES_EMPRESA WHERE NIF_TE='" + NIF_TE + "'");
				while(rset.next()) {
					data = (new TutorEmpresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;

		}
	
	public static Centro anexoCentro() {
		
		 Centro data = null;
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".CENTROS WHERE COD_CENTRO='23457'");
				while(rset.next()) {
					data = (new Centro ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12)));
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
		}
	
	public static Ciclo anexoCiclo(String ciclo) {
		
		 Ciclo data = null;
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".CICLOS WHERE NOMBRE='" + ciclo + "'");
				while(rset.next()) {
					data = (new Ciclo ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7)));
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
		}
	
	public static ObservableList<String> anexoCicloNom() {
		
		ObservableList<String> ciclos = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT NOMBRE FROM " + usr + ".CICLOS");
				while(rset.next()) {
					ciclos.add(rset.getString(1));
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return ciclos; 
		}
	
	public static ObservableList<String> anexoCurso() {
		
		ObservableList<String> cursos = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT DISTINCT CURSO FROM " + usr + ".CURSAN");
				while(rset.next()) {
					cursos.add(rset.getString(1));
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return cursos; 
		}
	
	public static ObservableList<Alumno> AnexoAlumno(String ciclo, String curso) {
		
		 ObservableList<Alumno> data = FXCollections.observableArrayList();
			
			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT A.NIF_AL, A.NOMBRE, A.APELLIDOS FROM " + usr + ".ALUMNOS A, " + usr + ".CURSAN C, " + usr + ".SUPERVISAN S WHERE A.NIF_AL=C.NIF_AL AND A.NIF_AL=S.NIF_AL AND C.CURSO='"+ curso +"' AND CLAVE=(SELECT CLAVE FROM " + usr + ".CICLOS WHERE NOMBRE='" + ciclo + "')");
				while(rset.next()) {
					Alumno datos = (new Alumno ( rset.getString(1), rset.getString(2), rset.getString(3)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
			return data; 
		}
	
	
	
}
