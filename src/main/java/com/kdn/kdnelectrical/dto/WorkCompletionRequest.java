package com.kdn.kdnelectrical.dto;

public class WorkCompletionRequest {

    private String beforeImageUrl;
    private String afterImageUrl;
    private String workNotes;

    public String getBeforeImageUrl() { return beforeImageUrl; }
    public void setBeforeImageUrl(String beforeImageUrl) { this.beforeImageUrl = beforeImageUrl; }

    public String getAfterImageUrl() { return afterImageUrl; }
    public void setAfterImageUrl(String afterImageUrl) { this.afterImageUrl = afterImageUrl; }

    public String getWorkNotes() { return workNotes; }
    public void setWorkNotes(String workNotes) { this.workNotes = workNotes; }
}
