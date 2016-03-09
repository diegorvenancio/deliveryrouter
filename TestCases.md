# DELIVERY ROUTER TEST CASES:

## 1. CRIAÇÃO DE MAPA:

Utilizando SoapUI com um método POST foram usados estes dados de entrada:

Input:
http://localhost:8080/maps?mapName=SP

A B 10
B D 15
A C 20
C D 30
B E 50
D E 30

Output:
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 16
Date: Wed, 09 Mar 2016 20:50:44 GMT

Map SP Created!

Dados verificados nas tabelas da base via console da base H2 em: http://localhost:8080/h2


## 2. CRIAÇAO DE MAPA REPETIDO:

Considerando o mapa já inserido em teste anterior.
Utilizando SoapUI com um método post foram usados estes dados de entrada:

Input:
http://localhost:8080/maps?mapName=SP

A B 10
B D 15
A C 20
C D 30
B E 50
D E 30

Output:
HTTP/1.1 500 Internal Server Error
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 09 Mar 2016 20:58:27 GMT
Connection: close

{"timestamp":1457557107048,"status":500,"error":"Internal Server Error","exception":"br.com.drv.deliveryrouter.exception.DeliveryRouterException","message":"Map name already exists!","path":"/maps"}


## 3. CRIAÇÃO DE MAPA DE GRANDE PROPORÇÃO:

Utilizando SoapUI com um método POST foram usados estes dados de entrada:

Input:
http://localhost:8080/maps?mapName=CPS

Inserido no BODY o mapa contido no arquivo CPSMap.txt, com 1274 vetores e 637 pontos.

Output:
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 17
Date: Wed, 09 Mar 2016 21:01:53 GMT

Map CPS Created!

Tempo de resposta da criação: 1086ms


## 4. CASO BÁSICO DE ROTA PEDIDO NOS REQUISITOS:

Considerando o mapa já inserido em teste anterior.
Utilizando SoapUI com um método GET foram usados estes dados de entrada:

Input:
http://localhost:8080/route?mapName=SP&pointA=A&pointB=D&kmPerLitter=10&fuelCost=2.50

Output:
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 29
Date: Wed, 09 Mar 2016 21:09:45 GMT

A -> B -> D  total cost: 6.25


## 5. CÁLCULO DE ROTA BÁSICA COM PONTOS INVERTIDOS:

Considerando o mapa já inserido em teste anterior.
Utilizando SoapUI com um método GET foram usados estes dados de entrada:

Input:
http://localhost:8080/route?mapName=SP&pointA=D&pointB=A&kmPerLitter=10&fuelCost=2.50

Output:
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 29
Date: Wed, 09 Mar 2016 21:15:19 GMT

D -> B -> A  total cost: 6.25


## 6. CÁLCULO DE ROTA DE GRANDE PROPORÇÃO:

Considerando o mapa já inserido em teste anterior.
Utilizando SoapUI com um método GET foram usados estes dados de entrada:

Input:
http://localhost:8080/route?mapName=CPS&pointA=0&pointB=637&kmPerLitter=10&fuelCost=2.50

Output:
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 1264
Date: Wed, 09 Mar 2016 21:16:54 GMT

0 -> 4 -> 7 -> 11 -> 14 -> 18 -> 21 -> 25 -> 28 -> 32 -> 35 -> 39 -> 42 -> 46 -> 49 -> 53 -> 56 -> 60 -> 63 -> 67 -> 70 -> 74 -> 77 -> 81 -> 84 -> 88 -> 91 -> 95 -> 98 -> 102 -> 105 -> 109 -> 112 -> 116 -> 119 -> 123 -> 126 -> 130 -> 133 -> 137 -> 140 -> 144 -> 147 -> 151 -> 154 -> 158 -> 161 -> 165 -> 168 -> 172 -> 175 -> 179 -> 182 -> 186 -> 189 -> 193 -> 196 -> 200 -> 203 -> 207 -> 210 -> 214 -> 217 -> 221 -> 224 -> 228 -> 231 -> 235 -> 238 -> 242 -> 245 -> 249 -> 252 -> 256 -> 259 -> 263 -> 266 -> 270 -> 273 -> 277 -> 280 -> 284 -> 287 -> 291 -> 294 -> 298 -> 301 -> 305 -> 308 -> 312 -> 315 -> 319 -> 322 -> 326 -> 329 -> 333 -> 336 -> 340 -> 343 -> 347 -> 350 -> 354 -> 357 -> 361 -> 364 -> 368 -> 371 -> 375 -> 378 -> 382 -> 385 -> 389 -> 392 -> 396 -> 399 -> 403 -> 406 -> 410 -> 413 -> 417 -> 420 -> 424 -> 427 -> 431 -> 434 -> 438 -> 441 -> 445 -> 448 -> 452 -> 455 -> 459 -> 462 -> 466 -> 469 -> 473 -> 476 -> 480 -> 483 -> 487 -> 490 -> 494 -> 497 -> 501 -> 504 -> 508 -> 511 -> 515 -> 518 -> 522 -> 525 -> 529 -> 532 -> 536 -> 539 -> 543 -> 546 -> 550 -> 553 -> 557 -> 560 -> 564 -> 567 -> 571 -> 574 -> 578 -> 581 -> 585 -> 588 -> 592 -> 595 -> 599 -> 602 -> 606 -> 609 -> 613 -> 616 -> 620 -> 623 -> 627 -> 630 -> 634 -> 637  total cost: 91.00

Tempo de resposta da criação: 363ms


## 7. ROTA COM O MESMO PONTO COMO ENTRADA E SAÍDA:

Considerando o mapa já inserido em teste anterior.
Utilizando SoapUI com um método GET foram usados estes dados de entrada:

Input:
http://localhost:8080/route?mapName=SP&pointA=A&pointB=A&fuelCost=5&kmPerLitter=2.5

Output:
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 19
Date: Wed, 09 Mar 2016 21:24:04 GMT

A  total cost: 0.00


## 8. ROTA COM APENAS UM VETOR DE SEPARAÇÃO:

Considerando o mapa já inserido em teste anterior.
Utilizando SoapUI com um método GET foram usados estes dados de entrada:

Input:
http://localhost:8080/route?mapName=SP&pointA=B&pointB=C&fuelCost=5&kmPerLitter=2.5

Output:
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 30
Date: Wed, 09 Mar 2016 21:26:26 GMT

B -> A -> C  total cost: 60.00

----------------------------------------------

Input:
http://localhost:8080/route?mapName=SP&pointA=D&pointB=C&fuelCost=5&kmPerLitter=2.5

Output:
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: text/plain;charset=UTF-8
Content-Length: 25
Date: Wed, 09 Mar 2016 21:29:30 GMT

D -> C  total cost: 60.00


## 9. TESTE DE PARÂMETRO FALTANDO NA REQUISIÇÃO DE MAPA:

Input:
http://localhost:8080/route?mapName=CPS&pointA=0&pointB=637&kmPerLitter=10

Output:
HTTP/1.1 406 Not Acceptable
Server: Apache-Coyote/1.1
Content-Length: 0
Date: Wed, 09 Mar 2016 21:18:22 GMT

----------------------------------------------

Input:
http://localhost:8080/route?mapName=CPS&pointA=0&pointB=637&fuelCost=5

Output:
HTTP/1.1 406 Not Acceptable
Server: Apache-Coyote/1.1
Content-Length: 0
Date: Wed, 09 Mar 2016 21:19:08 GMT


----------------------------------------------

Input:
http://localhost:8080/maps?mapName=

Output:
HTTP/1.1 400 Bad Request
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 09 Mar 2016 21:20:44 GMT
Connection: close

{"timestamp":1457558444277,"status":400,"error":"Bad Request","exception":"org.springframework.web.bind.MissingServletRequestParameterException","message":"Required String parameter 'mapName' is not present","path":"/maps"}



