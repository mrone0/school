package com.school.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer sid;

    /**
     * 学号
     */
    private Integer snumber;

    /**
     * 学生姓名
     */
    private String username;

    /**
     * 学校
     */
    private String school;

    /**
     * 身份证号
     */
    private Long number;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 生日
     */
    private String birthday;

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
     * 学号
     */
    public Integer getSnumber() {
        return snumber;
    }

    /**
     * 学号
     */
    public void setSnumber(Integer snumber) {
        this.snumber = snumber;
    }

    /**
     * 学生姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 学生姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 学校
     */
    public String getSchool() {
        return school;
    }

    /**
     * 学校
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * 身份证号
     */
    public Long getNumber() {
        return number;
    }

    /**
     * 身份证号
     */
    public void setNumber(Long number) {
        this.number = number;
    }

    /**
     * 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 收货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 收货地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
        User other = (User) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getSnumber() == null ? other.getSnumber() == null : this.getSnumber().equals(other.getSnumber()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getSchool() == null ? other.getSchool() == null : this.getSchool().equals(other.getSchool()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getSnumber() == null) ? 0 : getSnumber().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getSchool() == null) ? 0 : getSchool().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", snumber=").append(snumber);
        sb.append(", username=").append(username);
        sb.append(", school=").append(school);
        sb.append(", number=").append(number);
        sb.append(", avatar=").append(avatar);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", birthday=").append(birthday);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}