package com.example.mybatis.generalTest.receipt.repository;

import com.example.mybatis.common.EntityFacade;
import com.example.mybatis.generalTest.receipt.entity.Receipt;
import com.example.mybatis.generalTest.receipt.entity.ReceiptEntityDto;
import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptContents;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import com.example.mybatis.generalTest.receipt.vo.Receptionist;
import com.example.mybatis.generalTest.request.vo.RequestId;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReceiptEntityFacade implements EntityFacade<Receipt, ReceiptId> {

    private final ReceiptRepository receiptRepository;
    private final ItemRepository itemRepository;

    @Override
    public Receipt findEntity(ReceiptId id) {
        ReceiptEntityDto entityDto = receiptRepository.findOne(id);
        List<Item> items = itemRepository.findByReceipt(id);

        return makeEntity(entityDto, items);
    }

    @Override
    public void create(Receipt entity) {
        ReceiptEntityDto entityDto = ReceiptEntityDto.fromCreate(entity);
        receiptRepository.create(entityDto);
    }

    @Override
    public void update(Receipt entity) {
        ReceiptEntityDto entityDto = ReceiptEntityDto.fromUpdate(entity);
        receiptRepository.update(entityDto);
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
                .receptionist(Receptionist.ofId(dto.getReceptionistId()))
                .items(items)
                .build();
    }
}
