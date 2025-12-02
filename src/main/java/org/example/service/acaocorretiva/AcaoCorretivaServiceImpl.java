package org.example.service.acaocorretiva;

import org.example.model.AcaoCorretiva;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepositorio;
import org.example.repository.EquipamentoRepositorio;
import org.example.repository.FalhaRepositorio;
import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{
    FalhaRepositorio falhaRepositorio = new FalhaRepositorio();

    EquipamentoRepositorio equipamentoRepositorio = new EquipamentoRepositorio();

    AcaoCorretivaRepositorio acaoRepositorio = new AcaoCorretivaRepositorio();

    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {
        Falha falha = falhaRepositorio.buscarFalhaPorId(acao.getFalhaId());

        if(falha == null){
            throw new RuntimeException("Falha não encontrada!");
        }

        acao = acaoRepositorio.criarAcaoCorretiva(acao);

        falhaRepositorio.atualizarStatus(acao.getFalhaId(), "RESOLVIDA");

        if(falha.getCriticidade().equals("CRITICA")){
            equipamentoRepositorio.atualizarStatus(falha.getEquipamentoId(), "OPERACIONAL");
        }

        if(acao.getId() == null){
            throw new RuntimeException("Erro ao salvar Ação no banco de dados!");
        }
        return acao;
    }
}