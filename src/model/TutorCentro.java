package model;

public class TutorCentro {
	
	private String NIF_TC;
	private String nombre;
	private String telefono;
	private String email;
	private String cod_centro;
	
	public TutorCentro(String nIF_TC, String nombre, String telefono, String email, String cod_centro) {
		super();
		NIF_TC = nIF_TC;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.cod_centro = cod_centro;
	}

	public String getNIF_TC() {
		return NIF_TC;
	}

	public void setNIF_TC(String nIF_TC) {
		NIF_TC = nIF_TC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public String getCod_centro() {
		return cod_centro;
	}

	public void setCod_centro(String cod_centro) {
		this.cod_centro = cod_centro;
	}

}
