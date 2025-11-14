// Importação dos módulos principais
const express = require('express');
const cors = require('cors');
const morgan = require('morgan');
const path = require('path'); // Módulo 'path' para lidar com caminhos de ficheiros

// Importação do nosso router central e do gestor de erros
const mainRouter = require('./routes');
const errorHandler = require('./middleware/errorHandler');

// Inicialização da aplicação Express
const app = express();

// Definição da porta. Usamos 3000 ou a porta definida no ambiente (para deploy)
const PORT = process.env.PORT || 3000;

// --- Configuração de Middleware ---

// 1. CORS: Permite pedidos de outras origens (o nosso frontend)
app.use(cors());

// 2. Morgan: Faz log de todos os pedidos no formato 'dev'
// (ex: GET /api/alunos 200 5.123 ms)
app.use(morgan('dev'));

// 3. Express JSON Parser: Permite ao servidor entender JSON enviado no corpo dos pedidos (ex: em POST ou PUT)
app.use(express.json());

// 4. (Bónus) Servir Ficheiros Estáticos:
// Isto permite que o backend sirva diretamente as suas telas de frontend.
// Coloque esta pasta 'backend' no mesmo nível da sua pasta 'Frontend'.
// Opcional, mas útil.
const frontendPath = path.join(__dirname, '..', 'Frontend');
app.use(express.static(frontendPath));


// --- Rotas Principais ---

// Definimos que todas as nossas rotas de API começarão com /api
// ex: http://localhost:3000/api/logar
app.use('/api', mainRouter);

// Rota "Catch-all" para o Frontend (Se usar o middleware estático)
// Se nenhum pedido de API corresponder, ele envia o 'login.html'
// Isto ajuda na navegação do frontend
app.get('*', (req, res) => {
    res.sendFile(path.join(frontendPath, 'login.html'));
});


// --- Gestão de Erros ---

// Se qualquer rota chamar next(error), este middleware será acionado
app.use(errorHandler);

// --- Início do Servidor ---
app.listen(PORT, () => {
  console.log(`🚀 Servidor a postos e a ouvir em http://localhost:${PORT}`);
});