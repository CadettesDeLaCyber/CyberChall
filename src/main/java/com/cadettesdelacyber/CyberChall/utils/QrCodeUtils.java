package com.cadettesdelacyber.CyberChall.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtils {

	public static String generateQRCodeBase64(String url, int width, int height) throws Exception {
		Map<com.google.zxing.EncodeHintType, Object> hints = new HashMap<>();
		hints.put(com.google.zxing.EncodeHintType.CHARACTER_SET, "UTF-8");

		BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);
		byte[] image = stream.toByteArray();

		return "data:image/png;base64," + Base64.getEncoder().encodeToString(image);
	}
}