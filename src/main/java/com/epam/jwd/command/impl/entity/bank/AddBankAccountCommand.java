package com.epam.jwd.command.impl.entity.bank;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.BankAccount;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.impl.BankAccountService;

public class AddBankAccountCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        int patientId;
        String iban;
        String cardHolder;
        String expiryDate;
        int cvv;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("bank_account_patient") && requestContext.hasParameter("bank_account_iban") &&
                requestContext.hasParameter("bank_account_holder") && requestContext.hasParameter("bank_account_date") &&
                requestContext.hasParameter("bank_account_cvv")) {
            patientId = Integer.parseInt(requestContext.getParameter("bank_account_patient"));
            iban = requestContext.getParameter("bank_account_iban");
            cardHolder = requestContext.getParameter("bank_account_holder").toUpperCase();
            expiryDate = requestContext.getParameter("bank_account_date");
            cvv = Integer.parseInt(requestContext.getParameter("bank_account_cvv"));
        } else {
           return  () -> url + "&error=Missing+mandatory+field";
        }

        BankAccount bankAccount;
        try {
            bankAccount = BankAccountService.getInstance().createEntity(0, patientId, iban, cardHolder, expiryDate, cvv);
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            return () -> url + "&error=" + e.getMessage().replace(" ", "+");
        }

        return ()-> url+"&message=Bank+account+created!";
    }
}
