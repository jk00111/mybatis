package com.example.mybatis.common.approval.vo;

import com.example.mybatis.common.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApprovalUser {

    private final Integer id;
    private final String name;

    public static ApprovalUser of(User user) {
        return new ApprovalUser(user.getId(), user.getName());
    }
}
