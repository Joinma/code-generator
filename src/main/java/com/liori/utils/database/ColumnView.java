package com.liori.utils.database;

public class ColumnView {

    private String columnName;

    private String columnNameCameCase;

    private String columnType;

    private String columnComment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnNameCameCase() {
        return columnNameCameCase;
    }

    public void setColumnNameCameCase(String columnNameCameCase) {
        this.columnNameCameCase = columnNameCameCase;
    }

    @Override
    public String toString() {
        return "ColumnView{" +
                "columnName='" + columnName + '\'' +
                ", columnNameCameCase='" + columnNameCameCase + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                '}';
    }
}
