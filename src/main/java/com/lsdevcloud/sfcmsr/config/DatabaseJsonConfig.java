package com.lsdevcloud.sfcmsr.config;

import com.google.gson.JsonObject;
import lombok.NonNull;

import java.io.File;

/**
 * Created: 3/16/25
 *
 * @author lesc (lesc@lsdevcloud.com)
 */
public final class DatabaseJsonConfig extends BukkitJsonConfig
{

    public DatabaseJsonConfig(@NonNull File file)
    {
        super(file);
        this.defaults = BukkitJsonConfig.databaseDefaults();
    }

    public String getHost()
    {
        return this.getString("host");
    }

    public String getUsername()
    {
        return this.getString("username");
    }

    public String getPassword()
    {
        return this.getString("password");
    }

    public String getDatabase()
    {
        return this.getString("database");
    }

    public int getPort()
    {
        final var p = this.get().has("port") ? this.getString("port") : this.defaults.get("port").toString();

        try
        {
            return Integer.parseInt(p == null ? "3306" : p);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }
}
