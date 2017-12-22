package net.ion.oadr2.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class PropertiesReader {
	private static DefaultResourceLoader loader = new DefaultResourceLoader();
	private Properties properties = new Properties();
	private PropertiesReader(String recource,Properties properties) {
		try {
			if(properties!=null){
				for(String pName : properties.stringPropertyNames()){
					this.properties.put(pName,properties.getProperty(pName));
				}
			}
			
			this.properties.load(loader.getResource(recource).getInputStream());
			reference(this.properties);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Properties getProperties() {
		return (Properties) properties.clone();
	}
	
	public Map<String,String> getPropertiesAsMap() {
		
		Map<String,String> map = new HashMap<String,String>();
		
		for(String pName : properties.stringPropertyNames()){
			map.put(pName, properties.getProperty(pName));
		}
		return map;
	}

	protected Properties reference(Properties properties) throws FileNotFoundException, IOException {

		for (String propName : properties.stringPropertyNames()) {
			referenceParse(properties, propName);
		}

		return properties;
	}

	protected String referenceParse(Properties properties, String propName) {
		String value = null;
		if (properties.containsKey(propName)) {
			value = properties.getProperty(propName);
			if (StringUtils.isNotBlank(value)) {
				String[] selfRefs = StringUtils.substringsBetween(value, "${", "}");
				if (selfRefs != null) {
					for (String self : selfRefs) {
						value = StringUtils.replace(value, "${" + self + "}", referenceParse(properties, self));
					}
					properties.put(propName, value);
				} else {
					return value;
				}
			}
		} else {
			value = System.getProperty(propName);
		}

		return value;
	}

	public static PropertiesReader read(String recource,Properties properties) {
		return new PropertiesReader(recource,properties);
	}

	public static void main(String[] args) throws Exception {
		Resource resource = loader.getResource("default.properties");

		System.out.println(resource.getFile());
	}
}
