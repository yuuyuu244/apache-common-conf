package apache_commons_confSample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

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
        
        confSample instance = new confSample();
        instance.putMapTest();
            try {
                PropertiesConfiguration config = instance.loadConfig("wps.conf");
                // ���ʃy�[�W�p�X
                String commonPagePath = config.getProperty("COMMON_PAGE_PATH").toString();
                
                // �o�̓y�[�W�p�X
                List<Object> list= config.getList("PAGE_PATH");
                Map<String, String> map= new LinkedHashMap<>();
                
                list.forEach((item)->{
                    String tmp = item.toString();                   
                    String[] pageArray = tmp.split(",");
                    if (pageArray.length == 2) {
                        String alias = pageArray[0];
                        String page = pageArray[1];
                        // TODO �t�@�C���̑��݊m�F
                        map.put(alias, page);
                    }
                });
                
                map.forEach((key,value) -> {
                    System.out.print("map key:" + key);
                    System.out.println(" value:" + value);
                });
                
                // �������
                List<Object> allPatternList = config.getList("PATTERN");
                Map<String, List<WPSPattern>> map2 = new HashMap<>();
                // map(page�p�X���i�[)�����݂���ԁA�J��Ԃ��B
                map.forEach((alias,page) -> {
                    // map(�������(�S��))�����݂���ԁA�J��Ԃ��B
                    allPatternList.forEach(item -> {
                        // Object -> �������
                        String tmp = item.toString();
                        // �J���}��؂�
                        String[] patternArray = tmp.split(",");
                        // �J���}��؂�łR���ڂ������ꍇ
                        if (patternArray.length == 3) {
                            String aliass = patternArray[0];
                            String name = patternArray[1];
                            String pattern = patternArray[2];
                            
                            if(alias.equals(aliass)) {
                                List<WPSPattern> patterns = null;
                                // �y�[�W�̃G�C���A�X�Ɠ�������
                                if (map2.get(alias) == null ){
                                    patterns = new ArrayList<>();
                                } else {
                                    patterns = map2.get(alias);
                                }
                                patterns.add(new WPSPattern(name, pattern));
                                map2.put(alias, patterns);
                            } 
                        }
                    });
                });
                
                map2.forEach((alias,patterns) -> {
                    System.out.println("--------- map2 alias: " + alias + " ----------");
                    patterns.forEach(aaa ->{
                        System.out.print("list name=" + aaa.getName());
                        System.out.println(" / pattern=" + aaa.getPattern());
                    });
                });
                
               
//                Iterator<String> keys = config.getKeys();
//                if (keys != null) {
//                keys.forEachRemaining((String key)->{
//                  System.out.println(key);
////                  System.out.println(config.getString(key));
//                });
//                }
                
                
                
//                List<Object> list = config.getList("PAGE_PATH");

//                Properties pagePathPro = config.getProperties("PAGE_PATH");
//
//                list.forEach((item) -> {
//                     System.out.println(item.toString());
//                });
                
                
            } catch (ConfigurationException ex) {
                System.err.println("error");
                // TODO 
            }
    }
    /**
     * �ݒ�t�@�C���̓ǂݍ��݂����{���郁�\�b�h.
     * 
     * @param configPath �ݒ�t�@�C���̃p�X
     * @return �ݒ�t�@�C���̍��ڂ�ێ������C���X�^���X
     * @throws ConfigurationException �ݒ莞�̗�O
     */
    private PropertiesConfiguration loadConfig(String configPath) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration();
        // ��؂蕶�����w�肵�Ȃ��ݒ�
        config.setDelimiterParsingDisabled(true);        
//        config.setListDelimiter('0');
        // �ݒ�t�@�C���̓ǂݍ��݂����{
        config.load(configPath);
        return config;   
    }
    
    private void putMapTest() {
        LinkedHashMap<String, String> test = new LinkedHashMap<>();
        test.put("a", "sss");
        test.put("a", "ddd");
        
        test.forEach((String key, String value) -> {
            System.out.print(key);
            System.out.println(value);
        });
    }

}