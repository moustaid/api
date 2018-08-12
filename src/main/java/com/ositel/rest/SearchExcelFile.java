package com.ositel.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ositel.helper.UtilityHelper;
import com.sun.jersey.multipart.FormDataParam;

@Path("/searchExcelFile")
public class SearchExcelFile {

	
	private static final String ADD_FILE = "c:/uploadedFiles/";

	public SearchExcelFile() {
	}


	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") String  NameFile
		) throws JsonGenerationException, JsonMappingException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		UtilityHelper helper=new UtilityHelper();
		// verification des parametres d'entrées

		if (NameFile == null)
			return Response.status(400).entity("le nom du fichier est null").build();
		// verification si la destination existe
		
		String fileTarget = ADD_FILE + NameFile+".xlsx";
		String nameFile= NameFile+".xslx";
		return Response.status(200)
				.entity(""+helper.excelToJsonFile(fileTarget, nameFile))
				.build();
	}


}
