package team.service;

public class TeamException extends RuntimeException {
    static final long serialVersionUID = -7030745766939L;
    public TeamException(){}
    public TeamException(String message){
        super(message);
    }
}
