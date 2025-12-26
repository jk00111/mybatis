package com.example.approval.document.service;

import com.example.approval.document.dto.DocumentDto;
import com.example.approval.document.entity.Document;

public interface DocumentService {

    long write(Document document);

    void update(DocumentDto dto);

    void cancel(DocumentDto dto);

    Document findOne(long id);

}
