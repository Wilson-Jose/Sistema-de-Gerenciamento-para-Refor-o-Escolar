// Este controlador gere a lógica de autenticação.
// Por agora, apenas simulamos (mock) as respostas.

/**
 * @route   POST /api/logar
 * @desc    Autentica o professor
 */
exports.login = (req, res, next) => {
  try {
    // 1. Obtemos os dados do corpo do pedido (frontend)
    const { email_Professor, senha_Professor } = req.body;

    // 2. Verificação simples (mock)
    if (!email_Professor || !senha_Professor) {
      // 400 = Bad Request (Pedido Inválido)
      return res.status(400).json({
        success: false,
        message: 'Email e senha são obrigatórios.',
      });
    }

    // 3. Simulação de sucesso
    // No mundo real, aqui você verificaria a senha no banco de dados
    if (senha_Professor === '1234') {
      // 200 = OK
      res.status(200).json({
        success: true,
        message: 'Login bem-sucedido!',
        // No futuro, aqui iria um token JWT
        token: 'jwt-mock-token-123456', 
        nome: email_Professor, // Devolvemos o nome para o "Bem Vindo(a)"
      });
    } else {
      // 401 = Unauthorized (Não Autorizado)
      res.status(401).json({
        success: false,
        message: 'Credenciais inválidas. Tente senha "1234"',
      });
    }
  } catch (error) {
    // Se algo inesperado acontecer, passamos para o gestor de erros
    next(error);
  }
};

/**
 * @route   POST /api/cadastroProf
 * @desc    Regista um novo professor
 */
exports.register = (req, res, next) => {
  try {
    // Simplesmente simulamos que o registo foi um sucesso
    const { email_Professor } = req.body;
    
    // 201 = Created (Criado)
    res.status(201).json({
      success: true,
      message: `Professor ${email_Professor} cadastrado com sucesso!`,
    });
  } catch (error) {
    next(error);
  }
};