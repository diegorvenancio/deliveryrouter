# DELIVERY ROUTER
=================

## Pre-requisitos

- Maven
- JDK 1.8


## instalação e execução

- Após baixar o projeto do github, no diretório do projeto insira: mvn package
- Para executar, vá para o diretório target e insira:  java -jar deliveryrouter-1.0.0.jar
- O servidor receberá requisições na porta 8080. Isto é configurável no arquivo application.properties.
- A base h2 tem um console que pode ser acessado em http://localhost:8080/h2 com usuário admin e senha admin, configuráveis no arquivo application.properties.


## Funcionalidades

- Criação de mapas /maps

Feita via webservice com método POST com os seguintes parâmetros:

URL: http://localhost:8080/maps?mapName=<nome do mapa>
BODY: String representado vetores do mapa, com duas strings para ids do primeiro e segundo ponto e um número inteiro para a distância entre estes pontos, separados por espaços. Vetores separados por line break.

Exemplo:
http://localhost:8080/maps?mapName=SP

A B 10  
B D 15  
A C 20  
C D 30  
B E 50  
D E 30  


- Cálculo de Rota /route

Feito via método GET com os seguintes parâmetros:

URL: http://localhost:8080/route?mapName=<nome mapa>&pointA=<id ponto de partida>&pointB=<id ponto de chegada>&kmPerLitter=<consumo em km/l>&fuelCost=<custo de combustível por km>

Exemplo:
http://localhost:8080/route?mapName=SP&pointA=A&pointB=D&kmPerLitter=10&fuelCost=2.50


## Arquitetura

Para realizar este projeto escolhi o Spring Boot por ele simplificar muito o deploy e o setup inicial do projeto. Usei a base H2, Hibernate e Spring Data com geração automática de DDL. Este método é muito eficiente para criação de tabelas e persistência dos dados além de ter excelente produtividade na construção. A base H2 ainda oferece um console web que é muito útil para consulta e edição dos dados persistidos. Os dados da base são persistidos no arquivo deliveryrouter.mv.db.

Para a geração de rota, pesquisei na literatura científica quais eram as melhores opções de algorítmo para buscar a rota mais curta num grafo. Analisei os seguintes algorítmos:

- Ant Colony Optimization (ACO)
- Belman Ford
- Delta stepping (parallel algorithm)
- Dijkstra

Devido ao aviso quanto à mapas de grandes proporções, verifiquei o algorítmo Delta Stepping, que faz o cálculo paralelamente. Se o problema realmente precisasse de grande poder computacional o ideal seria usar um cálculo em paralelo, visto que por melhor que seja o hardware, o processador numa única thread não consegue fazer um cálculo mais que 2 ou 3x mais rápido que um processador commodity. Entretanto a implementação deste algorítmo é de alta complexidade e há pouco material sobre ele disponível na rede.

Caso o problema realmente levasse o hardware ao limite, eu poderia usar o ACO, que seria uma heurística para atingir uma solução razoável num tempo aceitável. A implementação é viável apesar de arriscada devido a atingir uma solução sub-ótima e ajustar os parâmetros para garantir a convergência das soluções pode ser problemático. Este seria minha segunda opção.

Finalmente analisei Dijkstra e Belmand Ford. A implementação dos dois é semelhante. Por Dijkstra ter sido citado em algumas fontes como a melhor opção, testei uma implementação padrão deste algorítmo com grande número de pontos e verifiquei que a solução era obtida em tempo muito abaixo de 1s. Escolhi então  este algorítmo e implementei a minha versão dele. 


## Testes

Fiz os testes manualmente com a ferramenta SoapUI e os test cases e resultados estão descritos no arquivo TestCases.md. Testei a execução normal, performance em casos extremos e falta de parâmetros em requisições.

Entendo que o ideal seria usar JUnit, entretanto devido ao difícil setup do Spring Boot para criar casos de teste que fossem representativos, decidi não utilizar por falta de tempo.
