package com.example.demo.repository.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 设备信息表
 * @TableName device
 */
@TableName(value ="device")
@Data
public class Device implements Serializable {
    /**
     * 设备唯一ID（UUID格式）
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 设备SKU（唯一库存单位编码）
     */
    @TableField(value = "sku")
    private String sku;

    /**
     * 所属用户ID（关联用户表）
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 设备型号（如“Xiaomi AC 2023”）
     */
    @TableField(value = "model")
    private String model;

    /**
     * 设备名称（如“客厅空调”）
     */
    @TableField(value = "name")
    private String name;

    /**
     * 设备大类（智能家居/智能照明等）
     */
    @TableField(value = "type")
    private Object type;

    /**
     * 设备子类型（如“智能台灯”“智能制冰机”）
     */
    @TableField(value = "sub_type")
    private String subType;

    /**
     * 连接方式
     */
    @TableField(value = "connection_type")
    private Object connectionType;

    /**
     * 固件版本（如“v2.1.6”）
     */
    @TableField(value = "firmware_version")
    private String firmwareVersion;

    /**
     * MAC地址（格式如 `00:1A:2B:3C:4D:5E`）
     */
    @TableField(value = "mac_address")
    private String macAddress;

    /**
     * IP地址（IPv4/IPv6）
     */
    @TableField(value = "ip_address")
    private String ipAddress;

    /**
     * 设备实时状态
     */
    @TableField(value = "status")
    private Object status;

    /**
     * 设备位置（如“客厅”“卧室”）
     */
    @TableField(value = "location")
    private String location;

    /**
     * 是否已激活
     */
    @TableField(value = "activation_status")
    private Integer activationStatus;

    /**
     * 最后在线时间
     */
    @TableField(value = "last_online_time")
    private Date lastOnlineTime;

    /**
     * 设备注册时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Device other = (Device) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSku() == null ? other.getSku() == null : this.getSku().equals(other.getSku()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getModel() == null ? other.getModel() == null : this.getModel().equals(other.getModel()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getSubType() == null ? other.getSubType() == null : this.getSubType().equals(other.getSubType()))
            && (this.getConnectionType() == null ? other.getConnectionType() == null : this.getConnectionType().equals(other.getConnectionType()))
            && (this.getFirmwareVersion() == null ? other.getFirmwareVersion() == null : this.getFirmwareVersion().equals(other.getFirmwareVersion()))
            && (this.getMacAddress() == null ? other.getMacAddress() == null : this.getMacAddress().equals(other.getMacAddress()))
            && (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getActivationStatus() == null ? other.getActivationStatus() == null : this.getActivationStatus().equals(other.getActivationStatus()))
            && (this.getLastOnlineTime() == null ? other.getLastOnlineTime() == null : this.getLastOnlineTime().equals(other.getLastOnlineTime()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSku() == null) ? 0 : getSku().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getModel() == null) ? 0 : getModel().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getSubType() == null) ? 0 : getSubType().hashCode());
        result = prime * result + ((getConnectionType() == null) ? 0 : getConnectionType().hashCode());
        result = prime * result + ((getFirmwareVersion() == null) ? 0 : getFirmwareVersion().hashCode());
        result = prime * result + ((getMacAddress() == null) ? 0 : getMacAddress().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getActivationStatus() == null) ? 0 : getActivationStatus().hashCode());
        result = prime * result + ((getLastOnlineTime() == null) ? 0 : getLastOnlineTime().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sku=").append(sku);
        sb.append(", user_id=").append(userId);
        sb.append(", model=").append(model);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", sub_type=").append(subType);
        sb.append(", connection_type=").append(connectionType);
        sb.append(", firmware_version=").append(firmwareVersion);
        sb.append(", mac_address=").append(macAddress);
        sb.append(", ip_address=").append(ipAddress);
        sb.append(", status=").append(status);
        sb.append(", location=").append(location);
        sb.append(", activation_status=").append(activationStatus);
        sb.append(", last_online_time=").append(lastOnlineTime);
        sb.append(", created_at=").append(createdAt);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}