package model;

public class Ciclo {
	
	private String clave;
	private String nombre;
	private String familia;
	private String clave_fam;
	private String capacidades;
	private String actividades;
	private String criterios;
	
	public Ciclo(String clave, String nombre, String familia, String clave_fam, String capacidades, String actividades,
			String criterios) {
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.familia = familia;
		this.clave_fam = clave_fam;
		this.capacidades = capacidades;
		this.actividades = actividades;
		this.criterios = criterios;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getClave_fam() {
		return clave_fam;
	}

	public void setClave_fam(String clave_fam) {
		this.clave_fam = clave_fam;
	}

	public String getCapacidades() {
		return capacidades;
	}

	public void setCapacidades(String capacidades) {
		this.capacidades = capacidades;
	}

	public String getActividades() {
		return actividades;
	}

	public void setActividades(String actividades) {
		this.actividades = actividades;
	}

	public String getCriterios() {
		return criterios;
	}

	public void setCriterios(String criterios) {
		this.criterios = criterios;
	}
	
	

}
