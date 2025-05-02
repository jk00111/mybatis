package com.example.mybatis.common.approval.vo;

import com.example.mybatis.common.IdentityValue;
import com.example.mybatis.common.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApprovalEscalateInfo {

    private final IdentityValue identityValue;
    private final ApprovalUser requester;
    private final List<ApprovalUser> line;

    private ApprovalEscalateInfo(builder builder) {
        this.identityValue = builder.identityValue;
        this.requester = builder.requester;
        this.line = builder.line;
    }

    public static class builder {
        private IdentityValue identityValue;
        private ApprovalUser requester;
        private List<ApprovalUser> line = new ArrayList<>();


        public builder identityValue(IdentityValue identityValue) {
            this.identityValue = identityValue;
            return this;
        }

        public builder requester(User user) {
            this.requester = convertApprovalUser(user);
            return this;
        }


        public builder line(List<User> users) {
            List<ApprovalUser> approvalUsers = new ArrayList<>();

            for (User user : users) {
                approvalUsers.add(convertApprovalUser(user));
            }

            if (!users.isEmpty()) {
                line = approvalUsers;
            }

            return this;
        }

        public ApprovalEscalateInfo build() {
            validateNotNull();
            return new ApprovalEscalateInfo(this);
        }

        private ApprovalUser convertApprovalUser(User user) {
            return ApprovalUser.of(user);
        }

        private void validateNotNull() {
            if (identityValue == null) {
                throw new NullPointerException("escalateInfo must have identityValue");
            }

            if (requester == null) {
                throw new NullPointerException("escalateInfo must have requester");
            }
        }
    }
}
