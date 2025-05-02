package com.example.mybatis.common.approval.repository;

import com.example.mybatis.common.approval.approvalLine.ApprovalUnit;
import com.example.mybatis.common.approval.vo.ApprovalId;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalLineMapper {

    List<ApprovalUnit> findByApprovalId(ApprovalId approvalId);

    void create(ApprovalUnit unit);

    void update(ApprovalUnit unit);

    void delete(ApprovalUnit unit);

}
