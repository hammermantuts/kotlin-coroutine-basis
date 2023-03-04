package cz.hammermantuts.kotlincoroutinebasis.builders.async.service

import cz.hammermantuts.kotlincoroutinebasis.builders.async.domain.Address
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream

class ExcelService : FileService<Address> {
    override fun read(file: File): List<Address> {
        val workbook = WorkbookFactory.create(FileInputStream(file))
        val sheet = workbook.getSheetAt(0)
        val headers = sheet.getRow(0).map { it.stringCellValue }
        val headerRow = sheet.getRow(0)

        val countryIndex = headerRow.getCell(0).columnIndex
        val cityIndex = headerRow.getCell(1).columnIndex
        val zipIndex = headerRow.getCell(2).columnIndex
        val streetIndex = headerRow.getCell(3).columnIndex
        val usernameIndex = headerRow.getCell(4).columnIndex

        val addresses = mutableListOf<Address>()
        for (rowIndex in 1..sheet.lastRowNum) {
            val row = sheet.getRow(rowIndex)


            val country = row.getCell(countryIndex).stringCellValue
            val city = row.getCell(cityIndex).stringCellValue
            val zip = getZip(row.getCell(zipIndex))
            val street = row.getCell(streetIndex).stringCellValue
            val username = row.getCell(usernameIndex).stringCellValue

            val address = Address(country, city, zip, street, username)
            addresses.add(address)
        }

        workbook.close()
        return addresses
    }

    private fun getZip(cell: Cell): String = when(cell.cellType){
            CellType.NUMERIC -> cell.numericCellValue.toString()
            CellType.STRING -> cell.stringCellValue
            else -> ""
    }
}