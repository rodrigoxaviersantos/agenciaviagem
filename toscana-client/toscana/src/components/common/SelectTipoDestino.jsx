import React, { useEffect, useState } from 'react';
import { getTiposDestino } from '../utils/ApiFuncoes';

const SelectTipoDestino = ({ handleDestinoInputChange, novoDestino }) => {
  const [tiposDestino, setTiposDestino] = useState([]);
  const [mNovaEntradaTipoDestino, setMNovaEntradaTipoDestino] = useState(false);
  const [novoTipoDestino, setNovoTipoDestino] = useState('');

  useEffect(() => {
    getTiposDestino().then((data) => {
      setTiposDestino(data);
    });
  }, []);

  const handleNovaEntradaTipoDestinoChange = (e) => {
    setNovoTipoDestino(e.target.value);
  };

  const handleAddNovoTipoDestino = () => {
    if (novoTipoDestino !== '') {
      setTiposDestino([...tiposDestino, novoTipoDestino]);
      setNovoTipoDestino('');
      setMNovaEntradaTipoDestino(false);
    }
  };

  return (
    <>
      {tiposDestino.length > 0 && (
        <div>
          <select
            id="tipoDestino"
            name="tipoDestino"
            value={novoDestino.tipoDestino}
            onChange={(e) => {
              if (e.target.value === 'Adiciona Novo') {
                setMNovaEntradaTipoDestino(true);
              } else {
                handleDestinoInputChange(e); // Corrigido aqui
              }
            }}
          >
            <option value={''}>Seleciona um tipo de destino</option>
            <option value={'Adiciona Novo'}>Adiciona Novo</option>
            {tiposDestino.map((type, index) => (
              <option key={index} value={type}>
                {type}
              </option>
            ))}
          </select>
          {mNovaEntradaTipoDestino && (
            <div className="input-group">
              <input
                className="form-control"
                type="text"
                placeholder="Insira um novo tipo de destino"
                onChange={handleNovaEntradaTipoDestinoChange}
              />
              <button
                className="btn btn-toscana"
                type="button"
                onClick={handleAddNovoTipoDestino}
              >
                Adicionar
              </button>
            </div>
          )}
        </div>
      )}
    </>
  );
};

export default SelectTipoDestino;
