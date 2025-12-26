package com.example.approval.document.service;

import com.example.approval.document.entity.Document;
import com.example.approval.document.repository.DocumentRepository;
import com.example.approval.document.dto.DocumentDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;

    @Override
    public long write(Document document) {
        repository.create(document);
        return document.getId();
    }

    @Override
    public void update(DocumentDto dto) {
        long id = dto.getId();
        Document document = repository.findOne(id);
        document.update(dto);
        repository.update(document);
    }

    @Override
    public void cancel(DocumentDto dto) {
        long id = dto.getId();
        Document document = repository.findOne(id);
        document.cancel(dto);
        repository.update(document);
    }

    @Override
    public Document findOne(long id) {
        return repository.findOne(id);
    }
}
