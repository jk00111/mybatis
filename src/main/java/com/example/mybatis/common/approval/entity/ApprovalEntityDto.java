package com.example.mybatis.common.approval.entity;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.approvalLine.ApprovalUnit;
import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalEntityDto {

    private Integer id;
    private String contents;
    private Integer requesterId;

    public static ApprovalEntityDto from(ApprovalEntity entity) {
        return null;
    }
}
