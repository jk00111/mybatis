package com.example.mybatis.common.approval.repository;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.entity.ApprovalEntity;
import com.example.mybatis.common.approval.entity.ApprovalEntityDto;
import com.example.mybatis.common.approval.entity.ApprovalDecider;
import com.example.mybatis.common.approval.vo.ApprovalId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApprovalRepositoryImpl implements ApprovalRepository {

    private final ApprovalMapper approvalMapper;
    private final ApprovalLineMapper lineMapper;

    @Override
    public ApprovalEntity findOne(ApprovalId id) {
        ApprovalEntityDto entityDto = approvalMapper.findOne(id);
        List<ApprovalDecider> line = lineMapper.findByApprovalId(id);

        return ApprovalEntity.of(entityDto, line);
    }

    @Override
    public void create(ApprovalEntity entity) {
        ApprovalEntityDto entityDto = ApprovalEntityDto.FromEscalate(entity);
        approvalMapper.create(entityDto);
        entity.setId(new ApprovalId(entityDto.getId()));

        ApprovalLine line = entity.getApprovalLine();
        createLine(line, entity.id().getId());
    }

    @Override
    public void update(ApprovalEntity entity) {
        ApprovalEntityDto entityDto = ApprovalEntityDto.from(entity);
        approvalMapper.update(entityDto);

        ApprovalLine line = entity.getApprovalLine();
        updateLine(line);
    }

    private void updateLine(ApprovalLine line) {
        line.forEach(approvalDecider -> {
            if (approvalDecider.isUpdated()) {
                lineMapper.update(approvalDecider);
            }
        });
    }

    private void createLine(ApprovalLine line, Integer approvalId) {
        line.forEach(approvalDecider -> {
            approvalDecider.register(approvalId);
            lineMapper.create(approvalDecider);
        });
    }
}
