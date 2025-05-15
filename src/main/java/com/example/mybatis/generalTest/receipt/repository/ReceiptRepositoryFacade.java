package com.example.mybatis.generalTest.receipt.repository;

import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.generalTest.receipt.entity.Receipt;
import com.example.mybatis.generalTest.receipt.entity.ReceiptEntityDto;
import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptContents;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import com.example.mybatis.generalTest.receipt.vo.Receptionist;
import com.example.mybatis.generalTest.request.vo.RequestId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class ReceiptRepositoryFacade implements ReceiptRepository {

    private final ReceiptMapper receiptMapper;
    private final ItemRepository itemRepository;


    @Override
    public void create(Receipt receipt) {
        ReceiptEntityDto entityDto = ReceiptEntityDto.fromCreate(receipt);
        receiptMapper.create(entityDto);
    }

    @Override
    public void update(Receipt receipt) {
        ReceiptEntityDto entityDto = ReceiptEntityDto.fromUpdate(receipt);
        receiptMapper.update(entityDto);
        registerItems(receipt.getId(), receipt.getItems());
    }

    @Override
    public void delete(ReceiptId id) {
        receiptMapper.delete(id);
    }

    @Override
    public Receipt findOne(ReceiptId id) {
        ReceiptEntityDto entityDto = receiptMapper.findOne(id);
        List<Item> items = itemRepository.findByReceipt(id);

        return makeEntity(entityDto, items);
    }

    private void registerItems(ReceiptId id, List<Item> updated) {
        List<Item> registered = itemRepository.findByReceipt(id);

        Set<Item> beforeSet = new HashSet<>(registered);
        Set<Item> updatedSet = new HashSet<>(updated);

        Set<Item> intersection = new HashSet<>(beforeSet);
        intersection.retainAll(updatedSet);

        beforeSet.removeAll(intersection);
        for (Item item : beforeSet) {
            itemRepository.delete(item);
        }

        updatedSet.removeAll(intersection);
        for (Item item : updatedSet) {
            itemRepository.create(item);
        }
    }

    private Receipt makeEntity(ReceiptEntityDto dto, List<Item> items) {
        return Receipt.builder()
                .id(new ReceiptId(dto.getId()))
                .requestId(new RequestId(dto.getRequestId()))
                .contents(ReceiptContents.builder()
                        .title(dto.getTitle())
                        .contentsDetail(dto.getContents())
                        .receiptDate(dto.getReceiptDate())
                        .build())
                .status(dto.getStatus())
                .receptionist(Receptionist.of(dto.getReceptionistId(), dto.getReceptionistName()))
                .items(items)
                .approvalId(new ApprovalId(dto.getApprovalId()))
                .build();
    }
}
