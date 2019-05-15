package model;

public class DatosEmpresa {

	private String nombreemp;
	private String NIFEmp;
	private String NombreCentroemp;
	private String CodigoConvenio;
	private String provinciaemp;
	private String direccionemp ;
	private String CPEmp;
	private String CIFEmp;
	private String telefonoEmp ;
	private String faxEmp;
	private String ciudadEmp;


	public DatosEmpresa(String nombreemp, String nIFEmp, String CodigoConvenio,
			String codigoCentroemp, String provinciaemp, String direccionemp, String cPEmp, String cIFEmp,
			String telefonoEmp, String faxEmp, String ciudadEmp) {
		super();
		this.nombreemp = nombreemp;
		NIFEmp = nIFEmp;
		NombreCentroemp = CodigoConvenio;
		this.CodigoConvenio = codigoCentroemp;
		this.provinciaemp = provinciaemp;
		this.direccionemp = direccionemp;
		CPEmp = cPEmp;
		CIFEmp = cIFEmp;
		this.telefonoEmp = telefonoEmp;
		this.faxEmp = faxEmp;
		this.ciudadEmp = ciudadEmp;
	}


	public String getNombreemp() {
		return nombreemp;
	}


	public void setNombreemp(String nombreemp) {
		this.nombreemp = nombreemp;
	}

	public String getNIFEmp() {
		return NIFEmp;
	}


	public void setNIFEmp(String nIFEmp) {
		NIFEmp = nIFEmp;
	}


	public String getNombreCentroemp() {
		return NombreCentroemp;
	}


	public void setNombreCentroemp(String nombreCentroemp) {
		NombreCentroemp = nombreCentroemp;
	}


	public String getCodigoConvenio() {
		return CodigoConvenio;
	}


	public void setCodigoCentroemp(String CodigoConvenio) {
		CodigoConvenio = CodigoConvenio;
	}


	public String getProvinciaemp() {
		return provinciaemp;
	}


	public void setProvinciaemp(String provinciaemp) {
		this.provinciaemp = provinciaemp;
	}


	public String getDireccionemp() {
		return direccionemp;
	}


	public void setDireccionemp(String direccionemp) {
		this.direccionemp = direccionemp;
	}


	public String getCPEmp() {
		return CPEmp;
	}


	public void setCPEmp(String cPEmp) {
		CPEmp = cPEmp;
	}


	public String getCIFEmp() {
		return CIFEmp;
	}


	public void setCIFEmp(String cIFEmp) {
		CIFEmp = cIFEmp;
	}


	public String getTelefonoEmp() {
		return telefonoEmp;
	}


	public void setTelefonoEmp(String telefonoEmp) {
		this.telefonoEmp = telefonoEmp;
	}


	public String getFaxEmp() {
		return faxEmp;
	}


	public void setFaxEmp(String faxEmp) {
		this.faxEmp = faxEmp;
	}


	public String getCiudadEmp() {
		return ciudadEmp;
	}


	public void setCiudadEmp(String ciudadEmp) {
		this.ciudadEmp = ciudadEmp;
	}



}
