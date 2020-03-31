package jfc.commons;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	/**
	 * 
	 * @param source   传入的日期字符串，入"1983-05-05"
	 * @param format   穿入的日期格式，对"1983-05-05"对应的格式是"yyyy-MM-dd"，写全了是"yyyy-MM-dd HH:mm:ss",时间上这个是当一个正则表达式模式给SimpleDateformat用的
	 * 					指定按照何种格式解析source中的字符串
	 * @return         生成的java.util.Date类型。    
	 */
	public static java.util.Date str2date(String source,String format){
		java.util.Date date=null;
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try {
			date=sdf.parse(source);
		} catch (ParseException e) {		
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 为什么会有这个方法？
	 * 		java.util.Date对应的是mysql的datetime,然而jdbc的pstm.set*()没有与java.util.Date对应的类型
	 * 			pstm.setDate(int,java.sql.Date)或者pstm.setTimestamp(int,java.sql.Timestamp)
	 * 		如果输入时字符串，先转成java.utial.date在转成java.sql.Timestamp入库，现在将这个过程封装一下。
	 * @param source
	 * @param format
	 * @return
	 */
	public static java.sql.Timestamp str2timestamp(String source,String format){
		java.sql.Timestamp timestamp=null;
		java.util.Date date=null;
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		
		try {
			date=sdf.parse(source);
			timestamp=new Timestamp(date.getTime());
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		return timestamp;
	}
	
	/**
	 * 将java.util.Date对象转化为String字符串 
	 * @param date              要格式的java.util.Date对象
	 * @param strFormat         输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
	 * @return 表示日期的字符串
	 */
	public static String date2str(java.util.Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(date);
		return str;
	}
	
	/**
     * 将java.sql.Timestamp对象转化为String字符串
     * 
     * @param time
     *            要格式的java.sql.Timestamp对象
     * @param strFormat
     *            输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
     * @return 表示日期的字符串
     */
    public static String timestamp2str(java.sql.Timestamp time, String strFormat) {
    	SimpleDateFormat df = new SimpleDateFormat(strFormat);
        String str = df.format(time);
        return str;
    }
    
    
    /**
     * 将java.sql.Timestamp对象转化为java.util.Date对象
     * 
     * @param time
     *            要转化的java.sql.Timestamp对象
     * @return 转化后的java.util.Date对象
     */
    public static java.util.Date timeToDate(java.sql.Timestamp time) {
        return time;
    }

    /**
     * 将java.util.Date对象转化为java.sql.Timestamp对象
     * 
     * @param date
     *            要转化的java.util.Date对象
     * @return 转化后的java.sql.Timestamp对象
     */
    public static java.sql.Timestamp dateToTime(java.util.Date date) {
        String strDate = date2str(date, "yyyy-MM-dd HH:mm:ss SSS");
        return str2timestamp(strDate, "yyyy-MM-dd HH:mm:ss SSS");
    }

    /**
     * 返回表示系统当前时间的java.util.Date对象
     * @return  返回表示系统当前时间的java.util.Date对象
     */
    public static java.util.Date nowDate(){
        return new java.util.Date();
    }
    
    /**
     * 返回表示系统当前时间的java.sql.Timestamp对象
     * @return  返回表示系统当前时间的java.sql.Timestamp对象
     */
    public static java.sql.Timestamp nowByTimestamp(){
        return dateToTime(new java.util.Date());
    }
        
}
