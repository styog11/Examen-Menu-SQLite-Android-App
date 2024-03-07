public class Client {
    int id;
    String nom;
    String email;
    String password ;
     public Client(int id,String nom,String email , String password){
         this.id=id;
         this.nom=nom;
         this.email=email;
         this.password=password;
     }

    @Override
    public String toString() {
        return "id: "+id +"nom: "+nom +"email: "+email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
