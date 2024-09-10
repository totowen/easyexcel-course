package com.geeksss.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

public class CustomMergeStrategy implements CellWriteHandler {

    /**
     * 合并列名。
     */
    private final List<String> mergeColumnNames;

    /**
     * 构造函数。
     *
     * @param mergeColumnNames 合并列名。
     */
    public CustomMergeStrategy(List<String> mergeColumnNames) {
        this.mergeColumnNames = mergeColumnNames;
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        // 校验：如果当前是表头，则不处理。
        if (isHead) {
            return;
        }

        // 校验：如果当前是第一行，则不处理。
        if (relativeRowIndex == 0) {
            return;
        }

        // 校验：如果当前列名不在合并列名列表中，则不处理。
        if (!this.mergeColumnNames.contains(head.getFieldName())) {
            return;
        }

        // 获取：当前表格、当前行下标、上一行下标、上一行对象、上一列对象。
        Sheet sheet = cell.getSheet();
        int rowIndexCurrent = cell.getRowIndex();
        int rowIndexPrev = rowIndexCurrent - 1;
        Row rowPrev = sheet.getRow(rowIndexPrev);
        Cell cellPrev = rowPrev.getCell(cell.getColumnIndex());

        // 获取：当前单元格值、上一单元格值。
        Object cellValueCurrent = cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : cell.getNumericCellValue();
        Object cellValuePrev = cellPrev.getCellType() == CellType.STRING ? cellPrev.getStringCellValue() : cellPrev.getNumericCellValue();

        // 校验：如果当前单元格值与上一单元格值不相等，则不处理。
        if (!cellValueCurrent.equals(cellValuePrev)) {
            return;
        }

        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        boolean merged = false;
        for (int i = 0; i < mergedRegions.size(); i++) {
            CellRangeAddress cellRangeAddress = mergedRegions.get(i);
            if (cellRangeAddress.isInRange(rowIndexPrev, cell.getColumnIndex())) {
                sheet.removeMergedRegion(i); // 移除合并单元格。
                cellRangeAddress.setLastRow(rowIndexCurrent); // 设置合并单元格的结束行。
                sheet.addMergedRegion(cellRangeAddress); // 重新添加合并单元格。
                merged = true;
                break;
            }
        }
        if (!merged) {
            CellRangeAddress cellRangeAddress = new CellRangeAddress(rowIndexPrev, rowIndexCurrent, cell.getColumnIndex(), cell.getColumnIndex());
            sheet.addMergedRegion(cellRangeAddress);
        }
    }

}
