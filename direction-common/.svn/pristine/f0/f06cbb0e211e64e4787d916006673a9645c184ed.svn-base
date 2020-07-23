package com.direction.common.utils.file;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.direction.common.utils.web.StaticConst;

/**
 * 
 * 上传文件
 * 
 * <pre>
 * 文件上传类
 * </pre>
 * 
 * @author jnzwxx
 * @since $Rev$
 *
 */
public class FileUploadUtils {

	// private static String FILE_SAVE_PATH = "E:/data/picture";
	private static String SMALL_IMG_FLAG = "_small";
	private static SimpleDateFormat dateFormate = new SimpleDateFormat("yyyyMM");

	/**
	 *
	 * 文件上传到磁盘
	 * 
	 * @param file
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName) {

		String result = "y"; // 扩展名格式：
		try {
			copyFile(file.getInputStream(), filePath, fileName).replaceAll("-", "");
		} catch (IOException e) {
			result = "n";
		}
		return result;
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
//	public static Map<String, String> uploadFile(HttpServletRequest request) throws Exception {
//
//		Map<String, String> result = new HashMap<String, String>();
//
//		// 接收到的文件信息
//		try {
//
//			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//			    request.getSession().getServletContext());
//
//			if (multipartResolver.isMultipart(request)) {
//
//				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//				Iterator<String> iter = multiRequest.getFileNames();
//
//				while (iter.hasNext()) {
//
//					MultipartFile file = (MultipartFile) multiRequest.getFile(iter.next());
//					String fileName = file.getOriginalFilename();
//
//					if (fileName != null && fileName.trim().length() > 0 && file.getSize() > 0) {
//
//						String[] tmpStr = fileName.split(File.separator + File.separator);
//						fileName = tmpStr[tmpStr.length - 1];
//						String fileType = getFileType(fileName);
//
//						String relatePath = createTmpFile(fileName);
//						String rootUrl = StaticConst.FILE_SAVE_PATH;
//						String realPath = rootUrl + relatePath;
//						File newFile = new File(realPath);
//
//						// 定义输出流 将文件保存在D盘 file.getOriginalFilename()为获得文件的名字
//						file.transferTo(newFile);
//
//						String picUrl = "";
//
//						// 如果是图片格式的就进行压缩
//						if ("jpg".equalsIgnoreCase(fileType)
//						    || "png".equalsIgnoreCase(fileType)
//						    || "bmp".equalsIgnoreCase(fileType)
//						    || "jpeg".equalsIgnoreCase(fileType)) {
//
//							// 压缩照片
//							String smallPath = transName(realPath, SMALL_IMG_FLAG);
//							saveMinPhoto(realPath, smallPath, 800, 0.9d, true);
//							// compressUnderSize(realPath, smallPath);
//							smallPath = transName(relatePath, SMALL_IMG_FLAG);
//							picUrl = smallPath;
//						} else {
//
//							picUrl = relatePath;
//						}
//						result.put("picUrl", picUrl);
//					}
//
//					/*
//					 * CommonsMultipartFile file = (CommonsMultipartFile) multiRequest.getFile(iter.next()); FileItem item =
//					 * file.getFileItem();
//					 * 
//					 * if (!item.isFormField()) {
//					 * 
//					 * String fileName = item.getName(); if (fileName != null && fileName.trim().length() > 0 && item.getSize() >
//					 * 0) { String[] tmpStr = fileName.split(File.separator + File.separator); fileName = tmpStr[tmpStr.length -
//					 * 1]; String fileType = getFileType(fileName);
//					 * 
//					 * String relatePath = createTmpFile(fileName); // String rootUrl =
//					 * request.getSession().getServletContext().getRealPath("/" + FILE_SAVE_PATH); String rootUrl =
//					 * FILE_SAVE_PATH; String realPath = rootUrl + relatePath; File newFile = new File(realPath);
//					 * 
//					 * // 保存到本地 item.write(newFile);
//					 * 
//					 * // String picUrl = FILE_SAVE_PATH; String picUrl = "";
//					 * 
//					 * // 如果是图片格式的就进行压缩 if ("jpg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType) ||
//					 * "bmp".equalsIgnoreCase(fileType) || "jpeg".equalsIgnoreCase(fileType)) {
//					 * 
//					 * // 压缩照片 String smallPath = transName(realPath, SMALL_IMG_FLAG); // saveMinPhoto(realPath, smallPath, 800,
//					 * 0.9d, true); compressUnderSize(realPath, smallPath); smallPath = transName(relatePath, SMALL_IMG_FLAG);
//					 * picUrl = smallPath; } else {
//					 * 
//					 * picUrl = relatePath; } result.put("picUrl", picUrl); } }
//					 */
//				}
//			}
//		} catch (Exception e) {
//			System.out.println("上传失败2" + e);
//			throw e;
//		}
//
//		return result;
//	}

	/**
	 * 压缩图片至指定大小
	 * 
	 * @param realPath
	 * @param smallPath
	 */
	public static void compressUnderSize(String realPath, String smallPath) {

		try {

			// 原图片
			File srcFile = new File(realPath);
			byte[] srcImgData = readInByteArray(srcFile);
			byte[] imgData = Arrays.copyOf(srcImgData, srcImgData.length);
			double scale = 0.9;

			long maxSize = StaticConst.MAX_SIZE;

			if (imgData.length > maxSize) {
				do {
					try {
						imgData = compress(imgData, scale);

					} catch (IOException e) {
						throw new IllegalStateException("压缩图片过程中出错，请及时联系管理员！", e);
					}

				} while (imgData.length > maxSize);
			}

			// Assert.assertTrue(imgData.length < maxSize);

			FileUtils.writeByteArrayToFile(new File(smallPath), imgData);
			System.gc();// 垃圾回收关闭流

			// 删除原图片
			if (srcFile.exists()) {

				srcFile.delete();
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 按照 宽高 比例压缩
	 * 
	 * @param imgIs
	 *          待压缩图片输入流
	 * @param scale
	 *          压缩刻度
	 * @param out
	 *          输出
	 * @return 压缩后图片数据
	 * @throws IOException
	 *           压缩图片过程中出错
	 */
	public static byte[] compress(byte[] srcImgData, double scale) throws IOException {

		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
		int width = (int) (bi.getWidth() * scale); // 源图宽度
		int height = (int) (bi.getHeight() * scale); // 源图高度

		Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = tag.getGraphics();
		g.setColor(Color.RED);
		g.drawImage(image, 0, 0, null); // 绘制处理后的图
		g.dispose();

		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		ImageIO.write(tag, "JPEG", bOut);

		return bOut.toByteArray();
	}

	/**
	 * 读取图片
	 * 
	 * @param imgFile
	 * @return
	 */
	private static byte[] readInByteArray(File imgFile) {

		try {
			return IOUtils.toByteArray(new FileInputStream(imgFile));

		} catch (IOException e) {
			throw new IllegalStateException("读取待压缩图片过程中出错，请及时联系管理员！", e);

		}
	}

	/**
	 * 等比例压缩算法： 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
	 * 
	 * @param srcURL
	 *          原图地址
	 * @param deskURL
	 *          缩略图地址
	 * @param comBase
	 *          压缩基数
	 * @param scale
	 *          压缩限制(宽/高)比例 一般用1： 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width= comBase,height按原图宽高比例
	 * @throws Exception
	 * @author shenbin
	 * @createTime 2014-12-16
	 * @lastModifyTime 2014-12-16
	 */
//	public static void saveMinPhoto(String srcURL, String deskURL, double comBase, double scale, boolean deleteOld)
//	    throws Exception {
//
//		File srcFile = new java.io.File(srcURL);
//		Image src = ImageIO.read(srcFile);
//		int srcHeight = src.getHeight(null);
//		int srcWidth = src.getWidth(null);
//		int deskHeight = 0;// 缩略图高
//		int deskWidth = 0;// 缩略图宽
//		double srcScale = (double) srcHeight / srcWidth;
//		/** 缩略图宽高算法 */
//		if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
//			if (srcScale >= scale || 1 / srcScale > scale) {
//				if (srcScale >= scale) {
//					deskHeight = (int) comBase;
//					deskWidth = srcWidth * deskHeight / srcHeight;
//				} else {
//					deskWidth = (int) comBase;
//					deskHeight = srcHeight * deskWidth / srcWidth;
//				}
//			} else {
//				if ((double) srcHeight > comBase) {
//					deskHeight = (int) comBase;
//					deskWidth = srcWidth * deskHeight / srcHeight;
//				} else {
//					deskWidth = (int) comBase;
//					deskHeight = srcHeight * deskWidth / srcWidth;
//				}
//			}
//		} else {
//			deskHeight = srcHeight;
//			deskWidth = srcWidth;
//		}
//		BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_INT_RGB);
//		tag.getGraphics().drawImage(src.getScaledInstance(deskWidth, deskHeight, Image.SCALE_SMOOTH), 0, 0, null); // 绘制缩小后的图
//		FileOutputStream deskImage = new FileOutputStream(deskURL); // 输出到文件流
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
//		encoder.encode(tag); // 近JPEG编码
//		deskImage.close();
//
//		if (deleteOld) {
//			if (srcFile.exists()) {
//				srcFile.delete();
//			}
//		}
//	}

	/**
	 * 获得文件类型
	 * 
	 * @param path
	 * @return
	 */
	private static String getFileType(String path) {

		if (path == null || path.trim().length() == 0) {
			return "";
		}

		return path.substring(path.lastIndexOf(".") + 1);
	}

	/**
	 * 获得要保存的临时文件
	 * 
	 * @param fileName
	 *          文件名
	 * @return
	 */
	private static String createTmpFile(String fileName) {

		String surffix = "";

		if (fileName != null) {
			surffix = fileName.substring(fileName.lastIndexOf("."));
		}
		// String uploadUrl = request.getSession().getServletContext().getRealPath("/" + FILE_SAVE_PATH);
		String uploadUrl = StaticConst.FILE_SAVE_PATH;
		String saveDir = uploadUrl + File.separator + dateFormate.format(Calendar.getInstance().getTime());
		System.out.println(saveDir);
		File tmpdir = new File(saveDir);

		if (!tmpdir.exists()) {
			tmpdir.mkdirs();
		}
		String saveFileName = UUID.randomUUID() + surffix;

		// 相对路径
		String path = File.separator + dateFormate.format(Calendar.getInstance().getTime()) + File.separator + saveFileName;
		return path;
	}

	/**
	 * 转换为压缩url
	 * 
	 * @param url
	 * @return
	 */
	private static String transName(String url, String flag) {

		if (url == null || url.trim().length() == 0) {
			return "";
		}
		String part1 = url.substring(0, url.lastIndexOf("."));
		String part2 = url.substring(url.lastIndexOf(".") + 1);
		String smallUrl = part1 + flag + "." + part2;
		return smallUrl;
	}

	/**
	 * 
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {

		String suffix = "";
		if (fileName.lastIndexOf(".") >= 0) {
			suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		}
		return suffix;
	}
	
	/**
	 * 
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffixName(String fileName) {

		String suffix = "";
		if (StringUtils.isNotBlank(fileName) && fileName.lastIndexOf(".") >= 0) {
			String[] urls = StringUtils.split(fileName, ".");
			if (urls.length > 0) {
				suffix = urls[urls.length -1];
			}
		}
		return suffix;
	}

	/**
	 * 
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @param dir
	 * @param realName
	 * @return
	 * @throws IOException
	 */
	private static String copyFile(InputStream in, String dir, String realName) throws IOException {

		File file = mkdirsmy(dir, realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}

	/**
	 * 
	 * 判断路径是否存在，否：创建此路径
	 * 
	 * @param dir
	 * @param realName
	 * @return
	 * @throws IOException
	 */
	public static File mkdirsmy(String dir, String realName) throws IOException {

		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 下载网络图片上传到服务器上
	 * 
	 * @param httpUrl
	 *          图片网络地址
	 * @param filePath
	 *          图片保存路径
	 * @param myFileName
	 *          图片文件名(null时用网络图片原名)
	 * @return 返回图片名称
	 */
	public static String getHtmlPicture(String httpUrl, String filePath, String myFileName) {

		URL url; // 定义URL对象url
		BufferedInputStream in; // 定义输入字节缓冲流对象in
		FileOutputStream file; // 定义文件输出流对象file
		try {
			String fileName = null == myFileName ? httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", "") : myFileName; // 图片文件名(null时用网络图片原名)
			url = new URL(httpUrl); // 初始化url对象
			in = new BufferedInputStream(url.openStream()); // 初始化in对象，也就是获得url字节流
			// file = new FileOutputStream(new File(filePath +"\\"+ fileName));
			file = new FileOutputStream(mkdirsmy(filePath, fileName));
			int t;
			while ((t = in.read()) != -1) {
				file.write(t);
			}
			file.close();
			in.close();
			return fileName;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 删除指定目录下的文件
	 * 
	 * @param url
	 * @return
	 */
	public static boolean delete(String path) {

		File file = new File(path);

		return file.delete();
	}
}
