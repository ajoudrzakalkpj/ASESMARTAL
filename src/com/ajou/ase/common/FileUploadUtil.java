package com.ajou.ase.common;

import java.util.UUID;

public class FileUploadUtil {
	public static String getTempName() {
		return UUID.randomUUID().toString();
	}
}
