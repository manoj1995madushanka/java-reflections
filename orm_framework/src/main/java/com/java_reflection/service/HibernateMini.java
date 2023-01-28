package com.java_reflection.service;

import com.java_reflection.annotation.Column;
import com.java_reflection.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * hibernate not having object relational mapping capability
 * but this have other lot of functionalities
 */
public class HibernateMini<T> {

    private Connection con;
    private AtomicLong id = new AtomicLong(1L);

    public static <T> HibernateMini<T> getConnection() throws SQLException {
        return new HibernateMini<T>();
    }

    private HibernateMini() throws SQLException {
        this.con = DriverManager.getConnection("");
    }

    public void write(T t) throws IllegalAccessException, SQLException {
        Class<? extends Object> classObject = t.getClass();
        Field[] declaredFields = classObject.getDeclaredFields();

        Field primaryKey = null;
        ArrayList<Field> columns = new ArrayList<>();
        StringJoiner joiner = new StringJoiner(",");

        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                primaryKey = field;
                // System.out.println("The primary key is : " + field.getName() + " value : " + field.get(t) + " and the columns are : ");
            } else if (field.isAnnotationPresent(Column.class)) {
                joiner.add(field.getName());
                columns.add(field);
                // System.out.println(field.getName() + " value : " + field.get(t));
            }
        }

        int values = joiner.length() + 1;//with primary key
        String qMarks = IntStream.range(0, values).mapToObj(e -> "?").collect(Collectors.joining(","));

        String sql = "insert into " + classObject.getSimpleName() + " ( " + primaryKey + joiner.toString() + ") " + "values (" + qMarks + ")";

        PreparedStatement preparedStatement = con.prepareStatement(sql);

        if (primaryKey.getType() == long.class) {
            preparedStatement.setLong(1, id.incrementAndGet());
        }

        int index = 2;
        for (Field field : columns) {
            field.setAccessible(true);
            if (field.getType() == int.class) {
                preparedStatement.setInt(index++, (Integer) field.get(t));
            } else if (field.getType()==String.class) {
                preparedStatement.setString(index++, (String) field.get(t));
            }
        }

        preparedStatement.executeUpdate();
    }
}
