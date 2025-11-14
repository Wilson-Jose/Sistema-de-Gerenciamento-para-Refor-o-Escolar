// /Backend/routes/authRoutes.js
const express = require('express');
const router = express.Router();
const authController = require('../controllers/authController');

// Rota para fazer login (baseado no seu login.html: th:action="@{/logar}")
// Rota completa: POST http://localhost:3000/api/logar
router.post('/logar', authController.login);

// Rota para registar um novo professor (baseado no login.html: th:href="@{/cadastroProf}")
// Rota completa: POST http://localhost:3000/api/cadastroProf
router.post('/cadastroProf', authController.register);

module.exports = router;