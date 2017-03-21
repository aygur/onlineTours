package com.naraikin.onlinetours.services;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.services.interfaces.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 14.03.17.
 */
public class customUserDetailsService implements UserDetailsService {
    private static Logger logger= Logger.getLogger(customUserDetailsService.class);

    @Autowired
    private ClientService clientService;

    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        Client client = null;
        try {
            client = clientService.getClientByLogin(login);
        } catch (ClientServiceException e) {
            logger.error(e);
        }
        System.out.println("User : "+client.getLogin());
        if(client == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(client.getLogin(), client.getPassword(),
                client.getBlocked() == 0 , true, true, true, getGrantedAuthorities(client));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Client client){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            authorities.add( new SimpleGrantedAuthority(client.getRole()));

        System.out.print("authorities :"+authorities);
        return authorities;
    }
}
