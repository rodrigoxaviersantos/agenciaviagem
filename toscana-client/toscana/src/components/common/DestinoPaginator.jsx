import React from 'react'

export const DestinoPaginator = ({paginaAtual, totalPaginas, mudancaPaginas}) => {
    const numerosPaginas = Array.from({length : totalPaginas}, (_, i) => i +1)
  return (
    <nav>
        <ul className="paginator, justify-content-center">
            {numerosPaginas.map((numerosPaginas) => (
            <li
                key={numerosPaginas}
                className={`pagina-item ${paginaAtual === numeroPagina ? "ativa" : ""}`}>
                    <button className='page-link' onClick={() => mudancaPaginas(numerosPaginas)}>
                        {numerosPaginas}
                    </button>


            </li>
            ))}

        </ul>
    </nav>
    
  )
}

export default DestinoPaginator
