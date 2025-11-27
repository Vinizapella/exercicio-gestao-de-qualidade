package org.example.service.equipamento;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;
import org.example.repository.EquipamentoRepositoryImp;

import java.sql.SQLException;

public class EquipamentoServiceImpl implements EquipamentoService{

    EquipamentoRepository repository = new EquipamentoRepositoryImp();

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {

        equipamento.setStatusOperacional("OPERACIONAL");

        return repository.save(equipamento);
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        if (id == null) {
            throw new RuntimeException("ID inválido!");
        }

        Equipamento equipamento = repository.findByID(id);

        if (equipamento == null) {
            throw new RuntimeException("Equipamento não encontrado!");
        }
        return equipamento;
    }

}
