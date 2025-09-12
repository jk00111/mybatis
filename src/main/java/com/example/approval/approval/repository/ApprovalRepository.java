package com.example.approval.approval.repository;

import com.example.approval.approval.entity.Approval;

public interface ApprovalRepository {

    Approval findOne(long id);
    void create(Approval approval);
    void update(Approval approval);
}
