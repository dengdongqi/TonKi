package com.dengdongqi.tonki.utils

import android.content.Context
import android.content.Context.PRINT_SERVICE
import android.graphics.pdf.PdfDocument
import android.print.PrintAttributes
import android.print.pdf.PrintedPdfDocument
import android.util.Log
import android.view.View
import java.io.FileOutputStream
import java.io.IOException

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: View 2 PDF
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */

object PDFUtils {
        fun createPdfFromView(context: Context, view: View?, pdfPath: String?) {
            if (view == null || pdfPath == null) {
                Log.e("DDQ", "content and pdfPath can not be null!")
                return
            }
            val displayMetrics = context.resources.displayMetrics
            val densityDpi = displayMetrics.densityDpi
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            val printAttrs = PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(PrintAttributes.Resolution("zooey", PRINT_SERVICE, densityDpi, densityDpi))
                .setMinMargins(PrintAttributes.Margins(0, 0, 0, 0))
                .setColorMode(PrintAttributes.COLOR_MODE_COLOR).build()
            // create a new document
            val document = PrintedPdfDocument(context, printAttrs)
            // crate a page description
            val pageInfo = PdfDocument.PageInfo.Builder(screenWidth, screenHeight, 1).create()
            // start a page
            val page = document.startPage(pageInfo)
            // draw something on the page
            view.draw(page.canvas)
            // finish the page
            document.finishPage(page)
            // add more pages
            // write the document content
            var os: FileOutputStream? = null
            try {
                Log.i("DDQ", "String:$pdfPath")
                os = FileOutputStream(pdfPath)
                document.writeTo(os)
                os.close()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                // close the document
                document.close()
            }
        }
}
