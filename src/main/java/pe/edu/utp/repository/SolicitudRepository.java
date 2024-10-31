package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.model.Solicitud;

/**
 * Repositorio para la entidad Solicitud, que permite realizar operaciones
 * CRUD en la base de datos SQL. Extiende JpaRepository para aprovechar
 * los métodos de consulta predefinidos.
 */
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {
    // Aquí puedes agregar métodos de consulta personalizados si es necesario
}
