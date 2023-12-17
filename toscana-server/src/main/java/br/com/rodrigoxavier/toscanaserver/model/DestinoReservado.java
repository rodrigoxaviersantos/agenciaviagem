package br.com.rodrigoxavier.toscanaserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinoReservado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservaId;

    @Column(name = "check_In")
    private LocalDate dateCheckin;

    @Column(name = "check_Out")
    private LocalDate dateCheckout;

    @Column(name = "nomeCompleto_Convidado")
    private String nomeCpltoConvidado;

    @Column(name = "email_Convidado")
    private  String emailConvidado;

    @Column(name = "adultos")
    private int numeroAdultos;

    @Column(name = "criancas")
    private int numeroCriancas;

    @Column(name = "total_Convidados")
    private int totalConvidados;

    @Column(name = "codigoConfirma_Reserva")
    private String codigoConfirmaReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id")
    private Destino destino;

    public void calculaTotalConvidado() {
        this.totalConvidados = this.numeroAdultos + numeroCriancas;
    }

    public void setNumeroAdultos(int numeroAdultos) {
        this.numeroAdultos = numeroAdultos;
        calculaTotalConvidado();
    }

    public void setNumeroCriancas(int numeroCriancas) {
        this.numeroCriancas = numeroCriancas;
        calculaTotalConvidado();
    }

    public DestinoReservado(String codigoConfirmaReserva) {
        this.codigoConfirmaReserva = codigoConfirmaReserva;
    }
}
