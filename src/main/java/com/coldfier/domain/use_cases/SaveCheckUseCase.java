package com.coldfier.domain.use_cases;

import com.coldfier.domain.models.Check;
import com.coldfier.utils.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SaveCheckUseCase {

    public void saveCheck(Check check) {

        BufferedOutputStream bos = null;

        try {
            String fileName = "check-" + Utils.getDateTimeFromTimestamp(System.currentTimeMillis());
            File file = new File("src/main/output/" + fileName + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            bos = new BufferedOutputStream(new FileOutputStream(file));

            bos.write(check.toString().getBytes(StandardCharsets.UTF_8));
            bos.close();

        } catch (Exception ignored) {
        } finally {
            try {
                if (bos != null) bos.close();
            } catch (Exception ignored) {}
        }
    }

}
