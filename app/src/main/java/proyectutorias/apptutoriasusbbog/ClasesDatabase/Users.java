package proyectutorias.apptutoriasusbbog.ClasesDatabase;

/**
 * Created by LOPEZPALACIOS on 25/09/2017.
 */

public class Users {

    private int id_usuario;
    private String nombre_usuario;
    private String email_usuario;
    private String perfil_usuario;
    private String bio_usuario;
    private String categorias_usuario;
    private String Nombre;

    public Users(int id_usuario, String nombre_usuario, String email_usuario, String perfil_usuario, String bio_usuario, String categorias_usuario, String nombre) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.email_usuario = email_usuario;
        this.perfil_usuario = perfil_usuario;
        this.bio_usuario = bio_usuario;
        this.categorias_usuario = categorias_usuario;
        this.Nombre =nombre;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getPerfil_usuario() {
        return perfil_usuario;
    }

    public void setPerfil_usuario(String perfil_usuario) {
        this.perfil_usuario = perfil_usuario;
    }

    public String getBio_usuario() {
        return bio_usuario;
    }

    public void setBio_usuario(String bio_usuario) {
        this.bio_usuario = bio_usuario;
    }

    public String getCategorias_usuario() {
        return categorias_usuario;
    }

    public void setCategorias_usuario(String categorias_usuario) {
        this.categorias_usuario = categorias_usuario;
    }

    public String getNombre() {
        return Nombre;
    }
}
