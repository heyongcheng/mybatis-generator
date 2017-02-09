package cn.he.tools.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorMain {
	
	public static void main(String[] args) {  
        List<String> warnings = new ArrayList<String>();  
        boolean overwrite = true;  
        //读取配置文件  
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);  
        Configuration config;  
        try {  
            config = cp.parseConfiguration(inputStream);
  
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
            MyBatisGenerator myBatisGenerator;  
            try {  
                myBatisGenerator = new MyBatisGenerator(config, callback,  
                        warnings);  
                myBatisGenerator.generate(null);  
                  
                //打印结果  
                for(String str : warnings){  
                    System.out.println(str);  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            System.out.println("success");
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (XMLParserException e) {  
            e.printStackTrace();  
        }  
    }
	
}
