package apache_commons_confSample;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.AbstractFileConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 
 * 
 * @author yuki
 * @see AbstractConfiguration.setListDelimiter(char)
 * @see http://javadox.com/commons-configuration/commons-configuration/1.8/org/apache/commons/configuration/PropertiesConfiguration.html
 */
public class confSample {
    public static void main(String[] args)  {
//        try {
            PropertiesConfiguration config;
            try {
//                AbstractFileConfiguration config = new PropertiesConfiguration();

                config = new PropertiesConfiguration();
                config.setListDelimiter('0');
                config.load("wps.conf");
//                config.setListDelimiter('0');
//                Object pagePath = config.getProperty("PAGE_PATH");
                
                List<Object> list= config.getList("PAGE_PATH");
                list.forEach((item)->{
                    System.out.println(item.toString());
                });
                
                Iterator<String> keys = config.getKeys();
                if (keys != null) {
                keys.forEachRemaining((String key)->{
                  System.out.println(key);
//                  System.out.println(config.getString(key));
                });
                }
                
                String commonPagePath = config.getString("COMMON_PAGE_PATH");
                System.out.println("COMMON_PAGE_PATH="+commonPagePath);
                
                
//                List<Object> list = config.getList("PAGE_PATH");
                Map<String, String> map= new LinkedHashMap<>();
//                Properties pagePathPro = config.getProperties("PAGE_PATH");

                list.forEach((item) -> {
                     System.out.println(item.toString());
                });
                
                
            } catch (ConfigurationException e) {
                // TODO �����������ꂽ catch �u���b�N
            }
    }

}