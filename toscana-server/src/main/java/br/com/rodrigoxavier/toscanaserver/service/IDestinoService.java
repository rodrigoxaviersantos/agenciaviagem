package br.com.rodrigoxavier.toscanaserver.service;

import br.com.rodrigoxavier.toscanaserver.model.Destino;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public interface IDestinoService {
    Destino addNewDestino(MultipartFile foto, String tipoDestino, BigDecimal precoDestino) throws SQLException, IOException;
}
