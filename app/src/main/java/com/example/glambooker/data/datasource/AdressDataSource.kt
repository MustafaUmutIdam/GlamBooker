package com.example.glambooker.data.datasource
import android.content.Context
import android.util.Log
import com.example.glambooker.R
import com.example.glambooker.data.entity.Adress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import org.apache.poi.ss.usermodel.WorkbookFactory


class AdressDataSource (val context: Context)   {

   /* fun uploadCities() : List<Adress> {
        val citiesList = ArrayList<Adress>()
        val c1 = Adress("Adana","Seyhan")
        val c2 = Adress("Kayseri","Develi")
        val c3 = Adress("Mersin","Yenişehir")
        citiesList.add(c1)
        citiesList.add(c2)
        citiesList.add(c3)

        return citiesList
    }*/

    suspend fun getAdress() : List<Adress> = withContext(Dispatchers.IO){
        val citiesList1 = ArrayList<Adress>()
        val inputStream = context.resources.openRawResource(R.raw.adress_excell)
        val workbook = WorkbookFactory.create(inputStream)

        val sheet = workbook.getSheetAt(0)

        for (row in sheet){
            val citycell = row.getCell(0)
            val towncell = row.getCell(1)
            if(citycell != null && towncell != null){
                val city = citycell.stringCellValue.trim()
                val town = towncell.stringCellValue.trim()
                citiesList1.add(Adress(city,town))
            }
        }
        workbook.close()
        citiesList1
    }



}