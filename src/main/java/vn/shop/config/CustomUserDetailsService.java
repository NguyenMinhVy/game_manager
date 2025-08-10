package vn.shop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.shop.entity.Role;
import vn.shop.entity.User;
import vn.shop.repository.RoleRepository;
import vn.shop.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            Role role = roleRepository.findById(user.getRoleId())
                    .orElseThrow(() -> new RuntimeException("not found role"));
            List<GrantedAuthority> authorities = role != null ?
                    Collections.singletonList(new SimpleGrantedAuthority(role.getRoleName())) :
                    Collections.emptyList();

            return new CustomUserDetails(
                    user.getUserId(),
                    user.getPhoneNumber(),
                    user.getPassword(),
                    authorities
            );
        } else {
            throw new UsernameNotFoundException("Invalid Phone Number or Password");
        }
    }
}
