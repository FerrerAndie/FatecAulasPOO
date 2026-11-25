# Jogo de Damas

Trabalho de Programação Orientada a Objetos: jogo de damas, executado pelo console, com salvamento e carregamento de partidas em arquivos locais. A implementação segue as regras e os comandos definidos no enunciado da disciplina.

## Identificação dos integrantes

Andrielle Araújo

Júlia Coutinho

Livia de Jesus

Luana de Lima

Márcia Mendes

## Instruções de compilação

1. [Importe e execute no Eclipse](#importação-e-execução-no-eclipse). Digite `NOVO Ana Pedro`, `MOVER A3 B4` e `ESTADO` para reconhecer o tabuleiro e o turno.

## Ambiente e estrutura

O projeto utiliza somente a biblioteca padrão do Java, com código compatível com Java 8 ou superior.

A suíte Java foi compilada com OpenJDK 17.0.20 e `--release 8`, com 57 casos aprovados. A importação, a compilação e os 33 casos obrigatórios também foram verificados na interface.

```
JogoDamas/
├── .project                  Configuração do projeto Eclipse
├── .classpath                Pastas de fontes e ambiente JavaSE-1.8
├── .settings/                Configuração de compilação e UTF-8
├── src/
│   ├── aplicacao/            Inicialização e interação pelo console
│   ├── jogo/                 Tabuleiro, regras e andamento da partida
│   ├── modelo/               Jogadores, peças, cores e posições
│   └── persistencia/         Contrato e armazenamento em texto
├── partidas/                 Exemplo de partida salva
├── testes/
│   ├── src/                  Testes automatizados em Java puro
│   ├── cenarios/             Partidas preparadas para os testes
│   ├── evidencias-eclipse/   Capturas e índice da validação pela interface
│   ├── roteiro-manual.md      Comandos e resultados esperados
│   └── relatorio-testes.md    Resultados e validações pendentes
└── docs/
    ├── guia-do-codigo.md
    └── roteiro-apresentacao.md
```

## Importação e execução no Eclipse

1. Instale um JDK 8 ou superior e abra o Eclipse com suporte a Java.
2. Extraia o ZIP, se estiver utilizando o pacote de entrega.
3. Selecione **File → Import → General → Existing Projects into Workspace**.
4. Em **Select root directory**, escolha a pasta que contém `JogoDamas/.project` ou a própria pasta `JogoDamas`.
5. Marque o projeto **JogoDamas** e clique em **Finish**.
6. Se o Eclipse indicar que o ambiente Java não foi encontrado, abra **Window → Preferences → Java → Installed JREs**, cadastre o JDK instalado e associe-o ao ambiente **JavaSE-1.8** em **Execution Environments**. Um JDK posterior também pode compilar código Java 8.
7. Abra `src/aplicacao/Main.java`, clique com o botão direito e selecione **Run As → Java Application**.
8. Digite os comandos na aba **Console**. Clique nessa aba antes de escrever.

Os arquivos de texto são UTF-8. A configuração do projeto registra essa codificação. Se os acentos aparecerem incorretamente no console, confira a codificação da configuração de execução em **Run Configurations → Common → Encoding → UTF-8**.

Os caminhos relativos de partidas e cenários são resolvidos a partir da pasta de trabalho da execução. No Eclipse, confira em **Run Configurations → Arguments → Working directory** se a pasta selecionada é a raiz de `JogoDamas`.

## Execução pelo terminal

Abra o terminal na pasta `JogoDamas`, com `javac` e `java` disponíveis no `PATH`.

Compile e execute a aplicação:

```
javac -encoding UTF-8 -d bin @fontes-producao.txt
java -cp bin aplicacao.Main
```

Em JDK 9 ou posterior, pode-se substituir a primeira linha por:

```
javac --release 8 -encoding UTF-8 -d bin @fontes-producao.txt
```

O argumento `--release` não existe no JDK 8; nesse JDK, utilize o primeiro comando. Os arquivos `fontes-producao.txt` e `fontes-testes.txt` contêm caminhos relativos, sem referência ao computador de desenvolvimento.

## Como jogar

A posição **A1 é escura**. As colunas vão de A a H e as linhas de 1 a 8. As brancas começam nas linhas 1 a 3 e avançam para a linha 8. As pretas começam nas linhas 6 a 8 e avançam para a linha 1. As brancas jogam primeiro.

| Comando | Função |
| --- | --- |
| `NOVO Ana Pedro` | Inicia outra partida; Ana recebe as brancas e Pedro, as pretas. |
| `MOVER A3 B4` | Tenta uma etapa de movimento ou de captura. |
| `ESTADO` | Mostra o bloco padronizado com tabuleiro, turno e situação. |
| `SALVAR partidas/minha-partida.txt` | Salva a partida entre turnos. |
| `CARREGAR partidas/minha-partida.txt` | Recupera uma partida validada. |
| `SAIR` | Encerra a aplicação. |

Os comandos e as coordenadas aceitam letras maiúsculas ou minúsculas. Em `NOVO`, informe um nome sem espaços para cada jogador. No salvamento e carregamento, tudo após o nome do comando é interpretado como o caminho do arquivo; caminhos com espaços são permitidos, sem aspas. O nome e a capitalização do caminho são preservados.

Exemplo de início:

```
NOVO Ana Pedro
MOVER A3 B4
MOVER B6 A5
ESTADO
SALVAR partidas/minha-partida.txt
SAIR
```

Em outra execução:

```
CARREGAR partidas/minha-partida.txt
ESTADO
```

### Regras implementadas

- A peça comum anda uma casa diagonal para frente, em uma casa vazia; ela captura para frente ou para trás.
- Havendo captura disponível, é obrigatório capturar. Entre todas as peças do jogador, deve ser escolhida uma sequência com o **maior número de capturas**. Empates permitem qualquer sequência máxima.
- Uma captura múltipla é digitada em etapas sucessivas. O turno e a peça permanecem os mesmos até o fim da sequência.
- As peças capturadas continuam ocupando e bloqueando suas casas até o fim da sequência. Não podem ser capturadas novamente.
- A dama se desloca por várias casas diagonais livres e captura a distância, considerando todas as casas de aterrissagem válidas. Não atravessa peças próprias nem bloqueios.
- A promoção ocorre quando uma peça comum **termina a jogada** na última linha. Passar por essa linha durante uma captura e sair dela não promove a peça.
- Vence quem elimina todas as peças adversárias ou deixa o adversário sem movimento legal. O carregamento também verifica essas condições.
- Uma tentativa inválida mantém o tabuleiro e o turno. Depois da vitória, `MOVER` é rejeitado e `NOVO` permite recomeçar.

### Leitura do estado

O comando `ESTADO` imprime oito linhas, da linha 8 para a linha 1. Cada linha tem oito caracteres, das colunas A para H.

| Símbolo | Significado |
| --- | --- |
| `b` | Peça branca comum |
| `p` | Peça preta comum |
| `B` | Dama branca |
| `P` | Dama preta |
| `.` | Casa vazia |

O bloco da partida inicial é:

```
TABULEIRO_INICIO
.p.p.p.p
p.p.p.p.
.p.p.p.p
........
........
b.b.b.b.
.b.b.b.b
b.b.b.b.
TABULEIRO_FIM
TURNO=BRANCAS
STATUS=EM_ANDAMENTO
```

Durante uma captura múltipla, as peças já capturadas ainda aparecem nesse bloco, pois continuam fisicamente no tabuleiro até a conclusão. A mensagem do console informa a peça que deve continuar a sequência.

## Classes e orientação a objetos

| Classe ou interface | Responsabilidade |
| --- | --- |
| `Main` | Cria os componentes e inicia o console. |
| `ConsoleJogo` | Lê comandos, apresenta mensagens e solicita operações à partida e à persistência. |
| `Partida` | Mantém jogadores, turno, situação e sequência de captura; valida e aplica cada etapa. |
| `Tabuleiro` | Armazena e consulta as peças nas 64 casas; produz a representação textual. |
| `Regras` | Consulta movimentos e calcula a maior sequência possível de captura. |
| `Movimento` | Representa origem, destino e, quando houver, a posição capturada em uma etapa. |
| `Posicao` | Representa uma coordenada imutável e converte textos como `A3`. |
| `Cor` | Representa brancas e pretas, direção de avanço e cor adversária. |
| `Jogador` | Associa um nome a uma cor. |
| `Peca` | Classe abstrata que define a cor e o comportamento comum esperado das peças. |
| `PecaComum` e `Dama` | Especializações que oferecem movimentos e capturas conforme seu tipo. |
| `PersistenciaPartida` | Interface que define as operações de salvar e carregar. |
| `PersistenciaArquivo` | Implementa esse contrato utilizando texto UTF-8 e arquivos locais. |

Os quatro pilares aparecem em comportamento efetivamente utilizado:

- **Encapsulamento:** O estado da partida e a grade do tabuleiro são privados. O console solicita `mover`, em vez de alterar casas diretamente. As consultas não disponibilizam um array editável ao chamador. A posição e a identidade das peças são imutáveis.
- **Herança:** `PecaComum` e `Dama` herdam de `Peca`, compartilhando a identidade da cor e especializando as regras de deslocamento e captura.
- **Polimorfismo:** O cálculo das jogadas trabalha com referências do tipo `Peca` e chama os métodos de movimentos e capturas. A implementação executada depende do objeto concreto, comum ou dama.
- **Abstração:** `Peca` descreve o que toda peça deve oferecer, sem representar uma peça genérica jogável. `PersistenciaPartida` permite que o console peça salvamento e carregamento sem conhecer o formato do arquivo.

O [guia do código](docs/guia-do-codigo.md) detalha o fluxo de uma jogada, a busca da maior captura e essas decisões de modelagem.

## Formato de persistência

Cada arquivo é texto UTF-8, com versão, nomes, turno, situação e oito linhas de tabuleiro. Exemplo de uma partida após `MOVER A3 B4`:

```
JOGO_DAMAS_V1
BRANCAS=Ana
PRETAS=Pedro
TURNO=PRETAS
STATUS=EM_ANDAMENTO
TABULEIRO_INICIO
.p.p.p.p
p.p.p.p.
.p.p.p.p
........
.b......
..b.b.b.
.b.b.b.b
b.b.b.b.
TABULEIRO_FIM
```

A pasta inclui uma [partida de exemplo](partidas/exemplo.txt), gerada após `NOVO Ana Pedro` e `MOVER A3 B4`. Para abri-la no console:

```
CARREGAR partidas/exemplo.txt
ESTADO
```

Os valores de turno são `BRANCAS` e `PRETAS`. Os valores de situação são `EM_ANDAMENTO`, `VITORIA_BRANCAS` e `VITORIA_PRETAS`. A posição na grade determina a coordenada da peça; o símbolo determina sua cor e se ela é comum ou dama.

O carregamento verifica o formato inteiro e a consistência do estado antes de substituir a partida atual. Arquivos ausentes, vazios, malformados ou inconsistentes geram uma mensagem de erro. Um estado marcado como em andamento, mas sem jogada legal para o jogador da vez, é reconhecido como vitória do adversário ao carregar. Um vencedor informado que contradiga o tabuleiro é rejeitado.

O salvamento é proibido durante uma captura múltipla. Entre turnos, o conteúdo é escrito em um arquivo temporário e então transferido ao destino, reduzindo o risco de substituir uma partida por dados incompletos. A pasta de destino precisa existir e permitir escrita; a aplicação informa falhas de acesso sem encerrar o jogo.

## 
