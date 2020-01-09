# catchup

primeiramente deve se criar uma base de dados chamada "catchup" no postgres e modificar o arquivo "application.properties" para colocar o url, username e password da nova base de dados. 

O projeto está em update então ele criará toda a base de dados na primeira execução.

Foram criados varios testes utilizando a base de dados H2 então você pode clicar no botão direito na área de testes e rodar eles para ver se o codigo está em pleno funcionamento. 

Está sendo utilizado o spring security, como é um ambiente de teste para facilitar criei um endpoint inicial para criar um usuário master do Sistema, com ele você poderá criar outros usuários e fazer outras coisas. 

O End Point inicial é: <http://localhost:8080/usuario/criarUsuarioMasterInicial> ele cria o usuário master só uma vez, mesmo que você execute o comando novamente. Utilizamos o a autenticação Bearer aí você deve colocar o email e a senha no endpoint <http://localhost:8080/login> ele irá devolver no cabeçalho um token Bearer, onde voce colocará na parte de autenticação, do seu software de enviar requisições Http, colocara Bearer Token e ao copiar o token não envie o nome Bearer pois pode dar problema, coloque só o token sem o prefix. 

Estou usando no projeto para documentar a APi do SWAGGER então no link: <http://localhost:8080/swagger-ui.html> você pode encontrar todos os end points que existem na api, uma breve descrição sobre ele e os campos que ele recebe. 

Para começar seria interessante começar testando a área de Usuarios temos 2 perfis de usuário perfil 1 de administrador e perfil 2 de colaborador. No Resource de Usuario é possível criar, editar, buscar e criar o usuário inicial. 

Após podemos ir para o Resource de aviso, podemos criar Aviso passando uma imagem em Base64. Após a criação vamos buscar o Aviso, eu implementei de uma maneira que sempre busca só o Último Aviso na base de dados. Como você pode ver no swagger você pode editar e deletar um aviso também. No resource Aviso o Colaborador tem permissão de GET somente.

Indo adiante poderíamos testar agora a área de Parcerias. Nesse setor eu isolei a entidade de Categoria e a entidade Empresa, pois uma empresa pode ter várias parcerias então devemos primeiro criar uma empresa para criar uma parceria. 

No Resource Categoria é permitido criar categoria, update categoria e listar todas as categorias. No Resource Empresa podemos criar empresa, editar empresa e lista uma empresa por CNPJ.

Apos criar a empresa e a categoria, você deve criar a parceria e enviar o id da categoria, o cnpj da empresa e os outros dados da parceria. Na parceria é possível criar, filtrar, editar e modificar status da parceria. 

Para finalizar você pode verificar o Resource de Post onde você adicionará as fotos ao feed, ele recebe fotos em base 64 e no buscar ele devolve as fotos em base 64 também.

Não consegui desenvolver o front-end devido a tudo que implementei no back end onde eu tive dificuldades em algumas coisas como em fazer funcionar o Spring Security então me dediquei ao maximo no backend, pois é onde eu tenho maior dominio.  

Não consegui desenvolver o frontend devido a tudo que implementei no backend onde eu tive dificuldades em algumas coisas como em fazer funcionar o Spring Security então me dediquei ao máximo no backend, pois é onde eu tenho maior domínio.