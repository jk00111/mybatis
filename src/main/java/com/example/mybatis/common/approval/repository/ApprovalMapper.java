package com.example.mybatis.common.approval.repository;

import com.example.mybatis.common.approval.entity.ApprovalEntityDto;
import com.example.mybatis.common.approval.vo.ApprovalId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApprovalMapper {

    void create(ApprovalEntityDto entityDto);

    void update(ApprovalEntityDto entityDto);

    void delete(ApprovalId id);

    ApprovalEntityDto findOne(ApprovalId id);

}
