package com.folcode.techoapi.excel.ExcelHelper;

import com.folcode.techoapi.models.entities.SocioEntity;
import com.folcode.techoapi.models.entities.repositories.SocioRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelHelper {

    private final SocioRepository socioRepository;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Nombre", "Apellido", "DNI", "Telefono","Mail", "Provincia","Monto","Tipo de tarjeta","Número de tarjeta"};
    static String SHEET = "Socios";

    public ExcelHelper( SocioRepository socioRepository) {

        this.socioRepository = socioRepository;
    }

    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static  ByteArrayInputStream sociosToExcel(List<SocioEntity> Socios) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for( SocioEntity socio :Socios) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(socio.getId());
                row.createCell(1).setCellValue(socio.getDni());
                row.createCell(2).setCellValue(socio.getNombre());
                row.createCell(3).setCellValue(socio.getApellido());
                row.createCell(4).setCellValue(socio.getTelef());
                row.createCell(5).setCellValue(socio.getMail());
                row.createCell(6).setCellValue(socio.getProvincia());
                row.createCell(7).setCellValue(socio.getMonto());
                row.createCell(8).setCellValue(socio.getTarjeta());
                row.createCell(9).setCellValue(socio.getNumTarjeta());
                row.createCell(10).setCellValue(socio.getUid());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch ( IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<SocioEntity> excelToSocios(InputStream is) {

        try {

            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
           // System.out.println(sheet);
            List<SocioEntity> socios = new ArrayList<>();

            int rowNumber = 0;



            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                SocioEntity socioEntity = new SocioEntity();

                int cellIdx = 0;



                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                   //
                    switch (cellIdx) {
//                        case 0:
//                            socioEntity.setId((long) currentCell.getNumericCellValue());
//                            break;

                        case 0:
                            socioEntity.setNombre( currentCell.getStringCellValue());
                                System.out.println("1");
                            break;

                        case 1:
                            socioEntity.setApellido(currentCell.getStringCellValue());
                            System.out.println("2");
                            break;

                        case 2:
                            double pepe=currentCell.getNumericCellValue();
//                             String jorge = Double.toString(pepe);
//                            System.out.println(jorge);
                            String dni = String.format("%.0f", pepe);
                            socioEntity.setDni(dni);
                            System.out.println("3");

                            break;
                        case 3:
                            socioEntity.setTelef((long)currentCell.getNumericCellValue());
                            System.out.println("4");
                            break;
                        case 4:
                            socioEntity.setMail(currentCell.getStringCellValue());
                            System.out.println("5");
                            break;
                        case 5:
                            socioEntity.setProvincia(currentCell.getStringCellValue());
                            System.out.println("6");
                            break;
                        case 6:
                            socioEntity.setMonto((long)currentCell.getNumericCellValue());
                            System.out.println("7");
                            break;
                        case 7:
                            socioEntity.setTarjeta(currentCell.getStringCellValue());
                            System.out.println("8");
                            System.out.println(currentCell);
                            break;
                        case 8:
                            System.out.println(currentCell);
                            socioEntity.setNumTarjeta((long) currentCell.getNumericCellValue());
                            System.out.println("9");
                            break;
                        default:
                            System.out.println("10");
                            break;
                    }

                    cellIdx++;

                }

                socios.add(socioEntity);

            }

            workbook.close();

            return socios;
        } catch (IOException e) {

            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

