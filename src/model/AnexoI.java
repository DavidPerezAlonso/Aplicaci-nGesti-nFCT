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

	/* Este método generará el PDF del AnexoI con los datos que se han pasado al constructor
	 * recibirá como parámentro el nombre del archivo y la ruta donde se ha de almacenar, si estos parámetros están vacíos
	 * generará uno con AnexoI.pdf
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
		11,                            // tamaño
		Font.BOLD));
		p1.setAlignment(Element.ALIGN_CENTER);
		documento.add(p1);

		/*
		Paragraph p2 = new Paragraph("Convenio de Colaboración Centro Educativo-Empresa",
						FontFactory.getFont("arial",   // fuente
						11,                            // tamaño
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


		PdfPCell celda = new PdfPCell (new Paragraph("\nCONVENIO Nº " + empdata.getCodigoConvenio(),
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda);
		documento.add(tabla);

		//------------------------------------ Segundo recuadro

		Paragraph p5 = new Paragraph("\n",FontFactory.getFont("arial", 10,  Font.NORMAL));
		documento.add(p5);

		tabla = new PdfPTable(1);
		tabla.setWidthPercentage(100);

		celda = new PdfPCell (new Paragraph("\nDe una parte: \n\nD. " + this.coledata.getNombre() +"\ncon NIF  " + this.coledata.getNIF() + "       como director del Centro Educativo    " + this.coledata.getNombreCentro() + "\ncódigo de centro   " + this.coledata.getCodigoCentro() + "  domiciliado en   " + this.coledata.getCiudad()+"\nprovincia de    " + this.coledata.getProvincia() + "     calle," + this.coledata.getDireccion() + "\nC.P. " + this.coledata.getCP() + ", CIF " + this.coledata.getCIF() + ", Telefono " + this.coledata.getTelefono() + ", Fax " + this.coledata.getFax()+"\n\n\nY de la otra \n\nD. " + this.empdata.getNombreemp() +"\ncon NIF " + this.empdata.getNIFEmp() + " como representante legal de la Empresa/Entidad colaboradora " + this.empdata.getNombreCentroemp() + "\ndomiciliada en " + this.empdata.getCiudadEmp()+"   provincia de " + this.empdata.getProvinciaemp() + " país España\ncalle," + this.empdata.getDireccionemp() + " C.P. " + this.empdata.getCPEmp() + "\nCIF " + this.empdata.getCIFEmp() + ", Telefono " + this.empdata.getTelefonoEmp() + ", FaxEmp " + this.empdata.getFaxEmp() +"\n",
                FontFactory.getFont("arial",   // fuente
                9,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda);
		documento.add(tabla);

		//------------------------------------ EXPONEN

		Paragraph p11 = new Paragraph("\nEXPONEN ",
						FontFactory.getFont("arial",   // fuente
						10,                            // tamaño
						Font.BOLD));
		p11.setAlignment(Element.ALIGN_CENTER);
		documento.add(p11);

		Paragraph p12 = new Paragraph("• Que ambas partes se reconocen recíprocamente capacidad y legitimidad para convenir. \n• Que el objeto del presente Convenio es establecer la colaboración entre las entidades a las que representan para el desarrollo de un Programa Formativo de Formación en Centros de Trabajo, dirigido a los alumnos que cursan Formación Profesional Reglada.\n• El módulo profesional de Formación en Centros de Trabajo está regulado por el artículo 25 del Real Decreto 1147/2011, de 29 de julio, por el que se establece la ordenación general de la formación profesional del sistema educativo",
						FontFactory.getFont("arial",   // fuente
						9));
				p12.setAlignment(Element.ALIGN_JUSTIFIED);
				documento.add(p12);

				Paragraph p13 = new Paragraph("\nACUERDAN ",
						FontFactory.getFont("arial",   // fuente
						10,                            // tamaño
						Font.BOLD));
				p13.setAlignment(Element.ALIGN_CENTER);
				documento.add(p13);

				Paragraph p14 = new Paragraph("• Suscribir el presente Convenio de colaboración para el desarrollo del módulo profesional de Formación en Centros de Trabajo de los Ciclos Formativos y las Prácticas Formativas de otras enseñanzas, de acuerdo con las normas emitidas por la Consejería de Educación y Juventud de la Comunidad de Madrid, que ambas partes conocen y acatan, y a lo dispuesto en las cláusulas que figuran al dorso de este documento.\n• Este convenio de colaboración entrará en vigor a partir del momento de su firma y tendrá una vigencia de cuatro años de conformidad con lo establecido en el artículo 49.h) de la Ley 40/2015, de 1 de octubre, de Régimen jurídico del Sector Público. \n• Asimismo, podrá prorrogarse por acuerdo unánime de las partes antes de la finalización de dicho plazo de vigencia por un período de cuatro años adicionales. Las causas de extinción de este convenio se estipulan en la cláusula séptima que figura al dorso.         \n• Incorporar durante su período de vigencia las relaciones nominales de alumnos acogidos al mismo (Relación de Alumnos), la programación de las actividades formativas a desarrollar por éstos en las empresas (Programa Formativo), y los documentos que faciliten su seguimiento y evaluación.\n",
						FontFactory.getFont("arial",   // fuente
						9));
				p14.setAlignment(Element.ALIGN_JUSTIFIED);
				documento.add(p14);

		Paragraph p10 = new Paragraph("\n",
						FontFactory.getFont("arial",   // fuente
						8,                            // tamaño
						Font.NORMAL));
		p10.setAlignment(Element.ALIGN_CENTER);
		documento.add(p10);

		// ****************************** Recuadro de Firma

		tabla = new PdfPTable(1);
		tabla.setWidthPercentage(100);

		celda = new PdfPCell (new Paragraph("\nEn                                                           a               de                                               de      \n\nEL TITULAR DEL CENTRO                                                                     EL REPRESENTANTE  DE LA EMPRESA\n\n\n\n\n\nFdo.:                                                                                        Fdo.:",
		                FontFactory.getFont("arial",   // fuente
		                9,                            // tamaño
		                Font.NORMAL)));             // color
		tabla.addCell(celda);
		documento.add(tabla);


		// ************************************************************* Claúsulas
		documento.newPage();
		Paragraph p16 = new Paragraph("\nCLAUSULAS ",
					FontFactory.getFont("arial",   // fuente
					9,                            // tamaño
					Font.BOLD));
		p16.setAlignment(Element.ALIGN_CENTER);
		documento.add(p16);

		Paragraph p17 = new Paragraph("PRIMERA.- Los alumnos que figuran en la «Relación de Alumnos» del presente convenio desarrollarán las actividades formativas programadas (Programa Formativo), en los locales del centro o centros de trabajo de la empresa firmante, o, en su caso, en aquellos lugares en los que la empresa desarrolle su actividad productiva, sin que ello implique relación laboral alguna con ella.\n\nSEGUNDA.- La empresa se compromete al cumplimiento de la programación de actividades formativas que previamente hayan sido acordadas con el centro educativo, a realizar su seguimiento y la valoración del progreso de los alumnos y, junto con el tutor del centro educativo, a la revisión de la programación, si una vez iniciado el período de prácticas, y a la vista de los resultados, fuese necesario.\n\nTERCERA.- La empresa nombrará un responsable para la coordinación de las actividades formativas a realizar en el centro de trabajo, que garantizará la orientación y consulta del alumno, facilitará las relaciones con el profesor-tutor del centro educativo y aportará los informes valorativos que contribuyen a la evaluación. A tal fin, facilitará al profesor-tutor del centro educativo el acceso a la empresa y las actuaciones de valoración y supervisión del proceso.\n\nCUARTA.- Cada alumno dispondrá de un documento de seguimiento y evaluación de las actividades realizadas, que será supervisado por el responsable de la empresa en colaboración con el tutor del centro educativo. En dicho documento figurarán las actividades formativas más significativas realizadas en la empresa, con registro de los resultados obtenidos, que cumplimentará el responsable de la empresa.\n\nQUINTA.- La empresa o entidad colaboradora no podrá cubrir, ni siquiera con carácter interino, ningún puesto de trabajo en plantilla con el alumno que realice actividades formativas en ella, salvo que se establezca al efecto una relación laboral de contraprestación económica por servicios contratados. En este caso, se considerará que el alumno abandona el programa formativo en el centro de trabajo, debiéndose comunicar este hecho por la empresa o institución colaboradora al Titular del Centro, quien lo pondrá en conocimiento de la Dirección del Área Territorial correspondiente.\n\nSEXTA.- Los alumnos no percibirán cantidad alguna por la realización de las actividades formativas en la empresa.\n\nSÉPTIMA.- Se podrá acordar la extinción o rescisión del presente convenio, cuando concurra alguna de las circunstancias siguientes, en cuyo caso ésta será comunicada a la otra parte con una antelación mínima de tres meses:\n    a) Cese de actividades del centro docente o de la entidad colaboradora.\n    b) Imposibilidad de desarrollar adecuadamente las actividades programadas, por causas imprevistas.\n    c) Incumplimiento de las cláusulas establecidas en el convenio de colaboración en relación con las normas por las que se rijan las actividades programadas.\n    d) De mutuo acuerdo.Igualmente, podrá excluirse la participación en el convenio de uno o varios alumnos por decisión unilateral del centro docente, de la institución colaboradora, o conjunta de ambos, previa audiencia del interesado, en los siguientes casos:\n    a) Faltas repetidas de asistencia o puntualidad no justificadas.\n    b) Actitud incorrecta o falta de aprovechamiento.\n    c) Incumplimiento del programa formativo en el centro de trabajo.\nEn cualquier caso, el centro docente deberá informar a la Dirección del Área Territorial de la extinción o rescisión del Convenio.\nAsimismo, los representantes de los trabajadores de los centros de trabajo serán informados del contenido específico del programa formativo que desarrollarán los alumnos sujetos al convenio de colaboración, de su duración, del horario de las actividades, y la localización del centro o centros de trabajo donde éstas se realizarán.\n\nOCTAVA.- Cualquier eventualidad de accidente que pudiera producirse será contemplada a tenor del Seguro Escolar, de acuerdo con la Reglamentación establecida por el Decreto 2078/71 de 13 de agosto (BOE del 13 de septiembre). Todo ello sin perjuicio de la póliza que la Consejería de Educación pueda suscribir como seguro adicional para mejorar indemnizaciones, cubrir daños a terceros o responsabilidad civil.\n\nNOVENA.- En todo momento, el alumno irá provisto del D.N.I. o documento acreditativo de la identidad y tarjeta de identificación del centro docente.\n\nDÉCIMA.- El tratamiento de los datos de carácter personal que se precisen con la finalidad de la gestión del módulo profesional de Formación en Centros de Trabajo se realizará, por ambas partes, según lo estipulado en el Reglamento Europeo 2016/679, de 27 de abril de 2016, de Protección de Datos Personales y la Ley Orgánica 3/2018, de 5 de diciembre, de Protección de Datos Personales y garantía de los derechos digitales en lo relativo al tratamiento de datos personales.\n\nUNDÉCIMA.- Las partes se obligan y se comprometen a poner en marcha las medidas que aseguren mantener durante la vigencia del presente Convenio, así como tras su finalización, el más riguroso secreto profesional y el carácter reservado y confidencial de los datos e informaciones relativos a las partes, sus instalaciones, productos y clientes, obligándose a adoptar todas las medidas que sean necesarias con el objeto de dar cumplimiento a lo establecido en esta cláusula.\n\nDUODÉCIMA.- El centro docente y la empresa cooperarán para garantizar que el alumnado recibe la formación y la información suficiente sobre los riesgos laborales que afectan a su actividad en la empresa y sobre las medidas de prevención personal y colectiva, seguridad e higiene, actuación ante emergencias, medidas medioambientales y equipos de protección individual que deberá utilizar durante su estancia formativa y especialmente los relacionados con las actividades del programa formativo que deba desarrollar.\n\nDÉCIMOTERCERA.- Cuando los alumnos sean menores de edad, la empresa deberá recabar de las personas que vayan a estar en contacto con menores de edad, la correspondiente certificación negativa expedida por el Registro Central de Delincuentes Sexuales.La inexistencia de antecedentes penales por delitos de carácter sexual es un requisito que debe mantenerse mientras se desarrolle la actividad que implica el contacto con menores.",
						FontFactory.getFont("arial",   // fuente
						7));
		p17.setAlignment(Element.ALIGN_JUSTIFIED);
		documento.add(p17);

		documento.close();


	}

}