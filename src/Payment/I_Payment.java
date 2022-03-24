package src.Payment;

import src.Payment.*;

public interface I_Payment {

    /**
     *
     * @param paymentID
     * @param paymentType
     * @param priceOfJob
     */
    abstract Payment.Payment createPayment(long paymentID, String paymentType, double priceOfJob);

    /**
     *
     * @param paymentID
     */
    abstract Payment.Payment getPayment(long paymentID);

    /**
     *
     * @param paymentID
     */
    abstract boolean deletePayment(long paymentID);

}