package me.dio.service.impl;


import me.dio.domain.model.*;
import me.dio.domain.repository.*;
import me.dio.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {


   private final UserRepository userRepository;
   private final CardRepository cardRepository;
   private final AccountRepository accountRepository;
   private final FeatureRepository featureRepository;
   private final NewsRepository newsRepository;



   public UserServiceImpl(UserRepository userRepository,
                          CardRepository cardRepository,
                          AccountRepository accountRepository,
                          FeatureRepository featureRepository,
                          NewsRepository newsRepository
   ) {
      this.userRepository = userRepository;
      this.cardRepository = cardRepository;
      this.accountRepository = accountRepository;
      this.featureRepository = featureRepository;
      this.newsRepository = newsRepository;
   }


   @Override
   public User findById(Long id) {
      return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
   }


   @Override
   public User create(User userToCreate) {
      if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
         throw new IllegalArgumentException("User with account number " + userToCreate.getAccount().getNumber() + " already exists");
      }

      // Check if account number already exists
      if (userToCreate.getAccount() != null && accountRepository.existsByNumber(userToCreate.getAccount().getNumber())) {
         throw new IllegalArgumentException("Account with number " + userToCreate.getAccount().getNumber() + " already exists");
      }

      // Check if card number already exists
      if (userToCreate.getCard() != null && cardRepository.existsByNumber(userToCreate.getCard().getNumber())) {
         throw new IllegalArgumentException("Card with number " + userToCreate.getCard().getNumber() + " already exists");
      }

      // Save related entities first
      if (userToCreate.getCard() != null) {
         Card savedCard = cardRepository.save(userToCreate.getCard());
         cardRepository.save(savedCard);
      }
      if (userToCreate.getAccount() != null) {
         Account savedAccount = accountRepository.save(userToCreate.getAccount());
         userToCreate.setAccount(savedAccount); // Ensure the account is managed
      }
      if (userToCreate.getFeatures() != null && !userToCreate.getFeatures().isEmpty()) {
         featureRepository.saveAll(userToCreate.getFeatures());
      }
      if (userToCreate.getNews() != null && !userToCreate.getNews().isEmpty()) {
         newsRepository.saveAll(userToCreate.getNews());
      }

      // Save the user entity after saving related entities
      return userRepository.save(userToCreate);
   }
}
