package model;

public class DatosColegio {

	/* Esta clase sirve para definir un objeto con todos los datos del Centro que se necesitan para
	 * formatear el Anexo I de FCTs
	 */

	private String nombre;
	private String NIF;
	private String NombreCentro;
	private String CodigoCentro;
	private String provincia;
	private String direccion;
	private String CP;
	private String CIF;
	private String telefono;
	private String fax;
	private String ciudad;
	private String DAT;



	public DatosColegio(String nombre, String nIF, String nombreCentro, String codigoCentro,
			String provincia, String direccion, String cP, String cIF, String telefono, String fax, String ciudad, String DAT) {
		super();
		this.nombre = nombre;
		NIF = nIF;
		NombreCentro = nombreCentro;
		CodigoCentro = codigoCentro;
		this.provincia = provincia;
		this.direccion = direccion;
		CP = cP;
		CIF = cIF;
		this.telefono = telefono;
		this.fax = fax;
		this.ciudad = ciudad;
		this.DAT = DAT;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNIF() {
		return NIF;
	}
	public void setNIF(String nIF) {
		NIF = nIF;
	}
	public String getNombreCentro() {
		return NombreCentro;
	}
	public void setNombreCentro(String nombreCentro) {
		NombreCentro = nombreCentro;
	}
	public String getCodigoCentro() {
		return CodigoCentro;
	}
	public void setCodigoCentro(String codigoCentro) {
		CodigoCentro = codigoCentro;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getCIF() {
		return CIF;
	}
	public void setCIF(String cIF) {
		CIF = cIF;
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
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDAT() {
		return DAT;
	}

	public void setDAT(String dAT) {
		DAT = dAT;
	}
	
}
