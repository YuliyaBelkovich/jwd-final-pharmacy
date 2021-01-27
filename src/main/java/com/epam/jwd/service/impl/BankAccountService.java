package com.epam.jwd.service.impl;

import com.epam.jwd.criteria.BankAccountCriteria;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.impl.BankAccountDao;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.domain.BankAccount;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.BankAccountFactory;
import com.epam.jwd.service.EntityService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class BankAccountService  implements EntityService<BankAccount> {

    private static BankAccountService bankAccountService;

    private BankAccountService(){}

    public static BankAccountService getInstance() {
        if (bankAccountService == null) {
            return bankAccountService = new BankAccountService();
        } else return bankAccountService;
    }

    @Override
    public EntityDao<BankAccount> getEntityDao() {
        EntityDao<BankAccount> dao = BankAccountDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<BankAccount> getEntityCache() {
        return getApplicationContext().getCache(BankAccount.class);
    }

    @Override
    public EntityFactory<BankAccount> getEntityFactory() {
        return BankAccountFactory.getInstance();
    }

    @Override
    public List<BankAccount> findInCashByCriteria(Criteria<BankAccount> criteria) {
        BankAccountCriteria bankAccountCriteria = (BankAccountCriteria) criteria;
       return getEntityCache()
                .stream()
                .filter(bankAccount -> {
                    int id = bankAccountCriteria.getId();
                    return id==0 || id == bankAccount.getId();
                })
                .filter(bankAccount -> {
                    int id = bankAccountCriteria.getPatientId();
                    return id==0 ||  id == bankAccount.getPatientId();
                })
                .filter(bankAccount -> {
                    String IBAN = bankAccountCriteria.getIBAN();
                    return IBAN == null || bankAccount.getIBAN().equals(IBAN);
                })
                .filter(bankAccount -> {
                    String cardHolder = bankAccount.getCardHolder();
                    return cardHolder == null || bankAccount.getCardHolder().equals(cardHolder);
                })
                .collect(Collectors.toList());
    }
}
