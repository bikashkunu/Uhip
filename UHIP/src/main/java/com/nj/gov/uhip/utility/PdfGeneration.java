package com.nj.gov.uhip.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nj.gov.uhip.ed.EligibilityDetailModel.EligibilityDetailModel;

@Service
public class PdfGeneration {
	public static ByteArrayInputStream citiesReport(EligibilityDetailModel edmodel) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfPTable table = new PdfPTable(5);

			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table
			// Set Column widths
			float[] columnWidths = { 1f, 1f, 1f, 1f, 1f };
			table.setWidths(columnWidths);
			/*
			 * table.setWidthPercentage(60); table.setWidths(new int[] { 1, 3, 3 });
			 */
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("CaseNo", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PlanName", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PlanStatus", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			PdfPCell cell;

			cell = new PdfPCell(new Phrase(edmodel.getEdTraceId().toString()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(edmodel.getCaseNum().toString()));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(edmodel.getBenefitAmt().toString())));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(edmodel.getPlanName())));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(edmodel.getPlanStatus())));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(5);
			table.addCell(cell);
			try {
				PdfWriter writer = PdfWriter.getInstance(document,

						new FileOutputStream("E:\\ITextTest.pdf"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// PdfWriter.getInstance(document, new FileOutputStream)
			// PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);
			document.close();
		}

		catch (DocumentException doexception) {
			doexception.printStackTrace();
			// TODO: handle exception
		}
		return new ByteArrayInputStream(out.toByteArray());

	}

}
