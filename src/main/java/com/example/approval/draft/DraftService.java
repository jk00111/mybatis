package com.example.approval.draft;

import com.example.approval.dto.DraftDto;

public interface DraftService {

    long escalate(DraftDto dto);

    Draft findOne(long id);

}
