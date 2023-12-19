import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'

const ExisteDestinos = () => {
  const[destinos, setDestinos] = useState([{id: "", tipoDestino: "", precoDestino: ""}])
  const[paginaAtual, setPaginaAtual] = useState(1)
  const[destinosPorPagina, setDestinosPorPagina] = useState(8)
  const[estaCarregando, setEstaCarregando] = useState(false)
  const[destinosFiltrados, setDestinosFiltrados] = useState([{id: "", tipoDestino: "", precoDestino: ""}])
  const[tipoDestinoSelecionado, setTipoDestinoSelecionado] = useState("")
  const[successMessage, setSuccessMessage] = useState("")
  const[errorMessage, setErrorMessage] = useState("")

  useEffect(() => {
    buscarDestinos()
  },[]);
  
  const buscarDestinos = async() => {
    setEstaCarregando(true)
    try{
        const resultado = await getTodosDestinos()
        setDestinos(resultado)
        setEstaCarregando(false)
    }catch(error) {
        setErrorMessage(error.message)
    }
  }

  useEffect(() => {
    if (tipoDestinoSelecionado === "") {
      setDestinosFiltrados(destinos);
    } else {
      const filtrado = destinos.filter((destino) => destino.tipoDestino === tipoDestinoSelecionado);
      setDestinosFiltrados(filtrado);
    }
    setPaginaAtual(1);
  }, [destinos, tipoDestinoSelecionado]);

  const manipularClickPorPagina = (numeroPagina) => {
    setPaginaAtual(numeroPagina);
  };

  const calculaTotalPaginas = (destinosFiltrados, destinosPorPagina, destinos) => {
    const totalDestinos = destinosFiltrados.length > 0 ? destinosFiltrados.length : destinos.length;
    return Math.ceil(totalDestinos / destinosPorPagina);
  };


  const indexUltimoDestino = paginaAtual * destinosPorPagina;
  const indexPrimeiroDestino = indexUltimoDestino - destinosPorPagina;
  const atualDestinos = destinosFiltrados.slice(indexPrimeiroDestino, indexUltimoDestino);

  return (
    <>
      {estaCarregando ? (
        <p>Carregando destinos existentes</p>
      ) : (
        <>
          <section className='mt-5 mb-5 container'>
            <div className='d-flex justify-content-center mb-3 mt-5'>
              <h2>Existe Destinos</h2>
            </div>
            <col md={6} className='mb-3 mb-md-0'>
            </col>
            <table className='table table-boardered table-hover'>
            <thead>
              <tr className='tex-center'>
                <th>ID</th>
                <th>Tipo Destino</th>
                <th>Preço Destino</th>
                <th>Ações</th>
              </tr>
            </thead>

            <tbody>
              {atualDestinos.map((destino) => (
                <tr key={destino.id} className='text-center'>
                  <td>{destino.id}</td>
                  <td>{destino.tipoDestino}</td>
                  <td>{destino.precoDestino}</td>
                  <td>
                    <button>Exibir / Editar</button>
                    <button>Excluir</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="pagination">
            {Array.from({ length: calculaTotalPaginas(destinosFiltrados, destinosPorPagina, destinos) }, (_, index) => index + 1).map((numeroPagina) => (
              <button key={numeroPagina} onClick={() => manipularClickPorPagina(numeroPagina)}>
                {numeroPagina}
              </button>
            ))}
          </div>

        </section>
      </> 
    )}
  </>
)
}
export default ExisteDestinos