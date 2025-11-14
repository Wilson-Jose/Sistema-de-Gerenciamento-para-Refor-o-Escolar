/**
 * Middleware de gestão de erros.
 * Qualquer rota que chame 'next(error)' será processada aqui.
 */
const errorHandler = (err, req, res, next) => {
  console.error("⛔ OCORREU UM ERRO:", err.stack);

  // Resposta padronizada de erro
  res.status(err.statusCode || 500).json({
    success: false,
    message: err.message || "Ocorreu um erro interno no servidor.",
  });
};

module.exports = errorHandler;