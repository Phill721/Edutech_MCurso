package com.mcurso.Edutech.Service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;

import com.mcurso.Edutech.Model.Curso;
import com.mcurso.Edutech.Model.EstadoCurso;
import com.mcurso.Edutech.Model.NivelCurso;
import com.mcurso.Edutech.Repository.CursoRepository;

public class CursoServiceTest {
    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarCurso(){
        Curso curso = new Curso(0, "Python", "Curso de python",
         "Informatica", NivelCurso.PRINCIPIANTE, 99.99, 1, 
         EstadoCurso.ACTIVO, 100,null,null,null,null);
        Curso cursoguardado = new Curso(1, "Python", "Curso de python", 
        "Informatica", NivelCurso.PRINCIPIANTE, 99.99, 1,
         EstadoCurso.ACTIVO, 100, null, null, null, null);
         when(cursoRepository.save(curso)).thenReturn(cursoguardado);

        Curso resultado = cursoService.crearCurso(curso);
        assertThat(resultado.getId()).isEqualTo(1);
        verify(cursoRepository).save(curso);
    }

    @Test
    void testListarCursos(){
        Curso c1 = new Curso(1, "Python", "Curso de python",
         "Informatica", NivelCurso.PRINCIPIANTE, 99.99, 1, 
         EstadoCurso.ACTIVO, 100,null,null,null,null);
        Curso c2 = new Curso(2, "Java", "Curso de Java",
         "Informatica", NivelCurso.INTERMEDIO, 99.99, 1, 
         EstadoCurso.ACTIVO, 100,null,null,null,null);
        Curso c3 = new Curso(3, "C++", "Curso de C++",
         "Informatica", NivelCurso.AVANZADO, 150.00, 1, 
         EstadoCurso.ACTIVO, 50,null,null,null,null);
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(c1,c2,c3));

        List<Curso> resultado = cursoService.listar();
        assertThat(resultado).hasSize(3).contains(c1,c2,c3);
        verify(cursoRepository).findAll();
    }

    @Test
    void testEliminarCurso(){
        int idcurso = 1;
        doNothing().when(cursoRepository).deleteById(idcurso);
        cursoService.eliminarxid(idcurso);
        verify(cursoRepository).deleteById(idcurso);
    }

    @Test
    void testBuscarxid(){
        int idexistente = 1;
        Curso cursoSimulado = new Curso(idexistente, "Python", "Curso de python",
         "Informatica", NivelCurso.PRINCIPIANTE, 99.99, 1, 
         EstadoCurso.ACTIVO, 100,null,null,null,null);

         when(cursoRepository.findById(idexistente)).thenReturn(Optional.of(cursoSimulado));
         Optional<Curso> resultado = cursoService.buscarxid(idexistente);
         assertThat(resultado).isPresent().hasValueSatisfying(curso -> {
            assertThat(curso.getId()).isEqualTo(idexistente);
            assertThat(curso.getTitulo()).isEqualTo("Python");
            assertThat(curso.getDescripcion()).isEqualTo("Curso de python");
            assertThat(curso.getCategoria()).isEqualTo("Informatica");
            assertThat(curso.getNivel()).isEqualTo(NivelCurso.PRINCIPIANTE);
            assertThat(curso.getPrecio()).isEqualTo(99.99);
            assertThat(curso.getSeccion()).isEqualTo(1);
            assertThat(curso.getEstado()).isEqualTo(EstadoCurso.ACTIVO);
            assertThat(curso.getCupo()).isEqualTo(100);
            assertThat(curso.getInscritos()).isEqualTo(null);
            assertThat(curso.getInstructores()).isEqualTo(null);
            assertThat(curso.getModulos()).isEqualTo(null);
            assertThat(curso.getCupones()).isEqualTo(null);
         });
         verify(cursoRepository).findById(idexistente);
    }

    @Test
    void testActualizarcurso(){
        int id = 1;
        Curso existente = new Curso(id, "Python", "Curso de python",
         "Informatica", NivelCurso.PRINCIPIANTE, 99.99, 1, 
         EstadoCurso.ACTIVO, 100,null,null,null,null);
        Curso actualizado = new Curso(id, "Curso de Python", "fundamentos de programacion",
         "Informatica", NivelCurso.PRINCIPIANTE, 49.99, 1, 
         EstadoCurso.ACTIVO, 100,null,null,null,null);
        
        when(cursoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(cursoRepository.save(existente)).thenReturn(existente);
        Optional<Curso> resultado = cursoService.actualizarCurso(id, actualizado);
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getTitulo()).isEqualTo("Curso de Python");
        assertThat(resultado.get().getDescripcion()).isEqualTo("fundamentos de programacion");
        assertThat(resultado.get().getPrecio()).isEqualTo(49.99);
        verify(cursoRepository).findById(id);
        verify(cursoRepository).save(existente);
    }
}
