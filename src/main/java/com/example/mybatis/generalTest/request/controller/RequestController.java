package com.example.mybatis.generalTest.request.controller;

import com.example.mybatis.common.config.model.CommonResponse;
import com.example.mybatis.generalTest.request.dto.RequestContentsDto;
import com.example.mybatis.generalTest.request.dto.RequestResponseDto;
import com.example.mybatis.generalTest.request.service.RequestQueryService;
import com.example.mybatis.generalTest.request.service.RequestService;
import com.example.mybatis.generalTest.request.vo.RequestContents;
import com.example.mybatis.generalTest.request.dto.RequestCondition;
import com.example.mybatis.generalTest.request.vo.RequestId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/general/requests")
public class RequestController {

    private final RequestService requestService;
    private final RequestQueryService queryService;

    @PostMapping
    public ResponseEntity<CommonResponse> write(@RequestBody RequestContentsDto dto) {
        RequestContents contents = dto.toContents();
        requestService.write(contents);
        return ResponseEntity.ok(new CommonResponse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> update(@RequestBody RequestContentsDto dto, @PathVariable Integer id) {
        RequestId requestId = new RequestId(id);
        RequestContents contents = dto.toContents();
        requestService.update(requestId, contents);
        return ResponseEntity.ok(new CommonResponse());
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<CommonResponse> submit(@PathVariable Integer id) {
        RequestId requestId = new RequestId(id);
        requestService.submit(requestId);
        return ResponseEntity.ok(new CommonResponse());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<CommonResponse> cancel(@PathVariable Integer id) {
        RequestId requestId = new RequestId(id);
        requestService.cancel(requestId);
        return ResponseEntity.ok(new CommonResponse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponseDto> findOne(@PathVariable Integer id) {
        return ResponseEntity.ok(queryService.findOne(new RequestId(id)));
    }

    @GetMapping
    public ResponseEntity<List<RequestResponseDto>> findAll(RequestCondition condition) {
        return ResponseEntity.ok(queryService.findAll(condition));
    }
}
