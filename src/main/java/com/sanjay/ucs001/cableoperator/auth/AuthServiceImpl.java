package com.sanjay.ucs001.cableoperator.auth;

import com.sanjay.ucs001.cableoperator.operator.Operator;
import com.sanjay.ucs001.cableoperator.operator.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final OperatorService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Operator> operator = this.userService.findByUsername(username);
        if (operator.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(operator.get());
    }
}
