/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_attendance_system;

/**
 *
 * @author java4
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Plexada2 {

    public void GetExcel() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance", "root", "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from attend");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("lawix10");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("EMP ID");
            rowhead.createCell((short) 1).setCellValue("NAME");
            rowhead.createCell((short) 2).setCellValue("MOBILE NO");
            rowhead.createCell((short) 3).setCellValue("DATE");
            rowhead.createCell((short) 4).setCellValue("TIME");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);
                row.createCell((short) 0).setCellValue(rs.getString("eid"));
                row.createCell((short) 1).setCellValue(rs.getString("name"));
                row.createCell((short) 2).setCellValue(rs.getString("mail"));
                row.createCell((short) 3).setCellValue(rs.getString("indate"));
                row.createCell((short) 4).setCellValue(rs.getString("time"));
                i++;
            }
            String yemi = "E:\\Report.xls";
            FileOutputStream fileOut = new FileOutputStream(yemi);
            workbook.write(fileOut);
            fileOut.close();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}