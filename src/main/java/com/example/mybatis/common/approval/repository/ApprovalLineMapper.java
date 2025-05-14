package com.example.mybatis.common.approval.repository;

import com.example.mybatis.common.approval.user.ApprovalUser;
import com.example.mybatis.common.approval.user.ApprovalDecider;
import com.example.mybatis.common.approval.vo.ApprovalId;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalLineMapper {

    List<ApprovalDecider> findByApprovalId(ApprovalId approvalId);

    void create(ApprovalUser user);

    void update(ApprovalUser user);

    void delete(ApprovalUser user);

}
