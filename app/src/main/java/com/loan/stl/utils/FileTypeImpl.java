package com.loan.stl.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/7/28 18:22
 * <p>
 * Description: 利用文件头来实现的一些文件类型的方法封装
 */
public class FileTypeImpl {
    private final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();

    static {
        FILE_TYPE_MAP.put("jpg", "FFD8FF");                         // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47");                       // PNG (png)
        FILE_TYPE_MAP.put("gif", "47494638");                       // GIF (gif)
        FILE_TYPE_MAP.put("tif", "49492A00");                       // TIFF (tif)
        FILE_TYPE_MAP.put("bmp", "424D");                           // Windows Bitmap (bmp)
        FILE_TYPE_MAP.put("dwg", "41433130");                       // CAD (dwg)
        FILE_TYPE_MAP.put("html", "68746D6C3E");                    // HTML (html)
        FILE_TYPE_MAP.put("rtf", "7B5C727466");                     // Rich Text Format (rtf)
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        FILE_TYPE_MAP.put("psd", "38425053");                       // Photoshop (psd)
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");   // Email [thorough only] (eml)
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");               // Outlook Express (dbx)
        FILE_TYPE_MAP.put("pst", "2142444E");                       // Outlook (pst)
        FILE_TYPE_MAP.put("xls", "D0CF11E0");                       // MS Word
        FILE_TYPE_MAP.put("doc", "D0CF11E0");                       // MS Excel 注意：word 和 excel的文件头一样
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");           // MS Access (mdb)
        FILE_TYPE_MAP.put("wpd", "FF575043");                       // WordPerfect (wpd)
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("pdf", "255044462D312E");                 // Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F");                       // Quicken (qdf)
        FILE_TYPE_MAP.put("pwl", "E3828596");                       // Windows Password (pwl)
        FILE_TYPE_MAP.put("wav", "57415645");                       // Wave (wav)
        FILE_TYPE_MAP.put("avi", "41564920");
        FILE_TYPE_MAP.put("ram", "2E7261FD");                       // Real Audio (ram)
        FILE_TYPE_MAP.put("rm", "2E524D46");                        // Real Media (rm)
        FILE_TYPE_MAP.put("mpg", "000001BA");
        FILE_TYPE_MAP.put("mov", "6D6F6F76");                       // Quick Time (mov)
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");               // Windows Media (asf)
        FILE_TYPE_MAP.put("mid", "4D546864");                       // MIDI (mid)
    }

    /**
     * 获取文件类型,包括图片,若格式不是已配置的,则返回null
     */
    public static String getFileType(File file) {
        String fileType = null;
        byte[] bytes    = new byte[50];
        try {
            InputStream is = new FileInputStream(file);
            is.read(bytes);
            fileType = getFileTypeByStream(bytes);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileType;
    }

    private static String getFileTypeByStream(byte[] bytes) {
        String                              fileTypeHex   = String.valueOf(getFileHexString(bytes));
        Iterator<Map.Entry<String, String>> entryIterator = FILE_TYPE_MAP.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> entry            = entryIterator.next();
            String                    fileTypeHexValue = entry.getValue();
            if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static String getFileHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (byte c : bytes) {
            int    v  = c & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
