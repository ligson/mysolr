package mysolr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

import mysolr.utils.SystemConfig;
import org.dom4j.io.XMLWriter;

/**
 * Application Lifecycle Listener implementation class AppListener
 */
@WebListener
public class AppListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public AppListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        try {
            SystemConfig.init();
            if (SystemConfig.solrHome != null && SystemConfig.solrHome.exists()) {
                System.setProperty("solr.solr.home",
                        "I:/dev/apache-tomcat-7.0.39-windows-x64/apache-tomcat-7.0.39/solr");
            }
            initDic(SystemConfig.dicHome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	System.out.println("系统开始启动");

    }

    private List<File> getDicFiles(File dicHome) {
        List<File> files = new ArrayList<>();
        File[] files2 = dicHome.listFiles();
        if(files2==null){
        	return files;
        }
        for (File file : files2) {
            if (file.isFile() && file.getName().endsWith(".dic")) {
                files.add(file);
            }
            if (file.isDirectory()) {
                files.addAll(getDicFiles(file));
            }
        }
        return files;
    }

    private void initDic(File dicHome) {
        List<File> files = getDicFiles(dicHome);
        StringBuffer buffer = new StringBuffer();
        for (File file : files) {
            buffer.append(file.getAbsolutePath() + ";");
        }
        System.out.println(buffer.toString());
       /* URL url = getClass().getClassLoader().getResource("IKAnalyzer.cfg.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(url);
            Element element = (Element) document.selectObject("//properties/entry[@key=\"ext_dict\"]");

            String name = element.getName();
            String value = element.getStringValue();

            if (value != null) {
                element.setText(buffer.toString() + value);
            }
            System.out.println(element.getText());
            assert url != null;
            //输出全部原始数据，在编译器中显示
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");//根据需要设置编码
            XMLWriter output = new XMLWriter(new FileWriter(new File(url.getFile())), format);
            output.write(document);
            output.close();
        } catch (DocumentException |
                IOException e
                )

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

}
