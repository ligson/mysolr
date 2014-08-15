package mysolr.utils;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SystemConfig {
	public static File dicHome;
	public static File solrHome;
	private static Logger logger = Logger.getLogger(SystemConfig.class);

	public static void init() throws Exception {
		URL url = SystemConfig.class.getClassLoader().getResource(
				"mysolr-config.properties");
		Properties properties = new Properties();
		properties.load(url.openStream());
		String path = (String) properties.get("solr.home");
		solrHome = new File(path);
		logger.info("load solr.home:" + path);
		path = (String) properties.get("ikanalyzer.dicHome");
		dicHome = new File(path);
		logger.info("load dic.home:" + path);

	}

	public static void main(String[] args) {
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
