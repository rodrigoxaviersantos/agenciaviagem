package br.com.rodrigoxavier.toscanaserver.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class DestinoResponse {
    private Long id;
    private String tipoDestino;
    private BigDecimal precoDestino;
    private Boolean estaReservado;
    private String foto; // Alterado para String para armazenar Base64 diretamente
    private List<ReservadoResponse> reservados;

    public DestinoResponse(Long id, String tipoDestino, BigDecimal precoDestino) {
        this.id = id;
        this.tipoDestino = tipoDestino;
        this.precoDestino = precoDestino;
    }

    public DestinoResponse(Long id, String tipoDestino, BigDecimal precoDestino, Boolean estaReservado,
                           byte[] fotoBytes, List<ReservadoResponse> reservados) {
        this.id = id;
        this.tipoDestino = tipoDestino;
        this.precoDestino = precoDestino;
        this.estaReservado = estaReservado;
        this.foto = fotoBytes != null ? Base64.encodeBase64String(fotoBytes) : null;
        this.reservados = reservados;
    }
}
