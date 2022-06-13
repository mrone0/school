package com.school.config;

import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

/**
 * LocalDateTimeSerializer
 */
@Configuration

public class LocalDateTimeSerializerConfig {
    public Date localDateTime(String  timestamp){
         Date date = new Date();
//         date.setTime(Long.parseLong(timestamp)*1000);
//        return  date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return date;
    }

    public String GeLin(Date date) throws ParseException {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sDateFormat.format(date);
        return format;
    }


//    @Value("${spring.jackson.date-format}")
//    private String DATE_TIME_PATTERN;
//
//    @Value("${spring.jackson.date-format}")
//    private  String DATE_PATTERN ;
//
//    /**
//     * string转localdate
//     */
//    @Bean
//    public Converter<String, LocalDate> localDateConverter() {
//        return new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(String source) {
//                if (source.trim().length() == 0) {
//                    return null;
//                }
//                try {
//                    return LocalDate.parse(source);
//                } catch (Exception e) {
//                    return LocalDate.parse(source, DateTimeFormatter.ofPattern(DATE_PATTERN));
//                }
//            }
//        };
//    }
//
//    /**
//     * string转localdatetime
//     */
//    @Bean
//    public Converter<String, LocalDateTime> localDateTimeConverter() {
//        return new Converter<String, LocalDateTime>() {
//            @Override
//            public LocalDateTime convert(String source) {
//                if (source.trim().length() == 0) {
//                    return null;
//                }
//                // 先尝试ISO格式: 2019-07-15T16:00:00
//                try {
//                    return LocalDateTime.parse(source);
//                } catch (Exception e) {
//                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
//                }
//            }
//        };
//    }
//
//    /**
//     * 统一配置
//     */
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        JavaTimeModule module = new JavaTimeModule();
//        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
//        return builder -> {
//            builder.simpleDateFormat(DATE_TIME_PATTERN);
//            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
//            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
//            builder.modules(module);
//        };
//    }
}
