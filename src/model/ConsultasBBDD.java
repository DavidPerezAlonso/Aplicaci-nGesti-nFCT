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

public class ConsultasBBDD {

	private String url= "";
	private static String user = "";
	private String pwd = "";
	private static String usr = "";
	private static Connection conexion;



	public ConsultasBBDD()  {

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

public ObservableList <Alumno> filtroAlumnos(String curso, String ciclo, String empresa, String tutor){

	ObservableList<Alumno> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();

		String query = "SELECT  DISTINCT A.NIF_AL, A.NOMBRE, A.APELLIDOS, A.DIRECCION, A.CIUDAD, A.CP, A.PROVINCIA, A.TELEFONO, A.EMAIL FROM " + usr + ".FCTALUMNOS A";

		if (!curso.equals("") && ciclo.equals("") && empresa.equals("") && tutor.equals(""))
			query+= ", " + usr + ".FCTCURSAN C WHERE C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL";

		else if (!curso.equals("") && !ciclo.equals("") && empresa.equals("") && tutor.equals(""))
			query+= ", " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI WHERE C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL AND CI.CLAVE=C.CLAVE AND CI.NOMBRE='" + ciclo + "'";

		else if (!curso.equals("") && !ciclo.equals("") && !empresa.equals("") && tutor.equals(""))
			query += ", " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTEMPRESAS E WHERE C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL AND CI.CLAVE=C.CLAVE AND CI.NOMBRE='" + ciclo + "'AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND E.N_CONV=S.N_CONV AND S.NIF_AL=A.NIF_AL";

		else if (!curso.equals("") && !ciclo.equals("") && !empresa.equals("") && !tutor.equals(""))
			query += ", " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTEMPRESAS E, " + usr + ".FCTTUTORES_CENTRO T WHERE C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL AND CI.CLAVE=C.CLAVE AND CI.NOMBRE='" + ciclo + "'AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND E.N_CONV=S.N_CONV AND S.NIF_AL=A.NIF_AL AND S.NIF_TC=T.NIF_TC AND S.NIF_TC=(SELECT NIF_TC FROM " + usr + " FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "')";

		else if (curso.equals("") && !ciclo.equals("") && empresa.equals("") && tutor.equals(""))
			query += ", " + usr + ".FCTCURSAN C,  " + usr + ".FCTCICLOS CI WHERE CI.NOMBRE = '" + ciclo + "' AND C.CLAVE=CI.CLAVE AND C.NIF_AL=A.NIF_AL";

		else if (curso.equals("") && !ciclo.equals("") && !empresa.equals("") && tutor.equals(""))
			query += ", " + usr + ".FCTCURSAN C,  " + usr + ".FCTCICLOS CI, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTEMPRESAS E WHERE CI.NOMBRE = '" + ciclo + "' AND C.CLAVE=CI.CLAVE AND C.NIF_AL=A.NIF_AL AND S.N_CONV=E.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND S.NIF_AL=A.NIF_AL";

		else if (curso.equals("") && !ciclo.equals("") && !empresa.equals("") && !tutor.equals(""))
			query += ", " + usr + ".FCTCURSAN C,  " + usr + ".FCTCICLOS CI, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTEMPRESAS E,  " + usr + ".FCTTUTORES_CENTRO T WHERE CI.NOMBRE = '" + ciclo + "' AND C.CLAVE=CI.CLAVE AND C.NIF_AL=A.NIF_AL AND S.N_CONV=E.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND S.NIF_AL=A.NIF_AL AND S.NIF_TC=T.NIF_TC AND S.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "')";

		else if (curso.equals("") && ciclo.equals("") && !empresa.equals("") && tutor.equals(""))
			query += ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S WHERE S.NIF_AL=A.NIF_AL AND S.N_CONV=E.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "')";

		else if (curso.equals("") && ciclo.equals("") && !empresa.equals("") && !tutor.equals(""))
			query += ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTTUTORES_CENTRO T WHERE S.NIF_AL=A.NIF_AL AND S.N_CONV=E.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND S.NIF_TC=T.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "')";

		else if (!curso.equals("") && ciclo.equals("") && !empresa.equals("") && !tutor.equals(""))
			query += ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTTUTORES_CENTRO T, " + usr + ".FCTCURSAN C WHERE S.NIF_AL=A.NIF_AL AND S.N_CONV=E.N_CONV AND E.N_CONV=(SELECT N_CONV FROM EMPRESAS WHERE NOMBRE='" + empresa + "') AND S.NIF_TC=T.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "') AND C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL";

		else if (!curso.equals("") && ciclo.equals("") && !empresa.equals("") && tutor.equals(""))
			query += ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C WHERE S.NIF_AL=A.NIF_AL AND S.N_CONV=E.N_CONV AND E.N_CONV=(SELECT N_CONV FROM FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL";

		else if (curso.equals("") && ciclo.equals("") && empresa.equals("") && !tutor.equals(""))
			query += ", " + usr + ".FCTTUTORES_CENTRO T, " + usr + ".FCTSUPERVISAN S WHERE A.NIF_AL=S.NIF_AL AND T.NIF_TC=S.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "')";

		else if (!curso.equals("") && ciclo.equals("") && empresa.equals("") && !tutor.equals(""))
			query += ", " + usr + ".FCTTUTORES_CENTRO T, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C WHERE A.NIF_AL=S.NIF_AL AND T.NIF_TC=S.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "') AND C.NIF_AL=A.NIF_AL AND C.CURSO='" + curso + "'";

		else if (!curso.equals("") && !ciclo.equals("") && empresa.equals("") && !tutor.equals(""))
			query += ", " + usr + ".FCTTUTORES_CENTRO T, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI WHERE A.NIF_AL=S.NIF_AL AND T.NIF_TC=S.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "') AND C.NIF_AL=A.NIF_AL AND C.CURSO='" + curso + "' AND C.CLAVE=CI.CLAVE AND CI.CLAVE=(SELECT CLAVE FROM " + usr + ".CICLOS WHERE NOMBRE='" + ciclo + "')";

		else if (curso.equals("") && !ciclo.equals("") && empresa.equals("") && !tutor.equals(""))
			query += ", " + usr + ".FCTTUTORES_CENTRO T, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI WHERE A.NIF_AL=S.NIF_AL AND T.NIF_TC=S.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".TFCTUTORES_CENTRO WHERE NOMBRE='" + tutor + "') AND CI.CLAVE=(SELECT CLAVE FROM " + usr + ".CICLOS WHERE NOMBRE='" + ciclo + "') AND CI.CLAVE=C.CLAVE AND A.NIF_AL=C.NIF_AL";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			Alumno datos = (new Alumno ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9)));
			data.add(datos);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}

public ObservableList <Empresa> filtroEmpresa(String tutor, String conv, String ciclo){

	ObservableList<Empresa> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();

		String query = "SELECT DISTINCT E.N_CONV, E.REPRESENTANTE, E.NIF_REP, E.CIF, E.NOMBRE, E.DIRECCION, E.CP, E.CIUDAD, E.PROVINCIA, E.PAIS, E.TELEFONO, E.FAX, E.FECHA_CONV FROM " + usr + ".FCTEMPRESAS E";

		if (!tutor.equals("") && conv.equals("") && ciclo.equals(""))
			query+= ", " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTTUTORES_CENTRO T WHERE E.N_CONV=S.N_CONV AND S.NIF_TC=T.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "')";

		else if (tutor.equals("") && !conv.equals("") && ciclo.equals(""))
			query+= " WHERE E.FECHA_CONV LIKE '%" + conv + "'";

		else if (tutor.equals("") && conv.equals("") && !ciclo.equals(""))
		query+= ", " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI, " + usr + ".FCTALUMNOS A WHERE E.N_CONV=S.N_CONV AND S.NIF_AL=A.NIF_AL AND A.NIF_AL=C.NIF_AL AND C.CLAVE=(SELECT CLAVE FROM " + usr + ".FCTCICLOS WHERE NOMBRE='" + ciclo + "')";

		else if (!tutor.equals("") && conv.equals("") && !ciclo.equals(""))
			query+= ", " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI, " + usr + ".FCTALUMNOS A, " + usr + ".FCTTUTORES_CENTRO T WHERE E.N_CONV=S.N_CONV AND S.NIF_AL=A.NIF_AL AND A.NIF_AL=C.NIF_AL AND C.CLAVE=(SELECT CLAVE FROM " + usr + ".FCTCICLOS WHERE NOMBRE='" + ciclo + "') AND S.NIF_TC=T.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "')";

		else if (!tutor.equals("") && !conv.equals("") && !ciclo.equals(""))
			query+= ", " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI, " + usr + ".FCTALUMNOS A, " + usr + ".FCTTUTORES_CENTRO T WHERE E.N_CONV=S.N_CONV AND S.NIF_AL=A.NIF_AL AND A.NIF_AL=C.NIF_AL AND C.CLAVE=(SELECT CLAVE FROM " + usr + ".FCTCICLOS WHERE NOMBRE='" + ciclo + "') AND S.NIF_TC=T.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "') AND E.FECHA_CONV LIKE '%" + conv + "'";

		else if (!tutor.equals("") && !conv.equals("") && ciclo.equals(""))
			query+= ", " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTTUTORES_CENTRO T WHERE E.N_CONV=S.N_CONV AND S.NIF_TC=T.NIF_TC AND T.NIF_TC=(SELECT NIF_TC FROM " + usr + ".FCTTUTORES_CENTRO WHERE NOMBRE='" + tutor + "') AND E.FECHA_CONV LIKE '%" + conv + "'";

		else if (tutor.equals("") && !conv.equals("") && !ciclo.equals(""))
			query+= ", " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTCICLOS CI, " + usr + ".FCTALUMNOS A WHERE E.N_CONV=S.N_CONV AND S.NIF_AL=A.NIF_AL AND A.NIF_AL=C.NIF_AL AND C.CLAVE=(SELECT CLAVE FROM " + usr + ".FCTCICLOS WHERE NOMBRE='" + ciclo + "') AND E.FECHA_CONV LIKE '%" + conv + "'";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			Empresa datos = (new Empresa ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13)));
			data.add(datos);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}

public ObservableList <Ciclo> filtroCiclo(String familiaprof, String nombre, String empresa){

	ObservableList<Ciclo> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();

		String query = "SELECT DISTINCT CI.CLAVE, CI.NOMBRE, CI.FAMILIA_PROF, CI.CLAVE_FAMILIA, CI.CAP_TERM, CI.ACT_FORM_PROD, CI.CRIT_EV FROM " + usr + ".FCTCICLOS CI";

		if (!familiaprof.equals("") && nombre.equals("") && empresa.equals(""))
			query+= "  WHERE CI.FAMILIA_PROF='" + familiaprof + "'";

		else if (familiaprof.equals("") && nombre.equals("") && !empresa.equals(""))
			query+= ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTALUMNOS A WHERE A.NIF_AL=S.NIF_AL AND C.NIF_AL=A.NIF_AL AND C.CLAVE=CI.CLAVE AND E.N_CONV=S.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') ";

		else if (familiaprof.equals("") && !nombre.equals("") && !empresa.equals(""))
			query+= ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTALUMNOS A WHERE A.NIF_AL=S.NIF_AL AND C.NIF_AL=A.NIF_AL AND C.CLAVE=CI.CLAVE AND E.N_CONV=S.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND CI.NOMBRE='" + nombre + "'";

		else if (!familiaprof.equals("") && nombre.equals("") && !empresa.equals(""))
			query+= ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTALUMNOS A WHERE A.NIF_AL=S.NIF_AL AND C.NIF_AL=A.NIF_AL AND C.CLAVE=CI.CLAVE AND E.N_CONV=S.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND CI.FAMILIA_PROF='" + familiaprof + "'";

		else if (!familiaprof.equals("") && !nombre.equals("") && !empresa.equals(""))
			query+= ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTCURSAN C, " + usr + ".FCTALUMNOS A WHERE A.NIF_AL=S.NIF_AL AND C.NIF_AL=A.NIF_AL AND C.CLAVE=CI.CLAVE AND E.N_CONV=S.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND CI.NOMBRE='" + nombre + "' AND CI.FAMILIA_PROF='" + familiaprof + "'";

		else if (!familiaprof.equals("") && !nombre.equals("") && empresa.equals(""))
			query+= "  WHERE CI.FAMILIA_PROF='" + familiaprof + "' AND CI.NOMBRE='" + nombre + "'";

		if (familiaprof.equals("") && !nombre.equals("") && empresa.equals(""))
			query+= "  WHERE CI.NOMBRE='" + nombre + "'";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			Ciclo datos = (new Ciclo ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7)));
			data.add(datos);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}

public ObservableList <TutorCentro> filtroTutor(String alumno, String empresa){

	ObservableList<TutorCentro> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();

		String query = "SELECT DISTINCT T.NIF_TC, T.NOMBRE, T.TELEFONO, T.EMAIL, T.CODIGO_CENTRO FROM " + usr + ".FCTTUTORES_CENTRO T";

		if (alumno.equals("") && !empresa.equals(""))
			query+= ", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTALUMNOS A WHERE S.NIF_TC=T.NIF_TC AND S.NIF_AL=A.NIF_AL AND S.N_CONV=E.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "')";

		else if (!alumno.equals("") && !empresa.equals(""))
			query +=", " + usr + ".FCTEMPRESAS E, " + usr + ".FCTSUPERVISAN S, " + usr + ".FCTALUMNOS A WHERE S.NIF_TC=T.NIF_TC AND S.NIF_AL=A.NIF_AL AND S.N_CONV=E.N_CONV AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".FCTEMPRESAS WHERE NOMBRE='" + empresa + "') AND S.NIF_AL='" + alumno + "'";

		else if (!alumno.equals("") && empresa.equals(""))
			query += ", " + usr + ".FCTALUMNOS A, " + usr + ".FCTSUPERVISAN S WHERE A.NIF_AL=S.NIF_AL AND T.NIF_TC=S.NIF_TC AND A.NIF_AL='" + alumno + "'";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			TutorCentro datos = (new TutorCentro ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
			data.add(datos);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}

public ObservableList <TutorCentro> buscarTutor(String nifTutor){

	ObservableList<TutorCentro> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();

		String query = "SELECT DISTINCT NIF_TC, NOMBRE, TELEFONO, EMAIL, CODIGO_CENTRO FROM " + usr + ".FCTTUTORES_CENTRO WHERE NIF_TC='" + nifTutor + "'";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			TutorCentro datos = (new TutorCentro ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
			data.add(datos);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}

public ObservableList <Alumno> buscarAlumno(String nifAlumno){

	ObservableList<Alumno> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();

		String query = "SELECT DISTINCT NIF_AL, NOMBRE, APELLIDOS, DIRECCION, CIUDAD, CP, PROVINCIA, TELEFONO, EMAIL FROM " + usr + ".FCTALUMNOS WHERE NIF_AL='" + nifAlumno + "'";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			Alumno datos = (new Alumno  ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9)));
			data.add(datos);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}

public ObservableList <Empresa> buscarEmpresa(String convenio){

	ObservableList<Empresa> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();

		String query = "SELECT DISTINCT N_CONV, REPRESENTANTE, NIF_REP, CIF, NOMBRE, DIRECCION, CP, CIUDAD, PROVINCIA, PAIS, TELEFONO, FAX, FECHA_CONV FROM " + usr + ".FCTEMPRESAS E WHERE N_CONV='" + convenio + "'";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			Empresa datos = (new Empresa  ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13)));
			data.add(datos);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}

public ObservableList <String> buscarCursos(){

	ObservableList<String> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();

		String query = "SELECT DISTINCT CURSO FROM " + usr + ".FCTCURSAN";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			String aux = rset.getString(1);
			data.add(aux);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}


public ObservableList <Ciclo> buscarCiclo(String clave){

	ObservableList<Ciclo> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();
		String query = "";
		if(clave.equals(""))
			query = "SELECT * FROM " + usr + ".FCTCICLOS";
		else
			query = "SELECT * FROM " + usr + ".FCTCICLOS WHERE CLAVE='" + clave + "'";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			Ciclo datos = (new Ciclo ( rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7)));
			data.add(datos);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}





public ObservableList<TutorEmpresa> buscarTutorEmpresa(String nConv){

	ObservableList<TutorEmpresa> data = FXCollections.observableArrayList();

	try {
		Statement stmt = conexion.createStatement();
		String query = "";
		query = "SELECT * FROM " + usr + ".FCTTUTORES_EMPRESA WHERE N_CONV='"+nConv+"'";

		ResultSet rset = stmt.executeQuery(query);
		while(rset.next()) {
			TutorEmpresa TE = new TutorEmpresa(rset.getString(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));
			data.add(TE);
		}
		rset.close();
		stmt.close();
		//conexion.close();

	}catch (SQLException s){
		s.printStackTrace();
	}

	return data;
}

}