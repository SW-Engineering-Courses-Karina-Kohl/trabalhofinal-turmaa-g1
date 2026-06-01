package br.edu.ufrgs.model;

public class Prescricao {
    private String id_prescricao;
    private String id_paciente;
    private String medicamento;
    private String dosagem_mg;
    private double peso_paciente;

    public Prescricao (String idPrescricao, String idPaciente, String medicamento, String dosagem_mg, double peso_paciente) {
        this.id_prescricao = idPrescricao;
        this.id_paciente = idPaciente;
        this.medicamento = medicamento;
        this.dosagem_mg = dosagem_mg;
        this.peso_paciente = peso_paciente;
    }

    public String getIdPrescricao() {
        return id_prescricao;
    }

    public String getIdPaciente() {
        return id_paciente;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public String getDosagem_mg() {
        return dosagem_mg;
    }

    public double getPeso_paciente() {
        return peso_paciente;
    }

}