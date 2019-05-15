package model;

public class TutorEmpresa {
	
	private String NIF_TE;
	private String nombre;
	private String telefono;
	private String email;
	private String nconv;
	
	public TutorEmpresa(String nIF_TE, String nombre, String telefono, String email, String nconv) {
		super();
		NIF_TE = nIF_TE;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.nconv = nconv;
	}

	public String getNIF_TE() {
		return NIF_TE;
	}

	public void setNIF_TE(String nIF_TE) {
		NIF_TE = nIF_TE;
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

	public String getNconv() {
		return nconv;
	}

	public void setNconv(String nconv) {
		this.nconv = nconv;
	}
	
}
