package com.lsdevcloud.sfcmsr.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * json config util
 * Created: 3/16/25
 *
 * @author lesc (lesc@lsdevcloud.com)
 */
public class BukkitJsonConfig
{

    private static final Logger LOGGER = Logger.getLogger(BukkitJsonConfig.class.getName());

    private final File file;
    private JsonObject config;

    public BukkitJsonConfig(@NonNull File file)
    {
        this.file = file;
        load(null);
    }

    public BukkitJsonConfig(@NonNull File file, @NonNull JsonObject config)
    {
        this.file = file;
        this.config = config;
    }

    /**
     * saves the current configuration to the file
     */
    public void save()
    {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))
        {
            writer.write(config.toString());
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Failed to save configuration file: " + file.getAbsolutePath(), e);
        }
    }

    /**
     * loads the configuration from the file, if file doesnt exist use provided defaults or empty json object
     */
    public void load(final @Nullable JsonObject defaults)
    {
        if (!file.exists())
        {
            config = defaults == null ? new JsonObject() : defaults;
            save();
            return;
        }

        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
        {
            config = JsonParser.parseReader(reader).getAsJsonObject();
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Failed to load configuration file: " + file.getAbsolutePath(), e);
            config = defaults == null ? new JsonObject() : defaults;
        }
    }

    /**
     * retrieves the current configuration
     * @return the json configuration object
     */
    public JsonObject get()
    {
        return this.config;
    }
}
