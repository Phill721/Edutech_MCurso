package com.mcurso.Edutech.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcurso.Edutech.Model.Curso;
import com.mcurso.Edutech.Repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso crearCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public Optional<Curso> buscarxid(int id){
        return cursoRepository.findById(id);
    }

    public Optional<Curso> actualizarCurso(int id, Curso updatedCurso){
        return cursoRepository.findById(id).map(curso -> {
            curso.setId(id);
            curso.setTitulo(updatedCurso.getTitulo());
            curso.setDescripcion(updatedCurso.getDescripcion());
            curso.setCategoria(updatedCurso.getCategoria());
            curso.setNivel(updatedCurso.getNivel());
            curso.setPrecio(updatedCurso.getPrecio());
            curso.setSeccion(updatedCurso.getSeccion());
            curso.setEstado(updatedCurso.getEstado());
            curso.setCupo(updatedCurso.getCupo());
            return cursoRepository.save(curso);
        } );
    }

    public boolean eliminarxid(int id){
        if (cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
