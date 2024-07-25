package com.biblioteca.Service;


import com.biblioteca.Model.User;

import java.util.List;

public class UserAuthenticator {
    public User authenticate(User user, List<User> userList) {
        if(userList.contains(user)) {
            user = userList.get(userList.indexOf(user));
            return user;
        }
        return null;
    }
}
