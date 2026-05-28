package br.edu.ufrgs.model;
import java.util.ArrayList;
import java.util.List;

public class Prescricao {
    private String idPrescricao;
    private Paciente paciente;
    private boolean comRisco; 
    private List<Medicamento> medicamentos;

    public Prescricao (String idPrescricao, Paciente paciente) {
        this.idPrescricao = idPrescricao;
        this.paciente = paciente;
        this.medicamentos = new ArrayList<>();
    }

    public String getIdPrescricao() {
        return idPrescricao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public boolean isComRisco() {
        return comRisco;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void adicionarMedicamento(Medicamento med) {
        medicamentos.add(med);
    }
}