package br.com.rodrigoxavier.toscanaserver.repository;

import br.com.rodrigoxavier.toscanaserver.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
    @Query("SELECT DISTINCT r.tipoDestino FROM Destino r")
    List<String> findDistinctTiposDestino();
}
