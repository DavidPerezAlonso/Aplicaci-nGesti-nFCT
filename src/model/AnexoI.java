package model;
import java.io.FileNotFoundException;
import com.itextpdf.text.Image;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import com.itextpdf.text.Image;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AnexoI {


	DatosColegio coledata;
	DatosEmpresa empdata;



	public AnexoI(DatosColegio coledata, DatosEmpresa empdata) {
		this.coledata = coledata;
		this.empdata = empdata;

	}

	/* Este m�todo generar� el PDF del AnexoI con los datos que se han pasado al constructor
	 * recibir� como par�mentro el nombre del archivo y la ruta donde se ha de almacenar, si estos par�metros est�n vac�os
	 * generar� uno con AnexoI.pdf
	 */
	public void generarAnexoI(String filename, String filepath) throws FileNotFoundException, DocumentException {
		String sFileNamePath = "";

		if(!filepath.equals(""))
			sFileNamePath = filepath;

		if(filename.equals(""))
			sFileNamePath += "AnexoI";
		else
			sFileNamePath += filename;

		sFileNamePath += ".pdf";

		// Se crea el documento
		Document documento = new Document();


		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
		FileOutputStream ficheroPdf = new FileOutputStream(sFileNamePath);

		// Se asocia el documento al OutputStream y se indica que el espaciado entre
		// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
		PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

		// Se abre el documento.
		documento.open();

		Paragraph p1 = new Paragraph("Anexo 1-b \n\n\n\n ",
		FontFactory.getFont("arial",   // fuente
		11,                            // tama�o
		Font.BOLD));
		p1.setAlignment(Element.ALIGN_CENTER);
		documento.add(p1);

		/*
		Paragraph p2 = new Paragraph("Convenio de Colaboraci�n Centro Educativo-Empresa",
						FontFactory.getFont("arial",   // fuente
						11,                            // tama�o
						Font.BOLDITALIC));
		p2.setAlignment(Element.ALIGN_CENTER);
		documento.add(p2);

		Paragraph p3 = new Paragraph("(Centros educativos de titularidad privada)",
						FontFactory.getFont("arial",   // fuente
						11,
						Font.NORMAL));
		p3.setAlignment(Element.ALIGN_CENTER);
		p3.setSpacingBefore(200);
		 */
		try
		{
			String path = "src\\view\\anexos\\CAM.jpg";
			Image foto = Image.getInstance(path);
			foto.scaleToFit(70, 70);
			foto.setAbsolutePosition(20, 740);
			//foto.setAlignment(Chunk.ALIGN_LEFT);
			documento.add(foto);
		}
		catch ( Exception e )
		{
			System.out.println(e.getMessage());
		}

		try
		{
			String path = "src\\view\\anexos\\UE.png";
			Image foto = Image.getInstance(path);
			foto.scaleToFit(90, 90);
			foto.setAbsolutePosition(450, 750);
			//foto.setAlignment(Chunk.ALIGN_LEFT);
			documento.add(foto);
		}
		catch ( Exception e )
		{
			System.out.println(e.getMessage());
		}

		// ------------------------------------ Primer recuadro
		PdfPTable tabla = new PdfPTable(1);
		tabla.setWidthPercentage(25);
		tabla.setHorizontalAlignment(Element.ALIGN_LEFT);


		PdfPCell celda = new PdfPCell (new Paragraph("\nCONVENIO N� " + empdata.getCodigoConvenio(),
                FontFactory.getFont("arial",   // fuente
                11,                            // tama�o
                Font.NORMAL)));             // color
		tabla.addCell(celda);
		documento.add(tabla);

		//------------------------------------ Segundo recuadro

		Paragraph p5 = new Paragraph("\n",FontFactory.getFont("arial", 10,  Font.NORMAL));
		documento.add(p5);

		tabla = new PdfPTable(1);
		tabla.setWidthPercentage(100);

		celda = new PdfPCell (new Paragraph("\nDe una parte: \n\nD. " + this.coledata.getNombre() +"\ncon NIF  " + this.coledata.getNIF() + "       como director del Centro Educativo    " + this.coledata.getNombreCentro() + "\nc�digo de centro   " + this.coledata.getCodigoCentro() + "  domiciliado en   " + this.coledata.getCiudad()+"\nprovincia de    " + this.coledata.getProvincia() + "     calle," + this.coledata.getDireccion() + "\nC.P. " + this.coledata.getCP() + ", CIF " + this.coledata.getCIF() + ", Telefono " + this.coledata.getTelefono() + ", Fax " + this.coledata.getFax()+"\n\n\nY de la otra \n\nD. " + this.empdata.getNombreemp() +"\ncon NIF " + this.empdata.getNIFEmp() + " como representante legal de la Empresa/Entidad colaboradora " + this.empdata.getNombreCentroemp() + "\ndomiciliada en " + this.empdata.getCiudadEmp()+"   provincia de " + this.empdata.getProvinciaemp() + " pa�s Espa�a\ncalle," + this.empdata.getDireccionemp() + " C.P. " + this.empdata.getCPEmp() + "\nCIF " + this.empdata.getCIFEmp() + ", Telefono " + this.empdata.getTelefonoEmp() + ", FaxEmp " + this.empdata.getFaxEmp() +"\n",
                FontFactory.getFont("arial",   // fuente
                9,                            // tama�o
                Font.NORMAL)));             // color
		tabla.addCell(celda);
		documento.add(tabla);

		//------------------------------------ EXPONEN

		Paragraph p11 = new Paragraph("\nEXPONEN ",
						FontFactory.getFont("arial",   // fuente
						10,                            // tama�o
						Font.BOLD));
		p11.setAlignment(Element.ALIGN_CENTER);
		documento.add(p11);

		Paragraph p12 = new Paragraph("� Que ambas partes se reconocen rec�procamente capacidad y legitimidad para convenir. \n� Que el objeto del presente Convenio es establecer la colaboraci�n entre las entidades a las que representan para el desarrollo de un Programa Formativo de Formaci�n en Centros de Trabajo, dirigido a los alumnos que cursan Formaci�n Profesional Reglada.\n� El m�dulo profesional de Formaci�n en Centros de Trabajo est� regulado por el art�culo 25 del Real Decreto 1147/2011, de 29 de julio, por el que se establece la ordenaci�n general de la formaci�n profesional del sistema educativo",
						FontFactory.getFont("arial",   // fuente
						9));
				p12.setAlignment(Element.ALIGN_JUSTIFIED);
				documento.add(p12);

				Paragraph p13 = new Paragraph("\nACUERDAN ",
						FontFactory.getFont("arial",   // fuente
						10,                            // tama�o
						Font.BOLD));
				p13.setAlignment(Element.ALIGN_CENTER);
				documento.add(p13);

				Paragraph p14 = new Paragraph("� Suscribir el presente Convenio de colaboraci�n para el desarrollo del m�dulo profesional de Formaci�n en Centros de Trabajo de los Ciclos Formativos y las Pr�cticas Formativas de otras ense�anzas, de acuerdo con las normas emitidas por la Consejer�a de Educaci�n y Juventud de la Comunidad de Madrid, que ambas partes conocen y acatan, y a lo dispuesto en las cl�usulas que figuran al dorso de este documento.\n� Este convenio de colaboraci�n entrar� en vigor a partir del momento de su firma y tendr� una vigencia de cuatro a�os de conformidad con lo establecido en el art�culo 49.h) de la Ley 40/2015, de 1 de octubre, de R�gimen jur�dico del Sector P�blico. \n� Asimismo, podr� prorrogarse por acuerdo un�nime de las partes antes de la finalizaci�n de dicho plazo de vigencia por un per�odo de cuatro a�os adicionales. Las causas de extinci�n de este convenio se estipulan en la cl�usula s�ptima que figura al dorso.         \n� Incorporar durante su per�odo de vigencia las relaciones nominales de alumnos acogidos al mismo (Relaci�n de Alumnos), la programaci�n de las actividades formativas a desarrollar por �stos en las empresas (Programa Formativo), y los documentos que faciliten su seguimiento y evaluaci�n.\n",
						FontFactory.getFont("arial",   // fuente
						9));
				p14.setAlignment(Element.ALIGN_JUSTIFIED);
				documento.add(p14);

		Paragraph p10 = new Paragraph("\n",
						FontFactory.getFont("arial",   // fuente
						8,                            // tama�o
						Font.NORMAL));
		p10.setAlignment(Element.ALIGN_CENTER);
		documento.add(p10);

		// ****************************** Recuadro de Firma

		tabla = new PdfPTable(1);
		tabla.setWidthPercentage(100);

		celda = new PdfPCell (new Paragraph("\nEn                                                           a               de                                               de      \n\nEL TITULAR DEL CENTRO                                                                     EL REPRESENTANTE  DE LA EMPRESA\n\n\n\n\n\nFdo.:                                                                                        Fdo.:",
		                FontFactory.getFont("arial",   // fuente
		                9,                            // tama�o
		                Font.NORMAL)));             // color
		tabla.addCell(celda);
		documento.add(tabla);


		// ************************************************************* Cla�sulas
		documento.newPage();
		Paragraph p16 = new Paragraph("\nCLAUSULAS ",
					FontFactory.getFont("arial",   // fuente
					9,                            // tama�o
					Font.BOLD));
		p16.setAlignment(Element.ALIGN_CENTER);
		documento.add(p16);

		Paragraph p17 = new Paragraph("PRIMERA.- Los alumnos que figuran en la �Relaci�n de Alumnos� del presente convenio desarrollar�n las actividades formativas programadas (Programa Formativo), en los locales del centro o centros de trabajo de la empresa firmante, o, en su caso, en aquellos lugares en los que la empresa desarrolle su actividad productiva, sin que ello implique relaci�n laboral alguna con ella.\n\nSEGUNDA.- La empresa se compromete al cumplimiento de la programaci�n de actividades formativas que previamente hayan sido acordadas con el centro educativo, a realizar su seguimiento y la valoraci�n del progreso de los alumnos y, junto con el tutor del centro educativo, a la revisi�n de la programaci�n, si una vez iniciado el per�odo de pr�cticas, y a la vista de los resultados, fuese necesario.\n\nTERCERA.- La empresa nombrar� un responsable para la coordinaci�n de las actividades formativas a realizar en el centro de trabajo, que garantizar� la orientaci�n y consulta del alumno, facilitar� las relaciones con el profesor-tutor del centro educativo y aportar� los informes valorativos que contribuyen a la evaluaci�n. A tal fin, facilitar� al profesor-tutor del centro educativo el acceso a la empresa y las actuaciones de valoraci�n y supervisi�n del proceso.\n\nCUARTA.- Cada alumno dispondr� de un documento de seguimiento y evaluaci�n de las actividades realizadas, que ser� supervisado por el responsable de la empresa en colaboraci�n con el tutor del centro educativo. En dicho documento figurar�n las actividades formativas m�s significativas realizadas en la empresa, con registro de los resultados obtenidos, que cumplimentar� el responsable de la empresa.\n\nQUINTA.- La empresa o entidad colaboradora no podr� cubrir, ni siquiera con car�cter interino, ning�n puesto de trabajo en plantilla con el alumno que realice actividades formativas en ella, salvo que se establezca al efecto una relaci�n laboral de contraprestaci�n econ�mica por servicios contratados. En este caso, se considerar� que el alumno abandona el programa formativo en el centro de trabajo, debi�ndose comunicar este hecho por la empresa o instituci�n colaboradora al Titular del Centro, quien lo pondr� en conocimiento de la Direcci�n del �rea Territorial correspondiente.\n\nSEXTA.- Los alumnos no percibir�n cantidad alguna por la realizaci�n de las actividades formativas en la empresa.\n\nS�PTIMA.- Se podr� acordar la extinci�n o rescisi�n del presente convenio, cuando concurra alguna de las circunstancias siguientes, en cuyo caso �sta ser� comunicada a la otra parte con una antelaci�n m�nima de tres meses:\n    a) Cese de actividades del centro docente o de la entidad colaboradora.\n    b) Imposibilidad de desarrollar adecuadamente las actividades programadas, por causas imprevistas.\n    c) Incumplimiento de las cl�usulas establecidas en el convenio de colaboraci�n en relaci�n con las normas por las que se rijan las actividades programadas.\n    d) De mutuo acuerdo.Igualmente, podr� excluirse la participaci�n en el convenio de uno o varios alumnos por decisi�n unilateral del centro docente, de la instituci�n colaboradora, o conjunta de ambos, previa audiencia del interesado, en los siguientes casos:\n    a) Faltas repetidas de asistencia o puntualidad no justificadas.\n    b) Actitud incorrecta o falta de aprovechamiento.\n    c) Incumplimiento del programa formativo en el centro de trabajo.\nEn cualquier caso, el centro docente deber� informar a la Direcci�n del �rea Territorial de la extinci�n o rescisi�n del Convenio.\nAsimismo, los representantes de los trabajadores de los centros de trabajo ser�n informados del contenido espec�fico del programa formativo que desarrollar�n los alumnos sujetos al convenio de colaboraci�n, de su duraci�n, del horario de las actividades, y la localizaci�n del centro o centros de trabajo donde �stas se realizar�n.\n\nOCTAVA.- Cualquier eventualidad de accidente que pudiera producirse ser� contemplada a tenor del Seguro Escolar, de acuerdo con la Reglamentaci�n establecida por el Decreto 2078/71 de 13 de agosto (BOE del 13 de septiembre). Todo ello sin perjuicio de la p�liza que la Consejer�a de Educaci�n pueda suscribir como seguro adicional para mejorar indemnizaciones, cubrir da�os a terceros o responsabilidad civil.\n\nNOVENA.- En todo momento, el alumno ir� provisto del D.N.I. o documento acreditativo de la identidad y tarjeta de identificaci�n del centro docente.\n\nD�CIMA.- El tratamiento de los datos de car�cter personal que se precisen con la finalidad de la gesti�n del m�dulo profesional de Formaci�n en Centros de Trabajo se realizar�, por ambas partes, seg�n lo estipulado en el Reglamento Europeo 2016/679, de 27 de abril de 2016, de Protecci�n de Datos Personales y la Ley Org�nica 3/2018, de 5 de diciembre, de Protecci�n de Datos Personales y garant�a de los derechos digitales en lo relativo al tratamiento de datos personales.\n\nUND�CIMA.- Las partes se obligan y se comprometen a poner en marcha las medidas que aseguren mantener durante la vigencia del presente Convenio, as� como tras su finalizaci�n, el m�s riguroso secreto profesional y el car�cter reservado y confidencial de los datos e informaciones relativos a las partes, sus instalaciones, productos y clientes, oblig�ndose a adoptar todas las medidas que sean necesarias con el objeto de dar cumplimiento a lo establecido en esta cl�usula.\n\nDUOD�CIMA.- El centro docente y la empresa cooperar�n para garantizar que el alumnado recibe la formaci�n y la informaci�n suficiente sobre los riesgos laborales que afectan a su actividad en la empresa y sobre las medidas de prevenci�n personal y colectiva, seguridad e higiene, actuaci�n ante emergencias, medidas medioambientales y equipos de protecci�n individual que deber� utilizar durante su estancia formativa y especialmente los relacionados con las actividades del programa formativo que deba desarrollar.\n\nD�CIMOTERCERA.- Cuando los alumnos sean menores de edad, la empresa deber� recabar de las personas que vayan a estar en contacto con menores de edad, la correspondiente certificaci�n negativa expedida por el Registro Central de Delincuentes Sexuales.La inexistencia de antecedentes penales por delitos de car�cter sexual es un requisito que debe mantenerse mientras se desarrolle la actividad que implica el contacto con menores.",
						FontFactory.getFont("arial",   // fuente
						7));
		p17.setAlignment(Element.ALIGN_JUSTIFIED);
		documento.add(p17);

		documento.close();


	}

}