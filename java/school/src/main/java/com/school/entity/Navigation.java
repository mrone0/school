package com.school.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName navigation
 */
@TableName(value ="navigation")
public class Navigation implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer sid;

    /**
     * 校园地址
     */
    private String address;

    /**
     * 地址照片
     */
    private String image;

    /**
     * 具体地址
     */
    private String 具体地址;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * id
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 校园地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 校园地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 地址照片
     */
    public String getImage() {
        return image;
    }

    /**
     * 地址照片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 具体地址
     */
    public String get具体地址() {
        return 具体地址;
    }

    /**
     * 具体地址
     */
    public void set具体地址(String 具体地址) {
        this.具体地址 = 具体地址;
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
        Navigation other = (Navigation) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.get具体地址() == null ? other.get具体地址() == null : this.get具体地址().equals(other.get具体地址()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((get具体地址() == null) ? 0 : get具体地址().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", address=").append(address);
        sb.append(", image=").append(image);
        sb.append(", 具体地址=").append(具体地址);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}