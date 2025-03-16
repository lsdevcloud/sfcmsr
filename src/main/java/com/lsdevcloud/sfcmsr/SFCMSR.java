package com.lsdevcloud.sfcmsr;

import com.lsdevcloud.sfcmsr.config.DatabaseJsonConfig;
import com.lsdevcloud.sfcmsr.database.DataSource;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public final class SFCMSR extends JavaPlugin
{

    /**
     * hikari data source
     */
    private DataSource dataSource;

    /**
     * plugin instance
     */
    private static SFCMSR instance;

    /**
     * prefix for in-game chat messages and console logs
     */
    private static final String PREFIX = "[sfcmsr] ";

    private DatabaseJsonConfig databaseConfig;

    private File databaseFile;

    @Override
    public void onEnable()
    {

        instance = this;

        if (this.getDataFolder().mkdirs()) getLogger().info("Created plugin folder");

        this.databaseFile = new File(getDataFolder(), "database.json");

        this.databaseConfig = new DatabaseJsonConfig(this.databaseFile);

        this.dataSource = new DataSource(
                this.databaseConfig.getHost(),
                this.databaseConfig.getPort(),
                this.databaseConfig.getDatabase(),
                this.databaseConfig.getUsername(),
                this.databaseConfig.getPassword()
        );

    }

    @Override
    public void onDisable()
    {
    }


}
