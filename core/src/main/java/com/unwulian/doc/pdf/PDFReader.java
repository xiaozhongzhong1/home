package com.unwulian.doc.pdf;

import com.unwulian.common.FileReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFReader implements FileReader<PDDocument> {

    @Override
    public PDDocument read(File file) {
        PDDocument doc = null;
        try {
            //加载一个pdf对象
            doc = PDDocument.load(file);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return doc;
    }

    /**
     * 获取所有页数的内容，便于用正则获取url信息
     * @param doc
     * @param startPage
     * @param endPage
     * @return
     */
    public String getContent(PDDocument doc,int startPage,int endPage){
        PDFTextStripper textStripper = null;
        try {
            textStripper = new PDFTextStripper("GBK");
            textStripper.setSortByPosition(true);
            textStripper.setStartPage(7);
            textStripper.setEndPage(151);
            String content = textStripper.getText(doc);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取"+startPage+"-"+endPage+"的内容失败");
        }

    }


    public static void main(String[] args) throws IOException {
        PDDocument read = null;
        try {
            PDFReader pdfReader = new PDFReader();
            String file = "C:\\Users\\Administrator\\Desktop\\test.pdf";
            read = pdfReader.read(new File(file));


            PDFTextStripper textStripper = new PDFTextStripper("GBK");
            textStripper.setSortByPosition(true);
            textStripper.setStartPage(7);
            textStripper.setEndPage(151);
            String content = textStripper.getText(read);
            System.out.println(read);
        } finally {
            read.close();
        }
    }
}