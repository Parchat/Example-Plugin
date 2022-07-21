package me.example.exampleplugin.api.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.Methods;
import me.example.exampleplugin.api.FileManager;
import me.example.exampleplugin.api.config.interfaces.IConfigFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.InputStream;

@Singleton
public class LangFile implements IConfigFile {

    // An example of fetching our configFolder
    @Inject
    @Named("ConfigFolder")
    private File configFolder;

    // An example of fetching our methods instance.
    @Inject private Methods methods;

    // An example of fetching our plugin instance.
    @Inject private ExamplePlugin plugin;

    // An example of fetching our fileManager instance.
    @Inject private FileManager fileManager;

    // Our blank file we re-assign and pass around.
    private File blankFile = null;

    private void create() {
        blankFile = new File(configFolder, "/" + "lang.yml");

        if (!blankFile.exists()) {
            InputStream jarFile = plugin.getClass().getResourceAsStream("/" + "lang.yml");
            methods.copyFile(jarFile, blankFile);
        }
    }

    @Override
    public void load() {
        // Copy the file from src/main/resources
        create();

        fileManager.addConfiguration(blankFile.getName(), blankFile, YamlConfiguration.loadConfiguration(blankFile));
    }

    @Override
    public void save() {
        // Copy the file from src/main/resources
        create();

        fileManager.saveFile(blankFile.getName());
    }

    @Override
    public void remove() {
        fileManager.removeFile(blankFile.getName());
    }

    @Override
    public void reload() {
        fileManager.reloadFile(blankFile.getName(), YamlConfiguration.loadConfiguration(fileManager.getFile(blankFile)));
    }

    @Override
    public FileConfiguration getFile() {
        return fileManager.getFile(blankFile.getName());
    }
}