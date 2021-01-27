package com.epam.jwd.command.impl.entity.bank;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.PageName;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.criteria.BankAccountCriteria;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.BankAccount;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.impl.BankAccountService;

import java.util.List;

public class SearchBankAccountCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        int patientId = 0;
        String iban = null;
        String cardHolder = null;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("bank_account_id")) {
            id = Integer.parseInt(requestContext.getParameter("bank_account_id"));
        }
        if (requestContext.hasParameter("bank_account_patient")) {
            patientId = Integer.parseInt(requestContext.getParameter("bank_account_patient"));
        }
        if (requestContext.hasParameter("bank_account_iban")) {
            iban = requestContext.getParameter("bank_account_iban");
        }
        if (requestContext.hasParameter("bank_account_holder")) {
            cardHolder = requestContext.getParameter("bank_account_holder");
        }


        Criteria<BankAccount> criteria = BankAccountCriteria.builder()
                .id(id)
                .setPatientId(patientId)
                .setIBAN(iban)
                .setCardHolder(cardHolder)
                .build();

        List<BankAccount> bankAccounts;

        try {
            bankAccounts = BankAccountService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "Bank account not found");
            return () -> url;
        }
        requestContext.setAttribute("bankAccount", bankAccounts);
        return () -> url;
    }
}
