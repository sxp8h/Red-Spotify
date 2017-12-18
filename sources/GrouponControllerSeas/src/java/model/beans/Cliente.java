
package model.beans;

import java.util.ArrayList;

public class Cliente {
    private int idUser;
    private String email;
    private String password;

    public Cliente() {
        
    }

    public Cliente(int idUser, String email, String password) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    @Override
    public String toString() {
        return "Cliente{" + "idUser=" + idUser + ", email=" + email + ", password=" + password + '}';
    }
    
    public static String toArrayJSon(ArrayList<Cliente> 
            listUser){
        String resp = "";
        int i = 0 ;
        resp+="[";
        for (Cliente cliente : listUser) {
            resp+="{";
            resp+= "'ID_USUARIO'";
            resp+= ":";
            resp+=cliente.getIdUser();
            resp+=",";
            resp+= "'EMAIL'";
            resp+= ":";
            resp+="'" + cliente.getEmail() + "'";
            resp+=",";
            resp+= "'PASSWORD'";
            resp+= ":";
            resp+="'" + cliente.getPassword() + "'";
            resp+="}";
            resp += (i+1==listUser.size())?"":",";
            i++;
        }
        resp+="]";
        
        return resp;
    }
}
