package com.example.core.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * MultipartFile和File相互转化工具类
 *
 * @author tzy
 * @since 2021/9/22 19:37
 */
public class MultipartFileUtil {

    /**
     * MultipartFile 向 File 转化 (注意这里的File是一个临时文件，使用后需调用 delTempFile 方法)
     *
     * @param multipartFile 目标文件
     * @return File 生成的临时文件对象
     */
    public static File multipartFileToFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        File file = new File(fileName);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            System.out.println("-----MultipartFile转换File失败-----");
            e.printStackTrace();
        }
//        OutputStream out = null;
//        try{
//            //获取文件流，以文件流的方式输出到新文件
////    InputStream in = multipartFile.getInputStream();
//            out = new FileOutputStream(file);
//            byte[] ss = multipartFile.getBytes();
//            for(int i = 0; i < ss.length; i++){
//                out.write(ss[i]);
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }finally {
//            if (out != null){
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return file;
    }

    /**
     * 删除指定的临时文件 一般与上面的 multipartFileToFile 方法配套使用
     *
     * @param file 目标临时文件
     * @return boolean 是否删除成功
     */
    public static boolean delTempFile(File file) {
        File f = new File(file.toURI());
        if (f.delete()) {
            System.out.println("删除成功");
            return true;
        } else {
            System.out.println("删除失败");
            return false;
        }
    }

//    /**
//     * File 向 MultipartFile 转换 (暂时不能使用)
//     * @param file 目标文件
//     * @return MultipartFile 转换后的文件
//     */
//    public static MultipartFile fileToMultipartFile(File file){
//        FileInputStream fileInputStream = null;
//        MultipartFile multipartFile = null;
//        try {
//            fileInputStream = new FileInputStream(file);
//            multipartFile = new MockMultipartFile(file.getName(),file.getName(),
//                    ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return multipartFile;
//    }
}
