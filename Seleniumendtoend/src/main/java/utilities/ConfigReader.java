package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties prop;

    public static Properties initProperties() {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/Config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            e.getMessage();
        }
        return prop;
    }

    public static String getBaseUrl(String key) {
        return prop.getProperty(key + ".baseUrl");
    }
}
