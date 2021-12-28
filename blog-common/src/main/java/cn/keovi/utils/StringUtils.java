package cn.keovi.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huangad@coracle.com
 * @create 2017/4/13
 */
public class StringUtils {
    /**
     * 空字符
     */
    public static final String EMPTY = "";

    /**
     * 下划线字符
     */
    public static final char UNDERLINE = '_';

    /**
     * 下划线字符
     */
    public static final char MIDDLE_LINE = '-';

    public final static String DEFAULT_CHARSET = "UTF-8";
    private StringUtils() {
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs 需要判断字符串
     * @return 判断结果
     */
    public static boolean isEmpty(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param cs 需要判断字符串
     * @return 判断结果
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 字符串驼峰转下划线格式
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String camelToUnderline(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append(UNDERLINE);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 字符串数组驼峰转下划线格式
     *
     * @param params 需要转换的字符串数组
     * @return 转换好的字符串数组
     */
    public static String[] camelToUnderline(String[] params) {
        if (params != null) {
            String[] temp = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                temp[i] = camelToUnderline(params[i]);
            }
            return temp;
        }
        return null;
    }

    /**
     * 字符串下划线转驼峰格式
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String underlineToCamel(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        String temp = param.toLowerCase();
        int len = temp.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = temp.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 字符串中划线转驼峰格式
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String middleLineToCamel(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        String temp = param.toLowerCase();
        int len = temp.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = temp.charAt(i);
            if (c == MIDDLE_LINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 首字母转换小写
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String firstToLowerCase(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(param.length());
        sb.append(param.substring(0, 1).toLowerCase());
        sb.append(param.substring(1));
        return sb.toString();
    }

    /**
     * 首字母转换大写
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String firstToUpperCase(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(param.length());
        sb.append(param.substring(0, 1).toUpperCase());
        sb.append(param.substring(1));
        return sb.toString();
    }

    /**
     * 判断字符串是否为纯大写字母
     *
     * @param str 要匹配的字符串
     * @return 是否为纯大写字母
     */
    public static boolean isUpperCase(String str) {
        return match("^[A-Z]+$", str);
    }

    /**
     * 正则表达式匹配
     *
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 字符串转Long类型
     *
     * @param param 字符值
     * @return 转换后的Long类型值
     */
    public static Long toLong(String param) {
        return isNotEmpty(param) ? Long.valueOf(param) : null;
    }

    /**
     * 将Object类型转换为String类型
     *
     * @param param 需要转换的参数
     * @return 转换后的String类型值
     */
    public static String toString(Object param) {
        return param != null ? param.toString() : "";
    }


    public static String toString(Object obj, String defaultValue) {
        return obj != null ? obj.toString() : defaultValue;
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.length() < 1);
    }

    /**
     * <p>
     * Checks if a String is not empty ("") and not null.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @return
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(String str) {
        if (str == null || str.length() < 1) {
            return true;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if a String is not empty (""), not null and not whitespace only.
     * </p>
     *
     * @param str
     * @return <code>true</code> if the String is not empty and not null and
     *         not whitespace
     */
    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }

    /**
     * <p>
     * Checks if a String can parse to Int
     * </p>
     *
     * @param str
     *            The String to Check
     * @return
     */
    public static boolean isIntNumString(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p>
     * Checks if a String can parse to Number
     * </p>
     *
     * @param str
     *            The String to Check
     * @return
     */
    public static boolean isNumString(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p>
     * <Convert String to its Upper case/p>
     *
     * @param str
     *            the String to convert
     * @return <code>String of upper case</code>
     */
    public static String toUppderCase(String str) {
        if (str == null || str.length() < 1) {
            return str;
        } else {
            return str.toUpperCase();
        }
    }

    /**
     * <p>
     * Convert String to its Lower case
     * </p>
     *
     * @param str
     *            the String to convert
     * @return <code>String of lower case</code>
     */
    public static String toLowerCase(String str) {
        if (str == null || str.length() < 1) {
            return str;
        } else {
            return str.toLowerCase();
        }
    }

    /**
     * <p>
     * Checks if a String is in a String List, case sensitive
     * </p>
     *
     * @param strToCheck
     *            String to check
     * @param strList
     *            String List
     * @return <code>true</code>if strToCheck is in strList,<code>false</code>otherwise
     */
    public static boolean isInList(String strToCheck, String... strList) {
        return isInList(strToCheck, false, strList);
    }

    /**
     * <p>
     * Checks if a String is in a String List by appointing ignoreCase for case
     * insensitive or sensitive
     * </p>
     *
     * @param strToCheck
     *            String to check
     * @param ignoreCase
     *            whether ignore the case
     * @param strList
     *            String List
     * @return <code>true</code>if strToCheck is in strList,<code>false</code>otherwise
     */
    public static boolean isInList(String strToCheck, boolean ignoreCase,
                                   String... strList) {
        for (String str : strList) {
            if (ignoreCase && str.equalsIgnoreCase(strToCheck)) {
                return true;
            } else if (!ignoreCase && str.equals(strToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Check if prefix starts with source
     * </p>
     *
     * @param source
     *            source string
     * @param prefix
     *            prefix string to check
     * @return <code>true</code>if source string starts with prefix string
     */
    public static boolean startsWithIgnoreCase(String source, String prefix) {
        if (source != null && prefix != null) {
            if (source.startsWith(prefix)) {
                return true;
            } else {
                return (StringUtils.toLowerCase(source)).startsWith(StringUtils
                        .toLowerCase(prefix));
            }
        }
        return false;
    }

    /**
     * <p>
     * Check if prefix starts with source
     * </p>
     *
     * @param source
     *            source string
     * @param prefix
     *            prefix string to check
     * @param toOffset
     *            start position of source string
     * @return
     */
    public static boolean startsWithIgnoreCase(String source, String prefix,
                                               int toOffset) {
        if (source != null && prefix != null) {
            if (source.startsWith(prefix, toOffset)) {
                return true;
            } else {
                return (StringUtils.toLowerCase(source)).startsWith(StringUtils
                        .toLowerCase(prefix), toOffset);
            }
        }
        return false;

    }

    /**
     * <p>
     * Check if prefix starts with source
     * </p>
     *
     * @param source
     *            source string
     * @param prefix
     *            prefix string to check
     * @return <code>true</code> if source string starts with prefix string
     */
    public static boolean startsWith(String source, String prefix) {
        if (source != null && prefix != null) {
            if (source.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Check if prefix starts with source
     * </p>
     *
     * @param source
     *            source string
     * @param prefix
     *            prefix string to check
     * @param toOffset
     *            start position of source string
     * @return
     */
    public static boolean startsWith(String source, String prefix, int toOffset) {
        if (source != null && prefix != null) {
            if (source.startsWith(prefix, toOffset)) {
                return true;
            }
        }
        return false;

    }

    /**
     * <p>
     * eq String.getBytes("utf-8"), Exception insensitive
     * </p>
     *
     * @param str
     *            input string
     * @return bytes
     */
    public static byte[] getBytes(String str) {
        try {
            return str.getBytes(DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * <p>
     * Convert int to String
     * </p>
     *
     * @param i
     * @return
     */
    public static String toString(int i) {
        return i + EMPTY;
    }

    /**
     * <p>
     * Convert double to String
     * </p>
     *
     * @param d
     * @return
     */
    public static String toString(double d) {
        return d + EMPTY;
    }

    /**
     * <p>
     * Convert float to String
     * </p>
     *
     * @param f
     * @return
     */
    public static String toString(float f) {
        return f + EMPTY;
    }

    /**
     * <p>
     * Convert long to String
     * </p>
     *
     * @param l
     * @return
     */
    public static String toString(long l) {
        return l + EMPTY;
    }

    /**
     * <p>
     * Convert boolean to String
     * </p>
     *
     * @param b
     * @return
     */
    public static String toString(boolean b) {
        return b + EMPTY;
    }

    /**
     * <p>
     * Convert char to String
     * </p>
     *
     * @param c
     * @return
     */
    public static String toString(char c) {
        return c + EMPTY;
    }

    /**
     * <p>
     * Convert byte[] to Hex String
     * </p>
     *
     * @param src
     *            source byte[]
     * @return
     */
    public static String toHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * <p>
     * If input String is null return defaultValue, else return input String
     * </p>
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static String toString(String str, String defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        return str;
    }

    /**
     * <p>
     * Convert Integer List<Integer> to String List<String>
     * </p>
     *
     * @param intList
     *            the Integer list to convert
     * @return
     */
    public static List<String> toStringList(List<Integer> intList) {
        List<String> strList = new ArrayList<String>();
        for (int i : intList) {
            strList.add(StringUtils.toString(i));
        }
        return strList;
    }

    /**
     * <p>
     * Convert Integer Array to String Array
     * </p>
     *
     * @param intArray
     * @return
     */
    public static String[] toStringArray(int[] intArray) {
        int length = intArray.length;
        String[] strArray = new String[length];
        for (int i = 0; i < length; i++) {
            strArray[i] = StringUtils.toString(intArray[i]);
        }
        return strArray;
    }

    /**
     * <p>
     * Check whether source string contains appointing check string
     * </p>
     * <p>
     * if source is null return false
     * </p>
     *
     * @param source
     *            the source string
     * @param strToCheck
     *            the string to check
     * @return <code>true</code> if source contains strToCheck
     */
    public static boolean contains(String source, String strToCheck) {
        if (source != null) {
            return source.contains(strToCheck);
        }
        return false;
    }

    /**
     * String str = "abc\\#def#ghj #kkk # lll #3456\\##\\#\\#\\##09"; String[]
     * strs = split(str); result: abc#def ghj kkk lll 3456# ### 09
     * <p>
     * Split input string to a string array, use split char flag '#' and ignore
     * blank
     * </p>
     *
     * @param source
     *            the source string to split
     * @return
     */
    public static String[] split(String source) {
        return split(source, '#', true);
    }

    /**
     * String str = "abc\\#def#ghj #kkk # lll #3456\\##\\#\\#\\##09"; String[]
     * strs = split(str,'#',true); result: abc#def ghj kkk lll 3456# ### 09
     * <p>
     * Split input string to a string array
     * </p>
     *
     * @param source
     *            the source string to split
     * @param splitChar
     *            split char flag
     * @param ignoreBlank
     *            weather use trim for each element
     * @return
     */
    public static String[] split(String source, char splitChar,
                                 boolean ignoreBlank) {
        char[] chars = source.toCharArray();
        char escapeChar = '\\';
        StringBuilder sb = new StringBuilder();
        boolean ifRecSplitChar = false;
        List<String> strList = new ArrayList<String>();
        for (char c : chars) {
            if (!ifRecSplitChar && c == splitChar) {
                ifRecSplitChar = false;
                if (ignoreBlank) {
                    strList.add(sb.toString().trim());
                } else {
                    strList.add(sb.toString());
                }
                sb = new StringBuilder();
            } else {
                if (c == escapeChar) {
                    ifRecSplitChar = true;
                } else {
                    sb.append(c);
                    ifRecSplitChar = false;
                }
            }
        }
        if (ignoreBlank) {
            strList.add(sb.toString().trim());
        } else {
            strList.add(sb.toString());
        }
        return strList.toArray(new String[]{});
    }



    /**
     * <p>
     * Check whether all character of input string is lower case
     * </p>
     *
     * @param str
     * @return
     */
    public static boolean isLowerCase(String str) {
        if (StringUtils.isNotBlank(str)) {
            char[] cs = str.toCharArray();
            for (char c : cs) {
                if (Character.isUpperCase(c)) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * Convert a String first letter to Upper case etc. 'abc' to 'Abc', 'ABC' to
     * 'ABC', '' to '', 'a' to 'A'
     * <p>
     * </p>
     *
     * @param str
     * @return
     */
    public static String firstLetterUpper(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        } else if (str.length() == 1) {
            return StringUtils.EMPTY + Character.toUpperCase(str.charAt(0));
        } else {
            char headerChar = str.charAt(0);
            return Character.toUpperCase(headerChar) + str.substring(1);
        }

    }

    /**
     * Convert a String first letter to Lower case
     * <p>
     * </p>
     *
     * @param str
     * @return
     */
    public static String firstLetterLower(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        } else if (str.length() == 1) {
            return StringUtils.EMPTY + Character.toLowerCase((str.charAt(0)));
        } else {
            char headerChar = str.charAt(0);
            return Character.toLowerCase(headerChar) + str.substring(1);
        }

    }

    /**
     * <p>
     * Split an inputString to string array, each string item length is segLen,
     * the last item no included
     * </p>
     *
     * @param sourceStr
     *            source string to split
     * @param segLen
     *            each segment length
     * @return
     */
    public static String[] getSegments(String sourceStr, int segLen) {
        int len = sourceStr.length();
        if (len < segLen) {
            return new String[] { sourceStr };
        } else {
            int segNum = len / segLen + 1;
            if (len % segLen == 0) {
                segNum = len / segLen;
            }
            String[] result = new String[segNum];
            for (int i = 0; i < segNum; i++) {

                result[i] = subString(sourceStr, i * segLen, (i + 1) * segLen);
            }
            return result;
        }
    }

    /**
     * <p>
     * String.subString extension, ignore IndexOutBoundsException
     * </p>
     *
     * @param sourceStr
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public static String subString(String sourceStr, int fromIndex, int toIndex) {
        int len = sourceStr.length();
        if (toIndex > len) {
            return sourceStr.substring(fromIndex, len);
        } else {
            return sourceStr.substring(fromIndex, toIndex);
        }
    }

    /**
     * <p>
     * String.length extension, ignore NullPointerException
     * </p>
     *
     * @param str
     * @return
     */
    public static int length(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        } else {
            return str.length();
        }
    }

    /**
     * <p>
     * Display first part string by appointing max length, the remains use
     * appointing omit string instead
     * </p>
     * etc. omitString("loveyou", 2, "##") = lo##
     *
     * @param source
     * @param maxLength
     * @param omitStr
     * @return
     */
    public static String omitString(String source, int maxLength, String omitStr) {
        if (StringUtils.length(source) < maxLength) {
            return source;
        } else {
            return StringUtils.subString(source, 0, maxLength) + omitStr;
        }
    }

    /**
     * <p>
     * Display first part string by appointing max length, the remains use '...'
     * instead
     * </p>
     * etc. omitString("loveyou", 2) = lo...
     *
     * @param source
     * @param maxLength
     * @return
     */
    public static String omitString(String source, int maxLength) {
        return StringUtils.omitString(source, maxLength, "...");
    }

    /**
     * <p>
     * Convert byte[] to Hex String
     * </p>
     *
     * @param src
     *            source byte[]
     * @return
     */
    public static String ToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String substringBefore(String source, String flag) {
        int index = source.indexOf(flag);
        if(index <= 0) {
            return source;
        }
        return source.substring(0, index);
    }

    public static String substringAfter(String source, String flag) {
        int index = source.lastIndexOf(flag);
        if(index <= 0) {
            return source;
        }
        return source.substring(index + flag.length());
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int chineseLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String upperCaseFirstLetter(String str) {
        if(str!=null && str.length()>0) {
            return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
        } else {
            return str;
        }
    }

    /**
     * 将map中取出的值转化为string
     * @param obj
     * @return
     */
    public static String object2String(Object obj) {
        if (obj instanceof String) {
            return (String)obj;
        } else if (obj instanceof Double) {
            return Double.toString((Double)obj);
        } else if (obj instanceof Integer) {
            return Integer.toString((Integer)obj);
        } else if (obj instanceof Long) {
            return Long.toString((Long)obj);
        } else {
            return null;
        }

    }

    /**
     * 将map中取出的string/int转化为int
     * @param obj
     * @return
     */
    public static Integer object2Integer(Object obj) {
        if (obj instanceof String && StringUtils.isNotBlank((String)obj)) {
            return Integer.parseInt((String)obj);
        } else if (obj instanceof Integer) {
            return (Integer)obj;
        } else {
            return null;
        }

    }

    /**
     * 去除首字母空格
     * @param obj
     * @return
     */
    public static String trimStr(Object obj) {
        return obj == null ? "" : String.valueOf(obj).trim();
    }



    /**
     * 判断list是否为空
     * <li>空：true</li>
     * <li>非空：false</li>
     * @param list
     * @return
     */
    public static boolean isListEmpty(List<?> list) {
        if (list == null) {
            return true;
        }
        if (list.size() == 0) {
            return true;
        }

        return false;
    }


    /**
     * 手机号验证
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean checkEmail(final String email) {
        if(email == null) {
            return false;
        }
        final Pattern pattern = Pattern.compile(
                "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
                        | Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean checkMobile(final String mobile) {
        final Pattern pattern = Pattern.compile("^1\\d{10}$",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
                        | Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(mobile);
        return matcher.find();
    }

    public static boolean checkAccount(final String account) {
        final Pattern pattern = Pattern.compile("^[^\\d]+.*$",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
                        | Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(account);
        return matcher.find();
    }

    /**
     * 支持手机号码,电子邮箱作为账号
     * @param account
     * @return
     */
    public static boolean checkMulAccount(final String account) {
        return checkMobile(account) || checkEmail(account) || checkAccount(account);
    }

    public static String generateRandomCode(int len) {
        StringBuilder builder = new StringBuilder();
        Random rand = new Random();
        for(int i = 0; i < len; i++) {
            builder.append(rand.nextInt(10));
        }
        return builder.toString();
    }
}
