package model;

public class Centro {

	private String cod_centro;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String cp;
	private String provincia;
	private String telefono;
	private String fax;
	private String CIF;
	private String titular;
	private String NIF_TIT;
	private String DAT;
	
	public Centro(String cod_centro, String nombre, String direccion, String ciudad, String cp, String provincia,
			String telefono, String fax, String cIF, String titular, String nIF_TIT, String dAT) {
		super();
		this.cod_centro = cod_centro;
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.cp = cp;
		this.provincia = provincia;
		this.telefono = telefono;
		this.fax = fax;
		CIF = cIF;
		this.titular = titular;
		NIF_TIT = nIF_TIT;
		DAT = dAT;
	}

	public String getCod_centro() {
		return cod_centro;
	}

	public void setCod_centro(String cod_centro) {
		this.cod_centro = cod_centro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String cIF) {
		CIF = cIF;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNIF_TIT() {
		return NIF_TIT;
	}

	public void setNIF_TIT(String nIF_TIT) {
		NIF_TIT = nIF_TIT;
	}

	public String getDAT() {
		return DAT;
	}

	public void setDAT(String dAT) {
		DAT = dAT;
	}
	
}
