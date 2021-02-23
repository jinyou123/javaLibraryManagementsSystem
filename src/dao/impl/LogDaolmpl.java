package dao.impl;

import dao.LogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDaolmpl implements LogDao {
    @Override
    public void insertLog(String log, Connection conn) throws SQLException {
        String sql = "INSERT INTO logs VALUES(?)";

        PreparedStatement  ps = conn.prepareStatement(sql);
        ps.setString(1,log);
        ps.executeUpdate();

    }
}
