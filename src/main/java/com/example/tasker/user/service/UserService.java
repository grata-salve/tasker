package com.example.tasker.user.service;

import com.example.tasker.domain.constants.Status;
import com.example.tasker.domain.model.Task;
import com.example.tasker.domain.model.User;
import com.example.tasker.domain.model.mapper.UserMapper;
import com.example.tasker.domain.repository.UserRepository;
import com.example.tasker.task.model.TaskDto;
import com.example.tasker.user.model.UserDto;
import java.io.IOException;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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

    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDto deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.deleteById(userId);
        return userMapper.toDto(user);
    }
}
