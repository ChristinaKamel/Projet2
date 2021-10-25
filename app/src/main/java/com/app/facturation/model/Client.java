package com.app.facturation.model;

public class Client {

    private String nomClient;
    private String telephone;
    private String email;
    private String adresse;

    public Client() {}

    public Client(String nomClient, String telephone, String email, String adresse) {
        this.nomClient = nomClient;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nomClient='" + nomClient + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
