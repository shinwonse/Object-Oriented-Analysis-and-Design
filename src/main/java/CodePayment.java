public class CodePayment {

    private Code code_info;
    private Boolean isCodeAvailable;

    public CodePayment() {
    }
    public CodePayment(Code code_info, Boolean isCodeAvailable) {
        code_info = this.code_info;
        isCodeAvailable = this.isCodeAvailable;
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
        return code_info.getDrink();
    }
}