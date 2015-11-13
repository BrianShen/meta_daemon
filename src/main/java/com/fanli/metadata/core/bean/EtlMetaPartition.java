package com.fanli.metadata.core.bean;

public class EtlMetaPartition {
    private Integer partitionId;

    private String partitionName;

    private String partitionCreateTime;

    private Integer tableId;

    private String addTime;

    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName == null ? null : partitionName.trim();
    }

    public String getPartitionCreateTime() {
        return partitionCreateTime;
    }

    public void setPartitionCreateTime(String partitionCreateTime) {
        this.partitionCreateTime = partitionCreateTime == null ? null : partitionCreateTime.trim();
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }
}