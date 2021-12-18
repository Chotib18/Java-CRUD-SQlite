package com.example.crudsqlitedasar;

public class User {
    private Integer idUser;
    private String namaUser;
    private String email;
    private String password;
    private String nomorkontak;
    private String foto;

    //membuat konstraktor tanpa parameter
    public User() {
    }

    //membuat kontraktor dengan parameter
    public User(Integer idUser, String namaUser, String email, String password, String nomorkontak, String foto) {
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.email = email;
        this.password = password;
        this.nomorkontak = nomorkontak;
        this.foto = foto;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
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

    public String getNomorkontak() {
        return nomorkontak;
    }

    public void setNomorkontak(String nomorkontak) {
        this.nomorkontak = nomorkontak;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
