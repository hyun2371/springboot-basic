package com.prgrms.voucher_manage.util;

import com.prgrms.voucher_manage.domain.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomerFileManager {

    private String path = System.getProperty("user.dir");

    private final Map<UUID, Customer> customerStorage = new ConcurrentHashMap<>();

    public CustomerFileManager(@Value("${file-path.customer}") String path) {
        this.path += path;
    }

    public Map<UUID, Customer> loadCustomerData(){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Customer customer = new Customer(UUID.fromString(data[0]), data[1]);
                customerStorage.put(UUID.fromString(data[0]), customer);
            }
        }  catch (Exception e){
            System.out.println(e.getMessage());
        }
        return customerStorage;
    }

}
