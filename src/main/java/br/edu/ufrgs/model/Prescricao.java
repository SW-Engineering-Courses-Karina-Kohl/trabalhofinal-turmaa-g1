package br.edu.ufrgs.model;

public class Prescricao {
    private String id_prescricao;
    private String id_paciente;
    private String medicamento;
    private String dosagem_mg;
    private double peso_paciente;
    private boolean alerta_seguranca = false;
    private String motivo_alerta = "Nnenhum risco detectado";
    private String alergia; // Campo para alergias do paciente


    public Prescricao (String idPrescricao, String idPaciente, String medicamento, String dosagem_mg, double peso_paciente, String alergia) {
        this.id_prescricao = idPrescricao;
        this.id_paciente = idPaciente;
        this.medicamento = medicamento;
        this.dosagem_mg = dosagem_mg;
        this.peso_paciente = peso_paciente;
        this.alergia = alergia;
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
    public boolean isAlerta_seguranca() {
        return alerta_seguranca;
    }

    public String getMotivo_alerta() {
        return motivo_alerta;
    }
    
    public String getAlergia() {
        return alergia;
    }

    public void ativarAlerta(String motivo) {
        this.alerta_seguranca = true;
        this.motivo_alerta = motivo;
    } /// Método para ativar o alerta de segurança e definir o motivo do alerta
}