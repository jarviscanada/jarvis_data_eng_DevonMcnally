package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.dao.domain.QuoteRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class QuoteDao implements CrudRepository<QuoteRow, String> {

    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    //private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
    }

    @Override
    public QuoteRow  save(QuoteRow quoteRow) {
        if(existsById(quoteRow.getId())){
            int updatedRowNo = updateOne(quoteRow);
            if(updatedRowNo != 1){
                throw new DataRetrievalFailureException("Unable to update quote");

            }
            else {
                addOne(quoteRow);
            }
        }

        return quoteRow;
    }
    private void addOne(QuoteRow quoteRow) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quoteRow);
        int row = simpleJdbcInsert.execute(parameterSource);

        if(row != 1){
            throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
        }
    }

    private int updateOne(QuoteRow quoteRow) {

        String update_sql = "UPDATE quote SET last_price=?, bid_price=?, " +
                "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";

        return jdbcTemplate.update(update_sql, makeUpdateValues(quoteRow));



    }

    private Object[] makeUpdateValues(QuoteRow quoteRow) {

        return null;
    }


    @Override
    public <S extends QuoteRow> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<QuoteRow> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<QuoteRow> findAll() {
        return null;
    }

    @Override
    public Iterable<QuoteRow> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(QuoteRow quoteRow) {

    }

    @Override
    public void deleteAll(Iterable<? extends QuoteRow> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
