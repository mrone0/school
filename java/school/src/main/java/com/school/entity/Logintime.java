package com.school.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName logintime
 */
@TableName(value ="logintime")
public class Logintime implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 登录时间
     */
    private Date logintime;

    /**
     * openid
     */
    private String openid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 登录时间
     */
    public Date getLogintime() {
        return logintime;
    }

    /**
     * 登录时间
     */
    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    /**
     * openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
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
        Logintime other = (Logintime) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLogintime() == null ? other.getLogintime() == null : this.getLogintime().equals(other.getLogintime()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLogintime() == null) ? 0 : getLogintime().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", logintime=").append(logintime);
        sb.append(", openid=").append(openid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}