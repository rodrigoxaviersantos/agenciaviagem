import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'

const ExisteDestinos = () => {
  const[destinos, setDestinos] = useState([])
  const[paginaAtual, setPaginaAtual] = useState(1)
  const[destinosPoPagina, setDestinosPorPagina] = useState(8)
  const[estaCarregando, setEstaCarregando] = useState(false)
  const[destinosFiltrados, setDestinosFiltrados] = useState([])
  const[tipoDestinoSelecionado, setTipoDestinoSelecionado] = useState("")
  const[successMessage, setSuccessMessage] = useState("")
  const[errorMessage, setErrorMessage] = useState("")

  useEffect(() => {
    buscarDestinos()
  },[])
  
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
    if(tipoDestinoSelecionado === ""){
        setDestinosFiltrados(destinos)
    }else{
        const filtrado = destinos.filtro((destino) => destino.tipoDestino === tipoDestinoSelecionado)
        setDestinosFiltrados(filtrado)
    }
    setPaginaAtual(1)
  }, [destinos, tipoDestinoSelecionado])
  return (
    <div>ExisteDestinos</div>
  )
}

export default ExisteDestinos
