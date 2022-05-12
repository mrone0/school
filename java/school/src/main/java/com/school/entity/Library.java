package com.school.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName library
 */
@TableName(value ="library")
public class Library implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 座位区域
     */
    private String seataddress;

    /**
     * 是否有位置
     */
    private String hasposition;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 座位区域
     */
    public String getSeataddress() {
        return seataddress;
    }

    /**
     * 座位区域
     */
    public void setSeataddress(String seataddress) {
        this.seataddress = seataddress;
    }

    /**
     * 是否有位置
     */
    public String getHasposition() {
        return hasposition;
    }

    /**
     * 是否有位置
     */
    public void setHasposition(String hasposition) {
        this.hasposition = hasposition;
    }

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
        Library other = (Library) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSeataddress() == null ? other.getSeataddress() == null : this.getSeataddress().equals(other.getSeataddress()))
            && (this.getHasposition() == null ? other.getHasposition() == null : this.getHasposition().equals(other.getHasposition()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSeataddress() == null) ? 0 : getSeataddress().hashCode());
        result = prime * result + ((getHasposition() == null) ? 0 : getHasposition().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", seataddress=").append(seataddress);
        sb.append(", hasposition=").append(hasposition);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}