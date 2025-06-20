package com.mcurso.Edutech.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {
    private int id;
    private String correo;
    private String password;
    private String nombrereal;
}
