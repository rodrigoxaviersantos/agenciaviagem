package br.com.rodrigoxavier.toscanaserver.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservadoResponse {

    private Long reservaId;

    private LocalDate dateCheckin;
    private LocalDate dateCheckout;

    private String nomeCpltoConvidado;

    private String emailConvidado;

    private int numeroAdultos;

    private int numeroCriancas;

    private int totalConvidados;
    private String codigoConfirmaReserva;


    private DestinoResponse destino;

    public ReservadoResponse(Long reservaId, LocalDate dateCheckin, LocalDate dateCheckout,
                             String codigoConfirmaReserva) {
        this.reservaId = reservaId;
        this.dateCheckin = dateCheckin;
        this.dateCheckout = dateCheckout;
        this.codigoConfirmaReserva = codigoConfirmaReserva;
    }
}
