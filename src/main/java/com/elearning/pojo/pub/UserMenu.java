package com.elearning.pojo.pub;

public class UserMenu {
    private Integer id;

    private Integer operatorId;

    private Integer isHasMenu;

    private Integer tenantId;

    public UserMenu(Integer id, Integer operatorId, Integer isHasMenu, Integer tenantId) {
        this.id = id;
        this.operatorId = operatorId;
        this.isHasMenu = isHasMenu;
        this.tenantId = tenantId;
    }

    public UserMenu() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getIsHasMenu() {
        return isHasMenu;
    }

    public void setIsHasMenu(Integer isHasMenu) {
        this.isHasMenu = isHasMenu;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}