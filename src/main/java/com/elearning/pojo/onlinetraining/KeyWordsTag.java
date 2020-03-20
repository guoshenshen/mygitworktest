package com.elearning.pojo.onlinetraining;

public class KeyWordsTag {
    private Integer tagID;

    private String keyWords;

    public KeyWordsTag(Integer tagID, String keyWords) {
        this.tagID = tagID;
        this.keyWords = keyWords;
    }

    public KeyWordsTag() {
        super();
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords == null ? null : keyWords.trim();
    }
}