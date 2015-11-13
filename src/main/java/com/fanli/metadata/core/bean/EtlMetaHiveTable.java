package com.fanli.metadata.core.bean;

public class EtlMetaHiveTable {
    private Long globalId;

    private Integer tableId;

    private String hiveDb;

    private String inputFormat;

    private String outputFormat;

    private String hdfsLocation;

    private String tableType;

    private String fieldTerminated;

    private String lineTerminated;

    private String storeFormat;

    private String ddlWithChange;

    public Long getGlobalId() {
        return globalId;
    }

    public void setGlobalId(Long globalId) {
        this.globalId = globalId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getHiveDb() {
        return hiveDb;
    }

    public void setHiveDb(String hiveDb) {
        this.hiveDb = hiveDb == null ? null : hiveDb.trim();
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat == null ? null : inputFormat.trim();
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat == null ? null : outputFormat.trim();
    }

    public String getHdfsLocation() {
        return hdfsLocation;
    }

    public void setHdfsLocation(String hdfsLocation) {
        this.hdfsLocation = hdfsLocation == null ? null : hdfsLocation.trim();
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType == null ? null : tableType.trim();
    }

    public String getFieldTerminated() {
        return fieldTerminated;
    }

    public void setFieldTerminated(String fieldTerminated) {
        this.fieldTerminated = fieldTerminated == null ? null : fieldTerminated.trim();
    }

    public String getLineTerminated() {
        return lineTerminated;
    }

    public void setLineTerminated(String lineTerminated) {
        this.lineTerminated = lineTerminated == null ? null : lineTerminated.trim();
    }

    public String getStoreFormat() {
        return storeFormat;
    }

    public void setStoreFormat(String storeFormat) {
        this.storeFormat = storeFormat == null ? null : storeFormat.trim();
    }

    public String getDdlWithChange() {
        return ddlWithChange;
    }

    public void setDdlWithChange(String ddlWithChange) {
        this.ddlWithChange = ddlWithChange == null ? null : ddlWithChange.trim();
    }
}