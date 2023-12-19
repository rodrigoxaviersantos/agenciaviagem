
import React, { useState } from 'react'

export const DestinoFiltro = ({data, setDadosFiltrados}) => {
    const[filtro, setFiltro] = useState("")
    const selecionaMudanca =(e) =>{
        const selecionaTipoDestino = e.target.value
        setFiltro(selecionaTipoDestino)
        const destinosFiltrados = data.filtro((destino)=> 
        destino.tipoDestino.toLowerCase()
       .includes(selecionaTipoDestino.toLowerCase()))
       setDadosFiltrados(destinosFiltrados)
    }
    const limparFiltro = () => {
        setFiltro("")
        setDadosFiltrados(data)
    }
    const tiposDestino = ["", ...new Set(data.map((destino) => destino.tipoDestino))]

  return (
    <div className='input-group mb-3'>
        <span className='input-group-text' id='destino-tipo-filtro'>
            Filtrar destinos por tipos 
        </span>
        <select 
        className='form-select'
        onChange={selecionaMudanca}>
            <option value={""}> selecione um tipo de destino para filtrar... </option>
            {tiposDestino.map((type, index) => (
                <option key={index} value={String (type)}>
                {String (type)}
                </option>
            ))}
        </select>
        <button className="btn- btn-toscana" type="button" onClick={limparFiltro}> 
        Limpar Filtro </button>
    </div>
  )
}
