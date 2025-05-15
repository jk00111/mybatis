package com.example.mybatis.common.approval.entity;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import com.example.mybatis.common.approval.user.ApprovalRequester;
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

    protected ApprovalEntityDto() {
    }

    public ApprovalEntityDto(Integer id, Integer requesterId, ApprovalRole requesterRole, ApprovalAction requesterAction) {
        this.id = id;
        this.requesterId = requesterId;
        this.requesterRole = requesterRole;
        this.requesterAction = requesterAction;
    }

    public ApprovalEntityDto(Integer requesterId, ApprovalRole requesterRole, ApprovalAction requesterAction) {
        this.requesterId = requesterId;
        this.requesterRole = requesterRole;
        this.requesterAction = requesterAction;
    }

    public static ApprovalEntityDto from(ApprovalEntity entity) {
        ApprovalId approvalId = entity.getId();
        ApprovalRequester requester = entity.getRequester();

        return new ApprovalEntityDto(approvalId.getId(), requester.id(), entity.getRequester().role(), entity.getRequester().action());
    }

    public static ApprovalEntityDto FromEscalate(ApprovalEntity entity) {
        ApprovalRequester requester = entity.getRequester();
        return new ApprovalEntityDto(requester.id(), entity.getRequester().role(), entity.getRequester().action());
    }
}
