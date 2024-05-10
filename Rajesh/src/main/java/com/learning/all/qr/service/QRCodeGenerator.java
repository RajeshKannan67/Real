package com.learning.all.qr.service;

import com.learning.all.entity.StudentEntity;

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

	public static BufferedImage generateQRCode(StudentEntity student) throws IOException, WriterException {

	    var qrCodeWriter = new QRCodeWriter();
	  BitMatrix bitMatrix = qrCodeWriter.encode(
	            		"ID: " + student.getUId() + "\n" +
	                    "Firstname: " + student.getName() + "\n" +
	                    "Place: " + student.getPlace() + "\n" +
	                    "School: " + student.getSchool() + "\n" +
	                    "Mobile: " + student.getPhnumber(), BarcodeFormat.QR_CODE, 200, 200);

	    BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
	    return qrCodeImage;
	}


	
}
