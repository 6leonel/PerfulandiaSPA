package com.perfulandia.controller;

import com.perfulandia.model.User;
import com.perfulandia.service.UserService;
import com.perfulandia.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista completa de los usuarios registrados.")
    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        List<EntityModel<User>> userModels = users.stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return CollectionModel.of(userModels,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }

    @Operation(
            summary = "Obtener usuario por ID",
            description = "Devuelve los datos de un usuario específico según su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) throw new UserNotFoundException("Usuario no encontrado");

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("usuarios"));
    }

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Registra un nuevo usuario en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(
            summary = "Actualizar usuario por ID",
            description = "Actualiza la información de un usuario existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @Operation(
            summary = "Eliminar usuario por ID",
            description = "Elimina un usuario del sistema según su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
