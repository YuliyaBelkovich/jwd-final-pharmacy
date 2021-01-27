package com.epam.jwd.command;

public enum PageName {
    MAIN_PAGE("/WEB-INF/jsp/MainPage.jsp"),
    DOCTOR_HOME("/WEB-INF/jsp/UserMainPage.jsp"),
    LOG_IN_PAGE("/WEB-INF/jsp/LogInPage.jsp"),
    PATIENT_HOME("/WEB-INF/jsp/PatientMainPage.jsp"),
    PHARMACIST_HOME("/WEB-INF/jsp/PharmacistMainPage.jsp"),
    REGISTER_PAGE("/WEB-INF/jsp/RegisterPage.jsp"),
    SEARCH_MEDICINE_PAGE("/WEB-INF/jsp/SearchMedicinePage.jsp"),
    SEARCH_MEDICINE_RESULT("/WEB-INF/jsp/SearchMedicineResult.jsp"),
    ENTITY_NOT_FOUND("/WEB-INF/jsp/EntityNotFoundPage.jsp"),
    ADD_MEDICINE_PAGE("/WEB-INF/jsp/AddMedicinePage.jsp"),
    MEDICINE_PAGE("/WEB-INF/jsp/MedicinePage.jsp"),
    SEARCH_PATIENT_PAGE("/WEB-INF/jsp/SearchPatientPage.jsp"),
    SEARCH_PATIENT_RESULT("/WEB-INF/jsp/SearchPatientResult.jsp"),
    PATIENT_PAGE("/WEB-INF/jsp/PatientPage.jsp"),
    SEARCH_DOCTOR_PAGE("/WEB-INF/jsp/SearchDoctorPage.jsp"),
    SEARCH_DOCTOR_RESULT("/WEB-INF/jsp/SearchDoctorResult.jsp"),
    DOCTOR_PAGE("/WEB-INF/jsp/DoctorPage.jsp"),
    SEARCH_APPOINTMENT_PAGE("/WEB-INF/jsp/SearchAppointmentPage.jsp"),
    SEARCH_APPOINTMENT_RESULT("/WEB-INF/jsp/SearchAppointmentResult.jsp"),
    APPOINTMENT_PAGE("/WEB-INF/jsp/AppointmentPage.jsp"),
    SEARCH_APPOINTMENT_WINDOW_PAGE("/WEB-INF/jsp/SearchAppointmentWindowPage.jsp"),
    SEARCH_APPOINTMENT_WINDOW_RESULT("/WEB-INF/jsp/SearchAppointmentWindowResult.jsp"),
    APPOINTMENT_WINDOW_PAGE("/WEB-INF/jsp/AppointmentWindowPage.jsp"),
    BANK_ACCOUNT_PAGE("/WEB-INF/jsp/BankAccountPage.jsp"),
    SEARCH_BANK_ACCOUNT("/WEB-INF/jsp/SearchBankAccountPage.jsp"),
    SEARCH_BANK_ACCOUNT_RESULT("/WEB-INF/jsp/SearchBankAccountResult.jsp"),
    SEARCH_RECIPE_PAGE("/WEB-INF/jsp/SearchRecipePage.jsp"),
    SEARCH_RECIPE_RESULT("/WEB-INF/jsp/SearchRecipeResult.jsp"),
    RECIPE_PAGE("/WEB-INF/jsp/RecipePage.jsp"),
    SEARCH_ORDER_PAGE("/WEB-INF/jsp/SearchOrderPage.jsp"),
    SEARCH_ORDER_RESULT("/WEB-INF/jsp/SearchOrderResult.jsp"),
    ORDER_PAGE("/WEB-INF/jsp/OrderPage.jsp"),
    SEARCH_PAYMENT_PAGE("/WEB-INF/jsp/SearchPaymentPage.jsp"),
    SEARCH_PAYMENT_RESULT("/WEB-INF/jsp/SearchPaymentResult.jsp"),
    PAYMENT_PAGE("/WEB-INF/jsp/PaymentPage.jsp"),
    SEARCH_RECIPE_REQUEST("/WEB-INF/jsp/SearchRecipeProlongationRequestPage.jsp"),
    SEARCH_RECIPE_REQUEST_RESULT("/WEB-INF/jsp/SearchRecipeProlongationResult.jsp"),
    RECIPE_REQUEST_PAGE("/WEB-INF/jsp/RecipeProlongationRequestPage.jsp"),
    ADD_APPOINTMENT_PAGE("/WEB-INF/jsp/AddAppointmentPage.jsp"),
    ADD_APPOINTMENT_WINDOW_PAGE("/WEB-INF/jsp/AddAppointmentWindowPage.jsp"),
    ADD_BANK_ACCOUNT_PAGE("/WEB-INF/jsp/AddBankAccountPage.jsp"),
    ADD_RECIPE_PAGE("/WEB-INF/jsp/AddRecipePage.jsp"),
    ADD_RECIPE_REQUEST("/WEB-INF/jsp/AddRecipeProlongationRequest.jsp"),
    ACCESS_DENIED_PAGE("/WEB-INF/jsp/AccessDeniedPage.jsp"),
    BASKET_PAGE("/WEB-INF/jsp/BasketPage.jsp"),
    ADD_PAYMENT("/WEB-INF/jsp/AddPaymentPage.jsp"),
    PAYMENT_SUCCESS("/WEB-INF/jsp/PaymentSuccess.jsp");


    private final String jspFileName;

    PageName(String jspFileName) {
        this.jspFileName = jspFileName;
    }

    public String getJspFileName() {
        return jspFileName;
    }
}
