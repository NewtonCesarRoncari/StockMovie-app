# StockMovie-app

Este projeto consiste na simulação de uma plataforma de bolsa de valores baseada em filmes que ainda serão lançados, 
realizado com comunicação via Api Web Service de modo colaborativo, a Api pode ser encontrada
<a href="https://github.com/ThiagoSousaSantana/stockmovies">neste repositório</a>

## Introdução

<p>A ideia principal
se foca em abrir o capital de filmes a serem ainda produzidos, estipulando um número de ações a um valor determinado, ao comprar uma
quantidade de ações de um filme a escolha do usuário, o próprio terá seu retorno financeiro quando o filme sair de circulação das salas de
cinema, fechando assim sua arrecadação, tal arrecadação de bilheteria é dividida entre os acionistas conforme a quantidade e o valor das 
ações adquiridas</p>

### Layouts
Login             |  Home    | Search 
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://raw.githubusercontent.com/NewtonCesarRoncari/StockMovie-app/master/app/readme/Stock_movie_login.jpeg" width="250" height="420" title="Tela de Login"/> | <img src="https://raw.githubusercontent.com/NewtonCesarRoncari/StockMovie-app/master/app/readme/Stock_movie_home.jpeg" width="250" height="420" title="Tela Home"/> | <img src="https://raw.githubusercontent.com/NewtonCesarRoncari/StockMovie-app/master/app/readme/Stock_movie_search.jpeg" width="250" height="420" title="Tela de Busca"/>

### Ações

Ao entrar na aplicação informando o usuário e senha o usuário pode escolher um filme desejado, um filme em que acredita que realmente 
pode gerar lucro em suas exibições baseadas na bilheteria, ao clicar no filme desejado é mostrado a tela dos detalhes do filme, na tela 
aberta se encontra informações dos filmes e suas estimativas de lucros, como o filme ainda não saiu, sua bilheteria é um valor estimado,
tal valor é necessário para o cálculo do valor da ação, a qualquer momento o usuário tem acesso aos seus dados e também a sua carteira
de investimentos

Detalhes do filme     |  Dados do usuário |  Carteira
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://raw.githubusercontent.com/NewtonCesarRoncari/StockMovie-app/master/app/readme/Stock_movie_detail.jpeg" width="250" height="420" title="Detalhes do filme"/> | <img src="https://raw.githubusercontent.com/NewtonCesarRoncari/StockMovie-app/master/app/readme/Stock_movie_user_data.jpeg" width="250" height="420" title="Dados do usuário"| <img src="https://raw.githubusercontent.com/NewtonCesarRoncari/StockMovie-app/master/app/readme/Stock_movie_user_wallet.jpeg" width="250" height="420" title="Carteira"/>

## Rodando a aplicação

Clone ou realize o download do projeto em formato Zip, más antes certifique se que contem os pré requisitos para as comunicações com o
banco de dados

### Pré requisitos

Para garantir o bom funcionamento da aplicação rode com: 
- Target JVM 1.8 
- Android Gradle Plugin Version 3.5.3 
- Gradle Version 5.4.1

### Instalando 

Após clonar o projeto, importe no seu Android Studio, aceitando as susjestões da Ide, os pré requisitos serão importados automaticamente,

- No pacote 'retrofit', há a classe 'ConnectionRetrofit.kt', altere a constante BASE_URL, para o endereço que irá utilizar ao
realizar o download da API

- Rode a aplicação normalmente

## Tecnologias utilizadas

- <a href="https://developer.android.com/guide/topics/ui/look-and-feel?hl=pt-br">Material Design<a/> 
- <a href="https://developer.android.com/guide/navigation?gclid=Cj0KCQiAvJXxBRCeARIsAMSkAppbYUXuaVm-tnHPOV9rH5RlVVScLrsUnhHxK-tbmHkYdTBeCDqU6aoaAphrEALw_wcB">Android Navigation</a>
- <a href="https://github.com/airbnb/lottie-android">Lotties</a>
- <a href="https://developer.android.com/topic/libraries/architecture/livedata">Live Data with ViewModel<a/>
- <a href="https://insert-koin.io/">Koin dependency injection<a/>
- <a href="https://github.com/bumptech/glide">Glide <a/>
- <a href="https://square.github.io/retrofit/">Retrofit<a/>
- <a href="https://developer.android.com/topic/libraries/architecture/room">Room Persistence Library<a/>
- <a href="https://kotlinlang.org/docs/reference/coroutines-overview.html">Coroutines<a/>
