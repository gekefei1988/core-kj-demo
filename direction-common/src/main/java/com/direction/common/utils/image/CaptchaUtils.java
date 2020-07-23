package com.direction.common.utils.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.patchca.background.BackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;

/**
 * 验证码工具.
 */
public class CaptchaUtils {

	private static Random random = new Random();
	private static ConfigurableCaptchaService ccs;

	// 摆波纹
	private static WobbleRippleFilterFactory wrff;
	// 双波纹
	private static DoubleRippleFilterFactory doff;
	// 曲线波纹
	private static CurvesRippleFilterFactory crff;
	// 漫纹波
	private static DiffuseRippleFilterFactory drff;
	// 大理石
	private static MarbleRippleFilterFactory mrff;

	private static void initialize() {

		if (ccs == null) {
			synchronized (CaptchaUtils.class) {
				if (ccs == null) {
					// 配置初始化
					ccs = new ConfigurableCaptchaService();

					// 设置图片大小
					ccs.setWidth(100);
					ccs.setHeight(28);

					// 设置文字数量
					RandomWordFactory wf = new RandomWordFactory();
					wf.setCharacters("ABDEFGHKMNRSWX2345689");
					wf.setMinLength(4);
					wf.setMaxLength(4);
					ccs.setWordFactory(wf);

					// 设置字体大小
					RandomFontFactory ff = new RandomFontFactory();
					ff.setMinSize(28);
					ff.setMaxSize(28);
					ccs.setFontFactory(ff);

					// 设置文字渲染边距
					BestFitTextRenderer tr = new BestFitTextRenderer();
					tr.setTopMargin(3);
					tr.setRightMargin(3);
					tr.setBottomMargin(3);
					tr.setLeftMargin(3);
					ccs.setTextRenderer(tr);

					// 设置字体颜色
					ccs.setColorFactory(new ColorFactory() {

						@Override
						public Color getColor(int x) {

							int r = random.nextInt(90);
							int g = random.nextInt(90);
							int b = random.nextInt(90);
							return new Color(r, g, b);
						}
					});

					// 设置背景
					ccs.setBackgroundFactory(new BackgroundFactory() {

						@Override
						public void fillBackground(BufferedImage image) {

							Graphics graphics = image.getGraphics();
							// 验证码图片的宽高
							int imgWidth = image.getWidth();
							int imgHeight = image.getHeight();
							// 填充为白色背景
							graphics.setColor(Color.WHITE);
							graphics.fillRect(0, 0, imgWidth, imgHeight);
							// 画 50 个噪点(颜色及位置随机)
							for (int i = 0; i < 50; i++) {
								// 随机颜色
								int rInt = random.nextInt(100) + 50;
								int gInt = random.nextInt(100) + 50;
								int bInt = random.nextInt(100) + 50;
								graphics.setColor(new Color(rInt, gInt, bInt));
								// 随机位置
								int xInt = random.nextInt(imgWidth - 3);
								int yInt = random.nextInt(imgHeight - 2);
								// 随机旋转角度
								int sAngleInt = random.nextInt(360);
								int eAngleInt = random.nextInt(360);
								// 随机大小
								int wInt = random.nextInt(6);
								int hInt = random.nextInt(6);
								// 填充背景
								graphics.fillArc(xInt, yInt, wInt, hInt, sAngleInt, eAngleInt);
								// 画5条干扰线
								if (i % 10 == 0) {
									int xInt2 = random.nextInt(imgWidth);
									int yInt2 = random.nextInt(imgHeight);
									graphics.drawLine(xInt, yInt, xInt2, yInt2);
								}
							}
						}
					});

					// 效果初始化
					wrff = new WobbleRippleFilterFactory(); // 摆波纹
					doff = new DoubleRippleFilterFactory(); // 双波纹
					crff = new CurvesRippleFilterFactory(ccs.getColorFactory()); // 曲线波纹
					drff = new DiffuseRippleFilterFactory(); // 漫纹波
					mrff = new MarbleRippleFilterFactory(); // 大理石
				}
			}
		}
	}

	/**
	 * 生成验证码.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return 验证码字符
	 */
	public static String generateCaptcha(OutputStream outputStream) throws IOException {

		// 初始化设置
		initialize();

		// 随机选择一个样式
		switch (random.nextInt(3)) {
			case 0:
				ccs.setFilterFactory(wrff); // 摆波纹
				break;
			case 1:
				ccs.setFilterFactory(doff); // 双波纹
				break;
			case 2:
				ccs.setFilterFactory(crff); // 曲线波纹
				break;
			case 3:
				ccs.setFilterFactory(drff); // 漫纹波
				break;
			case 4:
				ccs.setFilterFactory(mrff); // 大理石
				break;
		}

		// 生成验证码
		String s = EncoderHelper.getChallangeAndWriteImage(ccs, "png", outputStream);

		return s;
	}
	
	/**
	 * 验证码放入缓存, 缓存时间默认180秒.
	 * 
	 * @param verifyId
	 * @param code
	 */
	public static void putCache(String verifyId, String code) {
		CaptchaUtils.putCache(verifyId, code, 180);
	}
	
	/**
	 * 验证码放入缓存.
	 * 
	 * @param verifyId
	 * 
	 * @param code
	 * @param cacheSeconds
	 */
	public static void putCache(String verifyId, String code, int cacheSeconds) {
		CacheSysManageUtils.put(CacheSysModulesCategory.VERIFY_IMAGE, "VERIFYID_" + verifyId, code, cacheSeconds);
	}
	
	/**
	 * 验证码比对.
	 * 
	 * @param verifyId
	 * @param code
	 * @return
	 */
	public static boolean contrastVerifyCode(String verifyId, String code) {
		
		boolean isSuccess = false;
		
		if (StringUtils.isAnyBlank(verifyId, code)) {
			return isSuccess;
		}
		
		// 缓存中获取验证码
		String verifyCode = (String) CacheSysManageUtils.get(CacheSysModulesCategory.VERIFY_IMAGE, "VERIFYID_" + verifyId);
		
		if (StringUtils.isNotBlank(verifyCode)) {
			isSuccess = StringUtils.equalsIgnoreCase(verifyCode, code);
		}
		
		return isSuccess;
	}
}
