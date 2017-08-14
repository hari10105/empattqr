/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_attendance_system;

/**
 *
 * @author java4
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Database {
    ResultSet rsData;
    Statement st ;
    private static Connection con =  null;
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance", "root", "root");
        return con;
    }
    public ResultSet consulta(String sql)
    {
        try{
            con = Database.getConnection();
            st = con.createStatement();
            rsData = st.executeQuery(sql);
        }
        catch(Exception ex)
        {
            System.out.println("The Exception Error in Database.consulta "+ex.getMessage());
        }
        return rsData;
    }
    public DefaultTableModel returndata(String sql)
    {
        DefaultTableModel model = new DefaultTableModel();
        {
            try{
                ResultSet rs = consulta(sql);
                ResultSetMetaData rsMD = rs.getMetaData();
                int columnCoount = rsMD.getColumnCount();
                Object[] object = new Object[columnCoount];
                for(int i=0;i<columnCoount;i++)
                {
                    object[i] = rsMD.getColumnLabel(i+1);
                }
                model.setColumnIdentifiers(object);
                while(rs.next())
                {
                    Object[] dataFile = new Object[model.getColumnCount()];
                    for(int i=0;i<model.getColumnCount();i++)
                    {
                        dataFile[i] = rs.getObject(i+1);
                    }
                    model.addRow(dataFile);
                }
            }
            catch(Exception ex)
            {
                System.out.println("The Exception Error in Database.retutndata "+ex.getMessage());
            }
        }
        return model;
    }
    
}

