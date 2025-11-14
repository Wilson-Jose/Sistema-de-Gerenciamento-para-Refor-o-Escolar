const express = require('express');
const router = express.Router();

// Importamos os nossos routers especializados
const authRoutes = require('./authRoutes');
// const alunoRoutes = require('./alunoRoutes');     <-- COMENTE ESTA LINHA
// const horarioRoutes = require('./horarioRoutes');   <-- COMENTE ESTA LINHA
const alunoRoutes = require('./alunoRoutes');
const horarioRoutes = require('./horarioRoutes');

// Documentação:
// Cada vez que um pedido chega a /api, este router assume.

// Pedidos para /api/ (ex: /api/logar, /api/cadastroProf)
// serão geridos pelo 'authRoutes'
router.use('/', authRoutes);
// router.use('/alunos', alunoRoutes);               <-- COMENTE ESTA LINHA
// router.use('/horarios', horarioRoutes);           <-- COMENTE ESTA LINHA
// Pedidos para /api/alunos (ex: /api/alunos, /api/alunos/123)
// serão geridos pelo 'alunoRoutes'
router.use('/alunos', alunoRoutes);

// Pedidos para /api/horarios (ex: /api/horarios)
// serão geridos pelo 'horarioRoutes'
router.use('/horarios', horarioRoutes);

module.exports = router;