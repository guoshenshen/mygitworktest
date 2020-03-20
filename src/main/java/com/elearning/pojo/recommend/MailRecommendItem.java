package com.elearning.pojo.recommend;

public class MailRecommendItem {
    private Long id;

    private Integer tag;

    private String pictureUrl;

    private String recommendTitle;

    private String sponsorInfo;

    private String recommendDetail;

    private Long itemId;

    private Integer itemType;

    private Long recommendMailId;

    private Integer itemOrder;

    public MailRecommendItem(Long id, Integer tag, String pictureUrl, String recommendTitle, String sponsorInfo, String recommendDetail, Long itemId, Integer itemType, Long recommendMailId, Integer itemOrder) {
        this.id = id;
        this.tag = tag;
        this.pictureUrl = pictureUrl;
        this.recommendTitle = recommendTitle;
        this.sponsorInfo = sponsorInfo;
        this.recommendDetail = recommendDetail;
        this.itemId = itemId;
        this.itemType = itemType;
        this.recommendMailId = recommendMailId;
        this.itemOrder = itemOrder;
    }

    public MailRecommendItem() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getRecommendTitle() {
        return recommendTitle;
    }

    public void setRecommendTitle(String recommendTitle) {
        this.recommendTitle = recommendTitle == null ? null : recommendTitle.trim();
    }

    public String getSponsorInfo() {
        return sponsorInfo;
    }

    public void setSponsorInfo(String sponsorInfo) {
        this.sponsorInfo = sponsorInfo == null ? null : sponsorInfo.trim();
    }

    public String getRecommendDetail() {
        return recommendDetail;
    }

    public void setRecommendDetail(String recommendDetail) {
        this.recommendDetail = recommendDetail == null ? null : recommendDetail.trim();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Long getRecommendMailId() {
        return recommendMailId;
    }

    public void setRecommendMailId(Long recommendMailId) {
        this.recommendMailId = recommendMailId;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }
}