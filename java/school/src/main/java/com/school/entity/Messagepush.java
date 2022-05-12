package com.school.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName messagepush
 */
@TableName(value ="messagepush")
public class Messagepush implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动时间
     */
    private Date atime;

    /**
     * 活动地点
     */
    private String address;

    /**
     * 主办方
     */
    private String sponsor;

    /**
     * 活动说明
     */
    private String description;

    /**
     * 模板id
     */
    private String templateId;

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
     * 活动标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 活动标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 活动时间
     */
    public Date getAtime() {
        return atime;
    }

    /**
     * 活动时间
     */
    public void setAtime(Date atime) {
        this.atime = atime;
    }

    /**
     * 活动地点
     */
    public String getAddress() {
        return address;
    }

    /**
     * 活动地点
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 主办方
     */
    public String getSponsor() {
        return sponsor;
    }

    /**
     * 主办方
     */
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * 活动说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 活动说明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 模板id
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * 模板id
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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
        Messagepush other = (Messagepush) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAtime() == null ? other.getAtime() == null : this.getAtime().equals(other.getAtime()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getSponsor() == null ? other.getSponsor() == null : this.getSponsor().equals(other.getSponsor()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getAtime() == null) ? 0 : getAtime().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getSponsor() == null) ? 0 : getSponsor().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", atime=").append(atime);
        sb.append(", address=").append(address);
        sb.append(", sponsor=").append(sponsor);
        sb.append(", description=").append(description);
        sb.append(", templateId=").append(templateId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}