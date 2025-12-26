package com.example.approval.document.repository;

import com.example.approval.document.entity.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository {

    Document findOne(long id);

    void create(Document document);

    void update(Document document);

}
