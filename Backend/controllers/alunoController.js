// /Backend/controllers/alunoController.js

// Dados fictícios (mock) para simular um banco de dados
const mockAlunos = [
  { id: 1, nome: "João Silva", turma: "A", dataMatricula: "2024-01-10" },
  { id: 2, nome: "Maria Oliveira", turma: "B", dataMatricula: "2024-02-15" },
  { id: 3, nome: "Carlos Souza", turma: "A", dataMatricula: "2024-03-01" },
];

/**
 * @route   GET /api/alunos
 * @desc    Lista todos os alunos
 */
exports.getAllAlunos = (req, res, next) => {
  try {
    // Apenas devolvemos a lista de mock
    res.status(200).json({
      success: true,
      data: mockAlunos,
    });
  } catch (error) {
    next(error);
  }
};

/**
 * @route   GET /api/alunos/:id
 * @desc    Obtém um aluno específico
 */
exports.getAlunoById = (req, res, next) => {
  try {
    const aluno = mockAlunos.find(a => a.id === parseInt(req.params.id));

    if (!aluno) {
      return res.status(404).json({ success: false, message: "Aluno não encontrado" });
    }

    // Devolvemos o aluno encontrado e dados fictícios da ficha
    res.status(200).json({
      success: true,
      data: {
        ...aluno,
        // Dados fictícios para a 'ficha-aluno.html'
        fichas: [
          { id: 10, data: "2024-10-20" },
          { id: 11, data: "2024-10-27" },
        ],
        evolucao: "Dados de evolução do aluno...",
      }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * @route   POST /api/alunos
 * @desc    Cadastra um novo aluno
 */
exports.createAluno = (req, res, next) => {
  try {
    // No mundo real, aqui guardaria os dados do req.body no banco de dados
    const novoAluno = {
      id: Math.floor(Math.random() * 1000), // ID aleatório
      nome: req.body.nome || "Novo Aluno",
      turma: req.body.turma || "C",
    };
    
    mockAlunos.push(novoAluno); // Adicionamos ao nosso mock
    
    res.status(201).json({
      success: true,
      message: "Aluno cadastrado com sucesso!",
      data: novoAluno,
    });
  } catch (error) {
    next(error);
  }
};

/**
 * @route   GET /api/alunos/pesquisar
 * @desc    Pesquisa alunos pelo nome
 */
exports.searchAlunos = (req, res, next) => {
    try {
        const query = req.query.q.toLowerCase();
        const resultados = mockAlunos.filter(a => 
            a.nome.toLowerCase().includes(query)
        );
        
        res.status(200).json({ success: true, data: resultados });
    } catch (error) {
        next(error);
    }
};

/**
 * @route   GET /api/alunos/:id/relatorio
 * @desc    Gera um relatório fictício
 */
exports.getRelatorioAluno = (req, res, next) => {
    try {
        const aluno = mockAlunos.find(a => a.id === parseInt(req.params.id));
        if (!aluno) {
          return res.status(404).json({ success: false, message: "Aluno não encontrado" });
        }
        
        // Dados fictícios do relatório
        const relatorio = `
            Relatório de Desempenho
            Aluno: ${aluno.nome}
            Turma: ${aluno.turma}
            ---
            - Desempenho em Matemática: Bom
            - Desempenho em Português: A melhorar
            - Frequência: 95%
        `;
        
        res.status(200).json({ success: true, data: { relatorioTexto: relatorio } });
    } catch (error) {
        next(error);
    }
};