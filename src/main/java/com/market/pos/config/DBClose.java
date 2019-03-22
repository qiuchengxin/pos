package com.market.pos.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {

    /**
     * 关闭sql连接，销毁返回值和连接对象
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    public static void colseSqlConnection(ResultSet resultSet, Statement preparedStatement, Connection connection){
        try{
            if (resultSet != null){
                resultSet.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
