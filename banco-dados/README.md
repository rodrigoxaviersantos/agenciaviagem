<h1 align="center">
  Banco de Dados da Aplicação Toscana<p align='center'>
</h1>

# <img width="40" height="40" src="https://img.icons8.com/office/40/data-configuration.png" alt="data-configuration"/> Visão Geral 

O banco de dados da aplicação Toscana é projetado para armazenar informações relacionadas a destinos turísticos e reservas. Ele é composto por duas principais entidades: Destino e DestinoReservado.

###  Entidades

<img width="16" height="16" src="https://img.icons8.com/tiny-color/16/data-configuration.png" alt="data-configuration"/> Destino

A entidade Destino representa informações sobre destinos turísticos disponíveis para reserva. Cada destino tem um identificador único (id) e inclui os seguintes atributos:

- *id (Long):*  Identificador único do destino.

- *tipoDestino (String):*  Tipo ou categoria do destino turístico.

- *precoDestino (BigDecimal):* Preço associado ao destino.

- *estaReservado (Boolean):* Indica se o destino está atualmente reservado.

- *foto (Blob):* Armazena a foto associada ao destino.

- *reservados (List<DestinoReservado>):* Lista de reservas associadas a este destino.

Relacionamentos

Um destino pode ter várias reservas (reservados) - Relacionamento 1:N.

<img width="16" height="16" src="https://img.icons8.com/tiny-color/16/data-configuration.png" alt="data-configuration"/> DestinoReservado

A entidade DestinoReservado representa uma reserva específica para um determinado destino. Cada reserva possui um identificador único (reservaId) e inclui os seguintes atributos:

- *reservaId (Long):* Identificador único da reserva.

- *dateCheckin (LocalDate):* Data de check-in da reserva.

- *dateCheckout (LocalDate):* Data de check-out da reserva.

- *nomeCompleto_Convidado (String):* Nome completo do convidado.

- *email_Convidado (String):* Endereço de e-mail do convidado.

- *numeroAdultos (int):* Número de adultos na reserva.

- *numeroCriancas (int):* Número de crianças na reserva.

- *totalConvidados (int):* Total de convidados (soma de adultos e crianças).

- *codigoConfirma_Reserva (String):* Código de confirmação da reserva.

- *destino (Destino):* Destino associado a esta reserva.

Relacionamentos

Uma reserva pertence a um único destino (destino) - Relacionamento M:1.

### Diagrama do Banco de Dados

```plaintext
+-----------------------------------+               +---------------------------------+
|      Destino                      |               |   DestinoReservado              |
+-----------------------------------+               +---------------------------------+
| id: Long                          |               | reservaId: Long                 |
| tipoDestino: String               |               | dateCheckin: LocalDate          |
| precoDestino: BigDecimal          |               | dateCheckout: LocalDate         |
| estaReservado: Boolean            |               | nomeCompleto_Convidado: String  |
| foto: Blob                        |               | email_Convidado: String         |
| reservados: List<DestinoReservado>|               | numeroAdultos: int              |
+-----------------------------------+               | numeroCriancas: int             |
                                                    | totalConvidados: int            |
                                                    | codigoConfirma_Reserva: String  |
                                                    | destino: Destino                |
                                                    +--------------------------------+


```


```plaintext
+-------------------------------------+
|              DestinoReservado        |
+-------------------------------------+
| reservaId: Long                     |
| dateCheckin: LocalDate              |
| dateCheckout: LocalDate             |
| nomeCompleto_Convidado: String      |
| email_Convidado: String              |
| numeroAdultos: int                   |
| numeroCriancas: int                  |
| totalConvidados: int                 |
| codigoConfirma_Reserva: String       |
+-------------------------------------+
| <<FK>> destino_id                   |
+-------------------------------------+
| calculaTotalConvidado()             |
| setNumeroAdultos(int)                |
| setNumeroCriancas(int)               |
| DestinoReservado(String)             |
| getCheckInDate(): LocalDate         |
| getCheckOutDate(): LocalDate        |
| getReservadoConfirmaCodigo(): String |
+-------------------------------------+

+-------------------------------------+
|                Destino              |
+-------------------------------------+
| id: Long                            |
| tipoDestino: String                 |
| precoDestino: BigDecimal            |
| estaReservado: Boolean              |
| foto: Blob                          |
+-------------------------------------+
| reservados: List<DestinoReservado>  |
+-------------------------------------+
| Destino()                           |
| addReservado(DestinoReservado)      |
+-------------------------------------+
```
