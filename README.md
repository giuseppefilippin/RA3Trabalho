# RA3Trabalho

Aplicação Java com interface Swing desenvolvida como parte do trabalho da disciplina RA3.

## Descrição

Este projeto consiste em uma aplicação bancária que permite aos usuários realizar operações financeiras básicas através de uma interface gráfica construída com Java Swing.

## Funcionalidades

- **Cadastro de Usuários**: Permite o registro de novos usuários no sistema.
- **Operações Bancárias**: Realiza operações como depósitos, saques e transferências.
- **Investimentos**: Oferece opções de investimento para os usuários.
- **Tratamento de Exceções**: Implementa exceções personalizadas, como `SaldoInsuficienteException` e `ValorInvalidoException`, para garantir a integridade das operações.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- `Banco.java`: Classe principal que gerencia as operações bancárias.
- `BancoInterface.java`: Interface que define os métodos para operações bancárias.
- `Botao.java`: Componente personalizado de botão para a interface gráfica.
- `DadosUsuario.java`: Classe que representa os dados dos usuários.
- `Investimento.java`: Classe que gerencia as operações relacionadas a investimentos.
- `LeituraInterface.java`: Interface para leitura de dados.
- `OperacoesInterface.java`: Interface que define as operações disponíveis.
- `UsuarioBanco.java`: Classe que representa um usuário do banco.
- `SaldoInsuficienteException.java` e `ValorInvalidoException.java`: Exceções personalizadas para tratamento de erros específicos.

Além disso, o projeto inclui arquivos binários (`.bin`) que armazenam dados persistentes dos usuários e um arquivo de texto (`usuarios.txt`) com informações adicionais.

## Pré-requisitos

- **Java**: Certifique-se de ter o JDK instalado (versão 8 ou superior).

## Como Executar

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/giuseppefilippin/RA3Trabalho.git
   ```

2. **Compile o projeto**:

   Navegue até o diretório do projeto e execute:

   ```bash
   javac *.java
   ```

3. **Execute a aplicação**:

   Após a compilação, execute:

   ```bash
   java Banco
   ```

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](https://github.com/giuseppefilippin/RA3Trabalho/blob/main/LICENSE) para mais informações.

## Autor

Desenvolvido por [Giuseppe](https://github.com/giuseppefilippin).

