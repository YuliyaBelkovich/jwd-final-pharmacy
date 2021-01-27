package com.epam.jwd.command.impl.entity.bank;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.BankAccount;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.BankAccountService;

public class AddBankAccountCommand implements Command {
    private static final ResponseContext ADD_BANK_ACCOUNT_RESULT = () -> "/pharmacy?command=go_to_add_bank_account_page";

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        int patientId;
        String iban;
        String cardHolder;
        String expiryDate;
        int cvv;

        if (requestContext.hasParameter("bank_account_patient") && requestContext.hasParameter("bank_account_iban") &&
                requestContext.hasParameter("bank_account_holder") && requestContext.hasParameter("bank_account_date") &&
                requestContext.hasParameter("bank_account_cvv")) {
            patientId = Integer.parseInt(requestContext.getParameter("bank_account_patient"));
            iban = requestContext.getParameter("bank_account_iban");
            cardHolder = requestContext.getParameter("bank_account_holder").toUpperCase();
            expiryDate = requestContext.getParameter("bank_account_date");
            cvv = Integer.parseInt(requestContext.getParameter("bank_account_cvv"));
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return ADD_BANK_ACCOUNT_RESULT;
        }

        BankAccount bankAccount;
        try {
            bankAccount = BankAccountService.getInstance().createEntity(0, patientId, iban, cardHolder, expiryDate, cvv);
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.setAttribute("Error", e.getMessage());
            return ADD_BANK_ACCOUNT_RESULT;
        }

        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Bank account created!\n" + bankAccount.toString());
        return ADD_BANK_ACCOUNT_RESULT;
    }
}
