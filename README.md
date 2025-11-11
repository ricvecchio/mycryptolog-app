# MyCryptoLog App

---

![Kotlin](https://img.shields.io/badge/python-3.8%2B-blue)
![Flask](https://img.shields.io/badge/flask-2.3.3-green)
![License](https://img.shields.io/badge/license-MIT-yellow)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-orange)

Uma aplicação web para registro e acompanhamento de transações de criptomoedas, desenvolvida em Flask com interface moderna e responsiva.

---

## 🚀 Visão Geral

O MyCryptoLog App é uma aplicação web que permite aos usuários registrar, visualizar e gerenciar suas transações de criptomoedas de forma organizada. A aplicação oferece uma interface intuitiva para acompanhamento de investimentos em criptoativos.

---

## ✨ Funcionalidades

- **💰 Registro de Transações** - Adição de compras, vendas e transfers
- **📈 Acompanhamento** - Histórico completo de transações
- **🎨 Interface Responsiva** - Design moderno e adaptável
- **🔐 Autenticação** - Sistema de login e registro de usuários
- **📱 Design Mobile-First** - Otimizado para dispositivos móveis

---

## 🛠 Tecnologias Utilizadas

### Backend
- **Python 3.8+** - Linguagem de programação
- **Flask 2.3.3** - Framework web
- **SQLite** - Banco de dados
- **Flask-Login** - Gerenciamento de sessões
- **Werkzeug** - Utilidades de segurança

### Frontend
- **HTML5** - Estrutura da aplicação
- **CSS3** - Estilização
- **JavaScript** - Interatividade
- **Bootstrap 5** - Framework CSS
- **Chart.js** - Gráficos e visualizações

### Ferramentas de Desenvolvimento
- **Pip** - Gerenciador de pacotes Python
- **Virtualenv** - Ambiente virtual

---

## 📦 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- Kotlin 2.0.21 ou superior
- Pip (gerenciador de pacotes Python)
- Git

---

## 🔧 Instalação

Siga os passos abaixo para configurar o projeto localmente:

1. **Clone o repositório**
   ```bash
   git clone https://github.com/brect/mycryptolog-app.git
   cd mycryptolog-app
   ```

---

## 📁 Estrutura do Projeto

```
mycryptolog-app/
├── app.py                 # Arquivo principal da aplicação
├── requirements.txt       # Dependências do projeto
├── config.py              # Configurações da aplicação
├── static/                # Arquivos estáticos
│   ├── css/
│   ├── js/
│   └── images/
├── templates/             # Templates HTML
│   ├── base.html
│   ├── index.html
│   ├── login.html
│   └── register.html
├── models/                # Modelos de dados
├── routes/                # Rotas da aplicação
└── utils/                 # Utilitários
```

---

## 🔌 API Endpoints

### Autenticação
- **POST /register** - Registrar novo usuário
- **POST /login** - Fazer login
- **GET /logout** - Fazer logout

### Transações
- **POST /add_transaction** - Adicionar transação
- **GET /transactions** - Listar transações
- **PUT /transaction/<id>** - Atualizar transação
- **DELETE /transaction/<id>** - Excluir transação

---