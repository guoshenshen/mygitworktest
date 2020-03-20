package com.elearning.pojo.pub;

public class ResourceItem {
    private Long id;

    private Long resourceId;

    private Long itemId;

    private Integer resourceType;

    private Integer itemType;

    public ResourceItem(Long id, Long resourceId, Long itemId, Integer resourceType, Integer itemType) {
        this.id = id;
        this.resourceId = resourceId;
        this.itemId = itemId;
        this.resourceType = resourceType;
        this.itemType = itemType;
    }

    public ResourceItem() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }
}