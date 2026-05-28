package br.edu.ufrgs.model;

public class Medicamento {
    private String nomeMed;
    private double dosagemMg;

    public Medicamento (String nomeMed, double dosagemMg) {

        if (nomeMed == null || nomeMed.isEmpty()) {
            throw new IllegalArgumentException("Nome do medicamento não pode ser vazio.");
        }

        if (dosagemMg <= 0) {
            throw new IllegalArgumentException("Dosagem do medicamento deve ser um valor positivo.");
        }

        this.nomeMed = nomeMed;
        this.dosagemMg = dosagemMg;
    }

    public String getNomeMed() {
        return nomeMed;
    }

    public double getDosagemMg() {
        return dosagemMg;
    }

}