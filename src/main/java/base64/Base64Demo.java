package base64;

import com.mysql.cj.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Timer;

public class Base64Demo {
    public static void main(String[] args) {
        jwt();

        //"exp":1773901393,"iat":1710829393
        //"exp":4854496390,"iat":1730445190
        //"exp":4854496173,"iat":1730444973
        Date date = new Date(4854496173000L);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        System.out.println(formattedDate);
    }

    public static void jwt() {
        //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NzM5MDEzOTMsImlhdCI6MTcxMDgyOTM5MywiaXNzIjoid3d3LmFjbWUuY29tIiwic3ViIjoiVXNlcl80ZjFmMjU2Mi02N2Q0LTRhZWMtOTA1Ny04ZjI0MjhhMWVkYmUiLCJ1c2VyQ29udGV4dCI6IntcImxvZ2luVGltZVwiOjAsXCJ0ZW5hbnRVaWRcIjpcIlRlbmFudF9mNTRkOGRhNi04MjY2LTRkMjItODVlNi03ZjExYTk2YTFhY2NcIixcInVzZXJVaWRcIjpcIlVzZXJfNGYxZjI1NjItNjdkNC00YWVjLTkwNTctOGYyNDI4YTFlZGJlXCJ9In0.fBL7Pw8_2zsgfDpjAiVrSnBYoidSKg-EeVdORuKUyVM";
        //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjQ4NTQ0OTYzOTAsImlhdCI6MTczMDQ0NTE5MCwiaXNzIjoid3d3LmFjbWUuY29tIiwic3ViIjoiVXNlcl80ZjFmMjU2Mi02N2Q0LTRhZWMtOTA1Ny04ZjI0MjhhMWVkYmUiLCJ1c2VyQ29udGV4dCI6IntcImxvZ2luVGltZVwiOjAsXCJ0ZW5hbnRVaWRcIjpcIlRlbmFudF9mNTRkOGRhNi04MjY2LTRkMjItODVlNi03ZjExYTk2YTFhY2NcIixcInVzZXJVaWRcIjpcIlVzZXJfNGYxZjI1NjItNjdkNC00YWVjLTkwNTctOGYyNDI4YTFlZGJlXCJ9In0.k-AW8XiTMWf7Ld5AXwfzv65GDyiv0qeqTo7vqGKlEDs";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjQ4NTQ0OTYxNzMsImlhdCI6MTczMDQ0NDk3MywiaXNzIjoid3d3LmFjbWUuY29tIiwic3ViIjoiVXNlcl80ZjFmMjU2Mi02N2Q0LTRhZWMtOTA1Ny04ZjI0MjhhMWVkYmUiLCJ1c2VyQ29udGV4dCI6IntcImxvZ2luVGltZVwiOjAsXCJ0ZW5hbnRVaWRcIjpcIlRlbmFudF9mNTRkOGRhNi04MjY2LTRkMjItODVlNi03ZjExYTk2YTFhY2NcIixcInVzZXJVaWRcIjpcIlVzZXJfNGYxZjI1NjItNjdkNC00YWVjLTkwNTctOGYyNDI4YTFlZGJlXCJ9In0.f3XEFgTnsPr8UB0VtzB8T_sWrJZ1NmUGQ54H6dhEPiI";
        String[] base64Array = token.split("\\.");
        for (String str : base64Array) {
            System.out.println(str);
            System.out.println(decode(str));
        }
    }

    public static String encode(String src) {
        return Base64.getUrlEncoder().encodeToString(src.getBytes());
    }

    public static String decode(String src) {
        return new String(Base64.getUrlDecoder().decode(src.getBytes()));
    }

}
