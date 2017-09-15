package com.flyingwillow.restaurant.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author btshen
 * 获得config.properties中的配置属性
 */
public class Config {

	private static final String CONFIG_FILE = "conf/config.properties";

	private static Config instance = null;

	private Map row = null;

	/**
	 * 私有构造，不能直接new
	 */
	private Config() {
        InputStreamReader in = null;
		try {
			Properties properties = new Properties();
			ClassPathResource res = new ClassPathResource(CONFIG_FILE);
			in = new InputStreamReader(new FileInputStream(res.getFile()),"UTF-8");
			properties.load(in);
			if (properties.size() != 0) {
				row = new HashMap(properties);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化
	 * 
	 * @return
	 */
	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	/**
	 * 获得Row
	 * 
	 * @return
	 */
	public Map getRow() {
		return this.row;
	}

	/**
	 * @param configKey
	 * @return
	 */
	public static String gets(String configKey) {
		Map row = getInstance().getRow();
		if(row != null)
		  return (String) row.get(configKey);
		
		return null;
	}
	
	public static String gets(String configKey, String defaultValue) {
        Map row = getInstance().getRow();
        if(row != null){
			String value = (String) row.get(configKey);
			if(StringUtils.isNotBlank(value)){
				return value;
			}
		}

        
        return defaultValue;
    }

	public static int getInt(String configKey) {
		Map row = getInstance().getRow();
		if(row != null){
			String value = (String) row.get(configKey);
			String iValue = StringUtils.isNotBlank(value)&&value.matches("\\d+")?value:"0";
			return Integer.parseInt(iValue);
		}

		
		return 0;
	}

	public static int getInt(String configKey, int defaultValue){
		Map row = getInstance().getRow();
		if(row != null){
			String value = (String) row.get(configKey);
			if(StringUtils.isNotBlank(value)&&value.matches("\\d+")){
				return Integer.parseInt(value);
			}

		}
		return defaultValue;
	}

    public static String getProjectRootDir(){
        String rootDir = gets("HTML_ROOT_DIR");
        if(StringUtils.isBlank(rootDir)){
            return null;
        }
        return rootDir;
    }

    /**
     * 获取目录全路径，不存在时创建
     * */
    public static String getDir(String configKey){
        String dir = gets(configKey);
        if(StringUtils.isBlank(dir)){
            return null;
        }
        if("HTML_ROOT_DIR".equals(configKey)){
            return dir;
        }
        String fullDir = getProjectRootDir() + dir;
        File file = new File(fullDir);
        if(!file.exists()){
            file.mkdirs();
        }
        return fullDir;
    }

	public static String getMemcachedKey(String key){
		String prefix = gets("MEMCACHED_KEY_PREFIX");
		return prefix + "_" + key;
	}

}
