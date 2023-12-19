import React, { useState } from 'react';
import { addDestino } from '../utils/ApiFuncoes';
import SelectTipoDestino from '../common/SelectTipoDestino';


const AddDestino = () => {
  const [novoDestino, setNovoDestino] = useState({
    foto: null,
    tipoDestino: "",
    precoDestino: ""
  });

 
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [visualizaImagem, setVisualizaImagem] = useState("");

  const handleDestinoInputChange = (e) => {
    const name = e.target.name
    let value = e.target.value
    if (name === "precoDestino") {
      if (!isNaN(value)) {
        value = parseInt(value);
      } else {
        value = "";
      }
    }
    setNovoDestino({ ...novoDestino, [name]: value });
  };

  const handleImagemChange = (e) => {
    const selectImagem = e.target.files[0];
    setNovoDestino({ ...novoDestino, foto: selectImagem });
    setVisualizaImagem(URL.createObjectURL(selectImagem));
  };

  const handleSubimit = async (e) => {
    e.preventDefault();
    try {
      const sucesso = await addDestino(
        novoDestino.foto,
        novoDestino.tipoDestino,
        novoDestino.precoDestino
      );
      if (sucesso !== undefined) {
        setSuccessMessage("Um novo destino foi adicionado ao banco de dados");
        setNovoDestino({ foto: null, tipoDestino: "", precoDestino: "" });
        setVisualizaImagem("");
        setErrorMessage("");
      } else {
        setMensagemError("Erro ao adicionar destino");
      }
    } catch (error) {
      setErrorMessage(error.message);
    }
    setTimeout(() => {
			setSuccessMessage("")
			setErrorMessage("")
		}, 3000)
  };
    return (
        <>
        <section className="container, mt-5 mb-5">
            <div className='row justify-content-center'>
                <div className='col-md-8 col-lg-6'>
                    <h2 className='mt-5 mb-2'>Adiciona um Novo Destino</h2>
                    {successMessage && (
							        <div className="Sucesso show"> {successMessage}</div>
						        )}
                    {errorMessage && (
							        <div className="Perigo show"> {errorMessage}</div>
						        )}
						        {/* {mensagemError && <div className="Perigo show"> {mensagemError}</div>
                    } */}
                    <form onSubmit={handleSubimit}>
                        <div className="mb-3">
                            <label htmlFor="tipoDestino" className='form-label'>
                                Tipo Destino
                            </label>
                            <div>
                                <SelectTipoDestino 
                                handleDestinoInputChange={handleDestinoInputChange}
                                novoDestino={novoDestino}
                                />
                            </div>
                        </div>

                        <div className='mb-3'>
                            <label htmlFor="precoDestino" className='form-label'>
                                Preco Destino
                            </label>
                            <input 
                            required
                            type='number'
                            className='form-control'
                            id='precoDestino'
                            name='precoDestino'
                            value={novoDestino.precoDestino}
                            onChange={handleDestinoInputChange}
                            />
                        </div>

                        <div className='mb-3'>
                            <label htmlFor="foto" className='form-label'>
                                Foto Destino
                            </label>
                            <input 
                            required
                            name='foto'
                            id='foto'
                            type='file'
                            className='form-control'
                            onChange={handleImagemChange}
                            />
                            {visualizaImagem && (
                              <img 
                              src={visualizaImagem}
                              alt='Visualiza Foto de destino' 
                              style={{maxWidth: "400px", maxHeight: "400px"}}
                              className='mb-3'></img> 
                            )}
                        </div>
                        <div className='d-grid d-md-flex mt-2'>
                            <button type="subimit" className='btn btn-outline-primary ml-5'>
                                Salvar Destino
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        </>
    )
       
}

export default AddDestino

