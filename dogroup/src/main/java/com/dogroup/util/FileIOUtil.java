package com.dogroup.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 IO에 사용되는 Util
 * @author NYK
 *
 */
public class FileIOUtil {
	
	private static Logger LOG = LoggerFactory.getLogger(FileIOUtil.class);
	private static String SAVE_DIRECTORY = "D:\\files";
	
	/**
	 * 여러개의 파일을 업로드한다.
	 * @param list				업로드된 파일들
	 * @throws IOException		실패시 발생
	 */
	public static void upload(List<MultipartFile> list, int boardNo) throws IOException {
		LOG.info("upload 시작: boardNo" + boardNo);
		
		File file = new File(SAVE_DIRECTORY);
		if(!file.exists()) {
			file.mkdir();
		}
		
		for(MultipartFile mf : list) {
			String originName = mf.getOriginalFilename();
			long fileLength = mf.getSize();
			System.out.println("이름: " + originName);
			System.out.println("파일크기: " + fileLength);
			//파일 생성
			String saveFileName = boardNo + "_" + UUID.randomUUID() + "_" + originName;
			File saveFile = new File(SAVE_DIRECTORY, saveFileName);
			
			FileCopyUtils.copy(mf.getBytes(), saveFile);
		}
		LOG.info("upload 끝");
	}
	
	/**
	 * 한개의 파일을 업로드한다.
	 * @param mf				업로드된 파일
	 * @throws IOException		실패시 발생한다.
	 */
	public static void upload(MultipartFile mf, int boardNo) throws IOException {
		File file = new File(SAVE_DIRECTORY);
		if(!file.exists()) {
			file.mkdir();
		}
		//파일 생성
		String saveFileName = boardNo + "_" + UUID.randomUUID() + "_" + mf.getOriginalFilename();
		File saveFile = new File(SAVE_DIRECTORY, saveFileName);
		FileCopyUtils.copy(mf.getBytes(), saveFile);
	}
	/**
	 * 파일을 다운로드 한다.
	 * @param fileName				다운로드할 파일명
	 * @return 
	 * @throws IOException			실패시 발생
	 */
	public static Map<String, Object> download(String fileName) throws IOException {
		File file = new File(SAVE_DIRECTORY, fileName);
		
		if(!file.exists()) {
			throw new IOException("파일이 없습니다");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length())); //응답길이
		
		String contentType = Files.probeContentType(file.toPath());
		System.out.println("Files.probeContentType(file.toPath())=" + contentType);
		
		responseHeaders.set(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file.toPath()));
		
		if(contentType.startsWith("image/")) { //이미지파일인경우 바로 응답
			responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename="+URLEncoder.encode(fileName, "UTF-8"));
		}else { //이미지파일이 아닌경우 다운로드
			responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+URLEncoder.encode(fileName, "UTF-8"));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("file", file);
		map.put("responseHeaders", responseHeaders);
		return map;
	}
	
	/**
	 * 게시글에 딸린 파일들의 이름을 가져온다.
	 * @param boardNo			게시글 번호
	 * @return					첨부파일명의 리스트
	 */
	public static List<String> getFileNames(int boardNo) {
		List<String> fileNames = new ArrayList<>();
		File dir = new File(SAVE_DIRECTORY);
		String[] allFileNames = dir.list();
		for(String fn : allFileNames) {
			if(fn.startsWith(boardNo + "_")) {
				fileNames.add(fn);
			}
		}
		return fileNames;
	}
	
	/**
	 * 하나의 글의첨부파일들을 삭제한다.
	 * @param boardNo
	 */
	public static void removeBoardFiles(int boardNo) {
		File dir = new File(SAVE_DIRECTORY);
		File[] files = dir.listFiles();
		for(File f : files) {
			if(f.getName().startsWith(boardNo + "_")) {
				f.delete();
			}
		}
	}
	
	/**
	 * 여러글의 첨부파일들을 삭제한다.
	 * @param boardNoList
	 */
	public static void removeBoardFiles(List<Integer> boardNoList) {
		File dir = new File(SAVE_DIRECTORY);
		File[] files = dir.listFiles();
		for(File f : files) {
			for(int no : boardNoList) {
				if(f.getName().startsWith(no + "_")) {
					f.delete();
				}
			}
		}
	}
}
