package com.gmail.xlifehd;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.util.Properties;

public class Main {

    private static HikariDataSource dataSource;
    private static TS3Query query;
    private static int clientID;


    public static void main(String[] args) {
        createConfig();
        setupMySQL();
        setupTeamSpeak();
        QuizControl.init();
    }

    private final static void setupTeamSpeak() {
        Properties prop = new Properties();
        InputStream input = null;

        final TS3Config config = new TS3Config();

        try {

            input = new FileInputStream("config.properties");
            prop.load(input);

            config.setHost(prop.getProperty("tstelnethost"));
            config.setQueryPort(Integer.valueOf(prop.getProperty("tstelnetport")));

            final String queryUsername = prop.getProperty("tsqueryusername");
            final String queryPassword = prop.getProperty("tsquerypassword");
            final String clientNickname = prop.getProperty("tsnickname");
            final int channelID = Integer.valueOf(prop.getProperty("tschannelid"));

            config.setConnectionHandler(new ConnectionHandler() {

                public void onConnect(TS3Query ts3Query) {
                    TS3Api api = ts3Query.getApi();
                    api.login(queryUsername, queryPassword);
                    api.selectVirtualServerById(1);
                    api.setNickname(clientNickname);
                    api.moveQuery(channelID);
                    api.registerEvent(TS3EventType.TEXT_CHANNEL, channelID);
                    api.registerEvent(TS3EventType.TEXT_PRIVATE);

                    clientID = api.whoAmI().getId();
                }

                public void onDisconnect(TS3Query ts3Query) {

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        query = new TS3Query(config);
        query.connect();

    }

    private static void setupMySQL() {
        Properties prop = new Properties();
        InputStream input = null;

        final HikariConfig config = new HikariConfig();

        try {

            input = new FileInputStream("config.properties");
            prop.load(input);

            String host = prop.getProperty("dbhost");
            String port = prop.getProperty("dbport");
            String database = prop.getProperty("dbdatabase");

            config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false");
            config.setUsername(prop.getProperty("dbusername"));
            config.setPassword(prop.getProperty("dbpassword"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);

    }

    private static void createConfig() {

        File f = new File("config.properties");
        if (f.exists() && !f.isDirectory())
            return;

        Properties prop = new Properties();
        OutputStream output = null;
        try {

            output = new FileOutputStream("config.properties");

            prop.setProperty("dbhost", "127.0.0.1");
            prop.setProperty("dbport", "3306");
            prop.setProperty("dbdatabase", "database");
            prop.setProperty("dbusername", "username");
            prop.setProperty("dbpassword", "password");

            prop.setProperty("tstelnethost", "127.0.0.1");
            prop.setProperty("tstelnetport", "10011");

            prop.setProperty("tsqueryusername", "username");
            prop.setProperty("tsquerypassword", "password");

            prop.setProperty("tsnickname", "nickname");
            prop.setProperty("tschannelid", "channelid");

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    public static TS3Query getQuery() {
        return query;
    }

    public static int getClientID() {
        return clientID;
    }

}
