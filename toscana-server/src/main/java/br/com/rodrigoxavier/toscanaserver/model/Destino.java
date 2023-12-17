package br.com.rodrigoxavier.toscanaserver.model;

import org.apache.commons.lang3.RandomStringUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDestino;

    private BigDecimal precoDestino;

    private Boolean estaReservado = false;
    @Lob
    private Blob foto;

    @OneToMany(mappedBy="destino", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<DestinoReservado> reservados;


    public Destino() {
        this.reservados = new ArrayList<>();
    }

    public void addReservado(DestinoReservado reservado){
        if(reservados == null){
            reservados = new ArrayList<>();
        }
        reservados.add(reservado);
        reservado.setDestino(this);
        estaReservado = true;
        String codigoReserva = RandomStringUtils.randomNumeric(10);
        reservado.setCodigoConfirmaReserva(codigoReserva);
    }
}