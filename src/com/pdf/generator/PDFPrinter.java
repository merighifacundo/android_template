package com.pdf.generator;

import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.webkit.WebView;

import org.apache.cordova.LOG;

/**
 * Created by cesar on 22/01/2017.
 */

public class PDFPrinter extends PrintDocumentAdapter {

    static final String APPNAME = "PDFPrinter";

    private PrintDocumentAdapter mWrappedInstance = null;
    private WebView webView = null;

    public PDFPrinter(WebView webView, String fileName) {
        this.mWrappedInstance = webView.createPrintDocumentAdapter(fileName);
        this.webView = webView;
    }

    @Override
    public void onStart() {
        mWrappedInstance.onStart();
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes,
            CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {
        mWrappedInstance.onLayout(oldAttributes, newAttributes, cancellationSignal, callback, extras);
    }

    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal,
            WriteResultCallback callback) {
        mWrappedInstance.onWrite(pages, destination, cancellationSignal, callback);
    }

    @Override
    public void onFinish() {
        LOG.i(APPNAME, "Cleaning pdfwriter & webView objects.");
        mWrappedInstance.onFinish();
    }
}
