package com.liori.utils.database;

import com.liori.utils.baseinfo.BaseInfoUtil;
import com.liori.utils.word.CamelCaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>数据库工具类</p>
 * <b>create on 2019-01-15 21:34:20</b>
 *
 * @author liori
 * @since code-generator 0.0.2
 */
public class DatabaseUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private final static String URL = "jdbc:mysql://"
            + BaseInfoUtil.DATABASE_HOST
            + ":" + BaseInfoUtil.DATABASE_PORT
            + "/" + BaseInfoUtil.DATABASE_CATALOG
            + "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";

    private final static List<String> BASE_COLUMNS = Arrays.asList("id", "createTime", "updateTime", "enabled", "sequence");

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("jdbc driver 加载时发生异常\n" + e);
        }
    }

    /**
     * 获取指定表的所有字段、字段类型、字段注释
     *
     * @return List<ColumnView>
     */
    @Deprecated
    public static List<ColumnView> getTableColumns_outdate() {
        List<ColumnView> columnViews = new ArrayList<>();
        ResultSet commentResultSet = null;
        PreparedStatement pStemt = null;
        try (Connection conn = DriverManager.getConnection(URL, BaseInfoUtil.DATABASE_USERNAME, BaseInfoUtil.DATABASE_PASSWORD)) {
            ColumnView columnView;
            String selctAllSql = "select * from " + BaseInfoUtil.DATABASE_TABLE_NAME;
            pStemt = conn.prepareStatement(selctAllSql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnView = new ColumnView();
                // 字段名
                String columnName = rsmd.getColumnName(i + 1);
                String colmnNameCameCase = formatColumnName(columnName);
                if (BASE_COLUMNS.contains(colmnNameCameCase)) {
                    continue;
                } else {
                    columnView.setColumnName(colmnNameCameCase);
                }
                // 字段名驼峰（首字母大写）
                String colmnNameBigCameCase = CamelCaseUtil.changeUnderlineToCamelCase(columnName);
                columnView.setColumnNameCameCase(colmnNameBigCameCase);
                // 字段类型
                String columnType = rsmd.getColumnTypeName(i + 1);
                columnView.setColumnType(formatColumnType(columnType));
                // 字段注释
                String sql = "show full columns from " + BaseInfoUtil.DATABASE_TABLE_NAME + " where Field = '" + columnName + "'";
                commentResultSet = pStemt.executeQuery(sql);
                String comment = "";
                if (commentResultSet.next()) {
                    comment = commentResultSet.getString("Comment");
                    columnView.setColumnComment(comment);
                }
                columnViews.add(columnView);
            }
            return columnViews;
        } catch (SQLException e) {
            throw new RuntimeException("获取表字段时发生异常\n" + e);
        } finally {
            colseResource(commentResultSet, pStemt);
        }
    }

    public static List<ColumnView> getTableColumns() {
        List<ColumnView> columnViews = new ArrayList<>();
        ResultSet commentResultSet = null;
        PreparedStatement pStemt = null;
        try (Connection conn = DriverManager.getConnection(URL, BaseInfoUtil.DATABASE_USERNAME, BaseInfoUtil.DATABASE_PASSWORD)) {
            ColumnView columnView;
            String selctAllSql = "select * from " + BaseInfoUtil.DATABASE_TABLE_NAME;
            pStemt = conn.prepareStatement(selctAllSql);
            String showAllSql = "show full columns from " + BaseInfoUtil.DATABASE_TABLE_NAME;
            commentResultSet = pStemt.executeQuery(showAllSql);
            while (commentResultSet.next()) {
                columnView = new ColumnView();
                // 字段名
                String columnName = commentResultSet.getString("Field");
                String colmnNameCameCase = formatColumnName(columnName);
                if (BASE_COLUMNS.contains(colmnNameCameCase)) {
                    continue;
                } else {
                    columnView.setColumnName(colmnNameCameCase);
                }
                // 字段名驼峰（首字母大写）
                String colmnNameBigCameCase = CamelCaseUtil.changeUnderlineToCamelCase(columnName);
                columnView.setColumnNameCameCase(colmnNameBigCameCase);
                // 字段类型
                String columnType = commentResultSet.getString("Type");
                columnView.setColumnType(formatColumnType(columnType));
                String comment = commentResultSet.getString("Comment");
                columnView.setColumnComment(comment);
                columnViews.add(columnView);
            }
            return columnViews;
        } catch (SQLException e) {
            throw new RuntimeException("获取表字段时发生异常\n" + e);
        } finally {
            colseResource(commentResultSet, pStemt);
        }
    }

    private static void colseResource(ResultSet commentResultSet, PreparedStatement pStemt) {
        if (commentResultSet != null) {
            try {
                commentResultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException("关闭 ResultSet 时发生异常\n" + e);
            }
        }
        if (pStemt != null) {
            try {
                pStemt.close();
            } catch (SQLException e) {
                throw new RuntimeException("关闭 PreparedStatement 时发生异常\n" + e);
            }
        }
    }

    private static String formatColumnName(String columnName) {
        return CamelCaseUtil.changeUnderlineToCamelCaseFirstLowerCase(columnName);
    }

    private static String formatColumnType(String dbColumnType) {
        String columnType;
        switch (dbColumnType.toLowerCase().trim().split("\\(")[0]) {
            case "varchar":
                columnType = "String";
                break;
            case "text":
                columnType = "String";
                break;
            case "longtext":
                columnType = "String";
                break;
            case "bigint":
                columnType = "Long";
                break;
            case "int":
                columnType = "Integer";
                break;
            case "date":
                columnType = "Date";
                break;
            default:
                throw new RuntimeException("暂不支持的数据类型：" + dbColumnType);
        }
        return columnType;
    }
}
