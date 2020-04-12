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

	private String url= "";
	private   String user = "";
	private String pwd = "";
	private   String usr = "";
	private   Connection conexion;

	public ConexionBBDD()  {


		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("src/model/datos.ini");
			if (miFichero.exists()){
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				url=propiedades.getProperty("url");
				user=propiedades.getProperty("user");
				pwd=propiedades.getProperty("pwd");
				usr=propiedades.getProperty("usr");
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
			conexion = DriverManager.getConnection(url, user, pwd);

			if(conexion.isClosed())
				System.out.println("Fallo en Conexión con la Base de Datos");


		}catch (Exception e) {
			System.out.println("ERROR en conexión con ORACLE");
			e.printStackTrace();
		}
	}

	public ObservableList<Alumno> ConsultaA() {

		 ObservableList<Alumno> data = FXCollections.observableArrayList();

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTALUMNOS");
				while(rset.next()) {
					Alumno datos = (new Alumno ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

	public   ObservableList<Empresa> ConsultaE() {

		 ObservableList<Empresa> data = FXCollections.observableArrayList();

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTEMPRESAS");
				while(rset.next()) {
					Empresa datos = (new Empresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

	public   ObservableList<Ciclo> ConsultaC() {

		 ObservableList<Ciclo> data = FXCollections.observableArrayList();

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTCICLOS");
				while(rset.next()) {
					Ciclo datos = (new Ciclo ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

	public   ObservableList<TutorCentro> ConsultaTC() {

		 ObservableList<TutorCentro> data = FXCollections.observableArrayList();

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTTUTORES_CENTRO");
				while(rset.next()) {
					TutorCentro datos = (new TutorCentro ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

	public   Asignar consultaAsig(String DNI) {

		 Asignar data = null;

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTSUPERVISAN WHERE NIF_AL='" + DNI + "'");
				while(rset.next()) {
					data = (new Asignar ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12)));

				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

	public   ObservableList<TutorEmpresa> consultaTE(String nconv) {

		 ObservableList<TutorEmpresa> data = FXCollections.observableArrayList();

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTTUTORES_EMPRESA WHERE N_CONV='" + nconv + "'");
				while(rset.next()) {
					TutorEmpresa datos = (new TutorEmpresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
					data.add(datos);
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

	public   String claveCiclo(String nombre) {

			String clave = "";

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT CLAVE FROM " + usr + ".FCTCICLOS WHERE NOMBRE='" + nombre + "'");
				while(rset.next()) {
					clave = (rset.getString(1));
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}

			return clave;
		}



	// ESte método también está preparado para la inserción masiva de alumnos a través del botón importar.
	/* se han preparado varios códigos de error para poder gestionar los errores o lo que ha sucedido
	 * 1 alumno insertado correctamente
	 * -1 ya existe un alumno con ese NIF
	 * -2 Ha habido algún problema en la inserción con la base de datos
	 * -3 El alumno está insertado pero no está "matriculado"
	 */
	public int insertarAlumno(String NIF, String nombre, String apellidos, String direccion, String ciudad, String cp, String provincia, String telefono, String email, String clave, String curso) throws SQLException{

		Statement stmt = conexion.createStatement();

		int alumno=0;
		int cursan=0;

			try {

				alumno = stmt.executeUpdate("INSERT INTO " + usr + ".FCTALUMNOS VALUES ('"+ NIF + "','" + nombre + "','" + apellidos + "','" + direccion + "','" + ciudad + "','" + cp + "','" + provincia + "','" + telefono + "','" + email + "')");
				if(alumno == 1)
					cursan = stmt.executeUpdate("INSERT INTO " + usr + ".FCTCURSAN VALUES ('"+ curso + "','" + NIF + "','" + clave + "')");
				else
					return -3;

			}catch(SQLException sqle){

				int pos = sqle.getMessage().indexOf(":");
				String codeErrorSQL = sqle.getMessage().substring(0,pos);

				if(codeErrorSQL.equals("ORA-00001") ){
					//System.out.println("Ya existe una persona con ese NIF");
					return -1;
				}
				else{
					System.out.println(sqle.getMessage());
					//System.out.println("Ha habido algún problema con  Oracle al hacer la insercion");
					return -2;
				}

			}

			stmt.close();

			if (alumno == 1 && cursan == 1)
				return 1;

			return -3;

	}

	public   void insertarCurso(String curso, String NIF_AL, String clave) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("INSERT INTO " + usr + ".FCTCURSAN VALUES ('"+ curso + "','" + NIF_AL + "','" + clave + "')");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();

	}


	public   void modificarAlumno(String NIF, String nombre, String apellidos, String direccion, String ciudad, String cp, String provincia, String telefono, String email) throws SQLException{

		Statement stmt = conexion.createStatement();

		try {
			stmt.executeUpdate("UPDATE " + usr + ".FCTALUMNOS SET NIF_AL='"+ NIF + "',NOMBRE='" + nombre + "',APELLIDOS='" + apellidos + "',DIRECCION='" + direccion + "',CIUDAD='" + ciudad + "',CP='" + cp + "',PROVINCIA='" + provincia + "',TELEFONO='" + telefono + "',EMAIL='" + email + "' WHERE NIF_AL='" + NIF + "'");

			}catch(SQLException s) {
				s.printStackTrace();
			}

			stmt.close();
			conexion.close();
	}

	public   void insertarEmpresa(String nConv, String representante, String NIF_REP, String CIF, String nombre, String direccion, String cp, String ciudad, String provincia, String pais, String telefono, String fax, String fecha_conv) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("INSERT INTO " + usr + ".FCTEMPRESAS VALUES ('"+ nConv + "','" + representante + "','" + NIF_REP + "','" + CIF + "','" + nombre + "','" + direccion + "','" + cp + "','" + ciudad + "','" + provincia + "','" + pais + "','" + telefono + "','" + fax + "','" + fecha_conv + "')");

				}catch(SQLException s) {
					s.printStackTrace();
				}

			stmt.close();
			conexion.close();

	}

	public   void modificarEmpresa(String nConv, String representante, String NIF_REP, String CIF, String nombre, String direccion, String cp, String ciudad, String provincia, String pais, String telefono, String fax, String fecha_conv) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("UPDATE " + usr + ".FCTALUMNOS SET N_CONV='"+ nConv + "',REPRESENTANTE='" + representante + "',NIF_REP='" + NIF_REP + "',CIF='" + CIF + "',NOMBRE='" + nombre + "',DIRECCION='" + direccion + "',CP='" + cp + "',CIUDAD='" + ciudad + "',PROVINCIA='" + provincia + "',PAIS='" + pais + "',TELEFONO'" + telefono + "',FAX='" + fax + "',FECHA_CONV'" + fecha_conv + "' WHERE N_CONV='" + nConv + "'");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();
	}

	public   void insertarCiclo(String clave, String nombre, String familia, String clave_fam, String capacidades, String actividades, String criterios) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("INSERT INTO " + usr + ".FCTCICLOS VALUES ('"+ clave + "','" + nombre + "','" + familia + "','" + clave_fam + "','" + capacidades + "','" + actividades + "','" + criterios + "')");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();

	}

	public   void modificarCiclo(String clave, String nombre, String familia, String clave_fam, String capacidades, String actividades, String criterios) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("UPDATE " + usr + ".FCTCICLOS SET CLAVE='"+ clave + "',NOMBRE='" + nombre + "',FAMILIA_PROF='" + familia + "',CLAVE_FAMILIA='" + clave_fam + "',CAP_TERM='" + capacidades + "',ACT_FORM_PROD='" + actividades + "',CRIT_EV='" + criterios + "' WHERE CLAVE='" + clave + "'");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();

	}

	public   void insertarTutorC(String NIF, String nombre, String telefono, String email, String cod_centro) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("INSERT INTO " + usr + ".FCTTUTORES_CENTRO VALUES ('"+ NIF + "','" + nombre + "','" + telefono + "','" + email + "','" + cod_centro + "')");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();

	}

	public   void modificarTutorC(String NIF, String nombre, String telefono, String email) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("UPDATE " + usr + ".FCTTUTORES_CENTRO SET NIF_TC='"+ NIF + "',NOMBRE='" + nombre + "',TELEFONO='" + telefono + "',EMAIL='" + email + "' WHERE NIF_TC='" + NIF + "'");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();

	}

	public   void asignarPracticas(String fechaInicio, String fechaFin, String horasDia, String horasTotal, String horaInicioM, String horaFinM, String horaInicioT, String horaFinT, String NIF_AL, String NIF_TC, String NIF_TE, String nconv) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("INSERT INTO " + usr + ".FCTSUPERVISAN VALUES ('"+ fechaInicio + "','" + fechaFin + "','" + horasDia + "','" + horasTotal + "','" + horaInicioM + "','" + horaFinM + "','" + horaInicioT + "','" + horaFinT + "','" +  NIF_AL + "','" + NIF_TC + "','" + NIF_TE + "','" + nconv + "')");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				//conexion.close();

	}

	public   void modificarPracticas(String NIF_AL, String nconv, String NIF_TC, String NIF_TE, String fechaInicio, String fechaFin, String horasDia, String horasTotal, String horaInicioM, String horaFinM, String horaInicioT, String horaFinT, String AL_Anterior) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("UPDATE " + usr + ".FCTSUPERVISAN SET NIF_AL='"+ NIF_AL + "',NIF_TC='" + NIF_TC + "',NIF_TE='" + NIF_TE + "',N_CONV='" + nconv + "',FECHA_INICIO='" + fechaInicio + "',FECHA_FIN='" + fechaFin + "',HORAS_DIA='" + horasDia + "',HORAS_TOTALES='" + horasTotal + "',HORA_INICIO_M='" + horaInicioM + "',HORA_FIN_M='" + horaFinM + "',HORA_INICIO_T='" + horaInicioT + "',HORA_FIN_T='" + horaFinT + "' WHERE NIF_AL='" + AL_Anterior +"'");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();

	}

	public   void insertarTutorE(String NIF, String nombre, String telefono, String email, String nconv) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("INSERT INTO " + usr + ".FCTTUTORES_EMPRESA VALUES ('"+ NIF + "','" + nombre + "','" + telefono + "','" + email + "','" + nconv + "')");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();

	}

	public   void modificarTutorE(String NIF, String nombre, String telefono, String email, String nconv, String NIF_Anterior) throws SQLException{

		Statement stmt = conexion.createStatement();

			try {

				stmt.executeUpdate("UPDATE " + usr + ".FCTTUTORES_EMPRESA SET NIF_TE='"+ NIF + "',NOMBRE='" + nombre + "',TELEFONO='" + telefono + "',EMAIL='" + email + "',N_CONV='" + nconv + "' WHERE NIF_TE='" + NIF_Anterior + "'");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
				conexion.close();

	}

	public   Empresa anexoEmpresa(String nconv) {

		 Empresa data = null;

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTEMPRESAS WHERE N_CONV='" + nconv + "'");
				while(rset.next()) {
					data = (new Empresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13)));
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;

		}

	public   TutorCentro anexoTC(String NIF_TC) {

		 TutorCentro data = null;

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTTUTORES_CENTRO WHERE NIF_TC='" + NIF_TC + "'");
				while(rset.next()) {
					data = (new TutorCentro ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;

		}

	public   TutorEmpresa anexoTE(String NIF_TE) {

		 TutorEmpresa data = null;

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTTUTORES_EMPRESA WHERE NIF_TE='" + NIF_TE + "'");

				while(rset.next()) {
					data = (new TutorEmpresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;

		}

	public   Centro anexoCentro() {

		 Centro data = null;

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTCENTROS WHERE COD_CENTRO='28067847'");
				while(rset.next()) {
					data = (new Centro ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12)));
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

	public   Ciclo anexoCiclo(String ciclo) {

		 Ciclo data = null;

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM " + usr + ".FCTCICLOS WHERE NOMBRE='" + ciclo + "'");
				while(rset.next()) {
					data = (new Ciclo ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7)));
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

	public   ObservableList<String> anexoCicloNom() {

		ObservableList<String> ciclos = FXCollections.observableArrayList();

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT NOMBRE FROM " + usr + ".FCTCICLOS");
				while(rset.next()) {
					ciclos.add(rset.getString(1));
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return ciclos;
		}

	public   ObservableList<String> anexoCurso() {

		ObservableList<String> cursos = FXCollections.observableArrayList();

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT DISTINCT CURSO FROM " + usr + ".FCTCURSAN");
				while(rset.next()) {
					cursos.add(rset.getString(1));
				}
				rset.close();
				stmt.close();
				conexion.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return cursos;
		}

	public   ObservableList<Alumno> AnexoAlumno(String ciclo, String curso) {

		 ObservableList<Alumno> data = FXCollections.observableArrayList();

		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT A.NIF_AL, A.NOMBRE, A.APELLIDOS FROM " + usr + ".FCTALUMNOS A, " + usr + ".FCTCURSAN C, " + usr + ".FCTSUPERVISAN S WHERE A.NIF_AL=C.NIF_AL AND A.NIF_AL=S.NIF_AL AND C.CURSO='"+ curso +"' AND CLAVE=(SELECT CLAVE FROM " + usr + ".FCTCICLOS WHERE NOMBRE='" + ciclo + "')");

			while(rset.next()) {
				Alumno datos = (new Alumno ( rset.getString(1), rset.getString(2), rset.getString(3)));
				data.add(datos);
			}
			rset.close();
			stmt.close();
			conexion.close();

		}catch (SQLException s){
			s.printStackTrace();
		}
		return data;
	}



}
