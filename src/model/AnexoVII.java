package model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AnexoVII {
	DatosColegio coledata;
	DatosEmpresa empdata;
	DatosAlumnos alumno;




	public AnexoVII(DatosColegio coledata, DatosEmpresa empdata, DatosAlumnos alumno) {
		this.coledata = coledata;
		this.empdata = empdata;
		this.alumno = alumno;

	}

	/* Este método generará el PDF del AnexoVII con los datos que se han pasado al constructor
	 * recibirá como parámentro el nombre del archivo y la ruta donde se ha de almacenar, si estos parámetros están vacíos
	 * generará uno con AnexoI.pdf
	 */
	public void generarAnexoVII(String filename, String filepath) throws FileNotFoundException, DocumentException {

		// Se crea  el nombre del archvivo
		String sFileNamePath = "";

		if(!filepath.equals(""))
			sFileNamePath = filepath;

		if(filename.equals(""))
			sFileNamePath += "AnexoIII";
		else
			sFileNamePath += filename;

		sFileNamePath += ".pdf";


		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
		FileOutputStream ficheroPdf = new FileOutputStream(sFileNamePath);

		// Se crea el documento en vista apaisada
		Document documento = new Document(PageSize.A4.rotate());


		// Se asocia el documento al OutputStream y se indica que el espaciado entre
		// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
		PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

		// Se abre el documento.

		documento.open();

		Paragraph p1 = new Paragraph("Anexo 7 \n MÓDULO DE FORMACIÓN EN CENTROS DE TRABAJO",
		FontFactory.getFont("arial",   // fuente
		11,                            // tamaño
		Font.BOLD));
		p1.setAlignment(Element.ALIGN_CENTER);
		documento.add(p1);

		Paragraph p2 = new Paragraph("Ficha Semanal del Alumno \n\n",
				FontFactory.getFont("arial",   // fuente
				11,                            // tamaño
				Font.BOLDITALIC));
		p2.setAlignment(Element.ALIGN_CENTER);
		documento.add(p2);

		try
		{
			String path = "src\\view\\anexos\\CAM.jpg";
			Image foto = Image.getInstance(path);
			foto.scaleToFit(70, 70);
			foto.setAbsolutePosition(10, 520);
			//foto.setAlignment(Chunk.ALIGN_LEFT);
			documento.add(foto);
		}
		catch ( Exception e )
		{
			System.out.println(e.getMessage());

		}

		// No lleva logo UE
		/*
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
		}*/

		// DAtos

		PdfPTable tabla = new PdfPTable(2);
		tabla.setWidthPercentage(97);


		PdfPCell celda = new PdfPCell (new Paragraph("\nSemana de ...... a ........ de............................ de 20...: \n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		celda.setColspan(2);
		tabla.addCell(celda);

		celda = new PdfPCell (new Paragraph("\nCentro Docente: "+ this.coledata.getNombreCentro() +" \n\nProfesor Tutor del Centro Docente: " + this.alumno.getNombretutor() + "\n\n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda);

		PdfPCell celda1 = new PdfPCell (new Paragraph("\nEntidad Colaboradora: "+ this.empdata.getNombreemp() +" \n\nProfesor Tutor del Centro de Trabajo: " + this.alumno.getNombretutoremp() + "\n\n" ,
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda1);

		PdfPCell celda2 = new PdfPCell (new Paragraph("\nAlumno: "+ this.alumno.getNombrealumno() + " "+ this.alumno.getApellidosalumno() +" \n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda2);

		PdfPCell celda3 = new PdfPCell (new Paragraph("\nCiclo Formativo: "+ this.alumno.getCiclo() + "\n\n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.NORMAL)));             // color
		tabla.addCell(celda3);

		documento.add(tabla);


		p2 = new Paragraph("\n",
				FontFactory.getFont("arial",   // fuente
				8,                            // tamaño
				Font.BOLDITALIC));
		documento.add(p2);


		// Segunda Tabla
		PdfPTable tabla2 = new PdfPTable(4);
		tabla2.setWidthPercentage(97);

		// Asigna el tamaño diferente de las celdas
		float[] medidaCeldas = {0.40f, 2.20f, 0.60f, 0.80f};
		tabla2.setWidths(medidaCeldas);

		celda = new PdfPCell (new Paragraph("\nDÍAS\n\n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.ITALIC)));             // color

		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla2.addCell(celda);

		celda1 = new PdfPCell (new Paragraph("\nACTIVIDADES DESARROLLADAS\n\n" ,
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.ITALIC)));             // color
		celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla2.addCell(celda1);

		celda2 = new PdfPCell (new Paragraph("\nTIEMPO EMPLEADO \n\n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.ITALIC)));             // color
		celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla2.addCell(celda2);

		celda3 = new PdfPCell (new Paragraph("\nOBSERVACIONES \n\n",
                FontFactory.getFont("arial",   // fuente
                11,                            // tamaño
                Font.ITALIC)));             // color
		celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla2.addCell(celda3);

		String[] dias = {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES"};
		for(int i=0;i<5;i++){
			celda = new PdfPCell (new Paragraph("\n\n " + dias[i] +" \n\n" ,
	                FontFactory.getFont("arial",   // fuente
	                10,                            // tamaño
	                Font.NORMAL)));             // color
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla2.addCell(celda);
			tabla2.addCell(CeldaVacia());
			tabla2.addCell(CeldaVacia());
			tabla2.addCell(CeldaVacia());

		}

		documento.add(tabla2);



		Paragraph p15 = new Paragraph("\n           Firma del Alumno                                     VºBº Tutor del Centro                                          VºBº PROFESOR-TUTOR          \n\n\n            Fdo............................                                      Fdo............................                                     Fdo............................                  ",
				FontFactory.getFont("arial",   // fuente
				10));
		documento.add(p15);

		documento.close();


	}

	// Método que devuelve una celda vacía para insertar en la tabla
	public PdfPCell CeldaVacia(){

		PdfPCell celda = new PdfPCell (new Paragraph("\n\n   \n\n" ,
                FontFactory.getFont("arial",   // fuente
                10,                            // tamaño
                Font.NORMAL)));             // color

		return(celda);

	}
}

