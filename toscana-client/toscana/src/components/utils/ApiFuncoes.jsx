import axios from "axios";

export const api = axios.create({
    baseURL : "http://localhost:9192"
})
/**Esta função adiciona um novo destino ao banco de dados */
export async function addDestino(foto, tipoDestino, precoDestino){
    const formData = new FormData()
    formData.append("foto", foto)
    formData.append("tipoDestino", tipoDestino)
    formData.append("precoDestino", precoDestino)

    const response = await api.post("/destinos/add/new-destino", formData)
    if(response.status === 201){
        return true
    }else{
        return false
    }
}
/** Esta função obtém todos os tipos de destinos do banco de dados */
export async function getTiposDestino(){
    try{
        const response = await api.get("/destinos/tipos-destino")
        return response.data
    }catch (Error){
     throw new Error("Erro ao buscar tipos de destinos")
    }
}