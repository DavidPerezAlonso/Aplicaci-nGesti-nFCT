package model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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

public class AnexoVIII {
	DatosColegio coledata;
	DatosEmpresa empdata;
	DatosAlumnos alumno;



	public AnexoVIII(DatosColegio coledata, DatosEmpresa empdata, DatosAlumnos alumno) {
		this.coledata = coledata;
		this.empdata = empdata;
		this.alumno = alumno;

	}

	/* Este método generará el PDF del AnexoII con los datos que se han pasado al constructor
	 * recibirá como parámentro el nombre del archivo y la ruta donde se ha de almacenar, si estos parámetros están vacíos
	 * generará uno con AnexoI.pdf
	 */
	public void generarAnexoVIII(String filename, String filepath) throws FileNotFoundException, DocumentException {

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



		//************************** cabecera aNEXO 8 ********************************//

		Paragraph p2 = new Paragraph("\nAnexo 8\nMODULO DE FORMACION EN CENTROS DE TRABAJO",
						FontFactory.getFont("arial",   // fuente
						11,                            // tamaño
						Font.BOLD));
		p2.setAlignment(Element.ALIGN_CENTER);
		documento.add(p2);

		Paragraph p3 = new Paragraph("INFORME DEL TUTOR DEL  CENTRO DE TRABAJO\n\n",
						FontFactory.getFont("arial",   // fuente
						11,
						Font.BOLDITALIC));
		p3.setAlignment(Element.ALIGN_CENTER);
		documento.add(p3);

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
			foto.setAbsolutePosition(600, 520);
			documento.add(foto);
		}
		catch ( Exception e )
		{
			System.out.println(e.getMessage());
		}



		// *************************************** tabla aNEXO 8
		PdfPTable tabla = new PdfPTable(2);
		tabla.setWidthPercentage(97);


		PdfPCell celda = new PdfPCell (new Paragraph("Alumno: " + this.alumno.getNombrealumno() + " " + this.alumno.getApellidosalumno() +"\n\nCentro Docente: "+ this.coledata.getNombreCentro() ,
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		celda.setColspan(2);
		tabla.addCell(celda);

		PdfPCell celda3 = new PdfPCell (new Paragraph("\nCiclo Formativo: "+ this.alumno.getCiclo() ,
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		celda3.setColspan(2);
		tabla.addCell(celda3);

		PdfPCell celda1 = new PdfPCell (new Paragraph("\nCentro Trabajo: "+ this.empdata.getNombreemp(),
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda1);

		PdfPCell celda4 = new PdfPCell (new Paragraph("\nHoras Realizadas: "+ this.alumno.getTotalhoras(),
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda4);

		PdfPCell celda2 = new PdfPCell (new Paragraph("\nTutor del módulo FCT en el Centro de Trabajo: "+ this.alumno.getNombretutoremp(),
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		celda2.setColspan(2);
		tabla.addCell(celda2);

		documento.add(tabla);

		// ************************** separador tabla
		Paragraph p10 = new Paragraph("\n",
				FontFactory.getFont("arial",   // fuente
				9));
		documento.add(p10);

		// *************************************** TABLA ***********************************/

		tabla = new PdfPTable(1);
		tabla.setWidthPercentage(97);


		celda = new PdfPCell (new Paragraph("1.- Áreas y puestos de trabajo donde ha desarrollado las actividades formativas:\n\n\n\n\n\n\n\n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.ITALIC)));             // color
		tabla.addCell(celda);

		celda1 = new PdfPCell (new Paragraph("2.- Valoración de la estancia del alumno en el centro de trabajo:\n\n\n\n\n\n\n\n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.ITALIC)));             // color
		tabla.addCell(celda1);

		celda2 = new PdfPCell (new Paragraph("3.- Modificaciones a introducir en el programa formativo:\n\n\n\n\n\n\n\n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.ITALIC)));             // color
		tabla.addCell(celda2);

		documento.add(tabla);



		// *************************** FIRMA ***************************************//
		Paragraph p15 = new Paragraph("\n\n\n        En                                                           a               de                                               de 20     \n                                                                EL TITULAR DEL CENTRO DE TRABAJO                 ",
					FontFactory.getFont("arial",   // fuente
					9));
		documento.add(p15);

		documento.close();


	}

}

