package com.tax.service.service.impl;

import com.tax.service.exception.NoDataFound;
import com.tax.service.repository.StatusRepository;
import com.tax.service.entity.Status;
import com.tax.service.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public Status findById(Long id) {
        log.info("Get status by id:{}", id);
        return statusRepository.findById(id).orElseThrow(NoDataFound::new);
    }

}
