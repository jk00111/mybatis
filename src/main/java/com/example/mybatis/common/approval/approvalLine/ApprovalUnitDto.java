package com.example.mybatis.common.approval.approvalLine;

import com.example.mybatis.common.approval.enums.ApprovalRole;
import com.example.mybatis.common.approval.enums.ApprovalDecision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalUnitDto {

    private Integer approvalId;

    private Integer stage;

    private Integer order;

    private Integer userId;

    private String userName;

    private ApprovalRole approvalRole;

    private ApprovalDecision approvalDecision;

}
