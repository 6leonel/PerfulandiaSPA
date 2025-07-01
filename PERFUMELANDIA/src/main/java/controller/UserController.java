package controller;

import model.User;
import service.UserService;
import exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) throw new UserNotFoundException("Usuario no encontrado");
        EntityModel<User> model = EntityModel.of(user);
        model.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
        model.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("usuarios"));
        return model;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
