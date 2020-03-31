package jfc.commons;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	/**
	 * 
	 * @param source   ����������ַ�������"1983-05-05"
	 * @param format   ��������ڸ�ʽ����"1983-05-05"��Ӧ�ĸ�ʽ��"yyyy-MM-dd"��дȫ����"yyyy-MM-dd HH:mm:ss",ʱ��������ǵ�һ��������ʽģʽ��SimpleDateformat�õ�
	 * 					ָ�����պ��ָ�ʽ����source�е��ַ���
	 * @return         ���ɵ�java.util.Date���͡�    
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
	 * Ϊʲô�������������
	 * 		java.util.Date��Ӧ����mysql��datetime,Ȼ��jdbc��pstm.set*()û����java.util.Date��Ӧ������
	 * 			pstm.setDate(int,java.sql.Date)����pstm.setTimestamp(int,java.sql.Timestamp)
	 * 		�������ʱ�ַ�������ת��java.utial.date��ת��java.sql.Timestamp��⣬���ڽ�������̷�װһ�¡�
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
	 * ��java.util.Date����ת��ΪString�ַ��� 
	 * @param date              Ҫ��ʽ��java.util.Date����
	 * @param strFormat         �����String�ַ�����ʽ���޶����磺"yyyy-MM-dd HH:mm:ss"��
	 * @return ��ʾ���ڵ��ַ���
	 */
	public static String date2str(java.util.Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(date);
		return str;
	}
	
	/**
     * ��java.sql.Timestamp����ת��ΪString�ַ���
     * 
     * @param time
     *            Ҫ��ʽ��java.sql.Timestamp����
     * @param strFormat
     *            �����String�ַ�����ʽ���޶����磺"yyyy-MM-dd HH:mm:ss"��
     * @return ��ʾ���ڵ��ַ���
     */
    public static String timestamp2str(java.sql.Timestamp time, String strFormat) {
    	SimpleDateFormat df = new SimpleDateFormat(strFormat);
        String str = df.format(time);
        return str;
    }
    
    
    /**
     * ��java.sql.Timestamp����ת��Ϊjava.util.Date����
     * 
     * @param time
     *            Ҫת����java.sql.Timestamp����
     * @return ת�����java.util.Date����
     */
    public static java.util.Date timeToDate(java.sql.Timestamp time) {
        return time;
    }

    /**
     * ��java.util.Date����ת��Ϊjava.sql.Timestamp����
     * 
     * @param date
     *            Ҫת����java.util.Date����
     * @return ת�����java.sql.Timestamp����
     */
    public static java.sql.Timestamp dateToTime(java.util.Date date) {
        String strDate = date2str(date, "yyyy-MM-dd HH:mm:ss SSS");
        return str2timestamp(strDate, "yyyy-MM-dd HH:mm:ss SSS");
    }

    /**
     * ���ر�ʾϵͳ��ǰʱ���java.util.Date����
     * @return  ���ر�ʾϵͳ��ǰʱ���java.util.Date����
     */
    public static java.util.Date nowDate(){
        return new java.util.Date();
    }
    
    /**
     * ���ر�ʾϵͳ��ǰʱ���java.sql.Timestamp����
     * @return  ���ر�ʾϵͳ��ǰʱ���java.sql.Timestamp����
     */
    public static java.sql.Timestamp nowByTimestamp(){
        return dateToTime(new java.util.Date());
    }
        
}
