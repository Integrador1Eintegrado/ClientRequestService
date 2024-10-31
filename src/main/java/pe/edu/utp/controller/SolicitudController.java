package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.model.dto.SolicitudDTO;
import pe.edu.utp.service.SolicitudService;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Controlador REST para gestionar las solicitudes.
 */
@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    /**
     * Constructor que inyecta el servicio de solicitudes.
     *
     * @param solicitudService servicio para manejar las operaciones de solicitudes
     */
    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    /**
     * Lista todas las solicitudes.
     *
     * @return una lista de objetos SolicitudDTO
     * @throws ExecutionException   si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    @GetMapping
    public List<SolicitudDTO> listarSolicitudes() throws ExecutionException, InterruptedException {
        return solicitudService.listarSolicitudes();
    }

    /**
     * Crea una nueva solicitud.
     *
     * @param solicitud el objeto SolicitudDTO a crear
     * @return el ID de la solicitud creada
     * @throws ExecutionException   si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    @PostMapping
    public String crearSolicitud(@RequestBody SolicitudDTO solicitud) throws ExecutionException, InterruptedException {
        return solicitudService.crearSolicitud(solicitud);
    }

    /**
     * Obtiene una solicitud por su ID.
     *
     * @param id el ID de la solicitud a obtener
     * @return un ResponseEntity que contiene el objeto SolicitudDTO
     * @throws ExecutionException   si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> obtenerSolicitud(@PathVariable String id) throws ExecutionException, InterruptedException {
        SolicitudDTO solicitud = solicitudService.obtenerSolicitud(id);
        return ResponseEntity.ok(solicitud);
    }

    /**
     * Actualiza una solicitud existente por su ID.
     *
     * @param id        el ID de la solicitud a actualizar
     * @param solicitud el objeto SolicitudDTO con los nuevos datos
     * @return un ResponseEntity con estado No Content (204)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarSolicitud(@PathVariable String id, @RequestBody SolicitudDTO solicitud) {
        solicitudService.actualizarSolicitud(id, solicitud);
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina una solicitud por su ID.
     *
     * @param id el ID de la solicitud a eliminar
     * @return un ResponseEntity con estado No Content (204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable String id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
