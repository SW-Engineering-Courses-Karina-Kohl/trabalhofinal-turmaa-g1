package br.edu.ufrgs.model;

public class Paciente {
    private String cpf;
    private double pesoKG;

    public Paciente(String cpf, double pesoKG) {

        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        if (pesoKG <= 0) {
            throw new IllegalArgumentException("Peso em KG deve ser um valor positivo.");
        }
        
        this.cpf = cpf;
        this.pesoKG = pesoKG;
    }

    public String getCpf() {
        return cpf;
    }

    public double getPesoKG() {
        return pesoKG;
    }

    public boolean baixoPeso() {
        return pesoKG < 20;
    }
}

