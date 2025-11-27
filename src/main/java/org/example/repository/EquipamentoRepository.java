package org.example.repository;

import org.example.model.Equipamento;

import java.sql.SQLException;

public interface EquipamentoRepository {

    Equipamento save(Equipamento equipamento) throws SQLException;

    Equipamento findByID(long id) throws SQLException;

}
