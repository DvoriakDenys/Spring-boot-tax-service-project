package com.tax.service.service;

import com.tax.service.entity.Status;

import java.util.Optional;

public interface StatusService {
    Status findById(final Long id);
}
