package org.example.repository;

import org.example.database.Conexao;
import org.example.model.Equipamento;

import java.sql.*;

public class EquipamentoRepositoryImp implements EquipamentoRepository{

    @Override
    public Equipamento save(Equipamento equipamento) throws SQLException{
        String command = """
                INSERT INTO     
                Equipamento
                (nome, numeroDeSerie, areaSetor, statusOperacional)
                VALUES
                (?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, equipamento.getStatusOperacional());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                equipamento.setId(rs.getLong(1));
            }
        }
        return equipamento;
    }

    public Equipamento findByID(long id) throws SQLException{
        String command = """
                SELECT 
                nome,
                numeroDeSerie,
                areaSetor,
                statusOperacional 
                FROM
                Equipamento
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                String nome = rs.getString("nome");
                String numeroDeSerie = rs.getString("numeroDeSerie");
                String areaSetor = rs.getString("areaSetor");
                String statusOperacional = rs.getString("statusOperacional");

                return new Equipamento(id,nome,numeroDeSerie,areaSetor,statusOperacional);
            }
        }
        return null;
    }

}
