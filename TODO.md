## Detalhamento das Tarefas

### 1. Configurar MySQL para produção e H2 apenas para desenvolvimento

- [ ] Adicionar configurações de perfil no `application.properties` ou `application.yml`
  - `application-dev.properties` para desenvolvimento
  - `application-prod.properties` para produção
- [ ] Criar scripts SQL para inicializar o banco de dados MySQL
  - `src/main/resources/db/mysql-schema.sql`
  - `src/main/resources/db/mysql-data.sql`
- [ ] Garantir que o H2 seja usado apenas em ambiente de desenvolvimento
  - Atualizar `DatabaseInitializer.java` para usar H2 em desenvolvimento e MySQL em produção

### 2. Adicionar um serviço para registrar vagas totais pela controladora

- [ ] Criar um endpoint na controladora para registrar novas vagas
- [ ] Implementar lógica de serviço para adicionar novas vagas ao banco de dados
- [ ] Adicionar validações para garantir que os dados inseridos são válidos
- [ ] Atualizar a documentação para refletir a nova funcionalidade

### 3. Terminar a documentação dos controladores e DTOs

- [ ] Adicionar anotações de Swagger/OpenAPI em todos os controladores
- [ ] Adicionar anotações de Swagger/OpenAPI em todos os DTOs
- [ ] Documentar todos os possíveis erros e respostas de erro

### 4. Organizar futuras issues

- [ ] Configurar um sistema de rastreamento de issues (por exemplo, GitHub Issues, Jira)
- [ ] Criar templates para novas issues
- [ ] Priorizar e categorizar issues
  - Bugs
  - Novas funcionalidades
  - Melhorias
  - Documentação
