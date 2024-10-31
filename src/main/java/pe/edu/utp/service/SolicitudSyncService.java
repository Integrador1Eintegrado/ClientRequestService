package pe.edu.utp.service;

import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.model.Solicitud;
import pe.edu.utp.model.dto.SolicitudDTO;
import pe.edu.utp.repository.SolicitudRepository;

import javax.annotation.PostConstruct;

/**
 * Servicio para sincronizar en tiempo real los cambios de las solicitudes en Firestore
 * con la base de datos SQL local.
 */
@Service
public class SolicitudSyncService {

    private final CollectionReference solicitudesCollection;
    private final SolicitudRepository solicitudRepository;

    /**
     * Constructor que inicializa la referencia a la colección de solicitudes en Firestore
     * y el repositorio de solicitudes en SQL.
     *
     * @param firestore instancia de Firestore para acceder a la colección de solicitudes
     * @param solicitudRepository repositorio para manejar las solicitudes en SQL
     */
    @Autowired
    public SolicitudSyncService(Firestore firestore, SolicitudRepository solicitudRepository) {
        this.solicitudesCollection = firestore.collection("Solicitudes");
        this.solicitudRepository = solicitudRepository;
    }

    /**
     * Método que se ejecuta después de la construcción de la clase
     * y que inicia la escucha de cambios en Firestore.
     */
    @PostConstruct
    public void init() {
        listenToSolicitudes();
    }

    /**
     * Configura un listener para escuchar cambios en la colección de solicitudes en Firestore
     * y sincronizar estos cambios con la base de datos SQL.
     */
    private void listenToSolicitudes() {
        solicitudesCollection.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                System.err.println("Error listening to Firestore: " + e);
                return;
            }

            if (querySnapshot != null) {
                for (DocumentChange change : querySnapshot.getDocumentChanges()) {
                    DocumentSnapshot document = change.getDocument();
                    SolicitudDTO solicitudDTO = document.toObject(SolicitudDTO.class);
                    String id = document.getId();

                    switch (change.getType()) {
                        case ADDED:
                            saveToSQL(solicitudDTO, id);
                            break;
                        case MODIFIED:
                            updateInSQL(solicitudDTO, id);
                            break;
                        case REMOVED:
                            deleteFromSQL(id);
                            break;
                    }
                }
            }
        });
    }

    /**
     * Guarda una nueva solicitud en la base de datos SQL.
     *
     * @param solicitudDTO objeto SolicitudDTO que representa la solicitud
     * @param id ID de la solicitud en Firestore
     */
    private void saveToSQL(SolicitudDTO solicitudDTO, String id) {
        Solicitud solicitud = new Solicitud(id, solicitudDTO.getId_cliente(), solicitudDTO.getId_servicio(),
                solicitudDTO.getDescripcion(), solicitudDTO.getEstado());
        solicitudRepository.save(solicitud);
    }

    /**
     * Actualiza una solicitud existente en la base de datos SQL.
     *
     * @param solicitudDTO objeto SolicitudDTO con los datos actualizados de la solicitud
     * @param id ID de la solicitud en Firestore
     */
    private void updateInSQL(SolicitudDTO solicitudDTO, String id) {
        Solicitud solicitud = new Solicitud(id, solicitudDTO.getId_cliente(), solicitudDTO.getId_servicio(),
                solicitudDTO.getDescripcion(), solicitudDTO.getEstado());
        solicitudRepository.save(solicitud);
    }

    /**
     * Elimina una solicitud de la base de datos SQL usando su ID.
     *
     * @param id ID de la solicitud a eliminar
     */
    private void deleteFromSQL(String id) {
        solicitudRepository.deleteById(id);
    }
}
