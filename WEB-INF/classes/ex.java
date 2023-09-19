import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

import org.ExportModule1.ExportModule1;

public class ex extends ExportModule1 {
    public void main(String[] args) {
        try {
            Class.forName("com.postgressql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "Rajdr039*");
            String format = "pdf";
            ExportModule1 exporter =getExporter(format);
            ByteArrayOutputStream baos =exporter.exportTable(connection, "SELECT * FROM podetails");;

            String filename = "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.0\\webapps\\POMS2\\WEB-INF\\classes\\List."+format;
            FileOutputStream output = new FileOutputStream(filename);
            output.write(baos.toByteArray());
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
