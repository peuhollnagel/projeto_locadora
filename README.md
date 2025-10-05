# Sistema de Biblioteca (Java + PostgreSQL)

Gerencie livros, clientes e empréstimos em uma biblioteca usando Java e PostgreSQL.

## Como rodar

1. **Crie o banco de dados**
   - No PostgreSQL, crie o banco `locadora` e execute o script `banco.sql` da raiz do projeto.

2. **Ajuste usuário/senha**
   - Se necessário, edite `src/dao/ConnectionFactory.java` com seu usuário e senha do PostgreSQL.

3. **Compile e execute**
   - No Windows:
     ```
     javac -cp ".;lib\postgresql-42.7.8.jar" -d bin src\aplicacao\*.java src\dao\*.java src\entidades\*.java
     java -cp ".;bin;lib\postgresql-42.7.8.jar" aplicacao.Programa
     ```
   - No Linux/Mac/Git Bash:
     ```
     javac -cp ".:lib/postgresql-42.7.8.jar" -d bin src/aplicacao/*.java src/dao/*.java src/entidades/*.java
     java -cp ".:bin:lib/postgresql-42.7.8.jar" aplicacao.Programa
     ```

O sistema será iniciado no terminal e você poderá usar normalmente.
