package com.school.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName classroom
 */
@TableName(value ="classroom")
public class Classroom implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer cid;

    /**
     * 使用用户
     */
    private String name;

    /**
     * 教室位置
     */
    private String roomid;

    /**
     * 可用次数
     */
    private String usetime;

    /**
     * 是否被占用
     */
    private String tfsubscribe;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * id
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 使用用户
     */
    public String getName() {
        return name;
    }

    /**
     * 使用用户
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 教室位置
     */
    public String getRoomid() {
        return roomid;
    }

    /**
     * 教室位置
     */
    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    /**
     * 可用次数
     */
    public String getUsetime() {
        return usetime;
    }

    /**
     * 可用次数
     */
    public void setUsetime(String usetime) {
        this.usetime = usetime;
    }

    /**
     * 是否被占用
     */
    public String getTfsubscribe() {
        return tfsubscribe;
    }

    /**
     * 是否被占用
     */
    public void setTfsubscribe(String tfsubscribe) {
        this.tfsubscribe = tfsubscribe;
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
        Classroom other = (Classroom) that;
        return (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRoomid() == null ? other.getRoomid() == null : this.getRoomid().equals(other.getRoomid()))
            && (this.getUsetime() == null ? other.getUsetime() == null : this.getUsetime().equals(other.getUsetime()))
            && (this.getTfsubscribe() == null ? other.getTfsubscribe() == null : this.getTfsubscribe().equals(other.getTfsubscribe()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRoomid() == null) ? 0 : getRoomid().hashCode());
        result = prime * result + ((getUsetime() == null) ? 0 : getUsetime().hashCode());
        result = prime * result + ((getTfsubscribe() == null) ? 0 : getTfsubscribe().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", name=").append(name);
        sb.append(", roomid=").append(roomid);
        sb.append(", usetime=").append(usetime);
        sb.append(", tfsubscribe=").append(tfsubscribe);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}