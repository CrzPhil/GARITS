package Job;

public class CompletedJobs extends Job{
    private int jobID;
    private String jobType;
    private String dates;
    private float price;
    private String motNO;
    private String parts;
    private float duration;
    private int mileage;
    private String requiredParts;
    private String additionalInfo;
    private int status;

    /**
     *
     * @param jobID
     * @param jobType
     * @param dates
     * @param price
     * @param motNo
     * @param parts
     * @param duration
     * @param mileage
     * @param requiredParts
     * @param additionalInfo
     */
    public CompletedJobs(int jobID, String jobType, float duration, String dates, String parts, String motNo, int mileage, float price, String requiredParts, String additionalInfo) {
        this.jobID = jobID;
        this.jobType = jobType;
        this.duration = duration;
        this.dates = dates;
        this.parts = parts;
        this.motNO = motNo;
        this.mileage = mileage;
        this.price = price;
        this.requiredParts = requiredParts;
        this.additionalInfo = additionalInfo;
        this.status = 1;
    }

    @Override
    public String toString() {
        return this.jobID + "    " + this.dates + "    " + this.jobType + "    ￡" + this.price;
    }

    public int getJobID() {
        return jobID;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMotNO() {
        return motNO;
    }

    public void setMotNO(String motNO) {
        this.motNO = motNO;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getRequiredParts() {
        return requiredParts;
    }

    public void setRequiredParts(String requiredParts) {
        this.requiredParts = requiredParts;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

}
