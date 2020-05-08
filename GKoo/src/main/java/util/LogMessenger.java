package util;

public class LogMessenger {

    public static String getMessage(String error_message, String userid) {
        StringBuilder sb = new StringBuilder(error_message);
        sb.append(" [userid: ");
        sb.append(userid);
        sb.append("]");
        return sb.toString();
    }
    
    public static String getMessage(String error_message, String userid, String orderid) {
        StringBuilder sb = new StringBuilder(error_message);
        sb.append(" [userid: ");
        sb.append(userid);
        sb.append(" / orderid: ");
        sb.append(orderid);
        sb.append("]");
        return sb.toString();
    }
    
    public static String getMessage(String error_message, String label_1, String content_1, String label_2, String content_2) {
        StringBuilder sb = new StringBuilder(error_message);
        sb.append(" [");
        sb.append(label_1);
        sb.append(": ");
        sb.append(content_1);
        sb.append(" /");
        sb.append(label_2);
        sb.append(": ");
        sb.append(content_2);
        sb.append("]");
        return sb.toString();
    }
}
