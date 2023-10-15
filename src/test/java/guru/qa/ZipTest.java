package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTest {

    private static final String[][] FILE_CSV = new String[][]{
            {"Test", "Test2"},
            {"1", "a"},
            {"2", "b"},
            {"3", "c"}
    };
    private static final String FILE_XLSX = "c";
    public ClassLoader classLoader = ZipTest.class.getClassLoader();

    @DisplayName("Чтение и проверка сожержимого pdf файла")
    @Test
    void pdfFileTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("testzip.zip")) {
            ZipInputStream zip = new ZipInputStream(is);
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().contains("pdf")) {
                    PDF pdf = new PDF(zip);
                    Assertions.assertTrue(pdf.text.contains("DOWNLOADING PDF FILES IN A WEB BROWSER"));
                    Assertions.assertTrue(pdf.title.contains("PDF Test File"));
                    Assertions.assertTrue(pdf.author.contains("Charles A. Bliley"));
                }
            }
        }
    }

    @DisplayName("Чтение файла csv и проверка ожидаемого результата")
    @Test
    void csvFileTes() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("testzip.zip")) {
            ZipInputStream zip = new ZipInputStream(is);
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().contains("csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zip));
                    List<String[]> csv = csvReader.readAll();
                    Assertions.assertArrayEquals(FILE_CSV, csv.toArray());
                }
            }
        }
    }

    @DisplayName("Чтение файфла xls и сравнение значения в определенной ячейке")
    @Test
    void xlsxFileTes2() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("testzip.zip")) {
            ZipInputStream zip = new ZipInputStream(is);
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().contains("xlsx")) {
                    XLS xls = new XLS(zip);
                    Assertions.assertEquals(FILE_XLSX,
                            xls.excel.getSheetAt(0)
                                    .getRow(3)
                                    .getCell(1)
                                    .getStringCellValue());
                }
            }
        }
    }

}