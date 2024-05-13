package com.learning.all.qr.service;

import com.learning.all.entity.UserDetails;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
public class QRCodeGenerator {

	public static BufferedImage generateQRCode(UserDetails user) throws IOException, WriterException {

	    var qrCodeWriter = new QRCodeWriter();
	  BitMatrix bitMatrix = qrCodeWriter.encode(
	            		"ID: " + user.getUserId() + "\n" +
	                    "Name: " + user.getName() + "\n" +
	                    "Role: " + user.getRole() + "\n" +
	                    "School: " + user.getSchool() + "\n" +
	                    "Mobile: " + user.getPhnumber(), BarcodeFormat.QR_CODE, 200, 200);

	    BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
	    return qrCodeImage;
	}


	
}
