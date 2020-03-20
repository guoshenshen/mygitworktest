package com.elearning.pojo.systemManage;

import java.util.HashSet;
import java.util.Set;

public class Resource {
    private Integer ID;

    private String code;

    private String resourceName;

    private Integer parentId;

    private String link;

    private String linkBase;

    private String hyperlink;

    private Integer seq;

    private String comment;

    private Integer status;

    private String defaultRole;

    private String iconDir;

    private String iconDirBase;

    private Resource parent;

    private Set<Resource> childrenResources = new HashSet<>();



    public Resource(Integer ID, String code, String resourceName, Integer parentId, String link, String linkBase, String hyperlink, Integer seq, String comment, Integer status, String defaultRole, String iconDir, String iconDirBase) {
        this.ID = ID;
        this.code = code;
        this.resourceName = resourceName;
        this.parentId = parentId;
        this.link = link;
        this.linkBase = linkBase;
        this.hyperlink = hyperlink;
        this.seq = seq;
        this.comment = comment;
        this.status = status;
        this.defaultRole = defaultRole;
        this.iconDir = iconDir;
        this.iconDirBase = iconDirBase;
    }

    public Resource() {
    }

    public String getLinkBase() {
        return linkBase;
    }

    public void setLinkBase(String linkBase) {
        this.linkBase = linkBase;
    }

    public String getIconDirBase() {
        return iconDirBase;
    }

    public void setIconDirBase(String iconDirBase) {
        this.iconDirBase = iconDirBase;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

    public String getIconDir() {
        return iconDir;
    }

    public void setIconDir(String iconDir) {
        this.iconDir = iconDir;
    }

    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    public Set<Resource> getChildrenResources() {
        return childrenResources;
    }

    public void setChildrenResources(Set<Resource> childrenResources) {
        this.childrenResources = childrenResources;
    }
}