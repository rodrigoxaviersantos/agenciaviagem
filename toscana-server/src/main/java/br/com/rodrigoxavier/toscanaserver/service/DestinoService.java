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

import br.com.rodrigoxavier.toscanaserver.exception.ResourceNotFoundException;
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
import java.util.List;
import java.util.Optional;

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
    @Override
    public List<String> getAllTiposDestino(){
        return destinoRepository.findDistinctTiposDestino();
    }

    @Override
    public List<Destino> getAllDestinos() {
        return destinoRepository.findAll();
    }

    @Override
    public byte[] getDestinoFotoByDestinoId(Long destinoId) throws SQLException {
        Optional<Destino> MDestino = destinoRepository.findById(destinoId);
        if(MDestino.isEmpty()){
            throw new ResourceNotFoundException("Desculpe, Destino não foi encontrado!");
        }
        Blob fotoBlob = MDestino.get().getFoto();
        if(fotoBlob != null){
            return  fotoBlob.getBytes(1, (int) fotoBlob.length());
        }
        return null;
    }
}

