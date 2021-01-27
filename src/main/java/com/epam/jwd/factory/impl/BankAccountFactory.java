package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.BankAccount;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.EntityFactory;

public class BankAccountFactory implements EntityFactory<BankAccount> {

    private static BankAccountFactory bankAccountFactory = new BankAccountFactory();

    private BankAccountFactory (){}

    public static BankAccountFactory getInstance(){
        return bankAccountFactory;
    }
    @Override
    public BankAccount create(Object... args) throws FactoryException {
        BankAccount bankAccount;
        try{
            bankAccount = new BankAccount((int)args[0],
                    (int) args[1],
                    (String) args[2],
                    (String) args[3],
                    (String) args[4],
                    (int) args[5]);
        } catch (ClassCastException e){
            throw new FactoryException("Wrong arguments during the creation of the BankAccount object");
        }
        return bankAccount;
    }
}
