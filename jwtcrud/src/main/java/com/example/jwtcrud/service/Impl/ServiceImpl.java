package com.example.jwtcrud.service;



import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.jwtcrud.dto.*;
import com.example.jwtcrud.dto.JwtResponse;
import com.example.jwtcrud.model.Usuario;
import com.example.jwtcrud.repository.UsuarioRepository;
import com.example.jwtcrud.security.JwtUtil;

import lombok.RequiredArgsConstructor;


@Service
public class ServiceImpl implements inventorService {
 
    @Autowired
    private inventorRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public inventor createinventor(inventor task) {
        return inventorRepository.save(task);
    }

    @Override
    public inventor updateinventor(inventor inventor) {
        if (inventor.getId() == null || !inventorRepository.existsById(inventor.getId())) {
            throw new EntityNotFoundException("inventor not found with id: " + inventor.getId());
        }
        return inventorRepository.save(inventor);
    }

    @Override
    public void deleteTask(long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found with id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public void assignTask(long taskId, long userId) {
    }

    @Override
    public void unassignTask(long taskId, long userId) {
        // Implementation omitted as it's not required for this exercise
    }

    @Override
    public Task getTaskById(long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
    }

    @Override
    public List<Task> getAllTask() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        
        if (user != null && user.getTasks() != null) {
            return user.getTasks();
        }
        
        return List.of();
    }
}
