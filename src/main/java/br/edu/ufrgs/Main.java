package br.edu.ufrgs;
import br.edu.ufrgs.model.Paciente;
import br.edu.ufrgs.model.Medicamento;
import br.edu.ufrgs.model.Prescricao;


public class Main {
    public static void main(String[] args) {
        Paciente joao = new Paciente("125489625", 78.5);
        Medicamento paracetamol = new Medicamento("Paracetamol", 500);
        Prescricao presc1 = new Prescricao("presc001", joao);
        presc1.adicionarMedicamento(paracetamol);
        System.out.println("Paciente CPF: " + joao.getCpf());
        System.out.println("Paciente Peso: " + joao.getPesoKG() + " kg");
        System.out.println("Paciente está com baixo peso? " + (joao.baixoPeso() ? "Sim" : "Não"));
        System.out.println("Medicamentos da prescrição:");
        for (Medicamento med : presc1.getMedicamentos()) {
            System.out.println("- " + med.getNomeMed() + " (" + med.getDosagemMg() + " mg)");
        }
    }
}
