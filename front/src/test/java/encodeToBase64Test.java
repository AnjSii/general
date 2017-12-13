import java.io.IOException;

import com.wu.general.utils.QRCodeUtil;

public class encodeToBase64Test {
	public static void main(String[] args) {
		String win_userName = System.getProperty("user.name");
		String imagePath = "C:/Users/" + win_userName + "/Desktop/QRCode/img/encodeToBase64Test.png";
		String base64 = "";
		try {
			base64 = QRCodeUtil.encodeToBase64(imagePath);
		} catch (IOException e) {
			System.out.println("转换错误");
			e.printStackTrace();
		}
		System.out.println(base64);
	}
}
