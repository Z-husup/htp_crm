package com.example.htp_crm.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;
import java.util.Map;

public class TemplateProcessor {

    public static void fillTemplate(XWPFDocument document, Map<String, String> data) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            replacePlaceholders(paragraph, data);
        }
    }

    private static void replacePlaceholders(XWPFParagraph paragraph, Map<String, String> data) {
        List<XWPFRun> runs = paragraph.getRuns();
        for (int i = 0; i < runs.size(); i++) {
            XWPFRun run = runs.get(i);
            String text = run.getText(0);
            if (text != null) {
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    String placeholder = "${" + entry.getKey() + "}";
                    if (text.contains(placeholder)) {
                        text = text.replace(placeholder, entry.getValue());
                        run.setText(text, 0);
                    }
                }
            }
        }
    }
}
