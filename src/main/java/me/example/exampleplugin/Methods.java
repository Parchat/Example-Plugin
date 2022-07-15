package me.example.exampleplugin;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Singleton
public class Methods {

    // An example of fetching our plugin instance.
    @Inject private ExamplePlugin plugin;

    // This is what copies files out of src/main/resources
    public void copyFile(InputStream sourceFile, File destinationFile) {
        try (InputStream fis = sourceFile; FileOutputStream fos = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int i;

            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            for (StackTraceElement stack : e.getStackTrace()) {
                plugin.getLogger().severe(String.valueOf(stack));
            }
        }
    }
}