package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.UserDao;
import com.reins.bookstore.entity.BookInfo;
import com.reins.bookstore.entity.UserIcon;
import com.reins.bookstore.repository.UserIconRepository;
import com.reins.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.reins.bookstore.entity.User;

import java.util.Optional;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserIconRepository userIconRepository;

    @Override
    public User ifUser(String username, String password) {
        User user= userRepository.ifUser(username, password);
        if(user==null) return user;
        return addIcon(user);
    }
    @Override
    public List<User> getUsers(){
        List<User> users=userRepository.getUsers();
        for(User user : users){addIcon(user);}
        return users;
    }
    @Override
    public User getUser(Integer u_id){
        User user=userRepository.getUser(u_id);
        return addIcon(user);
    }
    @Override
    public User getOne(String username){
        User user=userRepository.existUser(username);
        return addIcon(user);
    }
    @Override
    public User addIcon(User user){
        Optional<UserIcon> Icon = userIconRepository.findById(user.getU_id());
        if (Icon.isPresent())
            user.setUserIcon(Icon.get());
        else
            user.setUserIcon(null);
        return user;
    }
    @Override
    public User existUser(String username) {
        return userRepository.existUser(username);
    }

    @Override
    public User setUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }
    @Override
    public User saveUser(User user1){
        UserIcon img;
        Integer id = user1.getU_id();
        System.out.println(2222);
        if (user1.getUserIcon() != null) {
            Optional<UserIcon> image = userIconRepository.findById(id);
            if (image.isPresent()) {
                image.get().setIcon(user1.getUserIcon().getIcon());
//            System.out.println(book.getBookInfo().getImage());
                img = image.get();
//            System.out.println(image.get().getImage());
            } else
                img = new UserIcon(id, user1.getUserIcon().getIcon());
            System.out.println(3333);
            userIconRepository.save(img);
        }
        System.out.println(444);
        return user1;
    }
}
