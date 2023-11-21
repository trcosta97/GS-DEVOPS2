# FitVictory API
## Arquitetura 
![image](https://github.com/trcosta97/GS-DEVOPS2/assets/101136329/9a36208b-67be-4925-a5c0-8e03447dc2cd)


 ## Endpoints

 ### Swagger
https://fitvicapi.azurewebsites.net/swagger-ui/index.html  

### Register
POST  
https://fitvicapi.azurewebsites.net/auth/register   
```console
{
  "nome": "string",
  "email": "string",
  "senha": "string",
  "cpf": "string",
  "role": "USER",
  "enderecoUsuario": {
    "numero": "string",
    "rua": "string",
    "cidade": "string",
    "estado": "string",
    "pais": "string",
    "cep": "stringst"
  }
}
```

### Login
POST  
https://fitvicapi.azurewebsites.net/auth/login     
```console
{
  "email": "string",
  "senha": "string"
}
```

### Update User
PUT  
https://fitvicapi.azurewebsites.net/user/<IdUsuario>    
```console
{
  "nome": "string",
  "senha": "string",
  "email": "string"
}
```

### Get User
GET  
https://fitvicapi.azurewebsites.net/user/<IdUsuario>  
Requisição sem corpo. Retorna usuário especificado pelo ID na url.  

### Get Users
GET  
https://fitvicapi.azurewebsites.net/user?page=0&size=10    
Requisição sem corpo. Retorna lista de usuários paginados com 10 por página, ordenados por ID (ordem de cadastro).

### Check In
PUT  
https://fitvicapi.azurewebsites.net/user/checkIn/<IdUsuario>    
```console
  {
  "tipo": "ACADEMIA",
  "descricao": "string",
  "pontos": 0,
  "enderecoAtividade": {
    "numero": "string",
    "rua": "string",
    "cidade": "string",
    "estado": "string",
    "pais": "string",
    "cep": "string"
  }
}
```

### Get Atividades por User
GET  
https://fitvicapi.azurewebsites.net/user/atividades/<IdUsuario>?page=0&size=10    
Requisição sem corpo. Inserir o ID do usuário no local indicado no link. Retorna lista de atividades por usuário. Requisição paginada com 10 por página, ordenados por ID (ordem de cadastro).
