package study.date;
import java.time.*;
import java.time.Clock;
import java.time.format.DateTimeFormatter;

import java.time.temporal.ChronoUnit;

/**
 * @ClassName DateApi_study
 * @Description java8 日期处理实践
 * @Author liangxp
 * @Date 2020/4/10 17:09
 **/
public class DateApi_study {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("今天日期:"+today);

        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.println("year:" + year +" month:" + month + " day:" + day);

        LocalDate earthQuake = LocalDate.of(2008, 5, 12);
        System.out.println("earthquake : " + earthQuake);
        
        //检查日期
        MonthDay  jinianri = MonthDay.of(earthQuake.getMonth(), earthQuake.getDayOfMonth());
        MonthDay day1 = MonthDay.from(today);
        if (day1.equals(jinianri)) {
            System.out.println("汶川地震纪念日");
        } else {
            System.out.println("不是汶川地震纪念日");
        }


        LocalDate nextweek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后日期: " + nextweek);

        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期: " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期: " + nextYear);

        Clock clock = Clock.systemUTC();
        System.out.println("clock : " + clock.millis());

        Clock defaultCLock = Clock.systemDefaultZone();
        System.out.println("clock : " + defaultCLock.millis());

        //指定时区时间
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);

        //表示信用卡有效期等固定日期
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);


        //检查闰年
        if(today.isLeapYear()){
            System.out.println("This year is Leap year");
        }else {
            System.out.println("This year is not a Leap year");
        }

        //计算两个日期之间的天数和月数
        LocalDate java8Release = LocalDate.of(2018, 12, 14);
        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : "
                + periodToNextJavaRelease.getMonths() );

        //当前的时间戳
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());

        //格式化日期
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow+"  格式化后的日期为:  "+formatted);

        //日期与字符串互转
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串:" + str);
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate date2 = LocalDate.parse(str, format2);
        System.out.println("日期类型:" + date2);


    }
}
