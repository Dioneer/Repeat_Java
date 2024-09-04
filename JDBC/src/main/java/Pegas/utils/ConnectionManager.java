package Pegas.utils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {
    private static final String URL = "db.url";
    private static final String USER = "db.username";
    private static final String PASSWORD = "db.password";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static BlockingQueue<Connection> pool;

    static{
            intConnectionPool();
    }

    private static void intConnectionPool(){
        String poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size ; i++) {
            Connection connection = open();
            var proxyConnection = (Connection)Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class}, (poxy, method,args)->
                    method.getName().equals("close") ? pool.add((Connection) poxy) : method.invoke(connection, args));
            pool.add(proxyConnection);
        }
    }

    public static Connection get() throws InterruptedException {
        return pool.take();
    }

    private static Connection open() {
        try {
            return DriverManager.getConnection(PropertiesUtil.get(URL), PropertiesUtil.get(USER),
                    PropertiesUtil.get(PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager(){
    }

}
