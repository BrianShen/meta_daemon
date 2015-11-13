package com.fanli.metadata.core.bean;

import java.util.Date;

public class EtlMetaColumn  {
    private Long globalId;

    private Integer columnId;

    private Integer tableId;

    private String columnName;

    private Integer columnIndex;

    private String columnType;

    private String columnComment;

    private String defaultValue;

    private Integer columnAccessCode;

    private Byte isPrimary;

    private Byte isForeign;

    private Byte isPartition;

    private Integer hotLevel;

    private Date addTime;

    private Date updateTime;

    private String lastUpdateUser;
    public Long getGlobalId() {
        return globalId;
    }

    public void setGlobalId(Long globalId) {
        this.globalId = globalId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment == null ? null : columnComment.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public Integer getColumnAccessCode() {
        return columnAccessCode;
    }

    public void setColumnAccessCode(Integer columnAccessCode) {
        this.columnAccessCode = columnAccessCode;
    }

    public Byte getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Byte isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Byte getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(Byte isForeign) {
        this.isForeign = isForeign;
    }

    public Byte getIsPartition() {
        return isPartition;
    }

    public void setIsPartition(Byte isPartition) {
        this.isPartition = isPartition;
    }

    public Integer getHotLevel() {
        return hotLevel;
    }

    public void setHotLevel(Integer hotLevel) {
        this.hotLevel = hotLevel;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }
}