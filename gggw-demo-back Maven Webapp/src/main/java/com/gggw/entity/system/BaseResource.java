package com.gggw.entity.system;

import java.util.List;

public class BaseResource {
    private Integer resourceId;

    private String resourceCode;

    private String resourceName;

    private String resourceType;

    private String description;

    private String url;

    private String urlInner;

    private Integer parentId;

    private Integer orderNum;

    private String classStyle;

    private String icon;

    private String memo;

    private String isActive;

    private List<BaseResource> kidResources;

    public List<BaseResource> getKidResources() {
        return kidResources;
    }

    public void setKidResources(List<BaseResource> kidResources) {
        this.kidResources = kidResources;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUrlInner() {
        return urlInner;
    }

    public void setUrlInner(String urlInner) {
        this.urlInner = urlInner == null ? null : urlInner.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getClassStyle() {
        return classStyle;
    }

    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle == null ? null : classStyle.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive == null ? null : isActive.trim();
    }
}