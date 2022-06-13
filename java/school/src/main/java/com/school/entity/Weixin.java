package com.school.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_weixin
 */
@TableName(value ="tb_weixin")
public class Weixin implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一标识
     */
    private String openId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 用户在开放平台的唯一标识
     */
    private String appId;

    /**
     * 是否授权
     */
    private Integer isAuth;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * sessionkey
     */
    private String sessionkey;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户唯一标识
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 用户唯一标识
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 头像
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 头像
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 用户在开放平台的唯一标识
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 用户在开放平台的唯一标识
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 是否授权
     */
    public Integer getIsAuth() {
        return isAuth;
    }

    /**
     * 是否授权
     */
    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * sessionkey
     */
    public String getSessionkey() {
        return sessionkey;
    }

    /**
     * sessionkey
     */
    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
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
        Weixin other = (Weixin) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getAvatarUrl() == null ? other.getAvatarUrl() == null : this.getAvatarUrl().equals(other.getAvatarUrl()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getIsAuth() == null ? other.getIsAuth() == null : this.getIsAuth().equals(other.getIsAuth()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getSessionkey() == null ? other.getSessionkey() == null : this.getSessionkey().equals(other.getSessionkey()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getAvatarUrl() == null) ? 0 : getAvatarUrl().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getIsAuth() == null) ? 0 : getIsAuth().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSessionkey() == null) ? 0 : getSessionkey().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openId=").append(openId);
        sb.append(", nickName=").append(nickName);
        sb.append(", gender=").append(gender);
        sb.append(", city=").append(city);
        sb.append(", province=").append(province);
        sb.append(", country=").append(country);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", appId=").append(appId);
        sb.append(", isAuth=").append(isAuth);
        sb.append(", createTime=").append(createTime);
        sb.append(", sessionkey=").append(sessionkey);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}