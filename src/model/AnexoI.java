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

		/*try
		{
			Image foto = Image.getInstance("CAM.jpg");
			//foto.scaleToFit(100, 100);
			foto.setAlignment(Chunk.ALIGN_LEFT);
			documento.add(foto);
		}
		catch ( Exception e )
		{
			System.out.println(e.getMessage());
		}*/

		Paragraph p1 = new Paragraph("Anexo 1 \n FORMACIÓN EN CENTROS DE TRABAJO ",
		FontFactory.getFont("arial",   // fuente
		11,                            // tamaño
		Font.BOLD));
		p1.setAlignment(Element.ALIGN_CENTER);
		documento.add(p1);

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
		documento.add(p3);


		Paragraph p4 = new Paragraph("\nCONVENIO Nº " + empdata.getCodigoConvenio(),
		FontFactory.getFont("arial",   // fuente
				10,                            // tamaño
						Font.BOLD));

				documento.add(p4);

				Paragraph p5 = new Paragraph("\nDe una parte ",
						FontFactory.getFont("arial",   // fuente
						9,                            // tamaño
						Font.BOLD));

				documento.add(p5);

				Paragraph p6 = new Paragraph("\nD. " + this.coledata.getNombre() +"\ncon NIF " + this.coledata.getNIF() + " como director del Centro Educativo " + this.coledata.getCodigoCentro() + "\ndomiciliado en " + this.coledata.getCiudad(),
						FontFactory.getFont("arial",   // fuente
						9));

				documento.add(p6);
				Paragraph p7 = new Paragraph("provincia de " + this.coledata.getProvincia() + " calle," + this.coledata.getDireccion() + "\nC.P. " + this.coledata.getCP() + ", CIF " + this.coledata.getCIF() + ", Telefono " + this.coledata.getTelefono() + ", Fax " + this.coledata.getFax(),
						FontFactory.getFont("arial",   // fuente
						9));

				documento.add(p7);


				Paragraph p8 = new Paragraph("\nY de la otra ",
						FontFactory.getFont("arial",   // fuente
						10,                            // tamaño
						Font.BOLD));

				documento.add(p8);

				Paragraph p9 = new Paragraph("\nD. " + this.empdata.getNombreemp() +"\ncon NIF " + this.empdata.getNIFEmp() + " como representante legal de la Empresa/Entidad colaboradora " + this.empdata.getNombreCentroemp() + "\ndomiciliada en " + this.empdata.getCiudadEmp(),
						FontFactory.getFont("arial",   // fuente
						9));

				documento.add(p9);

				Paragraph p10 = new Paragraph("provincia de " + this.empdata.getProvinciaemp() + " calle," + this.empdata.getDireccionemp() + "\nC.P. " + this.empdata.getCPEmp() + ", CIF " + this.empdata.getCIFEmp() + ", Telefono " + this.empdata.getTelefonoEmp() + ", FaxEmp " + this.empdata.getFaxEmp(),
						FontFactory.getFont("arial",   // fuente
						9));

				documento.add(p10);


				Paragraph p11 = new Paragraph("\nEXPONEN ",
						FontFactory.getFont("arial",   // fuente
						10,                            // tamaño
						Font.BOLD));
				p11.setAlignment(Element.ALIGN_CENTER);
				documento.add(p11);

				Paragraph p12 = new Paragraph("     • Que ambas partes se reconocen recíprocamente capacidad y legitimidad para convenir. \n     • Que el objeto del presente Convenio es establecer la colaboración entre las entidades a las que representan para el desarrollo de un Programa Formativo de Formación en Centros de Trabajo, dirigido a los alumnos que cursan Formación Profesional Reglada.\n     • El módulo profesional de Formación en Centros de Trabajo está regulado por el artículo 25 del Real Decreto 1147/2011, de 29 de julio, por el que se establece la ordenación general de la formación profesional del sistema educativo",
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

				Paragraph p14 = new Paragraph("         • Suscribir el presente Convenio de colaboración para el desarrollo del módulo profesional de Formación en Centros de Trabajo de los Ciclos Formativos y las Prácticas Formativas de otras enseñanzas, de acuerdo con las normas emitidas por la Consejería de Educación e Investigación de la Comunidad de Madrid, que ambas partes conocen y acatan, y a lo dispuesto en las cláusulas que figuran al dorso de este documento.\n    • Incorporar durante su período de vigencia las relaciones nominales de alumnos acogidos al mismo (Relación de Alumnos), la programación de las actividades formativas a desarrollar por éstos en las empresas (Programa Formativo), y los documentos que faciliten su seguimiento y evaluación.",
						FontFactory.getFont("arial",   // fuente
						9));
				p14.setAlignment(Element.ALIGN_JUSTIFIED);
				documento.add(p14);

				Paragraph p15 = new Paragraph("\n\n\nEn                                                           a               de                                               de      \nEL TITULAR DEL CENTRO                                                                     EL REPRESENTANTE  DE LA EMPRESA\n\n\n\n\nFdo.:                                                                     Fdo.:",
						FontFactory.getFont("arial",   // fuente
						9));
				documento.add(p15);

				Paragraph p16 = new Paragraph("\n\n\n\n\nCLAUSULAS ",
						FontFactory.getFont("arial",   // fuente
						10,                            // tamaño
						Font.BOLD));
				p16.setAlignment(Element.ALIGN_CENTER);
				documento.add(p16);

				Paragraph p17 = new Paragraph("PRIMERA.- Los alumnos que figuran en la «Relación de Alumnos» del presente convenio desarrollarán las actividades formativas programadas (Programa Formativo), en los locales del centro o centros de trabajo de la empresa firmante, o, en su caso, en aquellos lugares en los que la empresa desarrolle su actividad productiva, sin que ello implique relación laboral alguna con ella.\n\nSEGUNDA.- La empresa se compromete al cumplimiento de la programación de actividades formativas que previamente hayan sido acordadas con el centro educativo, a realizar su seguimiento y la valoración del progreso de los alumnos y, junto con el tutor del centro educativo, a la revisión de la programación, si una vez iniciado el período de prácticas, y a la vista de los resultados, fuese necesario.\n\nTERCERA.- La empresa nombrará un responsable para la coordinación de las actividades formativas a realizar en el centro de trabajo, que garantizará la orientación y consulta del alumno, facilitará las relaciones con el profesor-tutor del centro educativo y aportará los informes valorativos que contribuyen a la evaluación. A tal fin, facilitará al profesor-tutor del centro educativo el acceso a la empresa y las actuaciones de valoración y supervisión del proceso.\n\nCUARTA.- Cada alumno dispondrá de un documento de seguimiento y evaluación de las actividades realizadas, que será supervisado por el responsable de la empresa en colaboración con el tutor del centro educativo. En dicho documento figurarán las actividades formativas más significativas realizadas en la empresa, con registro de los resultados obtenidos, que cumplimentará el responsable de la empresa.\n\nQUINTA.- La empresa o entidad colaboradora no podrá cubrir, ni siquiera con carácter interino, ningún puesto de trabajo en plantilla con el alumno que realice actividades formativas en ella, salvo que se establezca al efecto una relación laboral de contraprestación económica por servicios contratados. En este caso, se considerará que el alumno abandona el programa formativo en el centro de trabajo, debiéndose comunicar este hecho por la empresa o institución colaboradora al Titular del Centro, quien lo pondrá en conocimiento de la Dirección del Área Territorial correspondiente.\n\nSEXTA.- Los alumnos no percibirán cantidad alguna por la realización de las actividades formativas en la empresa.\n\nSÉPTIMA.- La duración de este convenio es de un año a partir de su firma, considerándose prorrogado automáticamente cuando ninguna de las partes firmantes manifieste lo contrario. Podrá rescindirse por mutuo acuerdo entre el centro educativo y la institución colaboradora, o por denuncia de una de las partes, que será comunicada a la otra con una antelación mínima de 15 días, cuando concurra alguna de las circunstancias siguientes: \na) Cese de actividades del centro educativo o de la entidad colaboradora.\nb) Imposibilidad de desarrollar adecuadamente las actividades programadas, por causas imprevistas.\nc) Incumplimiento de las cláusulas establecidas en el convenio de colaboración en relación con las normas por las que se rijan las actividades programadas.\n\nIgualmente, podrá excluirse la participación en el convenio de uno o varios alumnos por decisión unilateral del centro educativo, de la institución colaboradora, o conjunta de ambos, previa audiencia del interesado, en los siguientes casos:\n\na) Faltas repetidas de asistencia o puntualidad no justificadas.\nb) Actitud incorrecta o falta de aprovechamiento.\nc) Incumplimiento del programa formativo en el centro de trabajo.\n\nEn cualquier caso, el Centro Educativo deberá informar a la Dirección del Área Territorial de la extinción o rescisión del Convenio.\nnAsimismo, los representantes de los trabajadores de los centros de trabajo serán informados del contenido específico del programa formativo que desarrollarán los alumnos sujetos al convenio de colaboración, de su duración, del horario de las actividades, y la localización del Centro o Centros de trabajo donde éstas se realizarán.\n\nOCTAVA.- Cualquier eventualidad de accidente que pudiera producirse será contemplada a tenor del Seguro Escolar, de acuerdo con la Reglamentación establecida por el Decreto 2078/71 de 13 de agosto (BOE del 13 de septiembre). Todo ello sin perjuicio de la póliza que la Consejería de Educación e Investigación pueda suscribir como seguro adicional para mejorar indemnizaciones, cubrir daños a terceros o responsabilidad civil.\n\nNOVENA.- En todo momento, el alumno irá provisto del D.N.I. o documento acreditativo de la identidad y tarjeta de identificación del centro educativo.",
						FontFactory.getFont("arial",   // fuente
						9));
				p17.setAlignment(Element.ALIGN_JUSTIFIED);
				documento.add(p17);

				documento.close();


	}

}