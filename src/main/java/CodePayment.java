public class CodePayment {

    private Code code_info;
    private Boolean isCodeAvailable;

    public CodePayment() {
    }

    public Code getCode_info() {
        return code_info;
    }
    public void setCode_info(Code code_info) {
        this.code_info = code_info;
    }
    public Boolean getCodeAvailable() {
        return isCodeAvailable;
    }
    public void setCodeAvailable(Boolean codeAvailable) {
        isCodeAvailable = codeAvailable;
    }
    public Drink codePayment(Code code_info){
        Drink drink_info = code_info.getDrink();
        return drink_info;
    }


}