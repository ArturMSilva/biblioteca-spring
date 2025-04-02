package br.com.biblioteca.services;

import br.com.biblioteca.entities.EmprestimoEntity;
import br.com.biblioteca.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MultaService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private EmailService emailService;

    public void processarMultas() {
        LocalDate dataAtual = LocalDate.now();
        List<EmprestimoEntity> emprestimosVencidos = emprestimoRepository.findByDataDevolucaoBeforeAndStatusNot(
                dataAtual, EmprestimoEntity.StatusEmprestimo.FINALIZADO);

        for (EmprestimoEntity emprestimo : emprestimosVencidos) {
            long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataDevolucao(), dataAtual);
            double multa = calcularMulta(diasAtraso);

            String destinatario = emprestimo.getUsuario().getEmail();
            String assunto = "Notificação de Atraso no Empréstimo";
            String mensagem = "Você está com " + diasAtraso + " dias de atraso. Multa: R$ " + multa;
            emailService.enviarEmail(destinatario, assunto, mensagem);

            if (emprestimo.getStatus() != EmprestimoEntity.StatusEmprestimo.ATRASADO) {
                emprestimo.setStatus(EmprestimoEntity.StatusEmprestimo.ATRASADO);
                emprestimoRepository.save(emprestimo);
            }
        }
    }

    private double calcularMulta(long diasAtraso) {
        double valorPorDia = 2.0; 
        return diasAtraso * valorPorDia;
    }

    @Scheduled(cron = "0 0 8 * * *") 
    public void executarProcessamentoDeMultas() {
        processarMultas();
    }
}