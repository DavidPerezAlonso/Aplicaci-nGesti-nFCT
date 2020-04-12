package model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AnexoII {
	DatosColegio coledata;
	DatosEmpresa empdata;
	DatosAlumnos alumno;



	public AnexoII(DatosColegio coledata, DatosEmpresa empdata, DatosAlumnos alumno) {
		this.coledata = coledata;
		this.empdata = empdata;
		this.alumno = alumno;

	}

	/* Este método generará el PDF del AnexoII con los datos que se han pasado al constructor
	 * recibirá como parámentro el nombre del archivo y la ruta donde se ha de almacenar, si estos parámetros están vacíos
	 * generará uno con AnexoI.pdf
	 */
	public void generarAnexoII(String filename, String filepath) throws FileNotFoundException, DocumentException {
		String sFileNamePath = "";

		if(!filepath.equals(""))
			sFileNamePath = filepath;

		if(filename.equals(""))
			sFileNamePath += "AnexoII";
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

		// ****************************************** ENCABEZADO Anexo 2.1

		Paragraph p1 = new Paragraph("Anexo 2.1 \n MÓDULO DE FORMACIÓN EN CENTROS DE TRABAJO ",
		FontFactory.getFont("arial",   // fuente
		11,                            // tamaño
		Font.BOLD));
		p1.setAlignment(Element.ALIGN_CENTER);
		documento.add(p1);

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

		Paragraph p2 = new Paragraph("\n\nRelación de Alumnos (1)               Dirección del Área Territorial de MADRID " + this.coledata.getDAT(),
						FontFactory.getFont("arial",   // fuente
						11,                            // tamaño
						Font.BOLDITALIC));
		p2.setAlignment(Element.ALIGN_CENTER);
		documento.add(p2);

		// ********************************* Primer Recuadro
		Paragraph p = new Paragraph("\n",
				FontFactory.getFont("arial",   // fuente
				9));
		documento.add(p);
		PdfPTable tabla = new PdfPTable(2);
		tabla.setWidthPercentage(50);

		PdfPCell celda = new PdfPCell (new Paragraph("\nNº del CONVENIO" ,
                FontFactory.getFont("arial",   // fuente
                10,                            // tamaño
                Font.BOLD)));             // color
		celda.setBackgroundColor(BaseColor.GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(celda);

		celda = new PdfPCell (new Paragraph("\nNº del Anexo 2.1 " ,
                FontFactory.getFont("arial",   // fuente
                10,                            // tamaño
                Font.BOLD)));             // color
		celda.setBackgroundColor(BaseColor.GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(celda);

		celda = new PdfPCell (new Paragraph("\n"+empdata.getCodigoConvenio() ,
                FontFactory.getFont("arial",   // fuente
                10,                            // tamaño
                Font.BOLD)));             // color
		tabla.addCell(celda);

		celda = new PdfPCell (new Paragraph("\n" ,
                FontFactory.getFont("arial",   // fuente
                10,                            // tamaño
                Font.BOLD)));             // color
		tabla.addCell(celda);

		documento.add(tabla);


		// ********************************* Segundo Recuadro

		Paragraph p11 = new Paragraph("\n",
				FontFactory.getFont("arial",   // fuente
				9));
		documento.add(p11);

		tabla = new PdfPTable(1);
		tabla.setWidthPercentage(100);

		celda = new PdfPCell (new Paragraph("\nRelación de alumnos acogidos al CONVENIO  Nº " + empdata.getCodigoConvenio() + " suscrito con fecha ..............." + " \nentre el Centro Educativo " + coledata.getNombreCentro() + "\n y la Empresa " + empdata.getNombreCentroemp() + " ,\n que realizarán el módulo de Formación en Centros de Trabajo (FCT) o Prácticas Formativas en el período abajo indicado.\n" ,
                FontFactory.getFont("arial",   // fuente
                9,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda);

		documento.add(tabla);

		// ********************************************************* Párrafo Central

		Paragraph p5 = new Paragraph("\nCURSO ACADÉMICO: " + alumno.getCurso() +"\nCLAVE: " + alumno.getClaveciclo() + " CICLO FORMATIVO: " + alumno.getCiclo() + "\nOTRAS ENSEÑANZAS: ………………..…………………………………………..………………………………………………………… ",
						FontFactory.getFont("arial",   // fuente
						9));

		documento.add(p5);

		Paragraph p6 = new Paragraph("\nFecha de inicio:  " + alumno.getFechainicio() + "    Fecha de terminación:  " + alumno.getFechafin() + "    Días de la semana  " + alumno.getDiassemana(),
						FontFactory.getFont("arial",   // fuente
						9));

		documento.add(p6);
		Paragraph p7 = new Paragraph("\n              Horario de mañana                                                  Horario de tarde                                            Horas día:       Total Horas:",
						FontFactory.getFont("arial",   // fuente
						9));

		documento.add(p7);


		Paragraph p8 = new Paragraph("\nHora inicio: " + alumno.getHorainiciomañana() + " Hora terminación: " + alumno.getHorafinmañana()+" Hora de inicio: " + alumno.getHorainiciotarde() + " Hora de terminación: " + alumno.getHorafintarde() + "                  " + alumno.getHorasdia() +  "   " + alumno.getTotalhoras(),
						FontFactory.getFont("arial",   // fuente
						10));
		documento.add(p8);

		Paragraph p9 = new Paragraph("\nLOCALIDAD DEL CENTRO DE TRABAJO: " + alumno.getLocalidad() + "  DIRECCIÓN: " + alumno.getDireccion() + "\n",
						FontFactory.getFont("arial",   // fuente
						9));

		documento.add(p9);

		// *************************** Recuadro de alumnos
		p11 = new Paragraph("\n",
				FontFactory.getFont("arial",   // fuente
				9));
		documento.add(p11);

		tabla = new PdfPTable(2);
		tabla.setWidthPercentage(50);

		float[] medidaCeldas = {0.60f,  0.40f};
		tabla.setWidths(medidaCeldas);

		celda = new PdfPCell (new Paragraph("APELLIDOS y Nombre " ,
		                FontFactory.getFont("arial",   // fuente
		                10,                            // tamaño
		                Font.BOLD)));             // color
		celda.setBackgroundColor(BaseColor.GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(celda);

		celda = new PdfPCell (new Paragraph("DNI " ,
		                FontFactory.getFont("arial",   // fuente
		                10,                            // tamaño
		                Font.BOLD)));             // color
		celda.setBackgroundColor(BaseColor.GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(celda);

		celda = new PdfPCell (new Paragraph(alumno.getApellidosalumno() + " , " + alumno.getNombrealumno(),
                FontFactory.getFont("arial",   // fuente
                9,                            // tamaño
                Font.NORMAL)));
		tabla.addCell(celda);
		celda = new PdfPCell (new Paragraph(alumno.getDNIalumno(),
                FontFactory.getFont("arial",   // fuente
                9,                            // tamaño
                Font.NORMAL)));
		tabla.addCell(celda);

		for (int i = 0; i < 9; i++)
		{
			tabla.addCell(" ");
		}
		documento.add(tabla);

		// ******************************** Segundo párrago
		p11 = new Paragraph("\n",
				FontFactory.getFont("arial",   // fuente
				9));
		documento.add(p11);
		Paragraph p10 = new Paragraph("\nEn cumplimiento de la cláusula tercera del CONVENIO, se procede a designar Profesor-Tutor del Centro Educativo a D." + alumno.getNombretutor() + " con NIF " + alumno.getDNItutor() +  " y Tutor del centro de trabajo a D." + alumno.getNombretutoremp(),
					FontFactory.getFont("arial",   // fuente
						9));

		documento.add(p10);

		// ******************************** Recuadro de Firma
		p11 = new Paragraph("\n",
				FontFactory.getFont("arial",   // fuente
				9));
		documento.add(p11);
		tabla = new PdfPTable(1);
		tabla.setWidthPercentage(100);

		celda = new PdfPCell (new Paragraph("\n\nEn                                                           a               de                                               de      \n\nEL EL DIRECTOR DEL CENTRO EDUCATIVO                                                             EL REPRESENTANTE  DE LA EMPRESA\n\n\n\n\nFdo.:                                                                     Fdo.:" ,
		                FontFactory.getFont("arial",   // fuente
		                9,                            // tamaño
		                Font.NORMAL)));             // color
		tabla.addCell(celda);
		documento.add(celda);
		documento.add(tabla);

		// ++++++++++++++++++++++++++++++ Letra pequeña
		Paragraph p16 = new Paragraph("    (1) Se cumplimentará un Anexo 2.1 por cada grupo de alumnos del mismo Ciclo Formativo o modalidad de enseñanza, que realice el módulo de FCT en la misma Institución.\n    (2) Especifíquese el Nº del CONVENIO, suscrito con anterioridad, al que se vincularán los Anexos 2.1 que sean necesarios.\n    (3) Consígnese correlativamente el nº del Anexo 2.1 con dos dígitos numéricos (01, 02, 03 ...).\n    (4) Se expresarán las horas reales de realización de la actividad, excluyendo, en el caso de jornada partida, las horas dedicadas a la comida.",
						FontFactory.getFont("arial",   // fuente
						5));
		documento.add(p16);

		documento.close();


	}

}

