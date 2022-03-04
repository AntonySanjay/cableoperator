package com.sanjay.ucs001.cableoperator.operator;

import java.util.Optional;

public interface OperatorService {
    Optional<Operator> findByUsername(String username);
}
