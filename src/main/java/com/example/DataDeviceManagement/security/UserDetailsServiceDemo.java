package com.example.DataDeviceManagement.security;

import com.example.DataDeviceManagement.dao.UtilisateurDao;
import com.example.DataDeviceManagement.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceDemo implements UserDetailsService {

    private UtilisateurDao utilisateurDao;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceDemo(UtilisateurDao utilisateurDao){
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public UserDetailsDemo loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurDao
                .findByEmailWithRoles(email)
                .orElseThrow(() -> new UsernameNotFoundException("Mauvais pseudo / mot de passe"));

        UserDetailsDemo userDetailsDemo = new UserDetailsDemo(utilisateur);

        return userDetailsDemo;
    }
}
