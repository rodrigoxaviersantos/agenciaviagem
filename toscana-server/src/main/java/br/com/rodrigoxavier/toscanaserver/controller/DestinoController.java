package br.com.rodrigoxavier.toscanaserver.controller;

import br.com.rodrigoxavier.toscanaserver.exception.FotoRecuperaException;
import br.com.rodrigoxavier.toscanaserver.model.Destino;
import br.com.rodrigoxavier.toscanaserver.model.DestinoReservado;
import br.com.rodrigoxavier.toscanaserver.response.DestinoResponse;
import br.com.rodrigoxavier.toscanaserver.response.ReservadoResponse;
import br.com.rodrigoxavier.toscanaserver.service.IDestinoService;
import br.com.rodrigoxavier.toscanaserver.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/destinos")
@CrossOrigin(origins = "http://localhost:5173/")
public class DestinoController {
    private final IDestinoService destinoService;
    private final ReservaService reservaService;

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

    @GetMapping("/destino/tipos")
    public List<String> getTiposDestino(){
        return destinoService.getTodosTiposDestino();
    }

    @GetMapping("/todos-destinos")
    public ResponseEntity<List<DestinoResponse>> getTodosDestinos() throws SQLException {
        List<Destino> destinos = destinoService.getTodosDestinos();
        List<DestinoResponse> destinoResponses = new ArrayList<>();
        for(Destino destino : destinos){
            byte[] fotoBytes = destinoService.getDestinoFotoByDestinoId(destino.getId());
            if(fotoBytes != null && fotoBytes.length > 0){
                String base64Foto = Base64.getEncoder().encodeToString(fotoBytes);
                DestinoResponse destinoResponse = getDestinoResponse(destino);
                destinoResponse.setFoto(base64Foto);
                destinoResponses.add(destinoResponse);
            }
        }
        return ResponseEntity.ok(destinoResponses);
    }

    private DestinoResponse getDestinoResponse(Destino destino) {
        List<DestinoReservado> reservados = getTodasReservasByDestinosId(destino.getId());
        List<ReservadoResponse> reservadoInfo = reservados
                .stream()
                .map(reservado -> new ReservadoResponse(reservado.getReservaId(),
                        reservado.getCheckInDate(),
                        reservado.getCheckOutDate(), reservado.getReservadoConfirmaCodigo())).toList();
        byte[] fotoBytes = null;
        Blob fotoBlob = destino.getFoto();
        if (fotoBlob != null){
            try{
                fotoBytes = fotoBlob.getBytes(1, (int) fotoBlob.length());
            }catch (SQLException e){
                throw new FotoRecuperaException("Erro ao recuperar a foto");
            }
        }
        return new DestinoResponse(destino.getId(),
                destino.getTipoDestino(),
                destino.getPrecoDestino(),
                destino.getEstaReservado(),fotoBytes, reservadoInfo);
    }

    private List<DestinoReservado> getTodasReservasByDestinosId(Long destinoId){
        return reservaService.getTodasReservasDestinoId(destinoId);

    }
}
