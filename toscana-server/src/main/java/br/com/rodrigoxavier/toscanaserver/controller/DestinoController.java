package br.com.rodrigoxavier.toscanaserver.controller;

import br.com.rodrigoxavier.toscanaserver.model.Destino;
import br.com.rodrigoxavier.toscanaserver.response.DestinoResponse;
import br.com.rodrigoxavier.toscanaserver.service.IDestinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/destinos")
public class DestinoController {
    private final IDestinoService destinoService;

    @PostMapping("/add/new-destino")
    public ResponseEntity<DestinoResponse> addNewDestino(
            @RequestParam("foto") MultipartFile foto,
            @RequestParam("tipoDestino") String tipoDestino,
            @RequestParam("precoDestino")BigDecimal precoDestino) throws SQLException, IOException {
        Destino saveDestino = destinoService.addNewDestino(foto,tipoDestino, precoDestino );
        DestinoResponse response = new DestinoResponse(saveDestino.getId(), saveDestino.getTipoDestino(),
                saveDestino.getPrecoDestino());
        return ResponseEntity.ok(response);
    }
}
