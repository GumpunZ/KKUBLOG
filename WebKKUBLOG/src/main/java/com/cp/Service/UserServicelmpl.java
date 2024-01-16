package com.cp.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cp.DTO.UserRegisterDTO;
import com.cp.Model.Profile;
import com.cp.Model.Role;
import com.cp.Model.User;
import com.cp.Repository.ProfileRepository;
import com.cp.Repository.RoleRepository;
import com.cp.Repository.UserRepository;

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProfileRepository profileRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
        UserServiceID userDetails = new userDetailsID(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRole()));
        userDetails.setId(user.getUserid());
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User save(UserRegisterDTO userRegisterDTO) {
        Role role = new Role();
        if (userRegisterDTO.getRole().equals("USER"))
            role = roleRepository.findByName("USER");
        else if (userRegisterDTO.getRole().equals("ADMIN"))
            role = roleRepository.findByName("ADMIN");
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setRole(role);
        user = userRepository.save(user);
        Profile profile = new Profile();
        profile.setName(userRegisterDTO.getName());
        profile.setLastname(userRegisterDTO.getLastname());
        profile.setEmail(userRegisterDTO.getEmail());
        profile.setPhone(userRegisterDTO.getPhone());
        profile.setDate_of_birth(userRegisterDTO.getDateOfBirth());
        profile.setUser(user);
        profile = profileRepository.save(profile);
        return user;
    }

}

