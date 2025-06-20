package com.mcurso.Edutech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcurso.Edutech.Model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
}
