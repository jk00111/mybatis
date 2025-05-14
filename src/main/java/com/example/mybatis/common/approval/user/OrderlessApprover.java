package com.example.mybatis.common.approval.user;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderlessApprover implements ApprovalDecider {

     @EqualsAndHashCode.Include
     private Integer approvalId;

     @EqualsAndHashCode.Include
     private Integer id;
     private String name;
     private ApprovalRole role;
     private ApprovalAction action;

     private boolean updateFlag = false;

     public OrderlessApprover(Integer approvalId, Integer id, String name, ApprovalRole role, ApprovalAction action) {
          this.approvalId = approvalId;
          this.id = id;
          this.name = name;
          this.role = role;
          this.action = action;
     }

     public OrderlessApprover(Integer id, String name, ApprovalRole role, ApprovalAction action) {
          this.id = id;
          this.name = name;
          this.role = role;
          this.action = action;
     }

     @Override
     public void register(Integer approvalId) {
          this.approvalId = approvalId;
     }

     @Override
     public boolean isUpdated() {
          return updateFlag;
     }

     @Override
     public void approve() {
          this.action = ApprovalAction.APPROVE;
          this.updateFlag = true;
     }
     
     @Override
     public void reject() {
          this.action = ApprovalAction.REJECT;
          this.updateFlag = true;
     }

     @Override
     public Integer id() {
          return id;
     }

     @Override
     public String name() {
          return name;
     }

     @Override
     public ApprovalRole role() {
          return role;
     }

     @Override
     public ApprovalAction action() {
          return action;
     }
}
