# Todo

Aplicação todo, app exemplo para workshop clojure.

## Estrutura de diretórios

- dev : Utilizado para criação de scripts e rotinas relacionadas ao desenvolvimento local
- resources : É onde fica todos os arquivos acessíveis ao servidor (public), inclusive os Cljs compilado
- src : onde fica o código clojurescript
- target : onde ficam as dependencias do clojurescript
- project.cljs : definições de dependencias e projeto. (Como o npm)

## step 1

Podemos executar 

```sh
$ lein figwheel
```

Para iniciar o servidor e repl. Dentro do REPL é possível executar codigos que executaram dentro da sua aplicação web

```cljs
> (js/alert "Oi")
```

Irá abrir um alert com "Oi"

Também podemos acessar o namespace do módulo e acessar suas funções de variáveis

```cljs
> (ns todo.core)
> (:text @app-state)
```

Utilizar o REPL durante o desenvolvimento é um grande aceleador, você pode rapidamente derefinir funções, testar valores de variávies e testar execução de funções

## step 2

Nesse step, vamos utilizar o [atom](https://clojure.org/reference/atoms) para definir um state para aplicação. Ele funciona como um redux, sendo possível adicionar observers com (add-watch )

Referencia de funções mais utilizadas
http://cljs.info/cheatsheet/

## step 3

Para finalizar o TODO, utilizamos a lib goog.events para criar um evento ao cadastrar um todo, adicionar ao state da aplicação

## step 4 - Simple SPA

Primeiramente, adicionaremos a dependencia para gerenciar rotas
dentro do arquivo project.clj

```cljs
[secretary "1.2.3"]
```

Após isso, executamos os seguintes comandos para baixar e compilar as dependencias

```sh
$ lein deps
$ lein cljsbuild once
```

Após isso, definimos as rotas dentro do arquivo src/core.cljs

## step 5 - Simple SPA

Neste passo, vamos utilizar jsonp para buscar dados de taxa de intercâmbio de uma api pública.
Essa é a maneira "classica" de fazer as coisas, utilizando callback para executar o que deseja após terminar a requisição

Note que na linha 28 do src/core.cljs, utilizamos a função (js->cljs) para converter um JSON em HashMap do clojure. Isso é feito para facilitar a manipução do objeto.

## step 6 - Simple SPA

Aqui mudamos a chamada com callback para utilizar o core.async
Nele utilizamos a chamado (go ) para executar código async e utilizamos channels para passar a informação dentro de uma chamada async

Links importantes

- http://www.braveclojure.com/core-async/