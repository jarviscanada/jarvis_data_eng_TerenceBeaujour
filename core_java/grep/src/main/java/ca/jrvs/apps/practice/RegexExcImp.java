package ca.jrvs.apps.practice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

    public boolean matchJpeg(String filename) {
        Pattern pattern = Pattern.compile("[.]jpg$|[.]jpeg$", Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(filename);
        boolean matchFound = match.find();
        if (matchFound) {
            return true;
        }
        return false;
    }

    public boolean matchIp(String ip) {
        String regexPattern = "(([0-9][0-9]{1,2})\\.([0-9][0-9]{1,2})\\.([0-9][0-9]{1,2})\\.([0-9][0-9]{1,2}))";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(ip);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;
    }

    public boolean isEmptyLine(String line) {
        Pattern pattern = Pattern.compile("^\\s*$");
        Matcher matcher = pattern.matcher(line);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RegexExcImp myRex = new RegexExcImp();
        boolean result = myRex.matchJpeg("string.jpg");
        System.out.println("Result of filename is: " + result);
        boolean ip = myRex.matchIp("0.152.45.8");
        System.out.println("Result of ip is: " + ip);
        boolean line = myRex.isEmptyLine("");
        System.out.println("Result of line is: " + line);
    }
}
