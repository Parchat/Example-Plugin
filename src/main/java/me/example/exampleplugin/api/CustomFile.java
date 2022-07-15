package me.example.exampleplugin.api;

import me.example.exampleplugin.ExamplePlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class CustomFile {

    // Other misc settings.
    private final String name;
    private final String fileName;
    private final String homeFolder;

    // Our file configuration
    FileConfiguration file;

    // A blank file variable we pass around.
    private final File blankFile;

    // An example of fetching our plugin instance.
    private final ExamplePlugin plugin;

    // If we should log or not.
    private final boolean loggable;

    /**
     * A custom file that is being made.
     * @param name Name of the file.
     * @param homeFolder The home folder of the file.
     */
    public CustomFile(String name, String homeFolder, boolean loggable, ExamplePlugin plugin) {
        this.name = name.replace(".yml", "");
        this.fileName = name;
        this.homeFolder = homeFolder;

        this.plugin = plugin;

        this.loggable = loggable;

        File newFile = new File(plugin.getDataFolder(), "/" + homeFolder);
        File namedFile = new File(newFile, "/" + name);

        blankFile = new File(plugin.getDataFolder(), "/" + homeFolder + "/" + fileName);

        if (newFile.exists()) {
            if (namedFile.exists()) {
                file = YamlConfiguration.loadConfiguration(namedFile);
            } else {
                file = null;
            }

            return;
        }

        newFile.mkdirs();

        if (loggable) plugin.getLogger().info("The folder " + homeFolder + "/ was not found so it was created.");

        file = null;
    }

    /**
     * Get the name of the file without the .yml part.
     * @return The name of the file without the .yml.
     */
    public String getName() {
        return name;
    }

    // Get the full name of the file with .yml included.
    public String getFileName() {
        return fileName;
    }

    // Get the name of the home folder where the file is.
    public String getHomeFolder() {
        return homeFolder;
    }

    // Get the custom file.
    public FileConfiguration getFile() {
        return file;
    }

    // Check if file exists, True if yes, False if no.
    public boolean exists() {
        return file != null;
    }

    // Save the file.
    public boolean saveFile() {
        if (file == null) return true;

        try {
            file.save(blankFile);

            if (loggable) plugin.getLogger().info("Saved " + fileName + ".");

            return true;
        } catch (Exception e) {
            plugin.getLogger().warning("Could not save " + fileName + "!");

            for (StackTraceElement stack : e.getStackTrace()) {
                plugin.getLogger().severe(String.valueOf(stack));
            }
        }

        if (loggable) plugin.getLogger().warning("There was a null custom file that could not be found!");

        return false;
    }

    // Reload the file.
    public boolean reloadFile() {

        if (file == null) return true;

        try {
            file = YamlConfiguration.loadConfiguration(blankFile);

            if (loggable) plugin.getLogger().info("Reloaded " + fileName + ".");
        } catch (Exception e) {
            plugin.getLogger().warning("Could not save " + fileName + "!");

            for (StackTraceElement stack : e.getStackTrace()) {
                plugin.getLogger().severe(String.valueOf(stack));
            }
        }

        if (loggable) plugin.getLogger().warning("There was a null custom file that could not be found!");

        return false;
    }
}