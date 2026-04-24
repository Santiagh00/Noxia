package edu.co.ustavillavo.noxia.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String cedula;

    private String correo;

    private String telefono;

    private Integer edad;

    private String genero;

    @Column(columnDefinition = "TEXT")
    private String respuestasDass;

    private Integer puntuacionDassDepresion;
    private Integer puntuacionDassAnsiedad;
    private Integer puntuacionDassEstres;

    @Column(columnDefinition = "TEXT")
    private String respuestasGad;

    private Integer puntuacionGad;

    @Column(columnDefinition = "TEXT")
    private String respuestasPhq;

    private Integer puntuacionPhq;

    private String nivelSeveridad;
    private String prioridad;

    @Column(nullable = false)
    private LocalDateTime fechaEvaluacion = LocalDateTime.now();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getRespuestasDass() { return respuestasDass; }
    public void setRespuestasDass(String respuestasDass) { this.respuestasDass = respuestasDass; }

    public Integer getPuntuacionDassDepresion() { return puntuacionDassDepresion; }
    public void setPuntuacionDassDepresion(Integer puntuacionDassDepresion) { this.puntuacionDassDepresion = puntuacionDassDepresion; }

    public Integer getPuntuacionDassAnsiedad() { return puntuacionDassAnsiedad; }
    public void setPuntuacionDassAnsiedad(Integer puntuacionDassAnsiedad) { this.puntuacionDassAnsiedad = puntuacionDassAnsiedad; }

    public Integer getPuntuacionDassEstres() { return puntuacionDassEstres; }
    public void setPuntuacionDassEstres(Integer puntuacionDassEstres) { this.puntuacionDassEstres = puntuacionDassEstres; }

    public String getRespuestasGad() { return respuestasGad; }
    public void setRespuestasGad(String respuestasGad) { this.respuestasGad = respuestasGad; }

    public Integer getPuntuacionGad() { return puntuacionGad; }
    public void setPuntuacionGad(Integer puntuacionGad) { this.puntuacionGad = puntuacionGad; }

    public String getRespuestasPhq() { return respuestasPhq; }
    public void setRespuestasPhq(String respuestasPhq) { this.respuestasPhq = respuestasPhq; }

    public Integer getPuntuacionPhq() { return puntuacionPhq; }
    public void setPuntuacionPhq(Integer puntuacionPhq) { this.puntuacionPhq = puntuacionPhq; }

    public String getNivelSeveridad() { return nivelSeveridad; }
    public void setNivelSeveridad(String nivelSeveridad) { this.nivelSeveridad = nivelSeveridad; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public LocalDateTime getFechaEvaluacion() { return fechaEvaluacion; }
    public void setFechaEvaluacion(LocalDateTime fechaEvaluacion) { this.fechaEvaluacion = fechaEvaluacion; }
}