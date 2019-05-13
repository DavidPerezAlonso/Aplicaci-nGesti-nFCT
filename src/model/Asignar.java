package model;

public class Asignar {
	
	private String NIF_AL;
	private String NIF_TC;
	private String NIF_TE;
	private String nconv;
	private String fechaInicio;
	private String fechaFin;
	private String horasDia;
	private String horasTotales;
	private String horaInicioM;
	private String horaFinM;
	private String horaInicioT;
	private String horaFinT;
	
	public Asignar(String nIF_AL, String nIF_TC, String nIF_TE, String nconv, String fechaInicio, String fechaFin,
			String horasDia, String horasTotales, String horaInicioM, String horaFinM, String horaInicioT,
			String horaFinT) {
		super();
		NIF_AL = nIF_AL;
		NIF_TC = nIF_TC;
		NIF_TE = nIF_TE;
		this.nconv = nconv;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.horasDia = horasDia;
		this.horasTotales = horasTotales;
		this.horaInicioM = horaInicioM;
		this.horaFinM = horaFinM;
		this.horaInicioT = horaInicioT;
		this.horaFinT = horaFinT;
	}

	public String getNIF_AL() {
		return NIF_AL;
	}

	public void setNIF_AL(String nIF_AL) {
		NIF_AL = nIF_AL;
	}

	public String getNIF_TC() {
		return NIF_TC;
	}

	public void setNIF_TC(String nIF_TC) {
		NIF_TC = nIF_TC;
	}

	public String getNIF_TE() {
		return NIF_TE;
	}

	public void setNIF_TE(String nIF_TE) {
		NIF_TE = nIF_TE;
	}

	public String getNconv() {
		return nconv;
	}

	public void setNconv(String nconv) {
		this.nconv = nconv;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getHorasDia() {
		return horasDia;
	}

	public void setHorasDia(String horasDia) {
		this.horasDia = horasDia;
	}

	public String getHorasTotales() {
		return horasTotales;
	}

	public void setHorasTotales(String horasTotales) {
		this.horasTotales = horasTotales;
	}

	public String getHoraInicioM() {
		return horaInicioM;
	}

	public void setHoraInicioM(String horaInicioM) {
		this.horaInicioM = horaInicioM;
	}

	public String getHoraFinM() {
		return horaFinM;
	}

	public void setHoraFinM(String horaFinM) {
		this.horaFinM = horaFinM;
	}

	public String getHoraInicioT() {
		return horaInicioT;
	}

	public void setHoraInicioT(String horaInicioT) {
		this.horaInicioT = horaInicioT;
	}

	public String getHoraFinT() {
		return horaFinT;
	}

	public void setHoraFinT(String horaFinT) {
		this.horaFinT = horaFinT;
	}

}
