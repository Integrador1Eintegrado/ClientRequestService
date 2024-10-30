package pe.edu.utp.service;

import com.google.cloud.firestore.*;
import com.google.common.collect.Lists;
import pe.edu.utp.model.dto.SolicitudDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.common.collect.Lists;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SolicitudService {

    private final CollectionReference solicitudesCollection;

    public SolicitudService(Firestore firestore) {
        this.solicitudesCollection = firestore.collection("Solicitudes");
    }
    public List<SolicitudDTO> listarSolicitudes() throws ExecutionException, InterruptedException {
        // Obtener todos los documentos de la colección
        QuerySnapshot querySnapshot = solicitudesCollection.get().get();

        // Convertir a lista usando Google Guava
        return Lists.newArrayList(
                querySnapshot.getDocuments().stream()
                        .map(document -> document.toObject(SolicitudDTO.class))
                        .iterator()
        );
    }
    public String crearSolicitud(SolicitudDTO solicitud) throws ExecutionException, InterruptedException {
        // Crea el documento y obtén el ID generado
        DocumentReference docRef = solicitudesCollection.add(solicitud).get();

        // Asigna el ID al objeto solicitud antes de guardarlo
        solicitud.setId(docRef.getId());

        // Actualiza el documento con el ID
        docRef.set(solicitud, SetOptions.merge());

        return docRef.getId(); // Devuelve el ID de la nueva solicitud
    }

    public SolicitudDTO obtenerSolicitud(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = solicitudesCollection.document(id);
        DocumentSnapshot document = docRef.get().get();
        if (document.exists()) {
            return document.toObject(SolicitudDTO.class);
        } else {
            return null; // O lanza una excepción si prefieres
        }
    }

    public void actualizarSolicitud(String id, SolicitudDTO solicitud) {
        DocumentReference docRef = solicitudesCollection.document(id);
        docRef.set(solicitud, SetOptions.merge());
    }

    public void eliminarSolicitud(String id) {
        DocumentReference docRef = solicitudesCollection.document(id);
        docRef.delete();
    }
}
