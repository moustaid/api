package com.ositel.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.multipart.FormDataParam;

@Path("/addExcelFile")
public class AddExcelFile {

	private static final String ADD_FILE = "c:/creatFiles/";

	public AddExcelFile() {
	}


	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") String NameFile) {
		// verification des parametres d'entrées
		if (NameFile == null)
			return Response.status(400).entity("Name File null").build();
		// verification si la destination existe
		try {
			createFile(ADD_FILE + "/" + NameFile + ".xlsx");
		} catch (SecurityException | IOException se) {
			return Response.status(500).entity("Peux pas crée une destination dans le serveur").build();
		}
		String uploadedFileLocation = ADD_FILE + NameFile;
		return Response.status(200)
				.entity("{Nom du fichier ==>chemin du fichier } " + NameFile + "==>" + uploadedFileLocation).build();
	}

	private void createFile(String dirName) throws SecurityException, IOException {
		
		File theDir = new File(ADD_FILE);
		if (!theDir.exists()) {
			theDir.mkdir();
		}

		FileOutputStream out = new FileOutputStream(dirName);

		out.close();
	}

	
}
