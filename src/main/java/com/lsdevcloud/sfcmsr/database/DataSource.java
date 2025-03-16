package com.lsdevcloud.sfcmsr.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created: 3/16/25
 *
 * @author lesc (lesc@lsdevcloud.com)
 */
@Getter
public final class DataSource
{
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    public DataSource(final String host, final int port, final String database, final String username, final String password)
    {

        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException
    {
        return ds.getConnection();
    }
}
