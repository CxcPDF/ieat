package dao;

import java.sql.SQLException;

//通过actionpre表更新获得action表，获得每个用户酸碱口味偏好
public interface updateActionDAO {
    public void updateAction() throws SQLException;
}
