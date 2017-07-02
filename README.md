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