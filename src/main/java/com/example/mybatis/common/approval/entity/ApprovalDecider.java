package com.example.mybatis.common.approval.entity;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.common.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApprovalDecider {

     @EqualsAndHashCode.Include
     private Integer approvalId;

     @EqualsAndHashCode.Include
     private Integer userId;
     private String name;
     private ApprovalRole role;
     private ApprovalAction action;

     private boolean updateFlag = false;

     protected ApprovalDecider() {
     }

     public ApprovalDecider(User user) {
          this.userId = user.getId();
          this.name = user.getName();
          this.role = ApprovalRole.APPROVER;
          this.action = ApprovalAction.NONE;
     }


     public ApprovalDecider(User user, ApprovalId id) {
          this.userId = user.getId();
          this.name = user.getName();
          this.approvalId = id.getId();
     }

     public void register(Integer approvalId) {
          this.approvalId = approvalId;
     }

     public boolean isUpdated() {
          return updateFlag;
     }

     public void approve() {
          this.action = ApprovalAction.APPROVE;
          this.updateFlag = true;
     }
     
     public void reject() {
          this.action = ApprovalAction.REJECT;
          this.updateFlag = true;
     }

     public Integer id() {
          return userId;
     }

     public String name() {
          return name;
     }

     public ApprovalRole role() {
          return role;
     }

     public ApprovalAction action() {
          return action;
     }
}
