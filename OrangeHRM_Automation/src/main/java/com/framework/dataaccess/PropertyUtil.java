package com.framework.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

	static Properties prop;

	public static void initProperty() throws IOException {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\configs\\orangehrm_config.properties";
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);

		prop = new Properties();
		prop.load(fis);
	}

	public static String readPropertyValue(String key){

		String value = prop.getProperty(key);
		return value;

	}

}
