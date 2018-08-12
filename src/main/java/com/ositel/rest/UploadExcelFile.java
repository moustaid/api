package com.ositel.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
@Path("{id}/uploadExcelFile")
public class UploadExcelFile {

	
	private static final String UPLOAD_FOLDER = "c:/uploadedFiles/";
	private static final String TARGET_FOLDER = "c:/creatFiles/";
	public UploadExcelFile() {
	}

	@GET
	public Response uploadFile(@PathParam("id") String id) {
		
		String uploadedInputStream=null;
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			return Response.status(500)
					.entity("probleme creation fichier par le serveur")
					.build();
		}
		String uploadedFileLocation = UPLOAD_FOLDER +"/"+ id+".xlsx";
		try {
		 uploadedInputStream	=TARGET_FOLDER+""+ id+".xlsx";
			saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			return Response.status(500).entity(" enregistrement du fichier KO"+id).build();
		}
		return Response.status(200)
				.entity("fichier sauvegarder au chemin " + uploadedFileLocation+"id:"+id).build();
	}
	
	private void saveToFile(String input, String target)
			throws IOException {
		InputStream  outTarget = new FileInputStream(input);
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = outTarget.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		outTarget.close();
	}
	
	private void createFolderIfNotExists(String dirName)
			throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}
}
	

