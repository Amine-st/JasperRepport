package com.oauth.labapi.utils;


import com.oauth.labapi.model.DataTest;
import com.oauth.labapi.model.Model;
import com.oauth.labapi.model.PModel;
import com.oauth.labapi.model.POModel;
import jdk.nashorn.internal.runtime.ErrorManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Component
public  class JasperCompilerManager implements CommandLineRunner {


    private static String logo_path = "C:\\Users\\AMINE-ALAOUI\\Desktop\\api_lab\\JasperRepport\\src\\main\\resources\\static\\logo_path.PNG";
    private static String footer_path = "C:\\Users\\AMINE-ALAOUI\\Desktop\\api_lab\\JasperRepport\\src\\main\\resources\\static\\footer.png";
    private ErrorManager log;
    public static String logos = "hello world";

    private static Map<String, Object> parameters(PModel poModel) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("logo", logo_path);
        parameters.put("footer_path",footer_path);
        parameters.put("poModel",  poModel);
        parameters.put("net.sf.jasperreports.export.character.encoding",  "UTF-8");
        parameters.put("net.sf.jasperreports.export.pdf.write.bom",  true);
        // parameters.put("REPORT_LOCALE", locale);

        return parameters;
    }

    public static File generateregulationSlaireMAD(PModel poModel) throws IOException {
        //URL reportPath  = getClass().getResource("/jasper/" + jrxml + ".jrxml");
        try
        {
            final JasperReport report = JasperCompileManager.compileReport("C:\\Users\\AMINE-ALAOUI\\Desktop\\api_lab\\JasperRepport\\src\\main\\java\\com\\oauth\\labapi\\utils\\factureRepport.jrxml");
            final Map<String, Object> parameters = parameters(poModel);
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("cotisations"));
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, dataSource);
            JRPdfExporter exporter = new JRPdfExporter();

           // ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
            //SimpleOutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                 //   response.getOutputStream());
            exporter.setExporterOutput(
                    new SimpleOutputStreamExporterOutput("test16.pdf"));
            exporter.setExporterInput(new SimpleExporterInput(jprint));
           // exporter.setExporterOutput(exporterOutput);
            exporter.exportReport();

           /* response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
            response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(pdfReportStream.toByteArray());
            responseOutputStream.close();
            pdfReportStream.close();*/


        }
        catch (final Exception e)
        {
            System.out.println(e);
        }
        return new File("test16.pdf");
    }
    @Override
    public void run(String... args) throws Exception {
       /* File f =  JasperCompilerManager.JasperCompilerManager();
        SendMail.sendemail("mohamed.ellaouzi@gmail.com","hello world",f);*/

    }

    public static void main(String[] args) throws JRException, IOException {

      //  POModel p =new POModel(1L,"test",400,"fact1","f1",new Date(),new Date(),30,440);
        PModel model = new PModel();

        generateregulationSlaireMAD(model);
        //File f =  JasperCompilerManager.JasperCompilerManager();
        //SendMail.sendemail("c.hamdaoui98@gmail.com","hello world",f);
        //File f =  JasperCompilerManager.JasperCompilerManager();
       // SendMail.sendemail("c.hamdaoui98@gmail.com","hello world",f);
    }

}
