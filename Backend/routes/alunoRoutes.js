const express = require('express');
const router = express.Router();
const alunoController = require('../controllers/alunoController');

// Rota para listar todos os alunos (para area-aluno.html)
// Rota completa: GET http://localhost:3000/api/alunos
router.get('/', alunoController.getAllAlunos);

// Rota para cadastrar um novo aluno (para cadastrar-aluno.html)
// Rota completa: POST http://localhost:3000/api/alunos
router.post('/', alunoController.createAluno);

// Rota para pesquisar alunos (para a barra de pesquisa em area-aluno.html)
// Rota completa: GET http://localhost:3000/api/alunos/pesquisar?q=maria
router.get('/pesquisar', alunoController.searchAlunos);

// Rota para obter um aluno específico (para ficha-aluno.html)
// Rota completa: GET http://localhost:3000/api/alunos/1
router.get('/:id', alunoController.getAlunoById);

// Rota para obter o relatório de um aluno (para emitir-relatorio.html)
// Rota completa: GET http://localhost:3000/api/alunos/1/relatorio
router.get('/:id/relatorio', alunoController.getRelatorioAluno);

module.exports = router;