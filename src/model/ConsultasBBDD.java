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

public class ConsultasBBDD {
	
	private String url= "";
	private static String usr = "";
	private String pwd = "";
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
	
public ObservableList <Alumno> filtroAlumnos(String curso, String ciclo, String empresa){
	
	ObservableList<Alumno> data = FXCollections.observableArrayList();
	
	try {
		Statement stmt = conexion.createStatement();
		String query = "SELECT * FROM " + usr + ".ALUMNOS A, " + usr + ".CURSAN C, " + usr + ".CICLOS CI, " + usr + ".SUPERVISAN S, " + usr + ".EMPRESAS E";
		if (!curso.equals("") && ciclo.equals("") && empresa.equals(""))
			query+= " WHERE C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL";
		
		else if (!ciclo.equals("") && !ciclo.equals("") && empresa.equals(""))
			query+= " WHERE C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL AND CI.CLAVE=C.CLAVE AND CI.NOMBRE='" + ciclo + "'";
		
		else if (!ciclo.equals("") && !ciclo.equals("") && !empresa.equals(""))
			query += " WHERE C.CURSO='" + curso + "' AND C.NIF_AL=A.NIF_AL AND CI.CLAVE=C.CLAVE AND CI.NOMBRE='" + ciclo + "'AND E.N_CONV=(SELECT N_CONV FROM " + usr + ".EMPRESAS WHERE NOMBRE='" + empresa + "') AND E.N_CONV=S.N_CONV AND A.NIF_AL=S.NIF_AL";
		
		// SELECT * FROM ALUMNOS A, CURSAN C, CICLOS CI, SUPERVISAN S, EMPRESAS E WHERE C.CURSO='2018/2019' AND C.NIF_AL=A.NIF_AL AND CI.CLAVE=C.CLAVE AND CI.NOMBRE='Educacion Infantil' AND E.N_CONV=(SELECT N_CONV FROM EMPRESAS WHERE NOMBRE='Pezqueñines S.A') AND E.N_CONV=S.N_CONV AND A.NIF_AL=S.NIF_AL
		
		ResultSet rset = stmt.executeQuery(query);
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
}