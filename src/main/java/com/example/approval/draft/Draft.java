package com.example.approval.draft;

public class Draft {

    private long id;
    private long approvalId;
    private long reviewId;


    public long approvalId() {
        return this.approvalId;
    }

    public long reviewId() {
        return this.reviewId;
    }
}
