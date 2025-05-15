package com.example.jwtcrud.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtcrud.dto.JwtResponse;
import com.example.jwtcrud.dto.LoginRequest;
import com.example.jwtcrud.dto.RegisterRequest;
import com.example.jwtcrud.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventor")
public class inventorControllerImpl implements inventorController {

    @Autowired
    private inventorService taskService;

    @Autowired
    private inventorMapper taskMapper;

    @Override
    @GetMapping
    public ResponseEntity<?> findAllTask() {
        List<inventor> inventor = inventorService.getAllTask();
        List<inventorDto> inventorDtos = inventor.stream()
                .map(inventorMapper::inventorToTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventorDtos);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_inventor')")
    public ResponseEntity<?> addTask(@RequestBody inventorDto dto) {
        inventor task = inventorMapper.taskDtoToinventor(dto);
        inventor savedTask = inventorService.createinventor(task);
        inventorDto responseDto = inventorMapper.taskToinventorDto(savedTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Override
    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_inventor')")
    public ResponseEntity<?> updateTask(@RequestBody inventorDto dto) {
        try {
            inventor inventor = taskMapper.taskDtoToinventor(dto);
            inventor updatedinventor = taskService.updateinventor(inventor);
            inventorDto responseDto = inventorMapper.inventorToTaskDto(updatedinventor);
            return ResponseEntity.ok(responseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_TASK')")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_TASK')")
    public ResponseEntity<?> findById(@PathVariable long id) {
        try {
            Task task = taskService.getTaskById(id);
            TaskDto responseDto = taskMapper.taskToTaskDto(task);
            return ResponseEntity.ok(responseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
