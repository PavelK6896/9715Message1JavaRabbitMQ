package app.web.pavelk.message1.common1;

import java.io.Serializable;

public class MyMessage implements Serializable {
    private String msg;
    public int ii = 11;


    public String getMsg() {
        return msg;
    }

    public MyMessage(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "msg='" + msg + '\'' +
                ", ii=" + ii +
                '}';
    }
}
