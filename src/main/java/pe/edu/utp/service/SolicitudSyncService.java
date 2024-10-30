package pe.edu.utp.service;

import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.model.Solicitud;
import pe.edu.utp.model.dto.SolicitudDTO;
import pe.edu.utp.repository.SolicitudRepository;

import javax.annotation.PostConstruct;


@Service
public class SolicitudSyncService {

    private final CollectionReference solicitudesCollection;
    private final SolicitudRepository solicitudRepository;

    @Autowired
    public SolicitudSyncService(Firestore firestore, SolicitudRepository solicitudRepository) {
        this.solicitudesCollection = firestore.collection("Solicitudes");
        this.solicitudRepository = solicitudRepository;
    }

    @PostConstruct
    public void init() {
        listenToSolicitudes();
    }

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

    private void saveToSQL(SolicitudDTO solicitudDTO, String id) {
        Solicitud solicitud = new Solicitud(id, solicitudDTO.getId_cliente(), solicitudDTO.getId_servicio(), solicitudDTO.getDescripcion(), solicitudDTO.getEstado());
        solicitudRepository.save(solicitud);
    }

    private void updateInSQL(SolicitudDTO solicitudDTO, String id) {
        Solicitud solicitud = new Solicitud(id, solicitudDTO.getId_cliente(), solicitudDTO.getId_servicio(), solicitudDTO.getDescripcion(), solicitudDTO.getEstado());
        solicitudRepository.save(solicitud);
    }

    private void deleteFromSQL(String id) {
        solicitudRepository.deleteById(id);
    }
}
