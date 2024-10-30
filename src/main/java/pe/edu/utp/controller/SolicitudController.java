package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.model.dto.SolicitudDTO;
import pe.edu.utp.service.SolicitudService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }
    @GetMapping
    public List<SolicitudDTO> listarSolicitudes() throws ExecutionException, InterruptedException {
        return solicitudService.listarSolicitudes();
    }
    @PostMapping
    public String crearSolicitud(@RequestBody SolicitudDTO solicitud) throws ExecutionException, InterruptedException {
        return solicitudService.crearSolicitud(solicitud);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> obtenerSolicitud(@PathVariable String id) throws ExecutionException, InterruptedException {
        SolicitudDTO solicitud = solicitudService.obtenerSolicitud(id);
        return ResponseEntity.ok(solicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarSolicitud(@PathVariable String id, @RequestBody SolicitudDTO solicitud) {
        solicitudService.actualizarSolicitud(id, solicitud);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable String id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
