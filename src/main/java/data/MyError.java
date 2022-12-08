package data;

import java.io.Serializable;

public class MyError implements Serializable {
    private String message;

    public MyError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
