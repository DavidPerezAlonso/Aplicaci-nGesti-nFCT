package model;

public class Alumno {

	private String NIF;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String ciudad;
	private String cp;
	private String provincia;
	private String telefono;
	private String email;


	public Alumno(String nIF, String nombre, String apellidos, String direccion, String ciudad, String cp,
			String provincia, String telefono, String email) {

		NIF = nIF;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.cp = cp;
		this.provincia = provincia;
		this.telefono = telefono;
		this.email = email;
	}



	public Alumno(String nIF, String nombre, String apellidos) {
		super();
		NIF = nIF;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}



	public String getNIF() {
		return NIF;
	}


	public void setNIF(String nIF) {
		NIF = nIF;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Alumno [NIF=" + NIF + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", ciudad=" + ciudad + ", cp=" + cp + ", provincia=" + provincia + ", telefono=" + telefono
				+ ", email=" + email + "]";
	}


}
