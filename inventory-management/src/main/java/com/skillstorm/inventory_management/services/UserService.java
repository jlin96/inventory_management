package com.skillstorm.inventory_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management.models.User;
import com.skillstorm.inventory_management.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public Optional<User> findById(int id) {
        return repo.findById(id);
    }

    //Error handling: Email needs to be unique
    public User save(User user) {
        //Save encrypted password
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPass = bcrypt.encode(user.getPassword());
        user.setPassword(encryptedPass);
        return repo.save(user);
    }

    //Error handling: Email needs to be unique
    public User update(int id, User user) {
        Optional<User> dbUser = repo.findById(id);

        if(dbUser.isPresent()) {
            User presentUser = dbUser.get();
            presentUser.setEmail(user.getEmail());
            presentUser.setFirstName(user.getFirstName());
            presentUser.setLastName(user.getLastName());
        } else {
            //return user not found exception
        }
        return repo.save(user);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public String authUser(User user) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        List<User> listUser = repo.findByEmail(user.getEmail());
        
        if(listUser.size() == 0) {
            return "Wrong email";
        }

        User dbUser = listUser.get(0);

        if(bcrypt.matches(user.getPassword(), dbUser.getPassword())){
            return "Authenticated";
        } else {
            return "Incorrect Password";
        }
    }
}
