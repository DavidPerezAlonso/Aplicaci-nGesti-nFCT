package model;

public class Empresa {
	
	private String nConv;
	private String representante;
	private String NIF_REP;
	private String CIF;
	private String nombre;
	private String direccion;
	private String cp;
	private String ciudad;
	private String provincia;
	private String pais;
	private String telefono;
	private String fax;
	private String fecha_conv;
	
	public Empresa(String nConv, String representante, String nIF_REP, String cIF, String nombre, String direccion,
			String cp, String ciudad, String provincia, String pais, String telefono, String fax, String fecha_conv) {
		super();
		this.nConv = nConv;
		this.representante = representante;
		this.NIF_REP = nIF_REP;
		this.CIF = cIF;
		this.nombre = nombre;
		this.direccion = direccion;
		this.cp = cp;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
		this.telefono = telefono;
		this.fax = fax;
		this.fecha_conv = fecha_conv;
	}

	public String getnConv() {
		return nConv;
	}

	public void setnConv(String nConv) {
		this.nConv = nConv;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getNIF_REP() {
		return NIF_REP;
	}

	public void setNIF_REP(String nIF_REP) {
		NIF_REP = nIF_REP;
	}

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String cIF) {
		CIF = cIF;
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

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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

	public String getFecha_conv() {
		return fecha_conv;
	}

	public void setFecha_conv(String fecha_conv) {
		this.fecha_conv = fecha_conv;
	}

}
