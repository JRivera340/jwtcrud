package com.example.jwtcrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwtcrud.model.Usuario;

//----
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import co.icesi.taskManager.model.Task;
import co.icesi.taskManager.model.User;
import co.icesi.taskManager.repositories.TaskRepository;
import co.icesi.taskManager.repositories.UserRepository;
import co.icesi.taskManager.services.interfaces.TaskService;
import jakarta.persistence.EntityNotFoundException;
//-----
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByUsername(String username);
}