/*DBReader.java: An interface of back-end to mysql database,
including DB connector, read a row, read table, change data and remove a row methods.

 * */
package ood.utils;
import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class DBReader {
    static ResultSet rs = null;
    public static final String url = "jdbc:mysql://localhost:3306/BANK?serverTimezone=UTC";// db name: Bank
    public static final String user = "root";//username
    public static final String password = "12345678";//password
    public static Connection conn = null;
    Statement stmt = null;

    public DBReader() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//connect type
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK?" +
                    "user=root&password=12345678");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap<String,String> getData(String table, String columnName, String value){
        String sql = "select * from " + table + " where " + columnName + "=\"" + value +"\"";//SQL sentence
        //out data
        LinkedHashMap<String,String> data = new LinkedHashMap<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
            }

            while (rs.next()) {
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    data.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
                }
//                System.out.println(data.toString());
                System.out.println("data got from mysql");
            }
            //close connection
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }


    public LinkedList<LinkedHashMap<String,String>> getTable(String table){
        String sql = "select * from " + table;//SQL sentence
        //out data
        LinkedList<LinkedHashMap<String,String>> data = new LinkedList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
            }

            while (rs.next()) {
                LinkedHashMap<String, String> tmp = new LinkedHashMap<>();
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    tmp.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
                }
                data.add(tmp);
//                System.out.println(data.toString());
                System.out.println("data got from mysql");
            }
            //close connection
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return data;

    }


    public Boolean changeData(String table, String key, String value,String IDKey, String ID){
        String sql = "UPDATE " + table +
                " SET " + key + " = \'" + value + "\'" +
                " WHERE " + IDKey + " = \'" + ID + "\';";
        //write data
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("data write to mysql");

        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Boolean addData(String table, LinkedHashMap<String,String> data){

        String sql = "";
        String keys = "";
        String values = "";

        for(String key : data.keySet()){
            keys += key + ",";
            values += "\'" + data.get(key) + "\'" + ",";
        }
        sql = "INSERT INTO " + table + " (" + keys.substring(0, keys.length() - 1) + ") " +
                "VALUES (" + values.substring(0, values.length() - 1) + ");";

        //write data
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("data write to mysql");
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean removeData(String table, String idKey, String idValue){
        //remove data
        String sql = "DELETE FROM " + table +
                " WHERE " + idKey + "=\'" + idValue + "\';";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("remove data from mysql");
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
