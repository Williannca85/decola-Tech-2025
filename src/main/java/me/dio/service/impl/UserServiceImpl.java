package me.dio.service.impl;


import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class UserServiceImpl implements UserService {


   private final UserRepository userRepository;

   public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }


   @Override
   public User findById(Long id) {
      return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
   }

   @Override
   public User create(User userToCreate) {
      if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
         throw new IllegalArgumentException("User with account number " + userToCreate.getAccount().getNumber() + " already exists");
      }
      return userRepository.save(userToCreate);
   }
}
