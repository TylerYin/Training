package com.fwzs.readfile;

import com.util.FileUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * @author Tyler
 */
public class ReadPropertiesTest {

    @Test
    public void testReadProperties() throws IOException {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
        Properties prop = PropertiesLoaderUtils.loadAllProperties("src/main/java/com/fwzs/readfile/demo_qrcode.properties");
        prop.setProperty("qrCode", generator.generate(32));

        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream("src/main/java/com/fwzs/readfile/demo_qrcode.properties");

            prop.store(oFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testReadFile() throws IOException {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
        Properties prop = PropertiesLoaderUtils.loadAllProperties("src/main/java/com/fwzs/readfile/demo_qrcode.txt");
        prop.setProperty("qrCode", generator.generate(32));

        /**
         * BS下读取项目中的文件
         *
         String classpath = "/" + this.getClass().getResource("/").getPath().replaceFirst("/", "");
         String filePath = classpath.replaceAll("WEB-INF/classes/", "") + "static/readfile/demo_qrcode.txt";
         *
         */

        // CS结构下读取项目中的文件
        String filePath = "src/main/java/com/fwzs/readfile/demo_qrcode.txt";

        File file = new File(filePath);
        Collection collection = new ArrayList();
        collection.add(generator.generate(32));
        FileUtils.writeLines(file, collection);
    }

    @Test
    public void testMessageFormat(){
        System.out.println(MessageFormat.format("validateCode1 = {0}, validateCode2 = {1}", "test", "ggg"));
    }
}
