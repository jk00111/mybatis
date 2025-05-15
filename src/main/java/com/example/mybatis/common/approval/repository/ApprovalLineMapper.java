package com.example.mybatis.common.approval.repository;

import com.example.mybatis.common.approval.user.ApprovalDecider;
import com.example.mybatis.common.approval.vo.ApprovalId;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApprovalLineMapper {

    List<ApprovalDecider> findByApprovalId(ApprovalId approvalId);

    void create(ApprovalDecider user);

    void update(ApprovalDecider user);

    void delete(ApprovalDecider user);

}
