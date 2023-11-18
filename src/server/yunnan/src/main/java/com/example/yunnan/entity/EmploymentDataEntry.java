package com.example.yunnan.entity;

public class EmploymentDataEntry {
    private String companyID;
    private int docEmploymentNumber;
    private int curEmploymentNumber;
    private String numDecreasedReason;
    private String mainReason;
    private String secondReason;
    private String reasonDetail;
    private int status;

    private int valid;

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "companyID:'" + companyID + '\'' +
                ", docEmploymentNumber:" + docEmploymentNumber +
                ", curEmploymentNumber:" + curEmploymentNumber +
                ", numDecreasedReason:" + numDecreasedReason +
                ", mainReason:" + mainReason +
                ", secondReason:" + secondReason +
                ", reasonDetail:'" + reasonDetail + '\'' +
                ", status:" + status
                ;
    }


    public void printMyData(){
        System.out.println(this.getCompanyID());
        System.out.println(this.getDocEmploymentNumber());
        System.out.println(this.getCurEmploymentNumber());
        System.out.println(this.getNumDecreasedReason());
        System.out.println(this.getMainReason());
        System.out.println(this.getSecondReason());
        System.out.println(this.getReasonDetail());
    }

//    public EmploymentDataEntry get_default_EDE(int seed){
//        EmploymentDataEntry dede = new EmploymentDataEntry();
//        switch (seed){
//            case(1) : {
//                dede.setCompanyID("11111111111");
//                dede.setDocEmploymentNumber(50);
//                dede.setCurEmploymentNumber(55);
//                dede.setNumDecreasedReason(-1);
//                dede.setMainReason(-1);
//                dede.setSecondReason(-1);
//                dede.setReasonDetail("");
//                dede.setStatus(1);
//                break;
//            }
//            case(2) : {
//                dede.setCompanyID("22222222222");
//                dede.setDocEmploymentNumber(59);
//                dede.setCurEmploymentNumber(55);
//                dede.setNumDecreasedReason(1);
//                dede.setMainReason(1);
//                dede.setSecondReason(2);
//                dede.setReasonDetail("季节性用工");
//                dede.setStatus(3);
//                break;
//            }
//            case(3) : {
//                dede.setCompanyID("33333333333");
//                dede.setDocEmploymentNumber(50);
//                dede.setCurEmploymentNumber(-1);
//                dede.setNumDecreasedReason(-1);
//                dede.setMainReason(-1);
//                dede.setSecondReason(-1);
//                dede.setReasonDetail("");
//                dede.setStatus(0);
//                break;
//            }
//        }
//        return dede;
//    }

    public String getCompanyID() {
        return companyID;
    }
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public int getDocEmploymentNumber() {
        return docEmploymentNumber;
    }

    public void setDocEmploymentNumber(int docEmploymentNumber) {
        this.docEmploymentNumber = docEmploymentNumber;
    }

    public int getCurEmploymentNumber() {
        return curEmploymentNumber;
    }

    public void setCurEmploymentNumber(int curEmploymentNumber) {
        this.curEmploymentNumber = curEmploymentNumber;
    }

    public String getNumDecreasedReason() {
        return numDecreasedReason;
    }

    public void setNumDecreasedReason(String numDecreasedReason) {
        this.numDecreasedReason = numDecreasedReason;
    }

    public String getMainReason() {
        return mainReason;
    }

    public void setMainReason(String mainReason) {
        this.mainReason = mainReason;
    }

    public String getSecondReason() {
        return secondReason;
    }

    public void setSecondReason(String secondReason) {
        this.secondReason = secondReason;
    }

    public String getReasonDetail() {
        return reasonDetail;
    }

    public void setReasonDetail(String reasonDetail) {
        this.reasonDetail = reasonDetail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
