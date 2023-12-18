//package br.com.rodrigoxavier.toscanaserver.service;
//
//import br.com.rodrigoxavier.toscanaserver.model.Destino;
//import br.com.rodrigoxavier.toscanaserver.repository.DestinoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.sql.rowset.serial.SerialBlob;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.Blob;
//import java.sql.SQLException;
//
//@Service
//@RequiredArgsConstructor
//
//public class DestinoService implements  IDestinoService{
//    private DestinoRepository destinoRepository;
//    @Override
//    public Destino addNewDestino(MultipartFile foto, String tipoDestino, BigDecimal precoDestino) throws SQLException, IOException {
//        Destino destino = new Destino();
//        destino.setTipoDestino(tipoDestino);
//        destino.setPrecoDestino(precoDestino);
//        if (!foto.isEmpty()){
//            byte[] fotoBytes = foto.getBytes();
//            Blob fotoBlob = new SerialBlob(fotoBytes);
//            destino.setFoto(fotoBlob);
//        }
//
//        return destinoRepository.save(destino);
//    }
//
//}

package br.com.rodrigoxavier.toscanaserver.service;

import br.com.rodrigoxavier.toscanaserver.model.Destino;
import br.com.rodrigoxavier.toscanaserver.repository.DestinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class DestinoService implements IDestinoService {

    private final DestinoRepository destinoRepository;

    @Override
    public Destino addNewDestino(MultipartFile foto, String tipoDestino, BigDecimal precoDestino) throws SQLException, IOException {
        Destino destino = new Destino();
        destino.setTipoDestino(tipoDestino);
        destino.setPrecoDestino(precoDestino);
        if (!foto.isEmpty()) {
            byte[] fotoBytes = foto.getBytes();
            Blob fotoBlob = new SerialBlob(fotoBytes);
            destino.setFoto(fotoBlob);
        }

        return destinoRepository.save(destino);
    }
}

