const express = require('express');
const router = express.Router();
const horarioController = require('./Sistema-de-Gerenciamento-para-Refor-o-Escolar-main/Backend/controllers/horarioController');

// Rota para listar os horários (para horarioProf.html)
// Rota completa: GET http://localhost:3000/api/horarios
router.get('/', horarioController.getHorarios);

// Rota para criar um novo horário (baseado em horarioProf.html: action="/horarioProf")
// Rota completa: POST http://localhost:3000/api/horarios
router.post('/', horarioController.createHorario);

// Rota para excluir um horário (baseado em horarioProf.html: th:href="@{/horario/excluir/{id}...")
// Rota completa: DELETE http://localhost:3000/api/horarios/1
router.delete('/:id', horarioController.deleteHorario);

module.exports = router;