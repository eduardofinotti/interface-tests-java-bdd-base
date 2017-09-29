#language: pt
Funcionalidade: Validar cadastro na plataforma Tools QA
  Como visitante da plataforma Tools QA
  Eu quero poder me registrar como usuário

  @start
  Cenário: Cadastrar usuário
    Dado que estou na página de registro
    E preencho todos os campos obrigatórios
    Quando clicar no botão Submit
    Então o formulário é enviado exibindo mensagem "Thank you for your registration"

	@enabled
  Cenário: O username do usuário deve ser único
    Dado que estou na página de registro
    E preencho todos os campos obrigatórios
    E preencho o campo username com dado já cadastrado
    Quando clicar no botão Submit
    Então mostrará a mensagem de erro "Error: Username already exists"

  @finish
  Cenário: Campos referente ao nome são obrigatórios
    Dado que estou na página de registro
    E somente não preencho os campos referente ao nome do usuário
    Quando clicar no botão Submit
    Então mostrará a mensagem "* This field is required"
