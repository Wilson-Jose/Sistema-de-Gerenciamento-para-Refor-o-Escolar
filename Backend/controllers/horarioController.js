// Mock da agenda
const mockHorarios = [
  { id_horario: 1, nomeEvento: "Aula de Matemática", diaSemana: "Segunda-feira", horaInicio: "08:00", horaFim: "10:00" },
  { id_horario: 2, nomeEvento: "Aula de Português", diaSemana: "Terça-feira", horaInicio: "10:00", horaFim: "12:00" },
];

/**
 * @route   GET /api/horarios
 * @desc    Lista todos os horários
 */
exports.getHorarios = (req, res, next) => {
  try {
    res.status(200).json({
      success: true,
      data: mockHorarios,
    });
  } catch (error) {
    next(error);
  }
};

/**
 * @route   POST /api/horarios
 * @desc    Cria um novo horário
 */
exports.createHorario = (req, res, next) => {
  try {
    // Em vez de th:object, o frontend enviará um JSON no corpo
    const { nomeEvento, diaSemana, horaInicio, horaFim } = req.body;

    if (!nomeEvento || !diaSemana || !horaInicio || !horaFim) {
        return res.status(400).json({ success: false, message: "Todos os campos são obrigatórios" });
    }

    const novoHorario = {
      id_horario: Math.floor(Math.random() * 1000),
      nomeEvento,
      diaSemana,
      horaInicio,
      horaFim,
    };
    
    mockHorarios.push(novoHorario);
    
    // No seu HTML, você espera um 'sucesso'.
    // Mas o ideal numa API REST é devolver o objeto criado.
    // Vamos devolver ambos para compatibilidade.
    res.status(201).json({
      success: true,
      message: "Horário salvo com sucesso!", // Para o th:if="${sucesso}"
      data: novoHorario,
    });
  } catch (error) {
    next(error);
  }
};

/**
 * @route   DELETE /api/horarios/:id
 * @desc    Exclui um horário
 */
exports.deleteHorario = (req, res, next) => {
    try {
        const { id } = req.params;
        const index = mockHorarios.findIndex(h => h.id_horario === parseInt(id));

        if (index === -1) {
            return res.status(404).json({ success: false, message: "Horário não encontrado" });
        }

        mockHorarios.splice(index, 1); // Remove o item do array

        res.status(200).json({ 
            success: true, 
            message: "Horário excluído com sucesso!"
        });
    } catch (error) {
        next(error);
    }
};