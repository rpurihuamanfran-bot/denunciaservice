package com.rojasp.denunciaservice.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "denuncia")
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 30, nullable = false)
    private String tipo;

    @Column(length = 200, nullable = false)
    private String direccion;

    @Column(length = 255, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer estado = 1;

    public Denuncia() {}

    public Denuncia(Long id, String dni, LocalDateTime fecha, String tipo, String direccion, String descripcion, Integer estado) {
        this.id = id;
        this.dni = dni;
        this.fecha = fecha;
        this.tipo = tipo;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    @PrePersist
    public void prePersist() {
        if (this.fecha == null) {
            this.fecha = LocalDateTime.now();
        }
        if (this.estado == null) {
            this.estado = 1;
        }
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
}
