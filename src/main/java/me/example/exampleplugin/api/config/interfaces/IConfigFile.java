package me.example.exampleplugin.api.config.interfaces;

import org.bukkit.configuration.file.FileConfiguration;

public interface IConfigFile {

    void load();

    void save();

    void reload();

    void remove();

    FileConfiguration getFile();

}