# 🛒 POO3 - Produtos

Sistema de Cadastro de Produtos desenvolvido para a disciplina de POO3, com foco em aplicação de conceitos de programação orientada a objetos, manipulação de arquivos e uso da JavaFX para construção da interface gráfica.

---

## 👨‍💻 Autor

**Samuel Mores**

---

## ✅ Funcionalidades Implementadas

- Cadastrar um novo produto com dados completos
- Excluir produtos existentes
- Consultar produtos por código
- Listar todos os produtos cadastrados
- Armazenamento de dados em arquivo CSV separado por ponto-e-vírgula
- Validação completa de dados de entrada
- Relatórios usando Stream API:
    - Produtos próximos ao vencimento (até 60 dias à frente e 2 dias atrás)
    - Produtos com estoque baixo (menos de 10 unidades)
    - Margem de lucro média por categoria
    - Agrupamento de produtos por setor da categoria

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- JavaFX
- Stream API (filter, map, collect)
- Arquivos CSV
- Programação Orientada a Objetos (POO)

---

## 🗂️ Estrutura do Projeto

📁 src/ ├── com.prova/ │ ├── controller/ │ ├── model/ │ ├── persistence/ │ ├── utils/ │ └── view/ ├── produtos.csv └── README.md


---

## 🚀 Como Executar

1. Clone ou baixe o projeto.
2. Abra em uma IDE compatível com JavaFX (recomendo IntelliJ).
3. Verifique se o caminho do `produtos.csv` está acessível (geralmente na raiz do projeto).
4. Compile e execute a classe `Main.java`.

---

## 📄 Observações

- O sistema é flexível quanto às datas de fabricação (aceita 2 dias no futuro e até 4 semanas no passado).
- Todas as alterações são salvas automaticamente no `produtos.csv`.
- A interface foi construída com `FXML` e respeita princípios de organização por camadas (MVC).

---

## 📬 Contato

Caso deseje colaborar ou tenha dúvidas, entre em contato com **Samuel Mores**.