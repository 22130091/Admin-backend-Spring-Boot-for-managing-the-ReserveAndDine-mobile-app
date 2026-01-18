package com.admin.pos.TableManagement;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Service
public class QRCodeService {
  private static final String QR_FOLDER = "uploads/qrcodes/";

  public void generateQrCode(String tableCode) {
    try {
      String qrContent = tableCode;
      String fileName = "table_" + tableCode + ".png";

      Path folderPath = Paths.get(QR_FOLDER);
      if (!Files.exists(folderPath)) {
        Files.createDirectories(folderPath);
      }

      Path filePath = folderPath.resolve(fileName);

      BitMatrix bitMatrix = new MultiFormatWriter().encode(
          qrContent,
          BarcodeFormat.QR_CODE,
          300,
          300);

      MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);

    } catch (Exception e) {
      throw new RuntimeException("Không thể tạo QR Code cho bàn: " + tableCode, e);
    }
  }
}
