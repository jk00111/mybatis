package com.example.mybatis.common.approval.entity;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.approvalLine.OrderlessApprovalLine;
import com.example.mybatis.common.approval.enums.ApprovalStatus;
import com.example.mybatis.common.approval.user.ApprovalRequester;
import com.example.mybatis.common.approval.user.SimpleApprovalRequester;
import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import lombok.Getter;

import java.util.List;

@Getter
public class ApprovalEntity {

    private ApprovalId id;
    private ApprovalRequester requester;
    private ApprovalLine approvalLine;
    private ApprovalStatus status;

    public ApprovalEntity(ApprovalId id, ApprovalRequester requester, ApprovalLine approvalLine) {
        this.id = id;
        this.requester = requester;
        this.approvalLine = approvalLine;
    }

    public ApprovalEntity(ApprovalRequester requester, ApprovalLine approvalLine, ApprovalStatus status) {
        this.requester = requester;
        this.approvalLine = approvalLine;
        this.status = status;
    }

    public void approve(ApprovalSubmit submit) {
        ApprovalDecider approver = approvalLine.findDecider(submit.submitUser());
        approver.approve();

        /**
         * 결재 라인 3명인 결재건이다.
         * 현재 이 객체는 1명이 결재헀던 2명이 안한 결재라인을 가지고 있다.
         * 이때, 결재안한 2명이 동시에 결재를 진행해서 결재를 완료했다.
         *
         * 그럼 3명이 전부 결재완료인데, 결재 데이터를 상태가 결재중이야.
         *
         * 이거를 해결할 수 있는 모든 방법들, 모든 장단점을 알아보고
         * 가장 적절한 방법으로 구현.
         *
         * 1. 가시성 -> 이것만으로 해결되는 케이스
         * 2. CAS -> 이것만으로 해결되는 케이스
         * 3. LOCK -> 이것만으로 해결되는 케이스
         *  (java)
         *  - 모니터 락
         *  
         *  (db)
         *  - row 락 (oracle)
         *  
         *  (구현 방식 - 개념적인 방식?)
         *  - 낙관적 락
         *  - 비관적 락
         *  
         *  (분산 서버 환경에서)
         *  - 분산 락
         *  
         *  (구현 방식 -> 코드레벨)
         *  - spin lock
         *  - pubsub lock
         *
         *
         */

        if (isFinish()) {
            finish();
        }
    }

    public void setId(ApprovalId id) {
        this.id = id;
    }

    public void reject(ApprovalSubmit submit) {
        ApprovalDecider approver = approvalLine.findDecider(submit.submitUser());
        approver.reject();

        this.status = ApprovalStatus.REJECTED;
    }

    public void finish() {
        this.status = ApprovalStatus.APPROVED;
    }

    public void cancel() {
        status = ApprovalStatus.CANCELED;
    }

    public ApprovalId id() {
        return id;
    }

    public boolean isFinish() {
        return approvalLine.isFinish();
    }

    public static ApprovalEntity escalate(ApprovalEscalateInfo info) {
        List<ApprovalDecider> line = info.getLine();
        ApprovalRequester requester = info.getRequester();
        return new ApprovalEntity(requester, new OrderlessApprovalLine(line), ApprovalStatus.ESCALATED);
    }

    public static ApprovalEntity of(ApprovalEntityDto entityDto, List<ApprovalDecider> line) {
        return  new ApprovalEntity(
                    new ApprovalId(entityDto.getId()),
                    new SimpleApprovalRequester(
                        entityDto.getRequesterId(),
                        entityDto.getRequesterName()
                            ),
                    new OrderlessApprovalLine(line)
                );
    }
}
