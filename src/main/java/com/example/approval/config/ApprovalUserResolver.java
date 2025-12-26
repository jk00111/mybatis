package com.example.approval.config;

import com.example.approval.vo.ApprovalUser;
import com.example.mybatis.common.entity.User;

public interface ApprovalUserResolver {

    ApprovalUser resolve(User entity);

}
