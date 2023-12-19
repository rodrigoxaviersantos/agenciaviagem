package br.com.rodrigoxavier.toscanaserver.service;

import br.com.rodrigoxavier.toscanaserver.model.Destino;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface IDestinoService {
    Destino addNewDestino(MultipartFile foto, String tipoDestino, BigDecimal precoDestino) throws SQLException, IOException;

    List<String> getTodosTiposDestino();

    List<Destino> getTodosDestinos();

    byte[] getDestinoFotoByDestinoId(Long destinoId) throws SQLException;


}
