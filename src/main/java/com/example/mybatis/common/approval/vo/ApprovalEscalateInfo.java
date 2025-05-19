package com.example.mybatis.common.approval.vo;

import com.example.mybatis.common.IdentityValue;
import com.example.mybatis.common.approval.entity.ApprovalDecider;
import com.example.mybatis.common.approval.user.ApprovalRequester;
import com.example.mybatis.common.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApprovalEscalateInfo {

    private final IdentityValue identityValue;
    private final ApprovalRequester requester;
    private final List<ApprovalDecider> line;

    private ApprovalEscalateInfo(Builder builder) {
        this.identityValue = builder.identityValue;
        this.requester = builder.requester;
        this.line = builder.line;
    }

    public static class Builder {
        private IdentityValue identityValue;
        private ApprovalRequester requester;
        private final List<ApprovalDecider> line = new ArrayList<>();


        public Builder identityValue(IdentityValue identityValue) {
            this.identityValue = identityValue;
            return this;
        }

        public Builder requester(User user) {
            this.requester = ApprovalRequester.from(user);
            return this;
        }


        public Builder line(User... users) {
            for (User user : users) {
                line.add(convertDecideUser(user));
            }

            return this;
        }

        public Builder line(List<User> users) {
            for (User user : users) {
                line.add(convertDecideUser(user));
            }

            return this;
        }

        public ApprovalEscalateInfo build() {
            validateNotNull();
            return new ApprovalEscalateInfo(this);
        }

        private ApprovalDecider convertDecideUser(User user) {
            return new ApprovalDecider(user);
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
