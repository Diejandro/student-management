package com.proyectopropuesto.student_management.controllers;


import com.proyectopropuesto.student_management.domain.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class StudentController {

    //Lista de recursos
    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Diego", "diego@mail.es", 32, "Java develop"),
            new Student(2, "Alejandro", "alej@mail.es", 31, "Ingeniería software"),
            new Student(3, "Blanca", "Blanjos@mail.com", 40, "Biología"),
            new Student(4, "Yolanda", "yolis@mail.uk", 45, "Astronomía")
    ));

    /**
     * Solicitud tipo GET de lista de alumnos.
     * @return lista de alumnos.
     */
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(students);
    }

    /**
     * Solicitud tipo GET de un alumno dado su email.
     * @param email Referencia para encontrar de alumno en lista.
     * @return Estado de solicitud de datos de alumno por email.
     */
    @GetMapping("/{email}")
    public ResponseEntity<?> getStudent(@PathVariable String email){
        for(Student s: students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado por email: " + email);
    }

    /**
     * Solicitud tipo POST de creación de un alumno.
     * @param student Alumno a crear.
     * @return Estado de solicitud de nuevo alumno añadido a la lista.
     */
    @PostMapping
    public ResponseEntity<Student> postStudent(@RequestBody Student student){
        students.add(student);

        return ResponseEntity.ok(student);
    }

    /**
     * Modifica los datos del alumno dado su ID
     * @param student Datos del alumno a modificados.
     * @return Estado de solicitud de datos del alumno existente actualizados.
     */
    @PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody Student student){
        for(Student s: students){
            if(s.getID() == student.getID()){
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());


                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Solicitud de modificación parcial de un alumno
     * @param student Datos del alumno a modificar.
     * @return Estado de la solicitud de actualización.
     */
    @PatchMapping
    public ResponseEntity<?> PatchStudent(@RequestBody Student student){
        for(Student s: students){
            if(s.getID() == student.getID()) {
                if (student.getName() != null) {
                    s.setName(student.getName());
                }
                if (student.getEmail() != null) {
                    s.setEmail(student.getEmail());
                }
                if (student.getAge() != 0) {
                    s.setAge(student.getAge());
                }
                if (student.getCourse() != null) {
                    s.setCourse(student.getCourse());
                }
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado con ID: " + student.getID());
    }

    /**
     * Eliminación de un alumno de la lista.
     * @param id Identifiador del alumno a eliminar.
     * @return Estado de la solicitud de eliminación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
        for(Student s: students){
            if(s.getID() == id){
                students.remove(s);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Alumno no encontrado o ya eliminado con ID: " + id);
    }
}























