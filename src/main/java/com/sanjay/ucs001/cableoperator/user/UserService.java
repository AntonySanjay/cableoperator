package com.sanjay.ucs001.cableoperator.user;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
