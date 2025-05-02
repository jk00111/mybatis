package com.example.mybatis.common.approval.repository;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.approvalLine.ApprovalUnit;
import com.example.mybatis.common.approval.entity.ApprovalEntity;
import com.example.mybatis.common.approval.entity.ApprovalEntityDto;
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
        List<ApprovalUnit> lines = lineMapper.findByApprovalId(id);

        return ApprovalEntity.of(entityDto, lines);
    }

    public void create(ApprovalEntity entity) {
        ApprovalEntityDto entityDto = ApprovalEntityDto.from(entity);
        approvalMapper.create(entityDto);

        ApprovalUnit units = entity.getApprovalUnit();
        createLineReclusive(units);
    }

    public void update(ApprovalEntity entity) {
        ApprovalEntityDto entityDto = ApprovalEntityDto.from(entity);
        approvalMapper.update(entityDto);

        ApprovalUnit units = entity.getApprovalUnit();
        updateLineReclusive(units);
    }

    private void updateLineReclusive(ApprovalUnit unit) {
        if (unit instanceof ApprovalLine line) {
            for (ApprovalUnit approvalUnit : line.getLeaf()) {
                updateLineReclusive(approvalUnit);
            }
        }

        updateUnit(unit);
    }

    private void updateUnit(ApprovalUnit unit) {
        if (unit.isUpdated()) {
            lineMapper.update(unit);
        }
    }

    private void createLineReclusive(ApprovalUnit unit) {
        if (unit instanceof ApprovalLine line) {
            for (ApprovalUnit approvalUnit : line.getLeaf()) {
                createLineReclusive(approvalUnit);
            }
        }

        lineMapper.create(unit);
    }
}
