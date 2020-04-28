package com.ustc.sse.sseoj.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**

/**
 * @author Qianbw
 * @create 2020-03-09 21:15
 * @desc
 **/
public class ExcelUtil {
    /**
     * 导出excel
     *
     * @param response
     * @param header
     * @param dataList
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, List<String> header, List<List<String>> dataList) throws Exception {
        exportExcel(response, "主标题", "副标题", header, dataList);
    }

    /**
     * 导出excel
     *
     * @param response
     * @param title
     * @param subheading
     * @param header
     * @param dataList
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, String title, String subheading, List<String> header, List<List<String>> dataList) throws Exception {
        //获取一个HSSFWorkbook对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = getHSSFCellStyle(workbook);
        //创建一个sheet
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        //创建一个标题行
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, header.size());
        //创建一个副标题行
        CellRangeAddress cellRangeAddress2 = new CellRangeAddress(1, 1, 0, header.size());
        sheet.addMergedRegion(cellRangeAddress);
        sheet.addMergedRegion(cellRangeAddress2);

        //标题，居中
        HSSFRow row0 = sheet.createRow(0);
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue(title);
        cell0.setCellStyle(style);
        // 第一行
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);
        //副标题
        cell1.setCellValue(subheading);
        cell1.setCellStyle(style);

        //表头
        HSSFRow row = sheet.createRow(2);

        HSSFCell cell = null;
        for (int i = 0; i < header.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(header.get(i));
            cell.setCellStyle(style);
        }

        //数据
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(i + 3);
            for (int j = 0; j < dataList.get(i).size(); j++) {
                row.createCell(j).setCellValue(dataList.get(i).get(j));
            }
        }

        OutputStream outputStream = response.getOutputStream();
        //设置页面不缓存
        response.reset();
        String filename = title;
        //设置返回文件名的编码格式
        response.setCharacterEncoding("utf-8");
        filename = URLEncoder.encode(filename, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + filename + ".xls");
        response.setContentType("application/msexcel");
        workbook.write(outputStream);
        outputStream.close();
    }

    /**
     * 导入数据（单页）
     *
     * @param file        文件
     * @param sheetIndex  页名的索引(从0开始，-1代表全部页)
     * @param headerIndex 表头的索引（用于获取共多少列以及第几行开始读数据）
     * @return
     * @throws IOException
     */
    public static List<List<Object>> importExcel(MultipartFile file, int sheetIndex, int headerIndex) throws Exception {
        Workbook workbook = null;
        //返回的data
        List<List<Object>> data = new ArrayList<>();
        workbook = getWorkbook(file);
        //导入某一页
        if (sheetIndex != -1 && sheetIndex > -1) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            List<List<Object>> lists = importOneSheet(sheet, headerIndex);
            data.addAll(lists);
        } else {
            //导入全部
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }
                List<List<Object>> lists = importOneSheet(sheet, headerIndex);
                data.addAll(lists);
            }
        }
        return data;
    }

    /**
     * 导入数据（所有页）
     *
     * @param file        文件
     * @param headerIndex 表头的索引（用于获取共多少列以及第几行开始读数据）
     * @return
     * @throws IOException
     */
    public static List<List<Object>> importExcel(MultipartFile file, int headerIndex) throws Exception {
        return importExcel(file, -1, headerIndex);
    }

    /**
     * 创建一个style
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle getHSSFCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        //居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
    }


    /**
     * 获取一个sheet里的数据
     *
     * @param sheet
     * @param headerIndex
     * @return
     * @throws Exception
     */
    private static List<List<Object>> importOneSheet(Sheet sheet, int headerIndex) throws Exception {
        List<List<Object>> data = new ArrayList<>();
        int row = sheet.getLastRowNum();
        //row = -1 表格中没有数据
        //row = headerIndex 表格中表头以下没有数据（指没有有用数据）
        if (row == -1 || row == headerIndex) {
            throw new Exception("表格中没有有用数据!");
        }
        //通过表头获取共多少列
        int coloumNum = sheet.getRow(headerIndex).getPhysicalNumberOfCells();
        //从表头下一行开始取数据
        for (int i = headerIndex + 1; i <= row; i++) {
            Row row1 = sheet.getRow(i);
            List<Object> list = new ArrayList<>();
            if (row1 != null) {
                for (int j = 0; j < coloumNum; j++) {
                    list.add(getCellValue(row1.getCell(j)));
                }
            }
            data.add(list);
        }
        return data;
    }

    /**
     * 获取workbook
     *
     * @return
     */
    private static Workbook getWorkbook(MultipartFile file) throws Exception {
        Workbook workbook = null;
        //获取文件名
        String fileName = file.getOriginalFilename();
        //判断文件格式
        if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            throw new Exception("文件格式有误!");
        }
        return workbook;
    }


    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }
        return cellValue.trim();
    }
}
