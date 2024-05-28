package com.example.tasker.user.service;

import com.example.tasker.domain.model.User;
import com.example.tasker.domain.repository.UserRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void updateImage(Long userId, MultipartFile image) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setImageData(image.getBytes());
        userRepository.save(user);
    }

    public byte[] getUserImage(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getImageData();
    }
}
