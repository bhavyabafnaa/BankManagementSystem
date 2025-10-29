package bank.persistence;

import bank.domain.BankAccount;
import java.util.List;

public interface AccountRepository {
    void save(BankAccount account) throws Exception;
    BankAccount findById(String name) throws Exception;
    List<BankAccount> findAll() throws Exception;
    void update(BankAccount account) throws Exception;
    BankAccount delete(String name) throws Exception;
}
