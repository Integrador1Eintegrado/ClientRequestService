package pe.edu.utp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Solicitudes")
public class Solicitud {

    @Id
    private String id; // ID de la solicitud (de tipo String)

    private String id_cliente; // ID del cliente que hace la solicitud
    private String id_servicio; // ID del servicio relacionado
    private String descripcion; // Descripción adicional de la solicitud hola
    private String estado = "pendiente"; // Estado por defecto

    // Constructor vacío
    public Solicitud() {}

    // Constructor con parámetros
    public Solicitud(String id, String id_cliente, String id_servicio, String descripcion, String estado) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_servicio = id_servicio;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(String id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
