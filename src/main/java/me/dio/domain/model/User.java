package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tb_user")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   @OneToOne(fetch= FetchType.LAZY)
   private Account account;

   @OneToOne
   private Card card;

   @OneToMany(fetch= FetchType.LAZY)
   private List<Feature> features;

   @OneToMany(fetch= FetchType.LAZY)
   private List<News> news;

   // Default constructor
   public User() {
   }

   public User(Long id, String name, Account account, Card card, List<Feature> features, List<News> news) {
      this.id = id;
      this.name = name;
      this.account = account;
      this.card = card;
      this.features = features;
      this.news = news;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public List<News> getNews() {
      return news;
   }

   public void setNews(List<News> news) {
      this.news = news;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Account getAccount() {
      return account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

   public List<Feature> getFeatures() {
      return features;
   }

   public void setFeatures(List<Feature> features) {
      this.features = features;
   }

   public Card getCard() {
      return card;
   }

   public void setCard(Card card) {
      this.card = card;
   }
}
