import java.awt.image.BufferedImage;
import java.io.IOException;

import com.wu.general.utils.QRCodeUtil;

public class QRCodeUtilTest {
	public static void main(String[] args) {
		// 创建二维码
		String content = "www.hicailiao.com";
		String win_userName = System.getProperty("user.name");
		String logo = "C:/Users/" + win_userName + "/Desktop/QRCode/img/favicon.ico";
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = QRCodeUtil.createImage(content, null, false);
		} catch (Exception e) {
			System.out.println("生成二维码失败");
			e.printStackTrace();
		}

		if (bufferedImage != null) {
			String base64 = "";
			try {
				base64 = QRCodeUtil.encodeToBase64(bufferedImage, "jpg");
			} catch (IOException e) {
				System.out.println("转换BASE64失败");
				e.printStackTrace();
			}
			System.out.println(base64);
		}
	}
}
