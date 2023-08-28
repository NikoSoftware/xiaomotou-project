package cn.xiaomotou.common.utils;


import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class UUIDGenerator {


    /**
     * 产生一个32位的UUID
     *
     * @return
     */

    public static String generate() {
        return new StringBuilder(32).append(format(getIP())).append(
                format(getJVM())).append(format(getHiTime())).append(
                format(getLoTime())).append(format(getCount())).toString();

    }

    private static final int IP;

    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private static short counter = (short) 0;

    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

    private final static String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuilder buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    private final static String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuilder buf = new StringBuilder("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    private final static int getJVM() {
        return JVM;
    }

    private final static short getCount() {
        synchronized (UUIDGenerator.class) {
            if (counter < 0) {
                counter = 0;
            }
            return counter++;
        }
    }

    /**
     * Unique in a local network
     */
    private final static int getIP() {
        return IP;
    }

    /**
     * Unique down to millisecond
     */
    private final static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    private final static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    private final static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    public static String getNumber() {
        Set<Integer> set = new HashSet<Integer>(); //定义一个set。
        Random r = new Random();                 //定义一个产生随机数的实体对象；
        while (set.size() < 6) {                  //产生6个：因为set是无序唯一的；
            int r_number = r.nextInt(10);// 产生0到9的整型数据
            set.add(r_number);
        }
        /**
         * 下面是遍历出产生的随机数
         */
        Iterator<Integer> it = set.iterator();//迭代器
        String s1 = "P";
        while (it.hasNext()) {
            Integer u = it.next();
            String s = String.valueOf(u);

            s1 += s;
        }
        return s1;
    }

    private final static int OFFSET = 538309;

    public static String MobileVfCode() {
        long seed = System.currentTimeMillis() + OFFSET;
        SecureRandom secureRandom = null; // 安全随机类
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String codeList = "1234567890"; // 验证码数字取值范围
        String sRand = ""; // 定义一个验证码字符串变量

        for (int i = 0; i < 6; i++) {
            int code = secureRandom.nextInt(codeList.length() - 1); // 随即生成一个0-9之间的整数
            String rand = codeList.substring(code, code + 1);
            sRand += rand; // 将生成的随机数拼成一个六位数验证码
        }
        return sRand; // 返回一个六位随机数验证码
    }

}
