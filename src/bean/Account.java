package bean;

public class Account {
    String bookNo; // 图书编号
    String readerNo; // 读者编号
    String loanTime; // 借阅时间
    String returnTime; //归还时间


    public Account(String bookNo, String readerNo, String loanTime, String returnTime) {
        this.bookNo = bookNo;
        this.readerNo = readerNo;
        this.loanTime = loanTime;
        this.returnTime = returnTime;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getReaderNo() {
        return readerNo;
    }

    public void setReaderNo(String readerNo) {
        this.readerNo = readerNo;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
