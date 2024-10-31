package pe.edu.utp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.GeoPoint;
import pe.edu.utp.model.deserializer.GeoPointDeserializer;
import pe.edu.utp.model.deserializer.TimestampDeserializer;

import java.util.Objects;

public class SolicitudDTO {

    @JsonProperty("id")
    private String id;                // ID de la solicitud

    @JsonProperty("descripcion")
    private String descripcion;       // Descripción de la solicitud

    @JsonProperty("estado")
    private String estado;            // Estado de la solicitud

    @JsonProperty("fechaFin")
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp fechaFin;       // Fecha de finalización

    @JsonProperty("fechaInicio")
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp fechaInicio;    // Fecha de inicio

    @JsonProperty("fechaSolicitud")
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp fechaSolicitud; // Fecha de creación

    @JsonProperty("id_cliente")
    private String id_cliente;        // ID del cliente

    @JsonProperty("id_servicio")
    private String id_servicio;       // ID del servicio

    @JsonProperty("ubicacion")
    @JsonDeserialize(using = GeoPointDeserializer.class)

    private GeoPoint ubicacion;       // Ubicación (coordenadas)

    @JsonProperty("visible")
    private boolean visible;          // Visibilidad

    public SolicitudDTO() {
    }

    public SolicitudDTO(String descripcion, String id, Timestamp fechaFin, String estado, Timestamp fechaInicio, Timestamp fechaSolicitud, String id_servicio, String id_cliente, GeoPoint ubicacion, boolean visible) {
        this.descripcion = descripcion;
        this.id = id;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaSolicitud = fechaSolicitud;
        this.id_servicio = id_servicio;
        this.id_cliente = id_cliente;
        this.ubicacion = ubicacion;
        this.visible = visible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public String getId_servicio() {
        return id_servicio;
    }

    public GeoPoint getUbicacion() {
        return ubicacion;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setId_servicio(String id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setUbicacion(GeoPoint ubicacion) {
        this.ubicacion = ubicacion;
    }

}