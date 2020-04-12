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

public class LoginBBDD {

	private String url= "";
	private static String user = "";
	private String pwd = "";
	private static String usr = "";
	private static Connection conexion;

		public LoginBBDD()  {

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

		public String comprobarUser(String usuario){

			String data = null;

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT USUARIO FROM " + usr + ".USUARIOS WHERE USUARIO='" + usuario + "'");
				while(rset.next()) {
					data = ( rset.getString(1));
				}
				rset.close();
				stmt.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}

		public String comprobarPass(String usuario){

			String data = null;

			try {
				Statement stmt = conexion.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT PASSWORD FROM " + usr + ".USUARIOS WHERE USUARIO='" + usuario + "'");
				while(rset.next()) {
					data = ( rset.getString(1));
				}
				rset.close();
				stmt.close();

			}catch (SQLException s){
				s.printStackTrace();
			}
			return data;
		}


		public static void crearUsuario(String usuario, String password) throws SQLException{

			Statement stmt = conexion.createStatement();

				try {

					stmt.executeUpdate("INSERT INTO " + usr + ".USUARIOS VALUES ('"+ usuario + "','" + password + "')");

					}catch(SQLException s) {
						s.printStackTrace();
					}

				stmt.close();
		}

		public static void cambiarPass(String password) throws SQLException{

			Statement stmt = conexion.createStatement();

			try {
				stmt.executeUpdate("UPDATE " + usr + ".USUARIOS SET PASSWORD='" + password + "'");

				}catch(SQLException s) {
					s.printStackTrace();
				}

				stmt.close();
		}
}
