package me.dio.controller;

import me.dio.domain.model.User;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

   private final UserService userService;

   public UserController(UserService userService) {
      this.userService = userService;
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<User> findById(@PathVariable Long id) {
      return ResponseEntity.ok().body(userService.findById(id));
   }

   @PostMapping
   public ResponseEntity<String> create(@RequestBody User userToCreate) {
      try {
         User createdUser = userService.create(userToCreate);
         URI location = ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(createdUser.getId())
               .toUri();
         return ResponseEntity.created(location).body(String.valueOf(createdUser));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body("Erro ao criar usuário: " + e.getMessage());
      }
   }
}
