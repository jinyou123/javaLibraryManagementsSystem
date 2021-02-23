package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface LogDao {
    void insertLog(String log, Connection conn) throws SQLException;
}
