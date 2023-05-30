package ua.com.alevel.persistence.repository.account;

import ua.com.alevel.persistence.entity.account.Account;

import ua.com.alevel.persistence.repository.BaseRepository;

public interface AccountRepository extends BaseRepository<Account> {
    Account findByIban(String iban);
}
