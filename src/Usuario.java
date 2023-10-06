public class Usuario {

    private Integer id;
    private String usuario;
    private String senha;
    private Integer idade;

    public Usuario(Integer id, String usuario, String senha, Integer idade) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "\n ID: " + id +
                "| Usuario: " + usuario +
                "| Senha: " + senha +
                "| Idade: " + idade;
    }
}
