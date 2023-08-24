public class RegisteredUser extends Person {
    @Override
    public String toString() {
        String output = "";
        output+=this.name+"\t";
        output+=this.phoneNumber+"\t";
        output+=this.emailAddress+"\t";
        output+=this.address;
        
        return output;
    }
}
