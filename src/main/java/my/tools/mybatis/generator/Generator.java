package my.tools.mybatis.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {
    public static void main(String[] args) throws Exception {
        // java命令行参数: -Ddb.password=123456
        // mybatis.generator会自己替换properties中的属性到xml中
        Properties properties = System.getProperties();
        String pass = properties.getProperty("db.password");
        System.out.printf("[DEBUG]: -Ddb.password=%s%n", pass);

        // 环境变量: db.password=123456
        // mybatis.generator不会读取环境变量，需要自己把环境变量中的属性，替换到xml_config对象的对应字段上
        String envpass = System.getenv("db.password");
        System.out.printf("[DEBUG]: env db.password=%s%n", envpass);

        run();
    }

    private static void run() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        //Configuration config = cp.parseConfiguration(Generator.class.getResourceAsStream("/generatorConfig-account.xml"));
        Configuration config = cp.parseConfiguration(Generator.class.getResourceAsStream("/generatorConfig-web.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        if (!warnings.isEmpty()) {
            for (String warning : warnings) {
                System.out.println(warning);
            }
        } else {
            System.out.println("OK");
        }
    }
}
