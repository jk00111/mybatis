package com.example.mybatis.generalTest.receipt.service.impl;

import com.example.mybatis.common.EntityFacade;
import com.example.mybatis.generalTest.receipt.dto.ReceiptServiceDto;
import com.example.mybatis.generalTest.receipt.repository.ItemRepository;
import com.example.mybatis.generalTest.receipt.repository.ReceiptRepository;
import com.example.mybatis.generalTest.receipt.dto.ReceiptCreateDto;
import com.example.mybatis.generalTest.receipt.entity.Receipt;
import com.example.mybatis.generalTest.receipt.repository.ReceiptEntityFacade;
import com.example.mybatis.generalTest.receipt.service.ReceiptService;
import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ItemRepository itemRepository;

    @Override
    public void init(ReceiptCreateDto createDto) {
        Receipt receipt = Receipt.ofCreate(createDto);
        entityFacade().create(receipt);
    }

    @Override
    public void update(ReceiptId id, ReceiptServiceDto dto) {
        Receipt receipt = entityFacade().findEntity(id);
        receipt.ofUpdate(dto.getContents(), dto.getReceptionist());

        entityFacade().update(receipt);
        registerItems(id, dto.getItems());
    }

    @Override
    public void cancel(ReceiptId id) {
        Receipt receipt = entityFacade().findEntity(id);
        receipt.cancel();
        entityFacade().update(receipt);
    }

    @Override
    public void submit(ReceiptId id) {
        Receipt receipt = entityFacade().findEntity(id);
        receipt.submit();
        entityFacade().update(receipt);
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

    private EntityFacade<Receipt, ReceiptId> entityFacade() {
        return new ReceiptEntityFacade(receiptRepository, itemRepository);
    }
}
