package nossagrana.usuario.entity;

public class UsuarioJaExisteException extends RuntimeException {
    private static final long serialVersionUID = 1149241039409861914L;

    public UsuarioJaExisteException(String mensagem) {
        super(mensagem);
    }
}
