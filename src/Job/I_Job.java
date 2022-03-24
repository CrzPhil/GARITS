package src.Job;

import src.Job.*;

import java.time.LocalDate;
import java.util.Date;

public interface I_Job {

    /**
     *
     * @param jobID
     * @param name
     * @param status
     * @param startDate
     * @param finishDate
     * @param price
     * @param jobDetails
     * @param partsNeed
     * @param duration
     */
    abstract Job.Job createJob(long jobID, String name, boolean status, Date startDate, Date finishDate, double price, String jobDetails, Job.SparePart partsNeed, double duration);

    /**
     *
     * @param jobID
     */
    abstract Job.Job getJob(long jobID);

    /**
     *
     * @param partID
     * @param type
     * @param name
     * @param purchaseDate
     */
    abstract Job.SparePart createSparePart(long partID, String type, String name, Date purchaseDate);

    /**
     *
     * @param partID
     */
    abstract void modifySparePart(long partID);

    /**
     *
     * @param partID
     */
    abstract boolean deleteSparePart(long partID);

    /**
     *
     * @param jobID
     */
    abstract void modifyJob(long jobID);

    /**
     *
     * @param jobID
     */
    abstract boolean deleteJob(long jobID);

    /**
     *
     * @param bookingID
     * @param date
     * @param jobType
     */
    abstract Job.Booking createBooking(long bookingID, Date date, String jobType);

    /**
     *
     * @param bookingID
     */
    abstract void modifyBooking(long bookingID);

    /**
     *
     * @param bookingID
     */
    abstract boolean deleteBooking(long bookingID);

    /**
     *
     * @param partID
     */
    boolean orderSparePart(long partID);

}