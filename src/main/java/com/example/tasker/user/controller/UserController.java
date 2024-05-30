package com.example.tasker.user.controller;

import com.example.tasker.user.model.UserDto;
import com.example.tasker.user.service.UserService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/{userId}/uploadImage")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void uploadImage(@PathVariable Long userId, @RequestParam("image") MultipartFile file) throws IOException {
            userService.updateImage(userId, file);
    }

    @GetMapping("/image/{userId}")
    public ResponseEntity<?> getUserImage(@PathVariable Long userId) {
        try {
            byte[] imageData = userService.getUserImage(userId);
            if (imageData == null || imageData.length == 0) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new ByteArrayResource(imageData));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/delete/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}
