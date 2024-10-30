package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, String> {
    // Aquí puedes agregar métodos de consulta personalizados si es necesario
}
