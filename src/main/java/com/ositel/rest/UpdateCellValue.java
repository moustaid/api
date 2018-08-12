package com.ositel.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ositel.helper.UtilityHelper;
import com.ositel.pojo.CellUpdate;
@Path("{rows}/{cells}/updateCellValue")
public class UpdateCellValue {

	
	private static final String UPLOAD_FOLDER = "c:/uploadedFiles";
	private static final String FILE_NAME = "sampleExcelFile.xlsx";

	public UpdateCellValue() {
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response uploadFile(@PathParam("rows") int rows,
			@PathParam("cells") int cells,CellUpdate cellupdate) throws JsonGenerationException, JsonMappingException,
	        EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		String fileTarget = UPLOAD_FOLDER +"/"+ FILE_NAME;
		
		UtilityHelper helper=new UtilityHelper();
		
		return Response.status(200)
				.entity(""+helper.updateCellValue(fileTarget, FILE_NAME, rows , cells, cellupdate.getValue())).build();
	}
	
}
	

