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
public class TaskServiceImpl implements TaskService {
 
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        if (task.getId() == null || !taskRepository.existsById(task.getId())) {
            throw new EntityNotFoundException("Task not found with id: " + task.getId());
        }
        return taskRepository.save(task);
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
