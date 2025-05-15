package com.example.mybatis.common.approval.repository;

import com.example.mybatis.common.approval.entity.ApprovalEntity;
import com.example.mybatis.common.approval.vo.ApprovalId;

public interface ApprovalRepository {

    ApprovalEntity findOne(ApprovalId id);

    void create(ApprovalEntity entity);

    void update(ApprovalEntity entity);
}
