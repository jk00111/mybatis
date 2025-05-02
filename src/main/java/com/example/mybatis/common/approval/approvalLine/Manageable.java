package com.example.mybatis.common.approval.approvalLine;

import java.util.List;


public interface Manageable {

    List<ApprovalUnit> getLeaf();

    void add(ApprovalUnit line);

    void remove(ApprovalUnit line);

}
