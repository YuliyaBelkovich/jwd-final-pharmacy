package com.epam.jwd.command;

import com.epam.jwd.command.impl.*;
import com.epam.jwd.command.impl.auth.LogInCommand;
import com.epam.jwd.command.impl.auth.LogOutCommand;
import com.epam.jwd.command.impl.auth.RegisterCommand;
import com.epam.jwd.command.impl.entity.DeleteEntityCommand;
import com.epam.jwd.command.impl.entity.StartCommand;
import com.epam.jwd.command.impl.entity.appointment.*;
import com.epam.jwd.command.impl.entity.bank.AddBankAccountCommand;
import com.epam.jwd.command.impl.entity.bank.SearchBankAccountCommand;
import com.epam.jwd.command.impl.entity.medicine.*;
import com.epam.jwd.command.impl.entity.order.AddOrderCommand;
import com.epam.jwd.command.impl.entity.order.SearchOrderCommand;
import com.epam.jwd.command.impl.entity.payment.AddPaymentCommand;
import com.epam.jwd.command.impl.entity.payment.SearchPaymentCommand;
import com.epam.jwd.command.impl.entity.recipe.*;
import com.epam.jwd.command.impl.entity.user.SearchUserCommand;
import com.epam.jwd.command.impl.entity.user.UpdateUserCommand;
import com.epam.jwd.command.impl.logic.AddToBasketCommand;
import com.epam.jwd.command.impl.logic.RemoveFromBasketCommand;
import com.epam.jwd.domain.Role;
import com.epam.jwd.service.impl.*;

import java.util.Arrays;
import java.util.List;

public enum CommandName {
    START("start", new StartCommand(), CommandType.FORWARD),
    GO_TO_MAIN_PAGE("go_to_main_page", new GoToCommand(PageName.MAIN_PAGE), CommandType.FORWARD),
    GO_TO_BASKET_PAGE("go_to_basket_page", new GoToCommand(PageName.BASKET_PAGE), CommandType.FORWARD, Role.PATIENT, Role.GUEST),
    GO_TO_LOGIN_PAGE("go_to_login_page", new GoToCommand(PageName.LOG_IN_PAGE), CommandType.FORWARD, Role.GUEST),
    GO_TO_REGISTER_PAGE("go_to_register_page", new GoToCommand(PageName.REGISTER_PAGE), CommandType.FORWARD, Role.GUEST),
    GO_TO_SEARCH_MEDICINE_PAGE("go_to_search_medicine_page", new GoToCommand(PageName.SEARCH_MEDICINE_PAGE), CommandType.FORWARD),
    GO_TO_USER_MAIN_PAGE("go_to_user_main_page", new GoToCommand(PageName.DOCTOR_HOME), CommandType.FORWARD, Role.PATIENT, Role.DOCTOR, Role.PHARMACIST),
    GO_TO_ADD_MEDICINE_PAGE("go_to_add_medicine_page", new GoToCommand(PageName.ADD_MEDICINE_PAGE), CommandType.FORWARD, Role.PHARMACIST),
    GO_TO_SEARCH_DOCTOR_PAGE("go_to_search_doctor_page", new GoToCommand(PageName.SEARCH_DOCTOR_PAGE), CommandType.FORWARD, Role.DOCTOR, Role.PATIENT, Role.PHARMACIST),
    GO_TO_SEARCH_PATIENT_PAGE("go_to_search_patient_page", new GoToCommand(PageName.SEARCH_PATIENT_PAGE), CommandType.FORWARD, Role.DOCTOR, Role.PHARMACIST),
    GO_TO_SEARCH_APPOINTMENT_PAGE("go_to_search_appointment_page", new GoToCommand(PageName.SEARCH_APPOINTMENT_PAGE), CommandType.FORWARD, Role.DOCTOR, Role.PHARMACIST, Role.PATIENT),
    GO_TO_SEARCH_APPOINTMENT_WINDOW_PAGE("go_to_search_appointment_window_page", new GoToCommand(PageName.SEARCH_APPOINTMENT_WINDOW_PAGE), CommandType.FORWARD, Role.DOCTOR),
    GO_TO_SEARCH_BANK_ACCOUNT_PAGE("go_to_search_bank_account_page", new GoToCommand(PageName.SEARCH_BANK_ACCOUNT), CommandType.FORWARD, Role.PATIENT),
    GO_TO_SEARCH_RECIPE_PAGE("go_to_search_recipe_page", new GoToCommand(PageName.SEARCH_RECIPE_PAGE), CommandType.FORWARD, Role.DOCTOR, Role.PATIENT, Role.PHARMACIST),
    GO_TO_SEARCH_RECIPE_REQUEST_PAGE("go_to_search_recipe_request_page", new GoToCommand(PageName.SEARCH_RECIPE_REQUEST), CommandType.FORWARD, Role.DOCTOR, Role.PATIENT),
    GO_TO_SEARCH_PAYMENT_PAGE("go_to_search_payment_page", new GoToCommand(PageName.SEARCH_PAYMENT_PAGE), CommandType.FORWARD, Role.PHARMACIST),
    GO_TO_SEARCH_ORDER_PAGE("go_to_search_order_page", new GoToCommand(PageName.SEARCH_ORDER_PAGE), CommandType.FORWARD, Role.PATIENT, Role.PHARMACIST),
    GO_TO_ADD_APPOINTMENT_PAGE("go_to_add_appointment_page", new GoToCommand(PageName.ADD_APPOINTMENT_PAGE), CommandType.FORWARD, Role.PATIENT, Role.DOCTOR),
    GO_TO_ADD_APPOINTMENT_WINDOW_PAGE("go_to_add_appointment_window_page", new GoToCommand(PageName.ADD_APPOINTMENT_WINDOW_PAGE), CommandType.FORWARD, Role.DOCTOR),
    GO_TO_ADD_BANK_ACCOUNT_PAGE("go_to_add_bank_account_page", new GoToCommand(PageName.ADD_BANK_ACCOUNT_PAGE), CommandType.FORWARD, Role.PATIENT),
    GO_TO_ADD_RECIPE_PAGE("go_to_add_recipe_page", new GoToCommand(PageName.ADD_RECIPE_PAGE), CommandType.FORWARD, Role.DOCTOR),
    GO_TO_ADD_RECIPE_REQUEST_PAGE("go_to_add_recipe_request_page", new GoToCommand(PageName.ADD_RECIPE_REQUEST), CommandType.FORWARD, Role.PATIENT),
    GO_TO_ADD_PAYMENT_PAGE("go_to_add_payment_page", new GoToCommand(PageName.ADD_PAYMENT), CommandType.FORWARD, Role.PATIENT, Role.GUEST),
    GO_TO_PAYMENT_SUCCESS_PAGE("go_to_payment_success_page", new GoToCommand(PageName.PAYMENT_SUCCESS), CommandType.FORWARD, Role.PATIENT, Role.GUEST),

    SEARCH_MEDICINE("search_medicine", new SearchMedicineCommand(), CommandType.FORWARD),
    ADD_MEDICINE("add_medicine", new AddMedicineCommand(), CommandType.REDIRECT, Role.PHARMACIST),
    MEDICINE_PAGE("go_to_medicine_page", new EntityPageCommand<>(MedicineService.getInstance(), "medicine", PageName.MEDICINE_PAGE), CommandType.FORWARD),
    DELETE_MEDICINE("delete_medicine", new DeleteEntityCommand<>(MedicineService.getInstance(), "medicine"), CommandType.FORWARD, Role.PHARMACIST),
    UPDATE_MEDICINE("update_medicine", new UpdateMedicineCommand(), CommandType.REDIRECT, Role.PHARMACIST),

    SEARCH_APPOINTMENT("search_appointment", new SearchAppointmentCommand(), CommandType.FORWARD, Role.PHARMACIST, Role.DOCTOR, Role.PATIENT),
    APPOINTMENT_PAGE("go_to_appointment_page", new EntityPageCommand<>(AppointmentService.getInstance(), "appointment", PageName.APPOINTMENT_PAGE), CommandType.FORWARD, Role.PHARMACIST, Role.DOCTOR, Role.PATIENT),
    ADD_APPOINTMENT("add_appointment", new AddAppointmentCommand(), CommandType.REDIRECT, Role.DOCTOR, Role.PATIENT),
    UPDATE_APPOINTMENT("update_appointment", new UpdateAppointmentCommand(), CommandType.REDIRECT, Role.DOCTOR, Role.PATIENT),

    SEARCH_APPOINTMENT_WINDOW("search_window", new SearchAppointmentWindowCommand(), CommandType.FORWARD, Role.DOCTOR,Role.PATIENT),
    APPOINTMENT_WINDOW_PAGE("go_to_appointment_window_page", new EntityPageCommand<>(AppointmentWindowService.getInstance(), "window", PageName.APPOINTMENT_WINDOW_PAGE), CommandType.FORWARD, Role.DOCTOR),
    ADD_APPOINTMENT_WINDOW("add_window", new AddAppointmentWindowCommand(), CommandType.REDIRECT, Role.DOCTOR),
    UPDATE_APPOINTMENT_WINDOW("update_window", new UpdateAppointmentWindowCommand(), CommandType.REDIRECT, Role.DOCTOR),

    SEARCH_BANK_ACCOUNT("search_bank_account", new SearchBankAccountCommand(), CommandType.FORWARD, Role.PATIENT, Role.PHARMACIST),
    BANK_ACCOUNT_PAGE("go_to_bank_account_page", new EntityPageCommand<>(BankAccountService.getInstance(), "bank_account", PageName.BANK_ACCOUNT_PAGE), CommandType.FORWARD, Role.PATIENT, Role.PHARMACIST),
    ADD_BANK_ACCOUNT("add_bank_account", new AddBankAccountCommand(), CommandType.REDIRECT, Role.PATIENT),

    SEARCH_RECIPE("search_recipe", new SearchRecipeCommand(), CommandType.FORWARD, Role.PHARMACIST, Role.DOCTOR, Role.PATIENT),
    RECIPE_PAGE("go_to_recipe_page", new EntityPageCommand<>(RecipeService.getInstance(), "recipe", PageName.RECIPE_PAGE), CommandType.FORWARD, Role.PHARMACIST, Role.DOCTOR, Role.PATIENT),
    ADD_RECIPE("add_recipe", new AddRecipeCommand(), CommandType.REDIRECT, Role.DOCTOR),
    UPDATE_RECIPE("update_recipe", new UpdateRecipeCommand(), CommandType.REDIRECT, Role.DOCTOR),

    ADD_TO_BASKET("add_to_basket", new AddToBasketCommand(), CommandType.FORWARD, Role.GUEST, Role.PATIENT),
    REMOVE_FROM_BASKET("remove_from_basket", new RemoveFromBasketCommand(), CommandType.FORWARD, Role.GUEST, Role.PATIENT),

    SEARCH_PAYMENT("search_payment", new SearchPaymentCommand(), CommandType.FORWARD, Role.PHARMACIST),
    PAYMENT_PAGE("go_to_payment_page", new EntityPageCommand<>(PaymentService.getInstance(), "payment", PageName.PAYMENT_PAGE), CommandType.FORWARD, Role.PHARMACIST),
    ADD_PAYMENT("add_payment", new AddPaymentCommand(), CommandType.REDIRECT, Role.PATIENT, Role.GUEST),

    SEARCH_ORDER("search_order", new SearchOrderCommand(), CommandType.FORWARD, Role.PATIENT, Role.DOCTOR),
    ADD_ORDER("add_order", new AddOrderCommand(), CommandType.REDIRECT, Role.PATIENT, Role.GUEST),

    SEARCH_RECIPE_REQUEST("search_request", new SearchRecipeProlongationRequestCommand(), CommandType.FORWARD, Role.PATIENT, Role.DOCTOR),
    RECIPE_REQUEST_PAGE("go_to_request_page", new EntityPageCommand<>(RecipeProlongationRequestService.getInstance(), "request", PageName.RECIPE_REQUEST_PAGE), CommandType.FORWARD),
    ADD_RECIPE_REQUEST("add_request", new AddRecipeProlongationRequestCommand(), CommandType.REDIRECT, Role.PATIENT),
    UPDATE_RECIPE_REQUEST("update_request", new UpdateRecipeProlongationRequestCommand(), CommandType.REDIRECT, Role.DOCTOR),

    LOG_IN("log_in", new LogInCommand(), CommandType.REDIRECT, Role.GUEST),
    REGISTER("register", new RegisterCommand(), CommandType.REDIRECT, Role.GUEST),
    LOG_OUT("log_out", new LogOutCommand(), CommandType.FORWARD, Role.PHARMACIST, Role.DOCTOR, Role.PATIENT),

    PATIENT_PAGE("go_to_patient_page", new EntityPageCommand<>(UserService.getInstance(), "patient", PageName.PATIENT_PAGE), CommandType.FORWARD,Role.DOCTOR,Role.PHARMACIST),
    DOCTOR_PAGE("go_to_doctor_page", new EntityPageCommand<>(UserService.getInstance(), "doctor", PageName.DOCTOR_PAGE), CommandType.FORWARD,Role.PATIENT,Role.PHARMACIST,Role.DOCTOR),
    SEARCH_USER("search_user", new SearchUserCommand(), CommandType.FORWARD, Role.DOCTOR, Role.PHARMACIST,Role.PATIENT),
    UPDATE_USER("update_user", new UpdateUserCommand(), CommandType.REDIRECT, Role.PHARMACIST, Role.DOCTOR, Role.PATIENT);


    private final String commandName;
    private final Command command;
    private CommandType type;
    private List<Role> roles;

    CommandName(String commandName, Command command, CommandType type, Role... roles) {
        this.commandName = commandName;
        this.command = command;
        this.type = type;
        this.roles = Arrays.asList(roles);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public ResponseContext execute(RequestContext requestContext) {
        return command.execute(requestContext);
    }

    public String getCommandName() {
        return commandName;
    }

    public CommandType getType() {
        return type;
    }

    public static CommandName resolveCommandByName(String commandName) {
        return Arrays.stream(values())
                .filter(commandName1 -> commandName1.getCommandName().equals(commandName))
                .findAny()
                .get();
        //todo smth with optional, exception??
    }


}
