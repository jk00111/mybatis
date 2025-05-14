package com.example.mybatis.common.approval.entity;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import com.example.mybatis.common.approval.user.ApprovalUser;
import com.example.mybatis.common.approval.vo.ApprovalId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalEntityDto {

    private Integer id;
    private Integer requesterId;
    private String requesterName;
    private ApprovalRole requesterRole;
    private ApprovalAction requesterAction;

    public ApprovalEntityDto(Integer id, Integer requesterId, ApprovalRole requesterRole, ApprovalAction requesterAction) {
        this.id = id;
        this.requesterId = requesterId;
        this.requesterRole = requesterRole;
        this.requesterAction = requesterAction;
    }

    public static ApprovalEntityDto from(ApprovalEntity entity) {
        ApprovalId approvalId = entity.getId();
        ApprovalUser requester = entity.getRequester();

        return new ApprovalEntityDto(approvalId.getId(), requester.id(), entity.getRequester().role(), entity.getRequester().action());
    }
}
