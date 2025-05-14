package com.example.mybatis.common.approval.repository;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.entity.ApprovalEntity;
import com.example.mybatis.common.approval.entity.ApprovalEntityDto;
import com.example.mybatis.common.approval.user.ApprovalDecider;
import com.example.mybatis.common.approval.vo.ApprovalId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApprovalRepository {

    private final ApprovalMapper approvalMapper;
    private final ApprovalLineMapper lineMapper;

    public ApprovalEntity findOne(ApprovalId id) {
        ApprovalEntityDto entityDto = approvalMapper.findOne(id);
        List<ApprovalDecider> lines = lineMapper.findByApprovalId(id);

        return ApprovalEntity.of(entityDto, lines);
    }

    public void create(ApprovalEntity entity) {
        ApprovalEntityDto entityDto = ApprovalEntityDto.from(entity);
        approvalMapper.create(entityDto);

        ApprovalLine line = entity.getApprovalLine();
        createLine(line, entityDto.getId());
    }

    public void update(ApprovalEntity entity) {
        ApprovalEntityDto entityDto = ApprovalEntityDto.from(entity);
        approvalMapper.update(entityDto);

        ApprovalLine line = entity.getApprovalLine();
        updateLine(line);
    }

    private void updateLine(ApprovalLine line) {
        List<ApprovalDecider> ApprovalDeciders = line.findAll();

        for (ApprovalDecider approvalDecider : ApprovalDeciders) {
            if (approvalDecider.isUpdated()) {
                lineMapper.update(approvalDecider);
            }
        }
    }

    private void createLine(ApprovalLine line, Integer approvalId) {
        List<ApprovalDecider> ApprovalDeciders = line.findAll();

        for (ApprovalDecider approvalDecider : ApprovalDeciders) {
            approvalDecider.register(approvalId);
            lineMapper.create(approvalDecider);
        }
    }
}
