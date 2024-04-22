package com.auto.companion.user.controller;


import com.auto.companion.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping("/image")
    public ResponseEntity<?> getUserImage(@PathVariable Long userId) {
        try {
            byte[] imageData = userService.getUserImage(userId);
            if (imageData == null || imageData.length == 0) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // depending on your image format
                    .body(new ByteArrayResource(imageData));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}