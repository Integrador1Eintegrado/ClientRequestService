package pe.edu.utp.service;

import com.google.cloud.firestore.*;
import com.google.common.collect.Lists;
import pe.edu.utp.model.dto.SolicitudDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Servicio para gestionar operaciones CRUD de Solicitudes en Firestore.
 */
@Service
public class SolicitudService {

    private final CollectionReference solicitudesCollection;

    /**
     * Constructor que inicializa la referencia a la colección de solicitudes en Firestore.
     *
     * @param firestore instancia de Firestore inyectada para acceder a la base de datos
     */
    public SolicitudService(Firestore firestore) {
        this.solicitudesCollection = firestore.collection("Solicitudes");
    }

    /**
     * Lista todas las solicitudes almacenadas en la colección de Firestore.
     *
     * @return una lista de objetos SolicitudDTO
     * @throws ExecutionException   si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    public List<SolicitudDTO> listarSolicitudes() throws ExecutionException, InterruptedException {
        QuerySnapshot querySnapshot = solicitudesCollection.get().get();
        return Lists.newArrayList(
                querySnapshot.getDocuments().stream()
                        .map(document -> document.toObject(SolicitudDTO.class))
                        .iterator()
        );
    }

    /**
     * Crea una nueva solicitud en Firestore y retorna el ID generado.
     *
     * @param solicitud objeto SolicitudDTO a crear
     * @return el ID de la solicitud creada
     * @throws ExecutionException   si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    public String crearSolicitud(SolicitudDTO solicitud) throws ExecutionException, InterruptedException {
        DocumentReference docRef = solicitudesCollection.add(solicitud).get();
        solicitud.setId(docRef.getId());
        docRef.set(solicitud, SetOptions.merge());
        return docRef.getId();
    }

    /**
     * Obtiene una solicitud específica por su ID desde Firestore.
     *
     * @param id ID de la solicitud a obtener
     * @return un objeto SolicitudDTO si se encuentra la solicitud, de lo contrario, null
     * @throws ExecutionException   si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    public SolicitudDTO obtenerSolicitud(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = solicitudesCollection.document(id);
        DocumentSnapshot document = docRef.get().get();
        if (document.exists()) {
            return document.toObject(SolicitudDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Actualiza una solicitud existente en Firestore.
     *
     * @param id        ID de la solicitud a actualizar
     * @param solicitud objeto SolicitudDTO con los nuevos datos para la solicitud
     */
    public void actualizarSolicitud(String id, SolicitudDTO solicitud) {
        DocumentReference docRef = solicitudesCollection.document(id);
        docRef.set(solicitud, SetOptions.merge());
    }

    /**
     * Elimina una solicitud de Firestore usando su ID.
     *
     * @param id ID de la solicitud a eliminar
     */
    public void eliminarSolicitud(String id) {
        DocumentReference docRef = solicitudesCollection.document(id);
        docRef.delete();
    }
}
