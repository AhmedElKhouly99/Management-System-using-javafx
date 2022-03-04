/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbtest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ahmed
 */
public class Database {

    static String person;
    static Connection con;
    static Statement stm;
    static Integer currentRow = 1;
    static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=person;user=ahmedjava;password=123;";
    static ResultSet rs;

    final public static void startConnection() {
        try {
            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
        }
    }

    public static void addPerson(Person p) throws SQLException {
        startConnection();
        person = "'" + p.fname + "','" + p.mname + "','" + p.lname + "','" + p.email + "','" + p.phone + "'";
        String sql = "insert into details (fname,mname,lname,email,phone) values(" + person + ")";
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        stm.close();
        con.close();
    }

    public static void editePerson(Person p) throws SQLException {
        startConnection();
        String sql = "update details set fname='" + p.fname + "',mname='" + p.mname + "',lname='" + p.lname + "',email='" + p.email + "',phone='" + p.phone + "' where id =" + p.id;
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        stm.close();
        con.close();
    }

    public static Person firstPerson() throws SQLException {
        startConnection();
        stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stm.executeQuery("select * from details");
        rs.first();
        currentRow = rs.getRow();
        Person p = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        stm.close();
        con.close();
        return p;
    }

    public static Person lastPerson() throws SQLException {
        startConnection();
        stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stm.executeQuery("select * from details");
        rs.last();
        currentRow = rs.getRow();
        Person p = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        stm.close();
        con.close();
        return p;
    }

    public static Person prePerson() throws SQLException {
        startConnection();
        stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stm.executeQuery("select * from details");
        rs.absolute(currentRow);
        if (!rs.isFirst()) {
            rs.previous();
            currentRow--;
        }
        Person p = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        stm.close();
        con.close();
        return p;
    }

    public static Person nextPerson() throws SQLException {
        startConnection();
        stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stm.executeQuery("select * from details");
        rs.absolute(currentRow);
        if (!rs.isLast()) {
            rs.next();
            currentRow++;
        }
        Person p = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        stm.close();
        con.close();
        return p;
    }

    public static void deletePerson(int id) throws SQLException {
        startConnection();
        String sql = "delete from details where id=" + id;
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        stm.close();
        con.close();
    }

}
