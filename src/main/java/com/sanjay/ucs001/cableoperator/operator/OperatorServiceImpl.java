package com.sanjay.ucs001.cableoperator.operator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {
    private final OperatorRepository operatorRepository;

    @Override
    public Optional<Operator> findByUsername(String username) {
        return this.operatorRepository.findByUsername(username);
    }
}
