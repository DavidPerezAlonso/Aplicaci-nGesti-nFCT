package model;

public class AlumnoExcel extends Alumno{

	private String Ciclo;
	private String Curso;

	public AlumnoExcel(String nIF, String nombre, String apellidos, String direccion, String ciudad, String cp,
			String provincia, String telefono, String email, String ciclo, String curso) {
		super(nIF, nombre, apellidos, direccion, ciudad, cp, provincia, telefono, email);
		Ciclo = ciclo;
		Curso = curso;
	}

	public String getCiclo() {
		return Ciclo;
	}

	public void setCiclo(String ciclo) {
		Ciclo = ciclo;
	}

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	@Override
	public String toString() {
		return "AlumnoExcel ["+super.toString() +"Ciclo=" + Ciclo + ", Curso=" + Curso + "]";
	}




}
