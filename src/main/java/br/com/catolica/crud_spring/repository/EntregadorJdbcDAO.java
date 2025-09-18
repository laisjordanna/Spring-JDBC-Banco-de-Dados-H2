package br.com.catolica.crud_spring.repository;

import br.com.catolica.crud_spring.model.Entregador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class EntregadorJdbcDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ContatoJdbcDAO contatoJdbcDAO;

    private final RowMapper<Entregador> entregadorMapper = new RowMapper<>() {
        @Override
        public Entregador mapRow(ResultSet rs, int rowNum) throws SQLException {
            Entregador entregador = new Entregador();
            entregador.setId(rs.getInt("e_id"));
            entregador.setNome(rs.getString("e_nome"));
            entregador.setContatoId(rs.getInt("contato_id"));
            return entregador;
        }
    };

    public List<Entregador> findAll() {
        String sql = "SELECT id as e_id, nome as e_nome, contato_id FROM entregador";
        return jdbcTemplate.query(sql, entregadorMapper);
    }

    public Entregador findById(int id) {
        String sql = "SELECT id as e_id, nome as e_nome, contato_id FROM entregador WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entregadorMapper, id);
    }

    public Entregador save(Entregador entregador) {
        String sql = "INSERT INTO entregador (nome, contato_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entregador.getNome());
            ps.setInt(2, entregador.getContatoId());
            return ps;
        }, keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        entregador.setId(id);
        return entregador;
    }

    public int update(int id, Entregador entregador) {
        String sql = "UPDATE entregador SET nome = ?, contato_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                entregador.getNome(),
                entregador.getContatoId(),
                id);
    }

    public int delete(int id) {
        String sql = "DELETE FROM entregador WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}