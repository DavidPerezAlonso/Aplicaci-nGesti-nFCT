package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeerFicherosExcel {

	private File file;

	public LeerFicherosExcel() {
		super();
		// TODO Auto-generated constructor stub
		// muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Excel");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("LibreOffice", "*.ods"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        Window stage = null;

        // Obtener el archivo seleccionado
        file = fileChooser.showOpenDialog(stage);
	}

	public ArrayList<AlumnoExcel> ImportarAlumnos() throws InvalidFormatException, IOException {

		ArrayList<AlumnoExcel> listaAlumnos = new ArrayList<AlumnoExcel>();

		if (file == null)
			return listaAlumnos;


		// leer archivo excel
		XSSFWorkbook worbook = new XSSFWorkbook(file);
		//obtener la hoja que se va leer
		XSSFSheet sheet = worbook.getSheetAt(0);
		//obtener todas las filas de la hoja excel
		Iterator<Row> rowIterator = sheet.iterator();

		Row row;
		// se recorre cada fila hasta el final
		while (rowIterator.hasNext()) {
			row = rowIterator.next();

			// Salta la primera línea de encabezado
			if(row.getRowNum() == 0)
				continue;

			//se obtiene las celdas por fila
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell cell;

			String NIF = "";
			String nombre= "";
			String apellidos= "";
			String direccion= "";
			String ciudad= "";
			String cp= "";
			String provincia= "";
			String telefono= "";
			String email= "";
			String ciclo= "";
			String curso= "";

			//se recorre cada celda

			while (cellIterator.hasNext()) {
				// se obtiene la celda en específico y se la imprime
				cell = cellIterator.next();

				switch(cell.getColumnIndex()){

				case 0:
					NIF = LFEgetCellValue(cell);
					break;

				case 1:
					nombre = cell.getStringCellValue();
					break;

				case 2:
					apellidos = cell.getStringCellValue();
					break;

				case 3:
					direccion = cell.getStringCellValue();
					break;

				case 4:
					ciudad = cell.getStringCellValue();
					break;

				case 5:
					cp = LFEgetCellValue(cell);
					break;

				case 6:
					provincia = cell.getStringCellValue();
					break;

				case 7:
					telefono = LFEgetCellValue(cell);
					break;

				case 8:
					email = cell.getStringCellValue();
					break;

				case 9:
					ciclo = cell.getStringCellValue();
					break;


				case 10:
					curso = LFEgetCellValue(cell);
					break;

				}
			}

			AlumnoExcel alumno = new AlumnoExcel(NIF, nombre, apellidos, direccion, ciudad, cp, provincia, telefono, email, ciclo, curso);
			listaAlumnos.add(alumno);
		}


		return listaAlumnos;

	}


	private String LFEgetCellValue(Cell cell){

		if(cell.getCellTypeEnum() == CellType.STRING)
			return (cell.getStringCellValue().trim());
		else{
			if(cell.getCellTypeEnum() == CellType.NUMERIC){
				String aux = Double.toString(cell.getNumericCellValue());
				aux = aux.substring(0, aux.length()-2);
				aux = aux.trim();
				return (aux);
			}
		}

		return "";

	}




}
