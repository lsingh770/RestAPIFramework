package com.api.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportLabelUpdater {

    public static void updateExtentReportLabels(String reportPath) {
        try {
            File reportFile = new File(reportPath);
            if (!reportFile.exists()) {
                System.out.println("Extent report not found: " + reportPath);
                return;
            }

            String content = new String(Files.readAllBytes(Paths.get(reportPath)), StandardCharsets.UTF_8);

            // Replace labels
            content = content.replace(">Tests<", ">Modules<");
            content = content.replace("\"Tests\"", "\"Modules\"");
            content = content.replace("Tests:", "Modules:");
            content = content.replace(">Tests ", ">Modules ");
            content = content.replace("tests passed", "modules passed");
            content = content.replace("tests failed", "modules failed");

            content = content.replace(">Steps<", ">Tests<");
            content = content.replace("\"Steps\"", "\"Tests\"");
            content = content.replace("Steps:", "Tests:");
            content = content.replace(">Steps ", ">Tests ");
            content = content.replace("steps passed", "tests passed");
            content = content.replace("steps failed", "tests failed");

            // Write updated content back
            Files.write(Paths.get(reportPath), content.getBytes(StandardCharsets.UTF_8));

            System.out.println("Extent report labels and count updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while updating report labels.");
        }
    }
}