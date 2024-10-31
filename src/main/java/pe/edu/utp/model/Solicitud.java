package pe.edu.utp.model;

import jakarta.persistence.*;

/**
 * Representa una solicitud en la base de datos.
 */
@Entity
@Table(name = "Solicitudes")
public class Solicitud {

    /**
     * ID de la solicitud.
     */
    @Id
    private String id;

    /**
     * ID del cliente que realiza la solicitud.
     */
    private String id_cliente;

    /**
     * ID del servicio asociado con la solicitud.
     */
    private String id_servicio;

    /**
     * Descripción adicional de la solicitud.
     */
    private String descripcion;

    /**
     * Estado de la solicitud, con un valor predeterminado de "pendiente".
     */
    private String estado = "pendiente";

    /**
     * Constructor vacío de la clase Solicitud.
     */
    public Solicitud() {}

    /**
     * Constructor de la clase Solicitud con parámetros.
     *
     * @param id          ID de la solicitud
     * @param id_cliente  ID del cliente que realiza la solicitud
     * @param id_servicio ID del servicio relacionado
     * @param descripcion Descripción adicional de la solicitud
     * @param estado      Estado de la solicitud
     */
    public Solicitud(String id, String id_cliente, String id_servicio, String descripcion, String estado) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_servicio = id_servicio;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     * Obtiene el ID de la solicitud.
     *
     * @return ID de la solicitud
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID de la solicitud.
     *
     * @param id ID de la solicitud
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del cliente que realiza la solicitud.
     *
     * @return ID del cliente
     */
    public String getId_cliente() {
        return id_cliente;
    }

    /**
     * Establece el ID del cliente que realiza la solicitud.
     *
     * @param id_cliente ID del cliente
     */
    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Obtiene el ID del servicio relacionado con la solicitud.
     *
     * @return ID del servicio
     */
    public String getId_servicio() {
        return id_servicio;
    }

    /**
     * Establece el ID del servicio relacionado con la solicitud.
     *
     * @param id_servicio ID del servicio
     */
    public void setId_servicio(String id_servicio) {
        this.id_servicio = id_servicio;
    }

    /**
     * Obtiene la descripción adicional de la solicitud.
     *
     * @return descripción de la solicitud
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción adicional de la solicitud.
     *
     * @param descripcion Descripción de la solicitud
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el estado de la solicitud.
     *
     * @return Estado de la solicitud
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la solicitud.
     *
     * @param estado Estado de la solicitud
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
