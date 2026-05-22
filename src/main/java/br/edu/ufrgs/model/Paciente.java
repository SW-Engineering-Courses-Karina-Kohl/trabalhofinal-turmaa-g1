package br.edu.ufrgs.model;

public class Paciente {
    private String cpf;
    private double pesoKG;

    public Paciente(String cpf, double pesoKG) {
        this.cpf = cpf;
        this.pesoKG = pesoKG;
    }

    public String getCpf() {
        return cpf;
    }

    public double getPesoKG() {
        return pesoKG;
    }
}

