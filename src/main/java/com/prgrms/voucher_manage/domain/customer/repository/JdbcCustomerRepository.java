package com.prgrms.voucher_manage.domain.customer.repository;

import com.prgrms.voucher_manage.domain.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.prgrms.voucher_manage.base.ErrorMessage.CUSTOMER_NOT_EXIST;
import static com.prgrms.voucher_manage.base.ErrorMessage.CUSTOMER_UPDATE_FAILED;
import static com.prgrms.voucher_manage.domain.customer.entity.CustomerType.matchTypeByString;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcCustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {
        String sql = "select * from customer";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Customer findById(UUID id) {
        String sql = "select * from customer where customer_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id.toString());
        } catch (Exception e){
            throw new RuntimeException(CUSTOMER_NOT_EXIST.getMessage());
        }
    }

    public Customer findByName(String name) {
        String sql = "select * from customer where name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, name);
        } catch (Exception e) {
            throw new RuntimeException(CUSTOMER_NOT_EXIST.getMessage());
        }
    }

    public List<Customer> findByType(String type) {
        String sql = "select * from customer where type = ?";
        return jdbcTemplate.query(sql, rowMapper, type);
    }

    public Customer save(Customer customer) {
        String sql = "insert into customer(customer_id, name, type) values (?, ?, ?)";
        jdbcTemplate.update(sql, customer.getId().toString(), customer.getName(), customer.getType().getData());
        return customer;
    }

    public void update(Customer customer) {
        String sql = "update customer set name = ? where customer_id = ?";
        try {
            jdbcTemplate.update(sql, customer.getName(), customer.getId().toString());
        } catch (Exception e) {
            throw new RuntimeException(CUSTOMER_UPDATE_FAILED.getMessage());
        }
    }

    private static final RowMapper<Customer> rowMapper = (resultSet, i) -> {
        UUID customerId = UUID.fromString(resultSet.getString("customer_id"));
        String name = resultSet.getString("name");
        String type = resultSet.getString("type");
        return new Customer(customerId, name, matchTypeByString(type));
    };
}
