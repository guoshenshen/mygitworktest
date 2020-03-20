package com.elearning.pojo.integralTask;

public class IntegralPermit {
    private Long id;

    private Long operatorId;

    private String updatePermit;

    private String deletePermit;

    private String addPermit;

    private Integer status;

    private String checkPermit;

    private String updateStuIntegPermit;

    public IntegralPermit(Long id, Long operatorId, String updatePermit, String deletePermit, String addPermit, Integer status, String checkPermit, String updateStuIntegPermit) {
        this.id = id;
        this.operatorId = operatorId;
        this.updatePermit = updatePermit;
        this.deletePermit = deletePermit;
        this.addPermit = addPermit;
        this.status = status;
        this.checkPermit = checkPermit;
        this.updateStuIntegPermit = updateStuIntegPermit;
    }

    public IntegralPermit() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getUpdatePermit() {
        return updatePermit;
    }

    public void setUpdatePermit(String updatePermit) {
        this.updatePermit = updatePermit == null ? null : updatePermit.trim();
    }

    public String getDeletePermit() {
        return deletePermit;
    }

    public void setDeletePermit(String deletePermit) {
        this.deletePermit = deletePermit == null ? null : deletePermit.trim();
    }

    public String getAddPermit() {
        return addPermit;
    }

    public void setAddPermit(String addPermit) {
        this.addPermit = addPermit == null ? null : addPermit.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCheckPermit() {
        return checkPermit;
    }

    public void setCheckPermit(String checkPermit) {
        this.checkPermit = checkPermit == null ? null : checkPermit.trim();
    }

    public String getUpdateStuIntegPermit() {
        return updateStuIntegPermit;
    }

    public void setUpdateStuIntegPermit(String updateStuIntegPermit) {
        this.updateStuIntegPermit = updateStuIntegPermit == null ? null : updateStuIntegPermit.trim();
    }
}